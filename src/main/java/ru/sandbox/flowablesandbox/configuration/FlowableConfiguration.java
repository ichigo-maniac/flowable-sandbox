package ru.sandbox.flowablesandbox.configuration;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RuntimeService;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

@Configuration
public class FlowableConfiguration {

    @Bean(name = "flowableSandboxConfiguration")
    public SpringProcessEngineConfiguration flowableConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration configuration = new SpringProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setTransactionManager(transactionManager);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        /** Deployment resources */
//        Resource[] deploymentResources = new Resource[1];
//        deploymentResources[0] = new ClassPathResource("/process_definitions/*.bpmn20.xml");
//        configuration.setDeploymentResources(deploymentResources);
        return configuration;
    }

    @Bean(name = "flowableSandboxProcessEngine")
    public ProcessEngine flowableProcessEngine(SpringProcessEngineConfiguration configuration) {
        return configuration.buildProcessEngine();
    }

    @Bean(name = "flowableSandboxRuntimeService")
    public RuntimeService runtimeService(ProcessEngine processEngine) {
        return processEngine.getRuntimeService();
    }

}
