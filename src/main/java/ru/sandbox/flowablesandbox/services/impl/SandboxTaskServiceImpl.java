package ru.sandbox.flowablesandbox.services.impl;

import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sandbox.flowablesandbox.services.SandboxTaskService;

import java.util.List;

@Service("sandboxTaskService")
public class SandboxTaskServiceImpl implements SandboxTaskService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Override
    public List<Task> getActiveTasks() {
        return taskService.createTaskQuery().includeProcessVariables().includeTaskLocalVariables().active().list();
    }

    @Override
    public List<HistoricTaskInstance> getCompletedTasks() {
        return historyService.createHistoricTaskInstanceQuery().includeProcessVariables().includeTaskLocalVariables().finished().list();
    }

    @Override
    public Task completeTask(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).active().singleResult();
        if (task != null) {
            taskService.complete(taskId);
            return task;
        } else {
            throw new RuntimeException("No task with id " + taskId);
        }
    }
}
