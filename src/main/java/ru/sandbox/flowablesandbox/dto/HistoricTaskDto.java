package ru.sandbox.flowablesandbox.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class HistoricTaskDto extends TaskInfoDto {

    private String deleteReason;

    private Date startTime;

    private Date endTime;

    private Long durationInMillis;

    private Long workTimeInMillis;

}
