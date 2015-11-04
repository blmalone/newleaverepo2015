package demos.spring.boot;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRequestDao extends CrudRepository<LeaveRequest, Integer> {
	
	List<LeaveRequest>findByEmployeeId(Integer userId);
	
	@Query("select lr from LeaveRequest lr where lr.employeeId = :id and lr.startDate >= :start and lr.startDate <= :end")
	List<LeaveRequest>findByEmployeeIdAndDateWindow(@Param("id")Integer employeeId, @Param("start")String projectStartDate, @Param("end")String projectEndDate);
	
}
