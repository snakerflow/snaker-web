package org.snaker.framework.security.dao;

import org.snaker.framework.orm.hibernate.HibernateDao;
import org.snaker.framework.security.entity.Resource;
import org.springframework.stereotype.Component;

/**
 * 资源持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class ResourceDao extends HibernateDao<Resource, Long> {

}
