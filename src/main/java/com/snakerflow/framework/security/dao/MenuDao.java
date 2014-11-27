package com.snakerflow.framework.security.dao;

import com.snakerflow.framework.orm.hibernate.HibernateDao;
import com.snakerflow.framework.security.entity.Menu;
import org.springframework.stereotype.Component;

/**
 * 菜单持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class MenuDao extends HibernateDao<Menu, Long> {

}
