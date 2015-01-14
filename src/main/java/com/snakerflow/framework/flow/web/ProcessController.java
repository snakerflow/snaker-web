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

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.snaker.engine.access.Page;
import org.snaker.engine.access.QueryFilter;
import org.snaker.engine.entity.HistoryOrder;
import org.snaker.engine.entity.HistoryTask;
import org.snaker.engine.entity.Process;
import org.snaker.engine.entity.Task;
import org.snaker.engine.helper.AssertHelper;
import org.snaker.engine.helper.StreamHelper;
import org.snaker.engine.helper.StringHelper;
import org.snaker.engine.model.ProcessModel;

import com.snakerflow.framework.security.shiro.ShiroUtils;
import com.snakerflow.framework.flow.SnakerHelper;
import com.snakerflow.framework.flow.service.SnakerEngineFacets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * 流程定义
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/snaker/process")
public class ProcessController {
	private static Log log = LogFactory.getLog(ProcessController.class);
	@Autowired
	private SnakerEngineFacets facets;
	/**
	 * 流程定义查询列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "list", method=RequestMethod.GET)
	public String processList(Model model, Page<Process> page, String displayName) {
		QueryFilter filter = new QueryFilter();
		if(StringHelper.isNotEmpty(displayName)) {
			filter.setDisplayName(displayName);
		}
		facets.getEngine().process().getProcesss(page, filter);
		model.addAttribute("page", page);
		return "snaker/processList";
	}
	
	/**
	 * 初始化流程定义
	 * @return
	 */
	@RequestMapping(value = "init", method=RequestMethod.GET)
	public String processInit() {
		facets.initFlows();
		return "redirect:/snaker/process/list";
	}
	
	/**
	 * 根据流程定义部署
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deploy", method=RequestMethod.GET)
	public String processDeploy(Model model) {
		return "snaker/processDeploy";
	}
	
	/**
	 * 新建流程定义
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add", method=RequestMethod.GET)
	public String processAdd(Model model) {
		return "snaker/processAdd";
	}
	
	/**
	 * 新建流程定义[web流程设计器]
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "designer", method=RequestMethod.GET)
	public String processDesigner(String processId, Model model) {
		if(StringUtils.isNotEmpty(processId)) {
			Process process = facets.getEngine().process().getProcessById(processId);
			AssertHelper.notNull(process);
			ProcessModel processModel = process.getModel();
			if(processModel != null) {
				String json = SnakerHelper.getModelJson(processModel);
				model.addAttribute("process", json);
			}
			model.addAttribute("processId", processId);
		}
		return "snaker/processDesigner";
	}
	
	/**
	 * 编辑流程定义
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method=RequestMethod.GET)
	public String processEdit(Model model, @PathVariable("id") String id) {
		Process process = facets.getEngine().process().getProcessById(id);
		model.addAttribute("process", process);
		if(process.getDBContent() != null) {
            try {
                model.addAttribute("content", StringHelper.textXML(new String(process.getDBContent(), "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
		return "snaker/processEdit";
	}
	
	/**
	 * 根据流程定义ID，删除流程定义
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method=RequestMethod.GET)
	public String processDelete(@PathVariable("id") String id) {
		facets.getEngine().process().undeploy(id);
		return "redirect:/snaker/process/list";
	}
	
	/**
	 * 添加流程定义后的部署
	 * @param snakerFile
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deploy", method=RequestMethod.POST)
	public String processDeploy(@RequestParam(value = "snakerFile") MultipartFile snakerFile, String id) {
		InputStream input = null;
		try {
			input = snakerFile.getInputStream();
			if(StringUtils.isNotEmpty(id)) {
                facets.getEngine().process().redeploy(id, input);
			} else {
                facets.getEngine().process().deploy(input);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return "redirect:/snaker/process/list";
	}
	
	/**
	 * 保存流程定义[web流程设计器]
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deployXml", method=RequestMethod.POST)
	@ResponseBody
	public boolean processDeploy(String model, String id) {
		InputStream input = null;
		try {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" + SnakerHelper.convertXml(model);
			System.out.println("model xml=\n" + xml);
			input = StreamHelper.getStreamFromString(xml);
			if(StringUtils.isNotEmpty(id)) {
				facets.getEngine().process().redeploy(id, input);
			} else {
				facets.getEngine().process().deploy(input);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}
	
	@RequestMapping(value = "start", method=RequestMethod.GET)
	public String processStart(Model model, String processName) {
		facets.startInstanceByName(processName, null, ShiroUtils.getUsername(), null);
		return "redirect:/snaker/process/list";
	}
	
	@RequestMapping(value = "json", method=RequestMethod.GET)
	@ResponseBody
	public Object json(String processId, String orderId) {
        Process process = facets.getEngine().process().getProcessById(processId);
        AssertHelper.notNull(process);
        ProcessModel model = process.getModel();
        Map<String, String> jsonMap = new HashMap<String, String>();
        if(model != null) {
            jsonMap.put("process", SnakerHelper.getModelJson(model));
        }

		if(StringUtils.isNotEmpty(orderId)) {
			List<Task> tasks = facets.getEngine().query().getActiveTasks(new QueryFilter().setOrderId(orderId));
			List<HistoryTask> historyTasks = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
			jsonMap.put("state", SnakerHelper.getStateJson(model, tasks, historyTasks));
		}
		log.info(jsonMap.get("state"));
        //{"historyRects":{"rects":[{"paths":["TO 任务1"],"name":"开始"},{"paths":["TO 分支"],"name":"任务1"},{"paths":["TO 任务3","TO 任务4","TO 任务2"],"name":"分支"}]}}
		return jsonMap;
	}
	
	@RequestMapping(value = "display", method=RequestMethod.GET)
	public String display(Model model, String processId, String orderId) {
        model.addAttribute("processId", processId);
		HistoryOrder order = facets.getEngine().query().getHistOrder(orderId);
		model.addAttribute("order", order);
		List<HistoryTask> tasks = facets.getEngine().query().getHistoryTasks(new QueryFilter().setOrderId(orderId));
		model.addAttribute("tasks", tasks);
		return "snaker/processView";
	}

    /**
     * 显示独立的流程图
     */
    @RequestMapping(value = "diagram", method=RequestMethod.GET)
    public String diagram(Model model, String processId, String orderId) {
        model.addAttribute("processId", processId);
        model.addAttribute("orderId", orderId);
        return "snaker/diagram";
    }
}
