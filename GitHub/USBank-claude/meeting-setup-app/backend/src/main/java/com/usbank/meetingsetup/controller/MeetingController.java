package com.usbank.meetingsetup.controller;

import com.usbank.meetingsetup.model.MeetingRequest;
import com.usbank.meetingsetup.model.MeetingResponse;
import com.usbank.meetingsetup.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/meetings")
@RequiredArgsConstructor
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping
    public ResponseEntity<MeetingResponse> schedule(@RequestBody MeetingRequest request) {
        return ResponseEntity.ok(meetingService.scheduleMeeting(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeetingResponse> getStatus(@PathVariable Long id) {
        return ResponseEntity.ok(meetingService.getStatus(id));
    }
}
