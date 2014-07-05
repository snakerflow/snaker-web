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

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.entity.Order;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.framework.security.shiro.ShiroUtils;
import org.snaker.modules.base.service.SnakerEngineFacets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 简单会签测试controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/flow/custom")
public class CustomController {
	@Autowired
	private SnakerEngineFacets facets;
	
	@RequestMapping(value = "start", method=RequestMethod.GET)
	public String start(Model model, String processId, String orderId, String taskId) {
		if(StringUtils.isEmpty(orderId)) {
			facets.startInstanceById(processId, ShiroUtils.getUsername(), null);
			return "redirect:/snaker/task/active";
		} else {
			Process process = facets.getEngine().process().getProcessById(processId);
			Order order = facets.getEngine().query().getOrder(orderId);
			Task task = facets.getEngine().query().getTask(taskId);
			model.addAttribute("task", task);
			model.addAttribute("order", order);
			model.addAttribute("process", process);
			return "flow/custom/task1";
		}
	}
	
	@RequestMapping(value = "task1/save", method=RequestMethod.POST)
	public String task1Save(String orderId, String taskId) {
		facets.execute(taskId, ShiroUtils.getUsername(), null);
		return "redirect:/snaker/task/active";
	}
}
