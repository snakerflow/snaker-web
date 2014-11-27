package com.snakerflow.app.modules.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.snakerflow.framework.flow.service.SnakerEngineFacets;
import com.snakerflow.framework.security.shiro.ShiroUtils;
import com.snakerflow.framework.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.snaker.engine.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.snakerflow.app.modules.dao.BorrowDao;
import com.snakerflow.app.modules.entity.Borrow;

@Component
public class BorrowManager {
    @Autowired
    private SnakerEngineFacets facets;
    @Autowired
    private BorrowDao dao;

    public void save(String processId, String orderId, String taskId, Borrow model) {
        /** 流程数据构造开始 */
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("apply.operator", ShiroUtils.getUsername());
        params.put("approval.operator", ShiroUtils.getUsername());
        /** 流程数据构造结束 */

        /**
         * 启动流程并且执行申请任务
         */
        if (StringUtils.isEmpty(orderId) && StringUtils.isEmpty(taskId)) {
            Order order = facets.startAndExecute(processId, ShiroUtils.getUsername(), params);
            /** 业务数据处理开始*/
            model.setOrderId(order.getId());
            model.setOperateTime(DateUtils.getCurrentDay());
            model.setOperator(ShiroUtils.getFullname());
            save(model);
        } else {
            facets.execute(taskId, ShiroUtils.getUsername(), params);
            /** 业务数据处理开始*/
            model.setOperator(ShiroUtils.getFullname());
            save(model);
        }
    }
	
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(Borrow entity) {
		dao.save(entity);
	}
	
	/**
	 * 根据主键ID删除对应的
	 * @param id
	 */
	public void delete(Long id) {
		dao.delete(id);
	}
	
	/**
	 * 根据主键ID获取实体
	 * @param id
	 * @return
	 */
	public Borrow get(Long id) {
		return dao.get(id);
	}
	
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<Borrow> getAll() {
		return dao.getAll();
	}
	
	public Borrow findByOrderId(String orderId) {
		List<Borrow> results = dao.findBy("orderId", orderId);
		if(results != null && results.size() > 0) {
			return results.get(0);
		} else {
			return null;
		}
	}
}
