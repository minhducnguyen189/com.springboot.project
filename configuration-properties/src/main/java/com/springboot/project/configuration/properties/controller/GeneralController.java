package com.springboot.project.configuration.properties.controller;

import com.springboot.project.configuration.properties.model.Data;
import com.springboot.project.configuration.properties.model.Data2;
import com.springboot.project.configuration.properties.model.Data3;
import com.springboot.project.configuration.properties.model.Data4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneralController {

    @Autowired
    private Data data;

    @Autowired
    private Data2 data2;

    @Autowired
    private Data3 data3;

    @Autowired
    private Data4 data4;

    @RequestMapping(method = RequestMethod.GET, path = "/v1/general/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Data> getSampleData() {
        return ResponseEntity.ok(data);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/general/data2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Data2> getSampleData2() {
        return ResponseEntity.ok(data2);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/general/data3", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Data3> getSampleData3() {
        return ResponseEntity.ok(data3);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/general/data4", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Data4> getSampleData4() {
        return ResponseEntity.ok(data4);
    }

}
