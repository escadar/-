package manager;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;
import entity.Employee;
import entity.User;

public class EmployeeManager {

	private final EntityManager entityManager;

	public EmployeeManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Employee employee) {
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
	}

	public void create(Employee employee) {

		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();

	}

	public Employee get(Integer id) {
		System.out.println("employeeee----" + id);
		return entityManager.find(Employee.class, id);
	}

	public List<Employee> getByName(String firstname) {
		String sql = "select * from employee where firstname like '";
		return (List) entityManager.createNativeQuery(sql + firstname + "'", Employee.class).getResultList();
	}

	public List<Employee> getByLastName(String lastname) {
		String sql = "select * from employee where laststname like '";
		return (List) entityManager.createNativeQuery(sql + lastname + "'", Employee.class).getResultList();
	}

	public Reply deleteEmployee(int id) {
		System.out.println("employee" + id);
		try {
			Employee employee = get(id);
			System.out.println(employee);
			entityManager.getTransaction().begin();
			entityManager.remove(employee);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (TransactionRequiredException e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return new Reply();
		}
	}

	public List<Employee> getAllEmployees() {
		String sql = "select id, firstname,lastname,email,phone,user "

				+ " from employee ";

		// System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, Employee.class).getResultList();
	}

	public Employee createEmployee(String firstname, String lastname, String email, String phone, String username,
			String password) {

		User user = new User();
		user.setPassword(password);
		user.setUsername(username);
		user.setType("e");
		ManagerHelper.getUserManager().create(user);

		Employee employee = new Employee(firstname, lastname, email, phone, user);
		entityManager.getTransaction().begin();
		entityManager.persist(employee);
		entityManager.getTransaction().commit();
		return employee;
	}

	public Employee updateEmployee(int id, String firstname, String lastname, String email, String phone) {

		Employee employee = new Employee(id, firstname, lastname, email, phone);
		entityManager.getTransaction().begin();
		entityManager.merge(employee);
		entityManager.getTransaction().commit();
		return employee;
	}
	/*
	 * public Reply updateEmployee(String companyname, String companynumber,
	 * String contactname, String email, String phone, int user) { Customer
	 * customer = new Customer(companyname, companynumber, contactname, email,
	 * phone, user); ManagerHelper.getEmployeeManager().update(customer);
	 * 
	 * return new Reply(); }
	 */

}
