package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.EmployeeProject;
import entity.Project;
import manager.ManagerHelper;
import manager.Reply;

@Path("project")
public class ProjectService {

	@GET
	@Path("get")
	public Project getProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().get(id);

	}

	@GET
	@Path("getByProjectName")
	public List<Project> getByName(@QueryParam("projectname") String projectname) {
		return ManagerHelper.getProjectManager().getByProjectName(projectname);
	}

	@GET
	@Path("getall")
	public List<Project> getAllProjects() {

		return ManagerHelper.getProjectManager().getAllProjects();

	}

	@GET
	@Path("createproject")
	public Project createProject(@QueryParam("projectname") String projectname, @QueryParam("customer") int customer,
			@QueryParam("customerprojectmanager") String customerprojectmanager,
			@QueryParam("projectmanageremail") String projectmanageremail,
			@QueryParam("projectmanagerphone") String projectmanagerphone, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate) {
		return ManagerHelper.getProjectManager().createProject(projectname, customer, customerprojectmanager,
				projectmanageremail, projectmanagerphone, startdate, enddate);

	}

	@GET
	@Path("updateProjectoject")
	public Reply updateProjectoject(@QueryParam("id") int id, @QueryParam("projectname") String projectname,
			@QueryParam("customer") int customer, @QueryParam("customerprojectmanager") String customerprojectmanager,
			@QueryParam("projectmanageremail") String projectmanageremail,
			@QueryParam("projectmanagerphone") String projectmanagerphone, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate) {
		return ManagerHelper.getProjectManager().updateProject(id, projectname, customer, customerprojectmanager,
				projectmanageremail, projectmanagerphone, startdate, enddate);

	}

	@GET
	@Path("getProjectsAboutToFinish")
	public List<Project> getProjectsAboutToFinish() {
		return ManagerHelper.getProjectManager().getProjectsAboutToFinish();

	}
	/*
	 * @GET
	 * 
	 * @Path("GetActiveProjectsByCustomer") public List<Project>
	 * GetCustomerActiveProjects(@QueryParam("customer")int customer){ return
	 * ManagerHelper.getProjectManager().GetActiveProjectsByCustomer(customer);
	 * }
	 */

	@GET
	@Path("GetActiveProjectsByCustomer2")
	public List<Project> GetCustomerActiveProjects2(@QueryParam("userId") int userId) {
		return ManagerHelper.getProjectManager().GetActiveProjectsByCustomer2(userId);
	}
	@GET
	@Path("GetProjectsByCustomer")
	public List<Project> GetProjectsByCustomer(@QueryParam("userId") int userId) {
		return ManagerHelper.getProjectManager().GetProjectsByCustomer(userId);
	} 
	@GET
	@Path("GetAllActiveProjects")
	public List<Project> GetAllActiveProjects() {
		return ManagerHelper.getProjectManager().GetAllActiveProjects();
	}

	@GET
	@Path("deleteProject")
	public Reply deleteProject(@QueryParam("id") int id) {
		return ManagerHelper.getProjectManager().deleteProject(id);
	}

	 @GET
		@Path("getProjectsByEmployee")
		public List<Project> getProjectsByEmployee(@QueryParam("userId") int userId) {
	     return ManagerHelper.getProjectManager().getProjectsByEmployee(userId);
			}

}
/*
 * @GET
 * 
 * @Path("popular") public List<Project> getPopularCustomers(){ return
 * ManagerHelper.getProjectManager().getPopularCustomers(); }
 */