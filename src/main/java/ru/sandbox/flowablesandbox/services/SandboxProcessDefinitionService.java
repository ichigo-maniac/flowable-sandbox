package ru.sandbox.flowablesandbox.services;

import org.flowable.engine.repository.ProcessDefinition;
import java.util.List;

public interface SandboxProcessDefinitionService {

    ProcessDefinition getActualProcessDefinition(String processDefinitionKey);

    List<ProcessDefinition> getActualProcessDefinitions();

    byte[] getProcessDefinitionDiagram(String processDefinitionKey);

}
