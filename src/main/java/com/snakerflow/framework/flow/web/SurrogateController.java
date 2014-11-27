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
package com.snakerflow.framework.flow.web;

import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.Surrogate;
import com.snakerflow.framework.flow.service.SnakerEngineFacets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 委托授权
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/snaker/surrogate")
public class SurrogateController {
	@Autowired
	private SnakerEngineFacets facets;
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model, Page<Surrogate> page) {
		facets.searchSurrogate(page, new QueryFilter());
		model.addAttribute("page", page);
		return "snaker/surrogateList";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("surrogate", new Surrogate());
		model.addAttribute("processNames", facets.getAllProcessNames());
		return "snaker/surrogateEdit";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model model) {
		model.addAttribute("surrogate", facets.getSurrogate(id));
		model.addAttribute("processNames", facets.getAllProcessNames());
		return "snaker/surrogateEdit";
	}
	
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") String id, Model model) {
		model.addAttribute("surrogate", facets.getSurrogate(id));
		return "snaker/surrogateView";
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(Surrogate surrogate, Long parentMenuId) {
		surrogate.setSdate(surrogate.getSdate() + " 00:00:00");
		surrogate.setEdate(surrogate.getEdate() + " 23:59:59");
		facets.addSurrogate(surrogate);
		return "redirect:/snaker/surrogate/list";
	}
	
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") String id) {
		facets.deleteSurrogate(id);
		return "redirect:/snaker/surrogate";
	}
}
