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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.framework.form.dao.DbTableDao;
import org.snaker.framework.form.dao.FieldDao;
import org.snaker.framework.form.entity.DbTable;
import org.snaker.framework.form.entity.Field;
import org.snaker.framework.orm.Page;
import org.snaker.framework.orm.PropertyFilter;
import org.snaker.framework.utils.HttlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字段管理类
 * @author yuqs
 * @since 1.0
 */
@Component
public class DbTableManager {
    private static final Logger log = LoggerFactory.getLogger(DbTableManager.class);
    @Autowired
    private DbTableDao dbTableDao;
    @Autowired
    private FieldDao fieldDao;
    /**
     * 保存实体
     * @param entity
     */
    public void save(DbTable entity) {
        dbTableDao.save(entity);
    }

    /**
     * 根据dbtable实体，部署表
     * @param dbtable
     */
    public void deploy(DbTable dbtable) {
        String dbType = dbTableDao.getDatabaseType();
        if(dbType == null || dbType.equals("")) {
            log.error("database type is null.");
            return;
        }
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("dbtable", dbtable);
        try {
            String httl = "/templates/ddls/" + dbType + "/ddl.httl";
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            HttlUtils.render(httl, parameters, output);
            String ddlSql = new String(output.toByteArray());
            log.info(ddlSql);
            dbTableDao.create(ddlSql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存字段
     * @param entity
     */
    public void save(Field entity) {
        fieldDao.save(entity);
    }

    /**
     * 根据主键ID删除对应的实体
     * @param id
     */
    public void delete(Long id) {
        dbTableDao.delete(id);
    }

    /**
     * 根据主键删除field
     * @param id
     */
    public void deleteField(Long id) {
        fieldDao.delete(id);
    }

    /**
     * 根据主键ID获取实体
     * @param id
     * @return
     */
    public DbTable get(Long id) {
        return dbTableDao.get(id);
    }

    /**
     * 根据主键ID获取实体
     * @param table
     * @return
     */
    public DbTable get(String table) {
        return dbTableDao.findUniqueBy("name", table);
    }

    /**
     * 根据主键获取field
     * @param id
     * @return
     */
    public Field getField(Long id) {
        return fieldDao.get(id);
    }

    /**
     * 根据分页对象、过滤集合参数，分页查询列表
     * @param page
     * @param filters
     * @return
     */
    public Page<DbTable> findPage(final Page<DbTable> page, final List<PropertyFilter> filters) {
        return dbTableDao.findPage(page, filters);
    }

    /**
     * 获取所有记录
     * @return
     */
    public List<DbTable> getAll() {
        return dbTableDao.getAll();
    }

}
