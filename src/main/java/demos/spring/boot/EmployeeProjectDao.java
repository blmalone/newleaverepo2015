package demos.spring.boot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeProjectDao extends CrudRepository<EmployeeProject, Long> {

	List<EmployeeProject> findEmployeeProjectsByProjectId(Integer projectId);
	
}
