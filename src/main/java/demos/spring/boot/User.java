package demos.spring.boot;

public class User {
	
	private String firstName;
	private String lastName;
	private static int user_id;
	private String emailAddress;
	private int holidaysRemaining, holidaysTaken;
	
	public User(String firstName, String lastName, String emailAddress){
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		
		
	}

}
