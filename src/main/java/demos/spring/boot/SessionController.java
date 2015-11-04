package demos.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@RequestMapping(value="/login", method=RequestMethod.POST, consumes="application/json")
	public Employee logIn(@RequestBody String email) {
		Employee employee = employeeDao.findByEmailAddress(email);
		return employee;
	}

}
