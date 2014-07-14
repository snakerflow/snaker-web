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

package org.snaker.framework.form.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snaker.framework.form.entity.SqlData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 表单数据的操作类
 * @author yuqs
 * @since 1.0
 */
public class FormDataDao extends JdbcDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(FormDataDao.class);
    public void save(List<SqlData> sqlDatas) {
        for(SqlData sqlData : sqlDatas) {
            String sql = sqlData.getSql();
            log.info("sql=" + sql);
            Object[] values = sqlData.getValues();
            getJdbcTemplate().update(sql, values);
        }
    }

    public Map<String, Object> get(List<SqlData> sqlDatas) {
        Map<String, Object> formData = new HashMap<String, Object>();
        for(SqlData sqlData : sqlDatas) {
            String sql = sqlData.getSql();
            log.info("sql=" + sql);
            try {
            	Map<String, Object> datas = getJdbcTemplate().queryForMap(sql, sqlData.getValues());
            	formData.putAll(datas);
            } catch(Exception e) {
            	e.printStackTrace();
            	log.error(e.getMessage());
            }
        }
        return formData;
    }
}
