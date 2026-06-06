package com.usbank.meetingsetup.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.usbank.meetingsetup.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

@Slf4j
@Service
public class CalendarService {

    private static final String APP_NAME = "US Bank Meeting Setup";
    private static final GsonFactory JSON = GsonFactory.getDefaultInstance();
    private static final List<String> SCOPES = List.of(CalendarScopes.CALENDAR);

    @Value("${google.calendar.credentials-path}")
    private String credentialsPath;

    @Value("${google.calendar.tokens-path}")
    private String tokensPath;

    public void scheduleEvent(Member member) throws Exception {
        NetHttpTransport transport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(transport, JSON, getCredential(transport))
                .setApplicationName(APP_NAME)
                .build();

        service.events().insert("primary", buildEvent(member)).execute();
        log.info("Calendar event created for meeting id={}", member.getId());
    }

    private Credential getCredential(NetHttpTransport transport) throws Exception {
        InputStream in = getClass().getClassLoader().getResourceAsStream(credentialsPath);
        if (in == null) {
            throw new FileNotFoundException(
                "Place credentials.json under backend/src/main/resources/ (see credentials.json.template)");
        }
        GoogleClientSecrets secrets = GoogleClientSecrets.load(JSON, new InputStreamReader(in));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                transport, JSON, secrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new File(tokensPath)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow,
                new LocalServerReceiver.Builder().setPort(8888).build())
                .authorize("user");
    }

    private Event buildEvent(Member member) {
        LocalDate date = LocalDate.parse(member.getDate());
        LocalTime time = LocalTime.parse(member.getStarttime());
        ZoneId zone = ZoneId.systemDefault();

        long startMs = LocalDateTime.of(date, time).atZone(zone).toInstant().toEpochMilli();
        long endMs   = LocalDateTime.of(date, time).plusMinutes(member.getDuration()).atZone(zone).toInstant().toEpochMilli();

        return new Event()
                .setSummary(member.getAgenda())
                .setDescription("Agenda Items: " + member.getAgendaitems() +
                                "\nRestrictions: " + member.getRestrictions())
                .setStart(new EventDateTime().setDateTime(new DateTime(startMs)))
                .setEnd(new EventDateTime().setDateTime(new DateTime(endMs)));
    }
}
