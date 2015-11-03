package demos.spring.boot;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LeaveRequest {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Integer employeeId;
	private Date startDate;
	private Date endDate;
	private LeaveType leaveType;
	private LeaveRequestStatus status;
	
	public LeaveRequest(Integer employeeId, Date startDate, Date endDate, LeaveType leaveType){
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.status = LeaveRequestStatus.PENDING;
	}
	
	protected LeaveRequest() {}

	public LeaveRequestStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveRequestStatus status) {
		this.status = status;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public Integer getId() {
		return id;
	}

}
