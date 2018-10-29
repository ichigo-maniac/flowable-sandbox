package ru.sandbox.flowablesandbox.services.impl;

import org.apache.commons.io.IOUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sandbox.flowablesandbox.services.SandboxProcessDefinitionService;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service("sandboxProcessDefinitionService")
public class SandboxProcessDefinitionServiceImpl implements SandboxProcessDefinitionService {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public ProcessDefinition getActualProcessDefinition(String processDefinitionKey) {
        return repositoryService.createProcessDefinitionQuery().
                processDefinitionKey(processDefinitionKey).latestVersion().singleResult();
    }

    @Override
    public List<ProcessDefinition> getActualProcessDefinitions() {
        return repositoryService.createProcessDefinitionQuery().latestVersion().list();
    }

    @Override
    public byte[] getProcessDefinitionDiagram(String processDefinitionKey) {
        ProcessDefinition processDefinition = getActualProcessDefinition(processDefinitionKey);
        if (processDefinition == null) {
            return new byte[0];
        }
        InputStream inputStream = repositoryService.getProcessDiagram(processDefinition.getId());
        try {
            byte[] result = IOUtils.toByteArray(inputStream);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
