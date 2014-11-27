/* Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.snakerflow.framework.security.service;

import java.util.ArrayList;
import java.util.List;

import com.snakerflow.framework.security.entity.Org;
import com.snakerflow.framework.security.entity.TreeNode;
import com.snakerflow.framework.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 安全树管理类(用户、部门)
 * @author yuqs
 * @since 0.1
 */
@Component
public class SecurityTreeManager {
	@Autowired
	private OrgManager orgManager;
	@Autowired
	private UserManager userManager;
	
	/**
	 * 根据parentId获取下级部门树集合
	 * @param parentId
	 * @return
	 */
	public List<TreeNode> getOrgTree(Long parentId) {
		List<Org> orgs = orgManager.getByParent(parentId);
		List<TreeNode> trees = new ArrayList<TreeNode>();
		TreeNode node = null;
		for(Org org : orgs) {
			node = new TreeNode();
			node.setId(org.getId());
			node.setpId(org.getParentOrg() == null ? Org.ROOT_ORG_ID : org.getParentOrg().getId());
			node.setName(org.getName());
			if(org.getParentOrg() == null) {
				node.setOpen(true);
			}
			trees.add(node);
		}
		return trees;
	}
	
	/**
	 * 根据parentId获取下级部门、用户树集合
	 * @param parentId
	 * @return
	 */
	public List<TreeNode> getOrgUserTree(Long parentId) {
		List<TreeNode> trees = new ArrayList<TreeNode>();
		List<Org> orgs = orgManager.getByParent(parentId);
		List<User> users = userManager.getByOrg(parentId);
		TreeNode node = null;
		for(Org org : orgs) {
			node = new TreeNode();
			node.setId(org.getId());
			node.setpId(org.getParentOrg() == null ? Org.ROOT_ORG_ID : org.getParentOrg().getId());
			node.setName(org.getName());
			if(org.getParentOrg() == null) {
				node.setOpen(true);
			}
			trees.add(node);
		}
		
		for(User user : users) {
			node = new TreeNode();
			node.setId(user.getId());
			node.setpId(user.getOrg() == null ? Org.ROOT_ORG_ID : user.getOrg().getId());
			node.setName(user.getFullname());
			trees.add(node);
		}
		return trees;
	}
}
