package com.usbank.meetingsetup.model;

import lombok.Data;

@Data
public class MeetingRequest {
    private String agenda;
    private String date;
    private String starttime;
    private Integer duration;
    private String agendaitems;
    private String restrictions;
}
