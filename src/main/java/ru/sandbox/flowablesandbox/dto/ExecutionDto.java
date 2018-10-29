package ru.sandbox.flowablesandbox.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class ExecutionDto implements Serializable {

    private String id;

    private Boolean suspended;

    private Boolean ended;

    private String activityId;

    private String processInstanceId;

    private String parentId;

    private String superExecutionId;

    private String rootProcessInstanceId;

    private String name;

    private String description;

}
