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

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.snaker.engine.entity.Order;
import org.snaker.engine.model.TaskModel;
import org.snaker.framework.security.shiro.ShiroUtils;
import org.snaker.modules.base.service.SnakerEngineFacets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 自由流controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/flow/free")
public class FreeFlowController {
	@Autowired
	private SnakerEngineFacets facets;
	@RequestMapping(value = "all" ,method=RequestMethod.GET)
	public String all(Model model, String processId, String orderId, String taskId) {
		model.addAttribute("processId", processId);
		if(StringUtils.isNotEmpty(orderId) && StringUtils.isNotEmpty(taskId)) {
			model.addAttribute("orderId", orderId);
			model.addAttribute("taskId", taskId);
		}
		return "flow/free/all";
	}
	@RequestMapping(value = "save" ,method=RequestMethod.POST)
	public String save(HttpServletRequest request, String processId, String orderId, String taskId) {
		String taskName = request.getParameter("taskName");
		String displayName = request.getParameter("displayName");
		String operator = request.getParameter("operator");
		String type = request.getParameter("type");
		if(StringUtils.isEmpty(orderId)) {
			Order order = facets.startInstanceById(processId, ShiroUtils.getUsername(), null);
			orderId = order.getId();
		}
		if(StringUtils.isNotEmpty(taskId)) {
			facets.getEngine().task().complete(taskId, ShiroUtils.getUsername(), null);
		}
		if("close".equals(type)) {
			facets.getEngine().order().complete(orderId);
		} else {
			TaskModel model = new TaskModel();
			model.setName(taskName);
			model.setDisplayName(displayName);
			model.setAssignee(taskName + ".operator");
			Map<String, Object> args = new HashMap<String, Object>();
			args.put(model.getAssignee(), operator);
			facets.getEngine().createFreeTask(orderId, ShiroUtils.getUsername(), args, model);
		}
		return "redirect:/snaker/task/active";
	}
}
