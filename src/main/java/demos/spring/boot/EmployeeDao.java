package demos.spring.boot;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

	Employee findByEmailAddress(String emailAddress);
	
}
