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
import org.snaker.framework.form.entity.Field;
import org.snaker.framework.form.entity.Form;
import org.snaker.framework.form.entity.FormData;
import org.snaker.framework.form.service.FormDataManager;
import org.snaker.framework.form.service.FormManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义表单controller
 * @author yuqs
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/form/data")
public class FormDataController {
    @Autowired
    private FormDataManager formDataManager;
    @Autowired
    private FormManager formManager;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Model model, Long formId, HttpServletRequest request) {
        Form form = formManager.get(formId);
        FormData formData = buildFormData(form, request);
        try {
            formDataManager.save(formData);
        } catch(Exception e) {
            //model.addAttribute("error", formData.getError());
            model.addAttribute("processId", formData.getProcessId());
            model.addAttribute("orderId", formData.getOrderId());
            model.addAttribute("taskId", formData.getTaskId());
            return "redirect:/snaker/all";
        }
        return "redirect:/snaker/task/active";
    }

    private FormData buildFormData(Form form, HttpServletRequest request) {
        FormData formData = new FormData(form);
        formData.setProcessId(request.getParameter("processId"));
        formData.setOrderId(request.getParameter("orderId"));
        formData.setTaskId(request.getParameter("taskId"));
        List<DbTable> tables = form.getTables();
        if(tables != null && tables.size() > 0) {
            for(DbTable table : tables) {
                if(table.isSub()) {
                    Map<String, String[]> value = new HashMap<String, String[]>();
                    for(Field field : table.getFields()) {
                        value.put(field.getName(), request.getParameterValues(table.getName() + "_" + field.getName()));
                    }
                    formData.addSubData(table, value);
                } else {
                    Map<String, String> value = new HashMap<String, String>();
                    for(Field field : table.getFields()) {
                        value.put(field.getName(), request.getParameter(table.getName() + "_" + field.getName()));
                    }
                    formData.addFieldData(table, value);
                }
            }
        }
        return formData;
    }
}
