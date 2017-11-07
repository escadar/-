package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.EmployeeProject;
import entity.Project;
import manager.ManagerHelper;
import manager.Reply;

@Path("employeeproject")
public class EmployeeProjectService {

	 @GET
	@Path("delete")
	public Reply deleteEmployeeProject(@QueryParam("id") int id) {
		return  ManagerHelper.getEmployeeProjectManager().deleteEmployeeProject(id);

	}
	 
	
	 @GET
	@Path("create")
	public EmployeeProject createEmployeeProject(@QueryParam("project") int project,@QueryParam("employee") int employee){
			 
	return  ManagerHelper.getEmployeeProjectManager().createEmployeeProject(project,employee);

	}
	 
	 @GET
	@Path("getProjectsByEmployee")
	public List<EmployeeProject> getProjectsByEmployee(@QueryParam("userId") int userId) {
     return ManagerHelper.getEmployeeProjectManager().getProjectsByEmployee(userId);
		}
	
	 
	 
		 
}
