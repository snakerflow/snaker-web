package com.snakerflow.framework.config.dao;

import com.snakerflow.framework.config.entity.Form;
import com.snakerflow.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 表单持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class FormDao extends HibernateDao<Form, Long> {
}
