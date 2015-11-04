package demos.spring.boot;

import java.sql.Date;
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
		System.out.println("!!!!!!!############*********************");
		System.out.println("The set up method has been called.");

		Employee emp = new Employee("Donald", "Duck", "donald@duck.net");
		empDao.save(emp);
		emp = empDao.findByEmailAddress("donald@duck.net");
		System.out.println("ID OF EMPLOYEE IS" + emp.getId());

		String startDate = "2015-11-03";
		String endDate = "2015-12-25";
		LeaveRequest leaveRequest = new LeaveRequest(emp.getId(), startDate, endDate, LeaveType.SCHEDULED_LEAVE);
		leaveDao.save(leaveRequest);
		
		String projectStart = "2015-11-21";
		String projectEnd = "2015-12-13";
		Project project = new Project("Test project", emp.getId(), projectStart, projectEnd);
		projectDao.save(project);
		List<Project> projects = (List<Project>) projectDao.findAll();
		project = projects.get(0);
		
		EmployeeProject employeeProject = new EmployeeProject(emp.getId(), project.getId());
		employeeProjectDao.save(employeeProject);
		
		return true;
	}
	
	
	
	
}
