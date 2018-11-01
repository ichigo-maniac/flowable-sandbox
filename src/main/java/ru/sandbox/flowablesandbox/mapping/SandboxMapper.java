package ru.sandbox.flowablesandbox.mapping;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.mapstruct.Mapper;
import ru.sandbox.flowablesandbox.dto.HistoricTaskDto;
import ru.sandbox.flowablesandbox.dto.ProcessDefinitionDto;
import ru.sandbox.flowablesandbox.dto.ProcessInstanceDto;
import ru.sandbox.flowablesandbox.dto.TaskDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SandboxMapper {

    ProcessDefinitionDto toProcessDefinitionDto(ProcessDefinition processDefinition);

    List<ProcessDefinitionDto> toProcessDefinitionDtoList(List<ProcessDefinition> processDefinitions);

    ProcessInstanceDto toProcessInstanceDto(ProcessInstance processInstance);

    List<ProcessInstanceDto> toProcessInstanceDtoList(List<ProcessInstance> processInstances);

    TaskDto toTaskDto(Task task);

    List<TaskDto> toTaskDtoList(List<Task> tasks);

    HistoricTaskDto toHistoricTaskDto(HistoricTaskInstance historicTaskInstance);

    List<HistoricTaskDto> tiHistoricTaskDtoList(List<HistoricTaskInstance> historicTaskInstances);

}
