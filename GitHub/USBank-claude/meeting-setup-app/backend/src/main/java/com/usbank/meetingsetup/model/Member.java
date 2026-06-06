package com.usbank.meetingsetup.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String agenda;
    private String date;
    private String starttime;
    private Integer duration;
    private String agendaitems;
    private String restrictions;
    private String status;
}
