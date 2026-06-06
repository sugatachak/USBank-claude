package com.usbank.meetingsetup.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeetingResponse {
    private Long id;
    private String status;
    private String message;
}
