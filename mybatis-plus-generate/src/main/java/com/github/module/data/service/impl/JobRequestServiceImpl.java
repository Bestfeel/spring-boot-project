package com.github.module.data.service.impl;

import com.github.module.data.entity.JobRequest;
import com.github.module.data.mapper.JobRequestMapper;
import com.github.module.data.service.IJobRequestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.module.data.entity.JobRequest;
import org.springframework.stereotype.Service;

/**
 * Created by feel on  2017-10-10.
 */
@Service
public class JobRequestServiceImpl extends ServiceImpl<JobRequestMapper, JobRequest> implements IJobRequestService {

}
