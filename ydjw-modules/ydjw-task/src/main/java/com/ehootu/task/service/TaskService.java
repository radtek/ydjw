package com.ehootu.task.service;

import com.ehootu.core.generic.GenericService;
import com.ehootu.task.model.Task;
import com.ehootu.task.model.TaskExecution;
import com.ehootu.user.model.Police;

import java.util.List;

/**
 * @author zhangyong
 * @create 2017-12-04 10:39
 * 任务服务
 **/
public interface TaskService extends GenericService<Task, Integer> {

    int createTask(Task task);

    List<Task> findList(Integer id);

    Task findById(Integer id);

    List<TaskExecution> findTaskExecution(Integer id);

    int createTaskExecution(TaskExecution taskExecution);

    //通过任务ID查询在此任务下的警务人员
    List<Police> policeListInTaskId(Integer taskId);

    //通过任务ID查询在此任务下的同警务室的警务人员
    List<Police> policeListNotInTaskId(Integer taskId);

    void updateTimes(Integer taskId);


}
