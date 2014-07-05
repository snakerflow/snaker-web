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

import org.snaker.framework.form.entity.Form;
import org.snaker.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 表单持久化类
 * @author yuqs
 * @since 1.0
 */
@Component
public class FormDao extends HibernateDao<Form, Long> {
}
