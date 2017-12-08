package com.niit.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.dao.JobDao;
import com.niit.model.Job;

@Repository("jobDao")
@Transactional
public class JobDaoimpl implements JobDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void saveJob(Job job) {
		System.out.println("job controller");
		Session session = sessionFactory.getCurrentSession();
		session.save(job);
		session.flush();
	}

	@Override
	public List<Job> listJobs() {
		return sessionFactory.getCurrentSession().createQuery("FROM Job", Job.class).getResultList();
	}

	@Override
	public Job getJobById(int id) {
		return sessionFactory.getCurrentSession().createQuery("from Job where id=?",Job.class).setParameter(0, id).getSingleResult();

	}
}
