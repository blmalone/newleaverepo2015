package demos.spring.boot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDao extends CrudRepository<Project, Integer> {

	List<Project> findByTeamLeadId(Integer employeeId);
	
}
