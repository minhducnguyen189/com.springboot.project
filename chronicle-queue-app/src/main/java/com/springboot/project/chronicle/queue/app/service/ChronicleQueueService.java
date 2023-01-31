package com.springboot.project.chronicle.queue.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.project.chronicle.queue.app.model.ErrorDetail;
import net.openhft.chronicle.Chronicle;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.ExcerptTailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ChronicleQueueService {

    private long currentIndex;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Chronicle chronicle;

    @PostConstruct
    public void getCurrentIndex() {
        this.currentIndex = this.chronicle.lastWrittenIndex();
    }

    public void addToQueue() {
        try {
            ExcerptAppender appender = this.chronicle.createAppender();
            appender.startExcerpt();

            ErrorDetail errorDetail = this.createErrorDetail();

            String errorDetailJson = this.objectMapper.writeValueAsString(errorDetail);
            appender.writeUTF(errorDetailJson);

            appender.finish();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<ErrorDetail> readAllFromQueue() {
        try {
            List<ErrorDetail> errorDetails = new ArrayList<>();
            ExcerptTailer tailer = this.chronicle.createTailer();
            while (tailer.nextIndex()) {
                ErrorDetail errorDetail = this.objectMapper.readValue(tailer.readUTF(), ErrorDetail.class);
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
            ExcerptTailer tailer = this.chronicle.createTailer();
            if (tailer.index(1 + this.currentIndex)) {
                ErrorDetail errorDetail = this.objectMapper.readValue(tailer.readUTF(), ErrorDetail.class);
                this.currentIndex = tailer.index();
                tailer.finish();
                return errorDetail;
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
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
