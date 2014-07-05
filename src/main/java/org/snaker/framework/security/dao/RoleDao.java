package org.snaker.framework.security.dao;

import org.snaker.framework.orm.hibernate.HibernateDao;
import org.snaker.framework.security.entity.Role;
import org.springframework.stereotype.Component;

/**
 * 角色持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class RoleDao extends HibernateDao<Role, Long> {

}
