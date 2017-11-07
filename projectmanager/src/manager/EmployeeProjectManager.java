package manager;

import java.util.List;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Employee;
import entity.EmployeeProject;
import entity.HourReport;
import entity.Project;

public class EmployeeProjectManager {

	private final EntityManager entityManager;

	public EmployeeProjectManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.merge(employeeProject);
		entityManager.getTransaction().commit();
	}

	public void create(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();

		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();

	}

	public void delete(EmployeeProject employeeProject) {
		entityManager.getTransaction().begin();
		entityManager.remove(employeeProject);
		entityManager.getTransaction().commit();
	}

	public EmployeeProject get(Integer id) {
		return entityManager.find(EmployeeProject.class, id);
	}

	public Reply deleteEmployeeProject(int id) {
		try {
			EmployeeProject employeeProject = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(employeeProject);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return new Reply();
		}
	}

	public EmployeeProject createEmployeeProject(int project, int employee) {

		
		Project p = ManagerHelper.getProjectManager().get(project);
		Employee e = ManagerHelper.getEmployeeManager().get(employee);

		EmployeeProject employeeProject = new EmployeeProject(p, e);
		entityManager.getTransaction().begin();
		entityManager.persist(employeeProject);
		entityManager.getTransaction().commit();
		
		return employeeProject;
	}

	public List<EmployeeProject> getProjectsByEmployee(int userId) {
		String sql = "select  emp.employee, e.firstname, p.projectname, u.id  from employeeproject emp"
				+ " inner join employee e on e.id= emp.employee inner join project  p on  p.id=emp.project"
				+ " inner join user u on u.id = e.user  where u.id= ' ";
		String sql1 = " group by p.projectname ";
		System.out.println(sql + userId + " " + sql1);
		return (List<EmployeeProject>) entityManager
				.createNativeQuery(sql + userId + "'" + " " + sql1, EmployeeProjectManager.class).getResultList();
	}

}
