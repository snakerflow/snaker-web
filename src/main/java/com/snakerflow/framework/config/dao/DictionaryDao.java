package com.snakerflow.framework.config.dao;

import com.snakerflow.framework.config.entity.Dictionary;
import com.snakerflow.framework.orm.hibernate.HibernateDao;
import org.springframework.stereotype.Component;

/**
 * 配置字典持久化类
 * @author yuqs
 * @since 0.1
 */
@Component
public class DictionaryDao extends HibernateDao<Dictionary, Long> {

}
