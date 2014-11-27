/*
 *  Copyright 2013-2014 the original author or authors.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.Approval/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.snakerflow.framework.flow.service;

import java.util.List;

import com.snakerflow.framework.flow.dao.ApprovalDao;
import com.snakerflow.framework.flow.entity.Approval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yuqs
 */
@Component
public class ApprovalManager {
    @Autowired
    private ApprovalDao dao;
	
	/**
	 * 保存实体
	 * @param entity
	 */
	public void save(Approval entity) {
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
	public Approval get(Long id) {
		return dao.get(id);
	}
	
	/**
	 * 获取所有记录
	 * @return
	 */
	public List<Approval> getAll() {
		return dao.getAll();
	}
	
	public List<Approval> findByFlow(String orderId, String taskName) {
		return dao.find("from Approval where orderId = ? and taskName = ?", orderId, taskName);
	}
}
