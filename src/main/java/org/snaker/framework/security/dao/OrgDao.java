package org.snaker.framework.security.dao;

import org.snaker.framework.orm.hibernate.HibernateDao;
import org.snaker.framework.security.entity.Org;
import org.springframework.stereotype.Component;

/**
 * 部门持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class OrgDao extends HibernateDao<Org, Long> {

}
