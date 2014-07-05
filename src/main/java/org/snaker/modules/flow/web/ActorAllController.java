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
package org.snaker.modules.flow.web;

import java.util.HashMap;
import java.util.Map;

import org.snaker.framework.security.shiro.ShiroUtils;
import org.snaker.modules.base.service.SnakerEngineFacets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 简单会签测试controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/flow/actorall")
public class ActorAllController {
	@Autowired
	private SnakerEngineFacets facets;
	
	@RequestMapping(value = "task1/save", method=RequestMethod.POST)
	public String task1Save(String processId, String taskId, String actorIds) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("task1.operator", ShiroUtils.getUsername());
		args.put("task2.operator", actorIds);
		facets.startAndExecute(processId, ShiroUtils.getUsername(), args);
		return "redirect:/snaker/task/active";
	}
	
	@RequestMapping(value = "task2/save", method=RequestMethod.POST)
	public String task2Save(String orderId, String taskId) {
		facets.execute(taskId, ShiroUtils.getUsername(), null);
		return "redirect:/snaker/task/active";
	}
}
