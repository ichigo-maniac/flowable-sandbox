package ru.sandbox.flowablesandbox.services.impl;

import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sandbox.flowablesandbox.services.SandboxProcessInstanceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sandboxProcessInstanceService")
public class SandboxProcessInstanceServiceImpl implements SandboxProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public ProcessInstance startProcessInstanceByKey(String processDefinitionKey) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("dummy", "dummy started");
        return runtimeService.startProcessInstanceByKey(processDefinitionKey, parameters);
    }

    @Override
    public ProcessInstance getProcessInstanceById(String processInstanceId) {
        return runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).includeProcessVariables().singleResult();
    }

    @Override
    public List<ProcessInstance> getActiveProcessInstances() {
        return runtimeService.createProcessInstanceQuery().includeProcessVariables().active().list();
    }
}
