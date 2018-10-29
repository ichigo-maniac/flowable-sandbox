package ru.sandbox.flowablesandbox.configuration;

import org.flowable.engine.*;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class FlowableConfiguration {

    @Bean(name = "flowableSandboxConfiguration")
    public SpringProcessEngineConfiguration flowableConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setDeploymentResources(getAutoDeployResources());
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

}
