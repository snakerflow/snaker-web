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

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.snaker.framework.form.entity.DbTable;
import org.snaker.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 表持久化类
 * @author yuqs
 * @since 1.0
 */
@Component
public class DbTableDao extends HibernateDao<DbTable, Long> {
    private static final String DEFAULT_DELIMITER = ";";

    /**
     * 创建DDL建表
     * @param ddl
     */
    public void create(String ddl) {
        if(StringUtils.isEmpty(ddl)) return;
        if(ddl.indexOf(DEFAULT_DELIMITER) != -1) {
            String[] sqls = ddl.split(DEFAULT_DELIMITER);
            for(String sql : sqls) {
                SQLQuery query = super.createSQLQuery(sql + DEFAULT_DELIMITER);
                query.executeUpdate();
            }
        }
    }
}
