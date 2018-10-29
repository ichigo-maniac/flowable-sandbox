package ru.sandbox.flowablesandbox.services;

import org.flowable.engine.runtime.ProcessInstance;
import java.util.List;

public interface SandboxProcessInstanceService {

    ProcessInstance startProcessInstanceByKey(String processDefinitionKey);

    ProcessInstance getProcessInstanceById(String processInstanceId);

    List<ProcessInstance> getActiveProcessInstances();

}
