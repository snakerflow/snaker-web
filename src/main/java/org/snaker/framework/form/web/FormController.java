/*
 *  Copyright 2013-2014 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.snaker.framework.form.web;

import org.snaker.framework.form.entity.DbTable;
import org.snaker.framework.form.entity.Form;
import org.snaker.framework.form.service.DbTableManager;
import org.snaker.framework.form.service.FormManager;
import org.snaker.framework.form.service.HtmlProcessor;
import org.snaker.framework.orm.Page;
import org.snaker.framework.orm.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

/**
 * 表单controller
 * @author yuqs
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/form/form")
public class FormController {
    @Autowired
    private FormManager formManager;
    @Autowired
    private DbTableManager dbTableManager;
    @Autowired
    private HtmlProcessor htmlProcessor;

    /**
     * 分页查询，返回列表视图
     * @param model
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, Page<Form> page, HttpServletRequest request) {
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        //设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("id");
            page.setOrder(Page.ASC);
        }
        page = formManager.findPage(page, filters);
        model.addAttribute("lookup", request.getParameter("lookup"));
        model.addAttribute("page", page);
        return "form/formList";
    }

    /**
     * 新建，返回编辑视图
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("form", new Form());
        model.addAttribute("tables", dbTableManager.getAll());
        return "form/formEdit";
    }

    /**
     * 表单设计，返回设计视图
     * @param model
     * @return
     */
    @RequestMapping(value = "designer", method = RequestMethod.GET)
    public String designer(Model model, Long id) {
        model.addAttribute("form", formManager.get(id));
        return "form/formDesigner";
    }

    /**
     * 表单设计，返回设计视图
     * @param id
     * @param html
     * @return
     */
    @RequestMapping(value = "designer/save", method = RequestMethod.POST)
    @ResponseBody
    public String designerSave(Long id, String html, HttpSession session) {
        Form form = formManager.get(id);
        String newHtml = htmlProcessor.processHtml(html);
        newHtml = newHtml.replaceAll("selvalue=\"", "");
        newHtml = newHtml.replaceAll("##\"", "");
        form.setHtml(newHtml);
        System.out.println(form.getHtml());
        String templatePath = session.getServletContext().getRealPath("/") 
        		+ "/WEB-INF/templates/flow/forms/";
        formManager.save(form, templatePath);
        return "success";
    }

    /**
     * 编辑，返回编辑视图
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        Form form = formManager.get(id);
        List<DbTable> tables = dbTableManager.getAll();
        List<DbTable> dbTables = form.getTables();
        for(DbTable dbTable : tables) {
            for(DbTable selTable : dbTables) {
                if(dbTable.getId().longValue() == selTable.getId().longValue()) {
                    dbTable.setSelected(1);
                }
                if(dbTable.getSelected() == null) {
                    dbTable.setSelected(0);
                }
            }
        }
        model.addAttribute("form", form);
        model.addAttribute("tables", tables);
        return "form/formEdit";
    }

    /**
     * 编辑，返回查看视图
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("form", formManager.get(id));
        return "form/formView";
    }

    /**
     * 根据formId查询关联的表
     * @param formId
     * @return
     */
    @RequestMapping(value = "tables", method = RequestMethod.GET)
    @ResponseBody
    public List<DbTable> tables(Long formId) {
        Form form = formManager.get(formId);
        return form.getTables();
    }

    /**
     * 新增、编辑的提交处理。保存实体并返回列表视图
     * @param form
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(Form form, Long[] orderIndexs) {
        Form dbForm = form;
        if(form.getId() != null && form.getId() > 0L) {
            dbForm = formManager.get(form.getId());
            dbForm.setName(form.getName());
            dbForm.setDisplayName(form.getDisplayName());
            dbForm.setType(form.getType());
            dbForm.getTables().clear();
        }

        if(orderIndexs != null) {
            for(Long order : orderIndexs) {
                DbTable table = new DbTable(order);
                dbForm.getTables().add(table);
            }
        }
        formManager.save(dbForm);
        return "redirect:/form/form";
    }

    /**
     * 根据主键ID删除实体，并返回列表视图
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        formManager.delete(id);
        return "redirect:/form/form";
    }
}
