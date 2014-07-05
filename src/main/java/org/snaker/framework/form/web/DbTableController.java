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
import org.snaker.framework.form.service.DbTableManager;
import org.snaker.framework.orm.Page;
import org.snaker.framework.orm.PropertyFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * 表controller
 * @author yuqs
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/form/dbtable")
public class DbTableController {
    @Autowired
    private DbTableManager dbTableManager;
    /**
     * 分页查询，返回列表视图
     * @param model
     * @param page
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model, Page<DbTable> page, HttpServletRequest request) {
        List<PropertyFilter> filters = PropertyFilter.buildFromHttpRequest(request);
        //设置默认排序方式
        if (!page.isOrderBySetted()) {
            page.setOrderBy("id");
            page.setOrder(Page.ASC);
        }
        page = dbTableManager.findPage(page, filters);
        model.addAttribute("page", page);
        return "form/dbTableList";
    }

    /**
     * 新建，返回编辑视图
     * @param model
     * @return
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("dbtable", new DbTable());
        return "form/dbTableEdit";
    }

    /**
     * 编辑，返回编辑视图
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dbtable", dbTableManager.get(id));
        return "form/dbTableEdit";
    }

    /**
     * 编辑，返回查看视图
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("dbtable", dbTableManager.get(id));
        return "form/dbTableView";
    }

    /**
     * 新增、编辑的提交处理。保存实体并返回列表视图
     * @param dbTable
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(DbTable dbTable) {
        dbTableManager.save(dbTable);
        return "redirect:/form/dbtable";
    }

    /**
     * 部署动态创建的表
     * @param id
     * @return
     */
    @RequestMapping(value = "deploy", method = RequestMethod.POST)
    public String deploy(Long id) {
        DbTable table = dbTableManager.get(id);
        dbTableManager.deploy(table);
        return "redirect:/form/dbtable";
    }

    /**
     * field保存
     * @param tableId
     * @param field
     * @return
     */
    @RequestMapping(value = "field/update", method = RequestMethod.POST)
    public String update(Long tableId, Field field) {
        DbTable table = dbTableManager.get(tableId);
        field.setDbTable(table);
        dbTableManager.save(field);
        return "redirect:/form/dbtable/field/list?id=" + tableId;
    }

    /**
     * field列表
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "field/list", method = RequestMethod.GET)
    public String fieldList(Model model, Long id) {
        model.addAttribute("dbtable", dbTableManager.get(id));
        return "form/dbTableField";
    }

    /**
     * field删除
     * @param id
     * @return
     */
    @RequestMapping(value = "field/delete/{id}", method = RequestMethod.GET)
    public String fieldDelete(@PathVariable("id") Long id) {
        dbTableManager.deleteField(id);
        return "redirect:/form/dbtable/field/list?id=" + id;
    }

    @RequestMapping(value = "field/create", method = RequestMethod.GET)
    public String fieldCreate(Model model, Long tableId) {
        model.addAttribute("tableId", tableId);
        model.addAttribute("field", new Field());
        return "form/fieldEdit";
    }

    /**
     * field编辑
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "field/edit/{id}", method = RequestMethod.GET)
    public String fieldEdit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("field", dbTableManager.getField(id));
        return "form/fieldEdit";
    }

    /**
     * field查看
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "field/view/{id}", method = RequestMethod.GET)
    public String fieldView(@PathVariable("id") Long id, Model model) {
     model.addAttribute("field", dbTableManager.getField(id));
     return "form/fieldView";
     }

     /**
     * 根据表名称获取字段集合
     * @param table
     * @return
     */
    @RequestMapping(value = "fields", method = RequestMethod.GET)
    @ResponseBody
    public List<Field> fields(String table) {
        if(table == null || table.equals("")) {
            return Collections.emptyList();
        }
        DbTable dbTable = dbTableManager.get(table);
        return dbTable.getFields();
    }

    /**
     * 根据主键ID删除实体，并返回列表视图
     * @param id
     * @return
     */
    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        dbTableManager.delete(id);
        return "redirect:/form/dbtable";
    }
}
