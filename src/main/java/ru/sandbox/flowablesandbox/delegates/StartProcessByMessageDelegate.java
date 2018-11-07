package ru.sandbox.flowablesandbox.delegates;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.runtime.ProcessInstance;
import ru.sandbox.flowablesandbox.providers.ApplicationContextProvider;
import ru.sandbox.flowablesandbox.services.SandboxProcessInstanceService;

public class StartProcessByMessageDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        ProcessInstance processInstance = getProcessInstanceService().startProcessInstanceByMessage("start-process-message");
        processInstance.toString();
    }

    public SandboxProcessInstanceService getProcessInstanceService() {
        return (SandboxProcessInstanceService) ApplicationContextProvider.getBean("sandboxProcessInstanceService");
    }
}
