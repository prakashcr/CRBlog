package com.niit.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.JobDao;
import com.niit.model.Job;

@RestController
public class JobController {
	@Autowired
	private JobDao jobDao;

	@RequestMapping(value = "/savejob", method = RequestMethod.POST)
	public ResponseEntity<?> saveJob(@RequestBody Job job) {

		job.setPostedOn(new Date());
		jobDao.saveJob(job);
		return new ResponseEntity<Job>(HttpStatus.OK);
	}

	@RequestMapping(value="/getalljobs")
	public ResponseEntity<List<Job>>GetAllJobs(){
		 List<Job> jobs=jobDao.listJobs();
		return new ResponseEntity<List<Job>>(jobs,HttpStatus.OK);
	
	}
	

	@RequestMapping( "/getjob/{id}")
	public ResponseEntity<Job> getJob(@PathVariable int id,Job job) {
		job = jobDao.getJobById(id);
		return new ResponseEntity<Job>(job, HttpStatus.OK);

	}

}