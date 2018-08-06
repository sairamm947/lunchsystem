package daoImpl;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import bean.Student;
import dao.IStudentdao;

@Repository
public class Iemployeeimpl implements IStudentdao{
	
	public SessionFactory sessionFactory;
	
	@Transactional
	public int registerdao(Student student) {
		Session ses=sessionFactory.getCurrentSession();
		int id=(Integer) ses.save(student);
		return id;
	}

}
