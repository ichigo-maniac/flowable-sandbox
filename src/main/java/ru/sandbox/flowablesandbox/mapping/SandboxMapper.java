package ru.sandbox.flowablesandbox.mapping;

import org.flowable.engine.repository.ProcessDefinition;
import org.mapstruct.Mapper;
import ru.sandbox.flowablesandbox.dto.ProcessDefinitionDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SandboxMapper {

    ProcessDefinitionDto toProcessDefinitionDto(ProcessDefinition processDefinition);

    List<ProcessDefinitionDto> toProcessDefinitionDtoList(List<ProcessDefinition> processDefinitions);

}
