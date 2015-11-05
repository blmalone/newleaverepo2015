package demos.spring.boot;

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
		// create some employees
		Employee emp = new Employee("Donald", "Duck", "donald@duck.net");
		empDao.save(emp);
		Employee emp2 = new Employee("Pete", "Davis", "pete@world.com");
		empDao.save(emp2);
		Employee emp3 = new Employee("Jane", "Donaldson", "janeybabe@fresh.net");
		empDao.save(emp3);
		Employee emp4 = new Employee("Mick", "Mickelson", "micky@mouse.com");
		empDao.save(emp4);
		Employee emp5 = new Employee("Paul", "Peters", "paulp@paulp.co.uk");
		empDao.save(emp5);
		Employee emp6 = new Employee("Johnny", "Jones", "jman@jonescom.net");
		empDao.save(emp6);
		
		// create some managers
		Employee manager = new Employee("Peter", "Pan", "peter@pan.com", AccessLevel.TEAM_LEADER);
		empDao.save(manager);
		Employee manager2 = new Employee("John", "Johnson", "john@johnson.com", AccessLevel.TEAM_LEADER);
		empDao.save(manager2);

		// create a couple of leave requests
		leaveDao.save(new LeaveRequest(emp3.getId(), 20160102, 20160105, LeaveType.TRAINING, "Java training"));
		leaveDao.save(new LeaveRequest(emp5.getId(), 20151212, 20151224, LeaveType.ADDITIONAL_HOLIDAY, "Heading to the Seychelles"));
		
		// create some projects with the first manager as team leader
		Project project = new Project("Project Squeezebox", manager.getId(), 20151121, 20151213);
		projectDao.save(project);
		Project project2 = new Project("Project Hopscotch", manager.getId(), 20160122, 20160228);
		projectDao.save(project2);
		projectDao.save(new Project("Project Anvil", manager.getId(), 20160301, 20160405));
		projectDao.save(new Project("Project Project", manager.getId(), 20160412, 20160606));
		
		// add some employees to some projects
		employeeProjectDao.save(new EmployeeProject(emp.getId(), project2.getId()));
		employeeProjectDao.save(new EmployeeProject(emp3.getId(), project.getId()));
		employeeProjectDao.save(new EmployeeProject(emp5.getId(), project.getId()));
		
		// has to return something because we're using it as a bean
		return true;
	}

}
