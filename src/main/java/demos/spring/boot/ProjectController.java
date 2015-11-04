package demos.spring.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectDao projectDao;
	
	@RequestMapping(value="/new", method=RequestMethod.POST, consumes="application/json")
	public Project createProject(@RequestBody Project project) {
		projectDao.save(project);
		return project;
	}
	
	@RequestMapping(value="/teamlead/{id}", method=RequestMethod.GET, produces="application/json")
	public List<Project> getProjectByTeamLeadId(@PathVariable Integer id) {
		List<Project> projects = projectDao.findByTeamLeadId(id);
		return projects;
	}
}
