package com.usbank.meetingsetup.service;

import com.usbank.meetingsetup.dao.MemberDao;
import com.usbank.meetingsetup.model.Member;
import com.usbank.meetingsetup.model.MeetingRequest;
import com.usbank.meetingsetup.model.MeetingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MemberDao memberDao;
    private final CalendarService calendarService;

    public MeetingResponse scheduleMeeting(MeetingRequest req) {
        Member member = Member.builder()
                .agenda(req.getAgenda())
                .date(req.getDate())
                .starttime(req.getStarttime())
                .duration(req.getDuration())
                .agendaitems(req.getAgendaitems())
                .restrictions(req.getRestrictions())
                .status("PENDING")
                .build();

        Long id = memberDao.save(member);
        member.setId(id);

        try {
            calendarService.scheduleEvent(member);
            memberDao.updateStatus(id, "SCHEDULED");
            return new MeetingResponse(id, "SCHEDULED", "Successfully scheduled");
        } catch (Exception e) {
            log.error("Calendar scheduling failed for id={}", id, e);
            memberDao.updateStatus(id, "FAILED");
            return new MeetingResponse(id, "FAILED", "Error while Scheduling");
        }
    }

    public MeetingResponse getStatus(Long id) {
        return memberDao.findById(id)
                .map(m -> new MeetingResponse(m.getId(), m.getStatus(),
                        "SCHEDULED".equals(m.getStatus()) ? "Successfully scheduled" : "Error while Scheduling"))
                .orElse(new MeetingResponse(id, "NOT_FOUND", "Meeting not found"));
    }
}
