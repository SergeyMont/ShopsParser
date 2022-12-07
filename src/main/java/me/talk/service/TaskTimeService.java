package me.talk.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TaskTimeService {
    //@Value("${update.delay}")
    // к сожалению данный вид аннотации требует подстановки константы
    // а через @Value переменная считается динамической
    private static final long DELAY = 30L;
    private final DownloaderDB downloaderDB;

    @EventListener(ApplicationReadyEvent.class)
    public void downloadAfterStartup() {
        downloaderDB.downloadDB();
    }


    @Scheduled(initialDelay = DELAY, timeUnit = TimeUnit.MINUTES)
    public void updateDB() {
        downloaderDB.downloadDB();
    }
}
