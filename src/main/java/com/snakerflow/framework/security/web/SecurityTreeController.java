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
package com.snakerflow.framework.security.web;

import java.util.List;

import com.snakerflow.framework.security.entity.TreeNode;
import com.snakerflow.framework.security.service.SecurityTreeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 安全树controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/security/tree")
public class SecurityTreeController {
	@Autowired
	private SecurityTreeManager manager;
	
	@RequestMapping(value = "orgTree", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> orgTree(Long parentId) {
		return manager.getOrgTree(parentId);
	}
	
	@RequestMapping(value = "orgUserTree", method = RequestMethod.POST)
	@ResponseBody
	public List<TreeNode> orgUserTree(Long parentId) {
		return manager.getOrgUserTree(parentId);
	}
}
