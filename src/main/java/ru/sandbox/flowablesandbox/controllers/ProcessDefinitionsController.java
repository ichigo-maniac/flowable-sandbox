package ru.sandbox.flowablesandbox.controllers;

import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sandbox.flowablesandbox.dto.ProcessDefinitionDto;
import ru.sandbox.flowablesandbox.mapping.SandboxMapper;
import ru.sandbox.flowablesandbox.services.SandboxProcessDefinitionService;
import java.util.List;

@RestController
@RequestMapping("/process_definitions")
public class ProcessDefinitionsController {

    @Autowired
    private SandboxProcessDefinitionService processDefinitionService;

    @Autowired
    private SandboxMapper sandboxMapper;

    @GetMapping("/actual_definitions")
    public List<ProcessDefinitionDto> getLatestProcessDefinitions() {
        List<ProcessDefinition> processDefinitions = processDefinitionService.getActualProcessDefinitions();
        return sandboxMapper.toProcessDefinitionDtoList(processDefinitions);
    }

    @GetMapping("/actual_definition/{processDefinitionKey}")
    public ProcessDefinitionDto getLatestProcessDefinition(@PathVariable("processDefinitionKey") String processDefinitionKey) {
        ProcessDefinition processDefinition = processDefinitionService.getActualProcessDefinition(processDefinitionKey);
        if (processDefinition != null) {
            return sandboxMapper.toProcessDefinitionDto(processDefinition);
        } else {
            return null;
        }
    }

    @GetMapping("/diagram/{processDefinitionKey}")
    public ResponseEntity<?> getProcessDefinitionDiagram(@PathVariable("processDefinitionKey") String processDefinitionKey) {
        ProcessDefinition processDefinition = processDefinitionService.getActualProcessDefinition(processDefinitionKey);
        if (processDefinition != null) {
            return prepareFileResponse(processDefinitionKey, processDefinitionService.getProcessDefinitionDiagram(processDefinitionKey));
        } else {
            return null;
        }
    }

    private ResponseEntity<?> prepareFileResponse(String name, byte[] diagramData) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        headers.setContentLength(diagramData.length);
        headers.setContentDisposition(
                ContentDisposition.builder("inline")
                        .filename("diagram_" + name + ".png")
                        .size((long) diagramData.length)
                        .build());
        return new ResponseEntity<>(new ByteArrayResource(diagramData), headers, HttpStatus.OK);
    }


}
