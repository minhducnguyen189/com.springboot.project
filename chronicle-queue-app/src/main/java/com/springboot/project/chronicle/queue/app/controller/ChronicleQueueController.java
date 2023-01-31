package com.springboot.project.chronicle.queue.app.controller;

import com.springboot.project.chronicle.queue.app.model.ErrorDetail;
import com.springboot.project.chronicle.queue.app.service.ChronicleQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChronicleQueueController {

    @Autowired
    private ChronicleQueueService chronicleQueueService;

    @RequestMapping(method = RequestMethod.POST, path = "/v1/chronicle-queue/error-details")
    public ResponseEntity<Void> addToQueue() {
        this.chronicleQueueService.addToQueue();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/chronicle-queue/error-details")
    public ResponseEntity<List<ErrorDetail>> getAllItemsFromQueue() {
        return ResponseEntity.ok(this.chronicleQueueService.readAllFromQueue());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/chronicle-queue/error-details/polls")
    public ResponseEntity<ErrorDetail> getNextItemFromQueue() {
        return ResponseEntity.ok(this.chronicleQueueService.getNextItemFromQueue());
    }

}
