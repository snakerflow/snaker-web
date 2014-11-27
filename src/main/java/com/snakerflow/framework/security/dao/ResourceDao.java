package com.snakerflow.framework.security.dao;

import com.snakerflow.framework.orm.hibernate.HibernateDao;
import com.snakerflow.framework.security.entity.Resource;
import org.springframework.stereotype.Component;

/**
 * 资源持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class ResourceDao extends HibernateDao<Resource, Long> {

}
