package com.snakerflow.framework.config.web;

import com.snakerflow.framework.config.entity.Field;
import com.snakerflow.framework.config.entity.Form;
import com.snakerflow.framework.config.service.DynamicFormManager;
import com.snakerflow.framework.orm.Page;
import com.snakerflow.framework.orm.PropertyFilter;
import com.snakerflow.framework.security.shiro.ShiroUtils;
import com.snakerflow.framework.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.snaker.engine.helper.JsonHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态表单管理Controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/config/form")
public class DynamicFormController {
    public static final String PARA_PROCESSID = "processId";
    public static final String PARA_ORDERID = "orderId";
    public static final String PARA_TASKID = "taskId";

    @Autowired
    private DynamicFormManager dynamicFormManager;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, Page<Form> page, HttpServletRequest request, String lookup) {
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        //设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("id");
            page.setOrder(Page.ASC);
        }
        page = dynamicFormManager.findPage(page, filters);
        model.addAttribute("page", page);
        model.addAttribute("lookup", lookup);
        return "config/formList";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("form", new Form());
        return "config/formEdit";
    }

    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("form", dynamicFormManager.get(id));
        return "config/formView";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("form", dynamicFormManager.get(id));
        return "config/formEdit";
    }

    @RequestMapping(value = "designer/{id}", method = RequestMethod.GET)
    public String designer(@PathVariable("id") Long id, Model model) {
        model.addAttribute("form", dynamicFormManager.get(id));
        return "config/formDesigner";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Form form) {
        form.setCreator(ShiroUtils.getUsername());
        form.setCreateTime(DateUtils.getCurrentTime());
        form.setFieldNum(0);
        dynamicFormManager.save(form);
        return "redirect:/config/form";
    }

    @RequestMapping(value = "processor")
    @ResponseBody
    public Boolean processor(Long formId, String type, String parse_form) {
        Form entity = null;
        try {
            entity = dynamicFormManager.get(formId);
            Map<String, Object> map = JsonHelper.fromJson(parse_form, Map.class);
            Map<String, Object> datas = (Map<String, Object>)map.get("add_fields");
            Map<String, String> nameMap = dynamicFormManager.process(entity, datas);
            String template = (String)map.get("template");
            String parseHtml = (String)map.get("parse");
            if(!nameMap.isEmpty()) {
                for(Map.Entry<String, String> entry : nameMap.entrySet()) {
                    template = template.replaceAll(entry.getKey(), entry.getValue());
                    parseHtml = parseHtml.replaceAll(entry.getKey(), entry.getValue());
                }
            }
            entity.setOriginalHtml(template);
            entity.setParseHtml(parseHtml);
            dynamicFormManager.save(entity);
            return Boolean.TRUE;
        } catch(Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        dynamicFormManager.delete(id);
        return "redirect:/config/form";
    }

    @RequestMapping(value = "use/{id}")
    public String use(@PathVariable("id") Long id, String processId, String orderId, String taskId, Model model) {
        model.addAttribute("form", dynamicFormManager.get(id));
        model.addAttribute("processId", processId);
        model.addAttribute("orderId", orderId);
        model.addAttribute("taskId", taskId);
        if(StringUtils.isEmpty(orderId) || StringUtils.isNotEmpty(taskId)) {
            return "config/formUse";
        } else {
            //setAttr("result", Form.dao.getDataByOrderId(model, orderId));
            return "config/formUseView";
        }
    }

    @RequestMapping(value = "formData/{id}")
    @ResponseBody
    public Map<String, Object> formData(@PathVariable("id") Long id, String orderId) {
        Form entity = dynamicFormManager.get(id);
        return dynamicFormManager.getDataByOrderId(entity, orderId);
    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, long formId) {
        String processId = request.getParameter(PARA_PROCESSID);
        String orderId = request.getParameter(PARA_ORDERID);
        String taskId = request.getParameter(PARA_TASKID);
        List<Field> fields = dynamicFormManager.getFields(formId);
        Map<String, Object> params = new HashMap<String, Object>();
        for(Field field : fields) {
            if(Field.FLOW.equals(field.getFlow())) {
                String name = field.getName();
                String type = field.getType();
                String paraValue = request.getParameter(name);
                Object value = null;
                if("text".equalsIgnoreCase(type)) {
                    value = paraValue;
                } else if("int".equalsIgnoreCase(type)) {
                    if(paraValue == null || "".equals(paraValue)) {
                        value = 0;
                    } else {
                        try {
                            value = Integer.parseInt(request.getParameter(name));
                        } catch (Exception e) {
                            value = 0;
                        }
                    }
                } else if("float".equalsIgnoreCase(type)) {
                    if(paraValue == null || "".equals(paraValue)) {
                        value = 0.0;
                    } else {
                        try {
                            value = Double.parseDouble(request.getParameter(name));
                        } catch(Exception e) {
                            value = 0.0;
                        }
                    }
                } else {
                    value = paraValue;
                }
                params.put(name, value);
            }
        }

        Map<String, String[]> paraMap = request.getParameterMap();
        dynamicFormManager.submit(formId, fields, params, request, processId, orderId, taskId);
        //redirect(getPara("url"));
        if(StringUtils.isNotEmpty(processId)) {
            return "redirect:/snaker/task/active";
        } else {
            return "redirect:/config/form";
        }
    }
}
