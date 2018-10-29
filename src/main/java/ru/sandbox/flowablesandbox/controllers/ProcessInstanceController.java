package ru.sandbox.flowablesandbox.controllers;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.sandbox.flowablesandbox.dto.ProcessInstanceDto;
import ru.sandbox.flowablesandbox.mapping.SandboxMapper;
import ru.sandbox.flowablesandbox.services.SandboxProcessDefinitionService;
import ru.sandbox.flowablesandbox.services.SandboxProcessInstanceService;

import java.util.List;

@RestController
@RequestMapping("/process_instances")
public class ProcessInstanceController {

    @Autowired
    private SandboxProcessInstanceService processInstanceService;

    @Autowired
    private SandboxProcessDefinitionService processDefinitionService;

    @Autowired
    private SandboxMapper sandboxMapper;

    @PostMapping("/start/{processDefinitionKey}")
    public ProcessInstanceDto startProcessInstance(@PathVariable("processDefinitionKey") String processDefinitionKey) {
        ProcessDefinition processDefinition = processDefinitionService.getActualProcessDefinition(processDefinitionKey);
        if (processDefinition != null) {
            ProcessInstance processInstance = processInstanceService.startProcessInstanceByKey(processDefinitionKey);
            return sandboxMapper.toProcessInstanceDto(processInstance);
        } else {
            throw new RuntimeException("No process definition with key " + processDefinitionKey);
        }
    }

    @GetMapping("/process_instance/{processInstanceId}")
    public ProcessInstanceDto getProcessInstance(@PathVariable("processInstanceId") String processInstanceId) {
        ProcessInstance processInstance = processInstanceService.getProcessInstanceById(processInstanceId);
        if (processInstance != null) {
            return sandboxMapper.toProcessInstanceDto(processInstance);
        } else {
            return null;
        }
    }

    @GetMapping("/active")
    public List<ProcessInstanceDto> getActiveProcessInstances() {
        List<ProcessInstance> processInstances = processInstanceService.getActiveProcessInstances();
        return sandboxMapper.toProcessInstanceDtoList(processInstances);
    }
}
