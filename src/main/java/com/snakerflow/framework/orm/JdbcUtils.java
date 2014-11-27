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

package com.snakerflow.framework.orm;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.Properties;

/**
 * Jdbc的工具类
 * @author yuqs
 * @since 1.0
 */
public class JdbcUtils {
    private static Properties databaseTypeMappings = getDefaultDatabaseTypeMappings();

    private static Properties getDefaultDatabaseTypeMappings() {
        Properties databaseTypeMappings = new Properties();
        databaseTypeMappings.setProperty("H2","h2");
        databaseTypeMappings.setProperty("MySQL","mysql");
        databaseTypeMappings.setProperty("Oracle","oracle");
        databaseTypeMappings.setProperty("PostgreSQL","postgres");
        databaseTypeMappings.setProperty("Microsoft SQL Server","mssql");
        return databaseTypeMappings;
    }

    /**
     * 根据连接对象获取数据库类型
     * @param conn 数据库连接
     * @return 类型
     * @throws Exception
     */
    public static String getDatabaseType(Connection conn) throws Exception {
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        String databaseProductName = databaseMetaData.getDatabaseProductName();
        return databaseTypeMappings.getProperty(databaseProductName);
    }
}
