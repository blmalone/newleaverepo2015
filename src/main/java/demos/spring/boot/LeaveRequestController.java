package demos.spring.boot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/leaverequest")
public class LeaveRequestController {

	@Autowired
	private LeaveRequestDao leaveRequestDao;
	
	@Autowired
	private EmployeeProjectDao empProjDao;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@RequestMapping(value="/new", method = RequestMethod.POST, consumes="application/json")
	public LeaveRequest addLeaveRequest(@RequestBody LeaveRequest lr) {
		leaveRequestDao.save(lr);
//		for(leaverequest.employee's projects') {
//			if(leaverequest.startdate >= project.startdate && leaverequest.startdate <= project.enddate) {
//				create new project-leaverequest association for project and leaverequest
//			}
//		}
		return lr;
	}

	@RequestMapping(value="/employee/{id}", method = RequestMethod.GET, produces="application/json")
	public List<LeaveRequest> getLeaveByEmployee(@PathVariable Integer id) {
		List<LeaveRequest> leaveRequests = leaveRequestDao.findByEmployeeId(id);
		return leaveRequests;
	}
	
	@RequestMapping(value="/project/{id}", method=RequestMethod.GET, produces="application/json")
	public List<LeaveRequest> getLeaveByProject(@PathVariable Integer id) {
		Project project = projectDao.findOne(id);
		List<EmployeeProject> empProjects = empProjDao.findEmployeeProjectsByProjectId(id);
		List<LeaveRequest> leaveRequests = new ArrayList<LeaveRequest>();
		for(EmployeeProject employeeProject : empProjects) {
			leaveRequests.addAll(leaveRequestDao.findByEmployeeIdAndDateWindow(employeeProject.getEmployeeId(), project.getStartDate(), project.getEndDate()));
		}
		return leaveRequests;
	}

}
