package com.thinhlq.firstjobapp.Job.Service;

import com.thinhlq.firstjobapp.Job.Job;
import org.springframework.stereotype.Service;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(Long id);

    boolean deleteJobById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
