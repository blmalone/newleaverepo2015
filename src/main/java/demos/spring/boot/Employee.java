package demos.spring.boot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private int holidaysRemaining;
	private int holidaysTaken;
	private AccessLevel accessLevel;

	public Employee(String firstName, String lastName, String emailAddress){
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.accessLevel = AccessLevel.TEAM_MEMBER;
	}
	
	public Employee(String firstName, String lastName, String emailAddress, AccessLevel accessLevel){
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.accessLevel = accessLevel;
	}
	
	protected Employee() {}

	public Integer getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public int getHolidaysRemaining() {
		return holidaysRemaining;
	}

	public void setHolidaysRemaining(int holidaysRemaining) {
		this.holidaysRemaining = holidaysRemaining;
	}

	public int getHolidaysTaken() {
		return holidaysTaken;
	}

	public void setHolidaysTaken(int holidaysTaken) {
		this.holidaysTaken = holidaysTaken;
	}
	
	public AccessLevel getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel) {
		this.accessLevel = accessLevel;
	}

}
