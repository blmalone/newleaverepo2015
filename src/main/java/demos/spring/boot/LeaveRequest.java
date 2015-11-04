package demos.spring.boot;

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
	private int startDate;
	private int endDate;
	private LeaveType leaveType;
	private LeaveRequestStatus status;
	private String description;

	public LeaveRequest(Integer employeeId, int startDate, int endDate, LeaveType leaveType, String description){
		this.employeeId = employeeId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.leaveType = leaveType;
		this.status = LeaveRequestStatus.PENDING;
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
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

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
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
