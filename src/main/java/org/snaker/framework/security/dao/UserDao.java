package org.snaker.framework.security.dao;

import org.snaker.framework.orm.hibernate.HibernateDao;
import org.snaker.framework.security.entity.User;
import org.springframework.stereotype.Component;

/**
 * 用户持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class UserDao extends HibernateDao<User, Long> {

}
