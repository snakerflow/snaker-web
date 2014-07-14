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

package org.snaker.framework.form.service;

import org.snaker.framework.form.dao.FormDao;
import org.snaker.framework.form.entity.Form;
import org.snaker.framework.orm.Page;
import org.snaker.framework.orm.PropertyFilter;
import org.snaker.framework.utils.HttlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字段管理类
 * @author yuqs
 * @since 1.0
 */
@Component
public class FormManager {
    private static final String TEMPLATE_FORM = "/templates/forms/template.httl";
    @Autowired
    private FormDao formDao;
    /**
     * 保存实体
     * @param entity
     */
    public void save(Form entity) {
        formDao.save(entity);
    }
    /**
     * 保存实体并产生可操作的视图页面
     * @param entity
     * @param templatePath
     */
    public void save(Form entity, String templatePath) {
        formDao.save(entity);
        generateView(entity, templatePath);
}

    private void generateView(Form form, String templatePath){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("form", form);
        try {
            HttlUtils.render(TEMPLATE_FORM, parameters, new FileOutputStream(templatePath + form.getName() + ".html"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键ID删除对应的实体
     * @param id
     */
    public void delete(Long id) {
        Form form = formDao.get(id);
        //TODO 删除视图文件
        formDao.delete(form);
    }

    /**
     * 根据主键ID获取实体
     * @param id
     * @return
     */
    public Form get(Long id) {
        return formDao.get(id);
    }

    /**
     * 根据name获取实体
     * @param name
     * @return
     */
    public Form get(String name) {
        return formDao.findUniqueBy("name", name);
    }

    /**
     * 根据分页对象、过滤集合参数，分页查询列表
     * @param page
     * @param filters
     * @return
     */
    public Page<Form> findPage(final Page<Form> page, final List<PropertyFilter> filters) {
        return formDao.findPage(page, filters);
    }

    /**
     * 获取所有记录
     * @return
     */
    public List<Form> getAll() {
        return formDao.getAll();
    }

}
