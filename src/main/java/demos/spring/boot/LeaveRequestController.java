package demos.spring.boot;

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
	
	@RequestMapping(value="/new", method = RequestMethod.POST, consumes="application/json")
	public LeaveRequest addLeaveRequest(@RequestBody LeaveRequest lr) {
		leaveRequestDao.save(lr);
		return lr;
	}
	
	@RequestMapping(value="/employee/{id}", method = RequestMethod.GET, produces="application/json")
	public List<LeaveRequest> getLeaveByEmployee(@PathVariable Integer id) {
		List<LeaveRequest> leaveRequests = leaveRequestDao.findByEmployeeId(id);
		return leaveRequests;
	}
	
}
