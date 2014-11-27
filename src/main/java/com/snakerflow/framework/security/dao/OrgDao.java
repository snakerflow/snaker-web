package com.snakerflow.framework.security.dao;

import com.snakerflow.framework.orm.hibernate.HibernateDao;
import com.snakerflow.framework.security.entity.Org;
import org.springframework.stereotype.Component;

/**
 * 部门持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class OrgDao extends HibernateDao<Org, Long> {

}
