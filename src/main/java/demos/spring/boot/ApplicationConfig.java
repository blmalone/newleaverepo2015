package demos.spring.boot;

import java.util.List;

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
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private EmployeeProjectDao employeeProjectDao;
	
	@Bean
	public boolean setUp() {
		// create an employee and get back their stored employee object with ID
		Employee emp = new Employee("Donald", "Duck", "donald@duck.net");
		empDao.save(emp);
		
		// create some more employees
		
		// create a manager
		Employee manager = new Employee("Peter", "Rabbit", "peter@hotdog.com", AccessLevel.TEAM_LEADER);
		empDao.save(manager);

		// create a leave request for the employee
		int startDate = 20151103;
		int endDate = 20151225;
		String description = "going on holiday";
		LeaveRequest leaveRequest = new LeaveRequest(emp.getId(), startDate, endDate, LeaveType.SCHEDULED_LEAVE, description);
		leaveDao.save(leaveRequest);
		
		// create a project with the manager as team leader
		int projectStart = 20151121;
		int projectEnd = 20151213;
		Project project = new Project("Test project", manager.getId(), projectStart, projectEnd);
		projectDao.save(project);
		
		// add the employee to the project
		EmployeeProject employeeProject = new EmployeeProject(emp.getId(), project.getId());
		employeeProjectDao.save(employeeProject);
		
		return true;
	}
	
	
	
	
}
