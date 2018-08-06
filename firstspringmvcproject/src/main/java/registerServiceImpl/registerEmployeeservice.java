package registerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bean.Student;
import dao.IStudentdao;
import daoImpl.Iemployeeimpl;
import registerService.IregisterService;

@Service
public class registerEmployeeservice implements IregisterService {

	@Autowired
	public IStudentdao dao;

	public int register(Student student) {
		return dao.registerdao(student);
	}


}
