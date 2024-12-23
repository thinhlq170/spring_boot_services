package com.thinhlq.firstjobapp.Job.Impl;

import com.thinhlq.firstjobapp.Job.Job;
import com.thinhlq.firstjobapp.Job.JobRepository;
import com.thinhlq.firstjobapp.Job.Service.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

//    private final List<Job> jobs = new ArrayList<>();
    private final JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Get all jobs
     * @return list of available jobs
     */
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    /**
     * create a new job and add it to the list of available jobs
     * @param job {@link Job}
     */
    @Override
    public void createJob(final Job job) {
//        job.setId(nextId++);
        jobRepository.save(job);
    }

    /**
     * Getting a job by its id
     * @param id @{@link Long}
     * @return @{@link Job}
     */
    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    /**
     * deleting a job by its id
     * @param id @{@link Long}
     * @return @{@link Boolean}
     */
    @Override
    public boolean deleteJobById(Long id) {
        boolean delete = true;
        try {
            final Optional<Job> job = jobRepository.findById(id);
            if (job.isPresent()) {
                jobRepository.deleteById(id);
            } else {
                delete = false;
            }
        } catch (Exception e) {
            delete = false;
        }

        return delete;
    }

    /**
     * Updating a job by its id
     * @param id @{@link Long}
     * @param updatedJob @{@link Job}
     * @return @{@link Boolean}
     */
    @Override
    public boolean updateJob(final Long id, final Job updatedJob) {
        final Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            final Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setLocation(updatedJob.getLocation());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
