package demos.spring.boot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/leaverequest")
public class LeaveRequestController {

	@Autowired
	private LeaveRequestDao leaveRequestDao;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@RequestMapping(value="/new", method = RequestMethod.POST, consumes="application/json")
	public LeaveRequest addLeaveRequest(@RequestBody LeaveRequest lr) {
		System.out.println(lr);
		leaveRequestDao.save(lr);
		return lr;
	}

	@RequestMapping(value="/employee/{id}", method = RequestMethod.GET, produces="application/json")
	public List<LeaveRequest> getLeaveByEmployee(@PathVariable Integer id) {
		List<LeaveRequest> leaveRequests = leaveRequestDao.findByEmployeeId(id);
		return leaveRequests;
	}
	
	@RequestMapping(value="/{id}/approve", method = RequestMethod.POST, produces="application/json")
	public LeaveRequest approveLeaveRequest(@PathVariable Integer id) {
		LeaveRequest leaveRequest = leaveRequestDao.findOne(id);
		leaveRequest.setStatus(LeaveRequestStatus.APPROVED);
		leaveRequestDao.save(leaveRequest);
		return leaveRequest;
	}
	
	@RequestMapping(value="/{id}/reject", method = RequestMethod.POST, produces="application/json")
	public LeaveRequest rejectLeaveRequest(@PathVariable Integer id) {
		LeaveRequest leaveRequest = leaveRequestDao.findOne(id);
		leaveRequest.setStatus(LeaveRequestStatus.REJECTED);
		leaveRequestDao.save(leaveRequest);
		return leaveRequest;
	}
	
	class TeamLeadLeaveRequest {
		Integer id;
		String employeeName;
		String projectName;
		int startDate;
		int endDate;
		String description;
		LeaveType leaveType;
		LeaveRequestStatus status;
	}
	
	@RequestMapping(value="/teamlead/{id}", method=RequestMethod.GET, produces="application/json")
	public String getLeaveRequestsByTeamLeadId(@PathVariable Integer id) {
		Gson gson = new Gson();
		List<TeamLeadLeaveRequest> requests = new ArrayList<TeamLeadLeaveRequest>();
		List<LeaveRequest> allRequests = (List<LeaveRequest>) leaveRequestDao.findAll();
		List<Project> projects = projectDao.findByTeamLeadId(id);
		for(Project project : projects) {
			for(LeaveRequest request : allRequests) {
				if(projectAndRequestOverlap(project, request)) {
					Employee employee = empDao.findOne(request.getEmployeeId());
					TeamLeadLeaveRequest newRequest = new TeamLeadLeaveRequest();
					newRequest.id = request.getId();
					newRequest.employeeName = employee.getFirstName() + " " + employee.getLastName();
					newRequest.projectName = project.getName();
					newRequest.startDate = request.getStartDate();
					newRequest.endDate = request.getEndDate();
					newRequest.description = request.getDescription();
					newRequest.leaveType = request.getLeaveType();
					newRequest.status = request.getStatus();
					requests.add(newRequest);
				}
			}			
		}
		return gson.toJson(requests);
	}
	
	private boolean projectAndRequestOverlap(Project project, LeaveRequest leaveRequest) {
		if (leaveRequest.getEndDate() >= project.getStartDate() && leaveRequest.getEndDate() <= project.getEndDate()) {
			return true;			
		} else if (leaveRequest.getStartDate() <= project.getEndDate() && leaveRequest.getEndDate() >= project.getEndDate()) {
			return true;
		}
		return false;
	}

}
