package ru.sandbox.flowablesandbox.dto;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class ProcessDefinitionDto implements Serializable {

    private String id;

    private String category;

    private String name;

    private String key;

    private String description;

    private Integer version;

    private String resourceName;

    private String deploymentId;

    private String diagramResourceName;

}
