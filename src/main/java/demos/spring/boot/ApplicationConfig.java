package demos.spring.boot;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	// If desired, create objects in database here on startup for testing purposes.

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private LeaveRequestDao leaveDao;
	
	@Bean
	public boolean setUp() {
		System.out.println("!!!!!!!############*********************");
		System.out.println("The set up method has been called.");
		Employee emp = new Employee("Donald", "Duck", "donald@duck.net");
		empDao.save(emp);
		emp = empDao.findByEmailAddress("donald@duck.net");
		Date startDate = new Date(2015,11,03);
		Date endDate = new Date(2015,12,25);
		LeaveRequest leaveRequest = new LeaveRequest(emp.getId(), startDate, endDate, LeaveType.SCHEDULED_LEAVE);
		leaveDao.save(leaveRequest);
		return true;
	}
	
	
	
	
}
