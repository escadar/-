package manager;


import java.util.List;

import javax.persistence.EntityManager;


import org.apache.openjpa.persistence.EntityManagerImpl;



import entity.Customer;

import entity.Project;

public class ProjectManager {

	private final EntityManager entityManager;

	public ProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Project project) {
		entityManager.getTransaction().begin();
		entityManager.merge(project);
		entityManager.getTransaction().commit();
	}

	public void create(Project project) {
		entityManager.getTransaction().begin();
		entityManager.persist(project);
		entityManager.getTransaction().commit();
	}

	public void delete(Project project) {
		entityManager.getTransaction().begin();
		entityManager.remove(project);
		entityManager.getTransaction().commit();
	}

	public Project get(Integer id) {
		return entityManager.find(Project.class, id);
	}

	public List<Project> getByProjectName(String projectName) {
		String sql = "select * from project where projectName like '";
		return (List) entityManager.createNativeQuery(sql + projectName + "'", Project.class).getResultList();
	}

	public List<Project> getAllProjects() {
		String sql = "SELECT * from project";
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();
	}

	public Project createProject(String projectname, int customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerphone, String startdate, String enddate) {
		try {
			Customer c = ManagerHelper.getCustomerManager().get(customer);

			Project project = new Project(projectname, c, customerprojectmanager, projectmanageremail,
					projectmanagerphone, startdate, enddate);
			create(project);
			entityManager.getTransaction().begin();
			entityManager.persist(project);
			entityManager.getTransaction().commit();

			return new Project();
		} catch (Exception e) {

			Project newproject = new Project();
			return newproject;

		}

	}

	public Reply updateProject(int id, String projectname, int customer, String customerprojectmanager,
			String projectmanageremail, String projectmanagerphone, String startdate, String enddate) {

		Customer c = ManagerHelper.getCustomerManager().get(customer);
		Project project = new Project(id, projectname, c, customerprojectmanager, projectmanageremail,
				projectmanagerphone, startdate, enddate);
		ManagerHelper.getProjectManager().update(project);
		System.out.println("updateProject");
		return new Reply();
	}

	public Reply deleteProject(int id) {
		try {
			Project project = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(project);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return new Reply();
		}
	}

	public List<Project> getProjectsAboutToFinish() {
		System.out.println("getProjectsAboutToFinish");
		String sql = "SELECT * from project where   enddate between now() "
				+ " and date_add(now(),interval 30 day )";
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();
	}

	public List<Project> GetActiveProjectsByCustomer2(int userId) {
		String sql = "SELECT  c.companyname,p.id,c.companyname,projectname, "
				+ "	 customerprojectmanager, projectmanageremail, "
				+ " projectmanageremail,projectmanagerphone,startdate,enddate  "
				+ " from project  p inner join customer  c on   c.id=p.customer "
				+ "inner join user u on u.id = c.user  where enddate > current_date() and u.id=  '  ";
		System.out.println("GetActiveProjectsByCustomer2");
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql + userId + "'", Project.class).getResultList();
	}
	public List<Project> GetProjectsByCustomer(int userId) {
		String sql = "SELECT  c.companyname,p.id,c.companyname,projectname, "
				+ "	 customerprojectmanager, projectmanageremail, "
				+ " projectmanageremail,projectmanagerphone,startdate,enddate  "
				+ " from project  p "
				+ "inner join customer  c on   c.id=p.customer "
				+ "inner join user u on u.id = c.user  where enddate > current_date() and u.id=  '  ";
		System.out.println(sql);
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql + userId + "'", Project.class).getResultList();
	} 

	/*
	 * public List<Project> GetActiveProjectsByCustomer(int id){ String sql =
	 * "select c.id, c.companyname,c.companynumber,c.email,c.phone,c.contactname, "
	 * + " true as 'isactive' from customer c where( "
	 * +" select count(p.id) from project p where p.customer = c.id " +
	 * " and (current_date() between p.startdate and p.enddate)> 0 ) and  c.id = ' "
	 * ; return (List)entityManager. createNativeQuery(sql
	 * +id+"'",Project.class).getResultList(); }
	 */

	public List<Project> GetAllActiveProjects() {

		String sql = "SELECT customer.companyname,project.id,customer.companyname,projectname,"
				+ "customerprojectmanager," + "customerprojectmanager,projectmanageremail,"
				+ " projectmanageremail,projectmanagerphone,startdate,enddate "
				+ " from project inner join customer on customer.id=project.customer "
				+ "  where enddate > current_date()";

		return (List) entityManager.createNativeQuery(sql, Project.class).getResultList();
	}

	public List<Project> getProjectsByEmployee(int userid) {
		String sql = "SELECT p.id, p.projectname FROM employeeproject ep"
				+ " inner join employee e on e.id = ep.employee"
				+ " inner join project p on p.id = ep.project"
				+ " inner join user u on u.id = e.user"
				+ " where u.id = ";
		String sql1 = " group by p.projectname"
				+ " order by p.id asc";
		return (List) entityManager.createNativeQuery(sql + userid + sql1, Project.class).getResultList();
	}

}
    
