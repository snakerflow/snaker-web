package org.snaker.framework.security.dao;

import org.snaker.framework.orm.hibernate.HibernateDao;
import org.snaker.framework.security.entity.Menu;
import org.springframework.stereotype.Component;

/**
 * 菜单持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class MenuDao extends HibernateDao<Menu, Long> {

}
