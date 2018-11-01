package ru.sandbox.flowablesandbox.services;

import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;

import java.util.List;

public interface SandboxTaskService {

    List<Task> getActiveTasks();

    List<HistoricTaskInstance> getCompletedTasks();

    Task completeTask(String taskId);

}
