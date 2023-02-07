package com.springboot.project.chronicle.queue.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.chronicle.queue.app.model.ErrorDetail;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class ChronicleQueueService {

    private long currentIndex;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("errorDetailQueue")
    private Chronicle errorDetailQueue;

    @Autowired
    @Qualifier("errorDetailQueueIndex")
    private Chronicle errorDetailQueueIndex;

    @PostConstruct
    public void getCurrentIndex() {
        this.currentIndex = this.errorDetailQueueIndex.lastWrittenIndex();
    }

    public void addToQueue() {
        try {
            ExcerptAppender appender = this.errorDetailQueue.createAppender();
            appender.startExcerpt();

            ErrorDetail errorDetail = this.createErrorDetail();

            String errorDetailJson = this.objectMapper.writeValueAsString(errorDetail);
            appender.writeUTFΔ(errorDetailJson);

            appender.finish();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ErrorDetail> readAllFromQueue() {
        try {
            List<ErrorDetail> errorDetails = new ArrayList<>();
            ExcerptTailer tailer = this.errorDetailQueue.createTailer();
            while (tailer.nextIndex()) {
                ErrorDetail errorDetail = this.objectMapper.readValue(tailer.readUTFΔ(), ErrorDetail.class);
                errorDetails.add(errorDetail);
            }
            tailer.finish();
            return errorDetails;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ErrorDetail getNextItemFromQueue() {
        try {
            ExcerptTailer tailer = this.errorDetailQueue.createTailer();
            if (tailer.index(1 + this.currentIndex)) {
                ErrorDetail errorDetail = this.objectMapper.readValue(tailer.readUTFΔ(), ErrorDetail.class);
                ExcerptAppender indexAppender = this.errorDetailQueueIndex.createAppender();
                indexAppender.startExcerpt();
                indexAppender.writeUTF(String.valueOf(tailer.index()));
                this.currentIndex = tailer.index();
                indexAppender.finish();
                tailer.finish();
                return errorDetail;
            }
            return new ErrorDetail();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void sendNotifiedEmail() {
        try {
            Thread.sleep(6000);
            ErrorDetail errorDetail = this.getNextItemFromQueue();
            if (Objects.isNull(errorDetail.getId())) {
                log.info("No Error Item In Queue: Queue is empty!");
                return;
            }
            log.info("Sent Notified Email id: " + errorDetail.getId() + " With Timestamp: " + errorDetail.getTimestamp());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ErrorDetail createErrorDetail() {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setId(UUID.randomUUID());
        errorDetail.setErrorCode(403);
        errorDetail.setErrorMessage("Error Message");
        errorDetail.setTimestamp(OffsetDateTime.now());
        return errorDetail;
    }

}
