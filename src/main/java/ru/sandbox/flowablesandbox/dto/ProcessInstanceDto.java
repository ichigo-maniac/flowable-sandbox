package ru.sandbox.flowablesandbox.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Getter @Setter
public class ProcessInstanceDto extends ExecutionDto implements Serializable {

    private String processDefinitionId;

    private String processDefinitionName;

    private String processDefinitionKey;

    private String processDefinitionVersion;

    private String deploymentId;

    private String businessKey;

    private Map<String, Object> processVariables;

    private Date startTime;

    private String startUserId;

    private String callbackId;

    private String callbackType;
}
