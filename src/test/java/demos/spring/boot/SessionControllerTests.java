/*
package demos.spring.boot;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class SessionControllerTests {

	@InjectMocks
	private SessionController sessionController;
	
	@Mock
	private EmployeeDao employeeDao;
	
	private Employee employee;
	
	@Before
	public void testSetUp() {
		employee = new Employee("Dave", "Jones", "dave@email.com");
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldAllowLoginIfEmailCorrect() {
		Mockito.when(employeeDao.findByEmailAddress("dave@email.com")).thenReturn(employee);
		assertEquals(employee, sessionController.logIn("dave@email.com"));
	}

}
*/

