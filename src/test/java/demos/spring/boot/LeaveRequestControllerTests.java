package demos.spring.boot;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class LeaveRequestControllerTests {
	
	@InjectMocks
	private LeaveRequestController leaveRequestController;
	
	@Mock
	private LeaveRequestDao leaveRequestDao;
	
	@Before
	public void testSetUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldCreateNewLeaveRequest() {
		LeaveRequest leaveRequest = new LeaveRequest(1, 20150125, 20150212, LeaveType.ADDITIONAL_HOLIDAY, "holiday");
		Mockito.when(leaveRequestDao.save(leaveRequest)).thenReturn(leaveRequest);
		leaveRequestController.addLeaveRequest(leaveRequest);
	}
	
	@Test
	public void shouldReturnLeaveRequestsForEmployeeById() {
		List<LeaveRequest> leaveRequestsForEmployee = new ArrayList<LeaveRequest>();
		for(int i = 11; i < 14; i+=2) {
			LeaveRequest leaveRequest = new LeaveRequest(1, (201501 + i), (201501 + i + 1), LeaveType.BEREAVEMENT, "sad");
			leaveRequestsForEmployee.add(leaveRequest);
		}
	}

}
