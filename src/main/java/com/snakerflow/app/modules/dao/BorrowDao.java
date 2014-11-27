package com.snakerflow.app.modules.dao;

import org.springframework.stereotype.Component;

import com.snakerflow.app.modules.entity.Borrow;
import com.snakerflow.framework.orm.hibernate.HibernateDao;

@Component
public class BorrowDao extends HibernateDao<Borrow, Long> {

}
