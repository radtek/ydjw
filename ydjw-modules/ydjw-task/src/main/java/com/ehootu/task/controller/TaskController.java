package com.ehootu.task.controller;

import com.ehootu.core.generic.BaseController;

import com.ehootu.task.model.Task;
import com.ehootu.task.model.TaskExecution;
import com.ehootu.task.service.TaskService;
import com.ehootu.task.util.WSServer;
import com.ehootu.user.model.Police;
import com.ehootu.user.service.PoliceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 * 通知公告控制器
 *
 * @author KongXiaoPing
 *
 *         2017年6月13日
 */
@Controller
@RequestMapping(value = "/task")
public class TaskController extends BaseController {
	
	private final static Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;

	@Autowired
	private PoliceService policeService;

	@RequestMapping(value = "/create")
	@ResponseBody
	public void create(Task task) {

		log.info(task.toString());

		int result = taskService.createTask(task);

		if (result ==0){
			resultError("1","添加失败");
		}else {
			resultSuccess();
		}
	}

	@RequestMapping(value = "/finds/{id}")
	public void findList(@PathVariable("id") Integer id ){
		log.info(""+id);
		List<Task> list = taskService.findList(id);
		resultSuccess(list);
	}

	@RequestMapping(value = "find/{id}")
	public void findById(@PathVariable("id") Integer id ){

		Task list = taskService.findById(id);
		resultSuccess(list);
	}

	@RequestMapping(value = "findTaskExecution/{id}")
	public void findTaskExecution(@PathVariable("id") Integer id ){

		List<TaskExecution> list = taskService.findTaskExecution(id);
		resultSuccess(list);
	}

	/**
	 * 通id查询出
	 * @param taskId
	 */
	@RequestMapping(value = "/police/list/notin/{id}")
	public void policeListNotInTaskId(@PathVariable("id") Integer taskId){
		log.info("查询id:"+taskId);
		List<Police> policeList  = taskService.policeListNotInTaskId(taskId);
		resultSuccess(policeList);
	}
	/**
	 * 通id查询出
	 * @param taskId
	 */
	@RequestMapping(value = "/police/list/in/{id}")
	public void policeListInTaskId(@PathVariable("id") Integer taskId){
		log.info("查询id:"+taskId);
		List<Police> policeList  = taskService.policeListInTaskId(taskId);
		resultSuccess(policeList);
	}

	/**
	 * 成功添加了子任务后，点击提交修改该任务状态
	 * @param taskId
	 */
	@RequestMapping(value = "/update/status/{id}")
	public void taskUpdateStatus(@PathVariable("id") Integer taskId){
		try {
			Task task = taskService.findById(taskId);
			if(task!=null){
				task.setTaskStatus(2);
				taskService.update(task);
				resultSuccess();
			}else {
				resultError("提交出错");
			}
		}catch (Exception e){
			e.printStackTrace();
			resultError("提交出错");
		}
	}

}
