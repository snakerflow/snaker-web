package org.snaker.modules.flow.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.snaker.framework.security.shiro.ShiroUtils;
import org.snaker.modules.base.service.SnakerEngineFacets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 请假流程Controller
 * @author yuqs
 * @since 0.1
 */
@Controller
@RequestMapping(value = "/flow/leave")
public class LeaveController {
	@Autowired
	private SnakerEngineFacets facets;
	
	/**
	 * 申请保存
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "apply/save" ,method=RequestMethod.POST)
	public String applySave(@RequestParam(value = "applyFile") MultipartFile applyFile, Model model, 
			String processId, HttpServletRequest request, float day) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("day", day);
		args.put("reason", request.getParameter("reason"));
		args.put("apply.operator", ShiroUtils.getUsername());
		args.put("approveDept.operator", ShiroUtils.getUsername());
		args.put("approveBoss.operator", ShiroUtils.getUsername());
		if(applyFile != null) {
			String applyFileName = applyFile.getOriginalFilename();
			if(StringUtils.isNotEmpty(applyFileName)) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				String filePath = "/uploads/" + sdf.format(new Date()) + "/";
				String rootPath = request.getSession().getServletContext().getRealPath("/") + filePath;
				// 创建文件  
				File dirPath = new File(rootPath);  
				if (!dirPath.exists()) {  
				    dirPath.mkdirs();  
				}
				File uploadFile = new File(rootPath + applyFileName);
				try {
					FileCopyUtils.copy(applyFile.getBytes(), uploadFile);
				} catch (IOException e) {
					throw new RuntimeException(e.getMessage(), e);
				}
				args.put("applyFileName", applyFileName);
				args.put("applyFilePath", filePath + applyFileName);
			}
		}
		facets.startAndExecute(processId, ShiroUtils.getUsername(), args);
		return "redirect:/snaker/task/active";
	}
	
	/**
	 * 部门经理审批保存
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "approveDept/save" ,method=RequestMethod.POST)
	public String approveDeptSave(Model model,String orderId, String taskId, HttpServletRequest request) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("approveDept.suggest", request.getParameter("approveDept.suggest"));
		args.put("departmentResult", request.getParameter("departmentResult"));
		args.put("nextOperator", request.getParameter("nextOperator"));
		args.put("nextOperatorName", request.getParameter("nextOperatorName"));
		args.put("ccOperator", request.getParameter("ccOperator"));
		args.put("ccOperatorName", request.getParameter("ccOperatorName"));
		String departmentResult = request.getParameter("departmentResult");
		if(departmentResult.equals("-1")) {
			facets.executeAndJump(taskId, ShiroUtils.getUsername(), args, null);
		} else if(departmentResult.equals("2")) {
			String nextOperator = request.getParameter("nextOperator");
			facets.transfer(taskId, ShiroUtils.getUsername(), nextOperator.split(","));
		} else {
			facets.execute(request.getParameter("taskId"), ShiroUtils.getUsername(), args);
		}
		String ccOperator = request.getParameter("ccOperator");
		if(StringUtils.isNotEmpty(ccOperator)) {
			facets.getEngine().order().createCCOrder(orderId, ccOperator.split(","));
		}
		
		return "redirect:/snaker/task/active";
	}
	
	/**
	 * 总经理审批保存
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "approveBoss/save" ,method=RequestMethod.POST)
	public String approveBossSave(Model model, HttpServletRequest request) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("approveBoss.suggest", request.getParameter("approveBoss.suggest"));
		facets.execute(request.getParameter("taskId"), ShiroUtils.getUsername(), args);
		return "redirect:/snaker/task/active";
	}
}
