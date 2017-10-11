package com.gizwits.module.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gizwits.module.data.service.IJobRequestService;
import com.gizwits.module.data.entity.JobRequest;



/**
 * 
 * Created by feel on  2017-10-10.
 */
@RestController
@RequestMapping("/data/jobRequest")
public class JobRequestController {

    @Autowired
    IJobRequestService iJobRequestService;


    @RequestMapping(value = "selectById")
    public JobRequest selectById(@RequestParam String id){
        JobRequest entity = iJobRequestService.selectById(id);
        return entity;
    }


    @RequestMapping(value = "insert")
    public void insert(){

    }


    @RequestMapping(value = "delete")
    public void delete(){

    }


    @RequestMapping(value = "update")
    public void update(){

    }

}
