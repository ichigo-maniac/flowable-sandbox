package ru.sandbox.flowablesandbox.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Getter @Setter
public class TaskInfoDto implements Serializable {

    private String id;

    private String name;

    private String description;

    private Integer priority;

    private String owner;

    private String assignee;

    private String processInstanceId;

    private String executionId;

    private String taskDefinitionId;

    private String scopeId;

    private String subScopeId;

    private String scopeType;

    private String scopeDefinitionId;

    private Date createTime;

    private String taskDefinitionKey;

    private Date dueDate;

    private String category;

    private String parentTaskId;

    private String tenantId;

    private String formKey;

    private Map<String, Object> taskLocalVariables;

    private Map<String, Object> processVariables;

    private Date claimTime;

}
