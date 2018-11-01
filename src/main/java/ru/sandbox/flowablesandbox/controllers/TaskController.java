package ru.sandbox.flowablesandbox.controllers;

import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sandbox.flowablesandbox.dto.HistoricTaskDto;
import ru.sandbox.flowablesandbox.dto.TaskDto;
import ru.sandbox.flowablesandbox.mapping.SandboxMapper;
import ru.sandbox.flowablesandbox.services.SandboxTaskService;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private SandboxTaskService taskService;

    @Autowired
    private SandboxMapper sandboxMapper;

    @GetMapping("/active_tasks")
    public List<TaskDto> getActiveTasks() {
        List<Task> tasks = taskService.getActiveTasks();
        return sandboxMapper.toTaskDtoList(tasks);
    }

    @GetMapping("/completed_tasks")
    public List<HistoricTaskDto> getCompletedTasks() {
        List<HistoricTaskInstance> completedTasks = taskService.getCompletedTasks();
        return sandboxMapper.tiHistoricTaskDtoList(completedTasks);
    }

    @PostMapping("/complete/{taskId}")
    public TaskDto completeTask(@PathVariable("taskId") String taskId) {
        Task completedTask = taskService.completeTask(taskId);
        return sandboxMapper.toTaskDto(completedTask);
    }

}
