package ru.sandbox.flowablesandbox.configuration;

import org.flowable.engine.*;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FlowableConfiguration {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean(name = "flowableSandboxConfiguration")
    public SpringProcessEngineConfiguration flowableConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setDeploymentResources(getAutoDeployResources());
        /** Set beans */
        Map<Object, Object> beans = new HashMap<>();
        Map<String, JavaDelegate> delegateMap = applicationContext.getBeansOfType(JavaDelegate.class);
        for (String key : delegateMap.keySet()) {
            beans.put(key, delegateMap.get(key));
        }
        configuration.setBeans(beans);
        return configuration;
    }

    private Resource[] getAutoDeployResources() {
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = resourcePatternResolver.getResources("classpath:/process_definitions/*.bpmn20.xml");
            return resources;
        } catch (IOException e) {
            return new Resource[0];
        }
    }

    @Bean(name = "flowableSandboxProcessEngine")
    public ProcessEngine flowableProcessEngine(SpringProcessEngineConfiguration configuration) {
        return configuration.buildProcessEngine();
    }

    @Bean(name = "flowableSandboxRuntimeService")
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

    @Bean(name = "flowableSandboxRepositoryService")
    public RepositoryService repositoryService(ProcessEngine processEngine) {
        return processEngine.getRepositoryService();
    }

    @Bean(name = "flowableSandboxTaskService")
    public TaskService taskService(ProcessEngine processEngine) {
        return processEngine.getTaskService();
    }

    @Bean(name = "flowableSandboxHistoryService")
    public HistoryService historyService(ProcessEngine processEngine) {
        return processEngine.getHistoryService();
    }

    @Bean(name = "flowableSandboxDynamicBpmnService")
    public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
        return processEngine.getDynamicBpmnService();
    }

}
