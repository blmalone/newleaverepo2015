package demos.spring.boot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestDao extends CrudRepository<LeaveRequest, Long> {
	
	List<LeaveRequest>findByEmployeeId(Integer userId);
	
}
