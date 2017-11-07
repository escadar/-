package manager;

import java.util.List;


import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.EntityManagerImpl;

import entity.Customer;

import entity.User;

public class CustomerManager {

	private final EntityManager entityManager;

	public CustomerManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(Customer customer) {
		entityManager.getTransaction().begin();
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}

	public void create(Customer custumer) {
		entityManager.getTransaction().begin();
		entityManager.persist(custumer);
		entityManager.getTransaction().commit();
	}

	public void delete(Customer custumer) {
		entityManager.getTransaction().begin();
		entityManager.remove(custumer);
		entityManager.getTransaction().commit();
	}

	public Customer get(Integer id) {
		return entityManager.find(Customer.class, id);
	}

	public List<Customer> getByCompanyName(String companyname) {
		String sql = "select * from customer where companyname like'";
		return (List) entityManager.createNativeQuery(sql + companyname + "'", Customer.class).getResultList();
	}

	public List<Customer> getCustomerByStatus() {
		System.out.println("getCustomerByStatus");
		String sql = "select c.id, c.companyname,c.companynumber,c.email,c.phone,c.contactname, "
				+ " true as 'isActive' from customer c where(  "
				+ " select count(p.id) from project p where p.customer = c.id "
				+ "  and (current_date() between p.startdate and p.enddate)> 0 )  ";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

	
	public Reply deleteCustomer(int id) {
		try {

			entityManager.getTransaction().begin();
			Customer customer = get(id);
			entityManager.remove(customer);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply r = new Reply();
			r.setId(Reply.FAIL_ID);
			r.setMsg(e.getMessage());
			return r;
		}
	}

	public Customer createCustomer(String companyname, String companynumber, String contactname, String email,
			String phone, String username, String password) {

		User user2 = new User();
		user2.setPassword(password);
		user2.setUsername(username);
		user2.setType("c");
		
		ManagerHelper.getUserManager().create(user2);
		
		Customer customer = new Customer(companyname, companynumber, contactname, email, phone, user2);
        entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();

		return customer;

	}

	
	  public Reply updateCustomer(int id,String companyname, String
	  companynumber, String contactname, String email, String phone) {
	  
	  Customer customer = new Customer(id,companyname,companynumber,contactname,email,phone);
	  
	  ManagerHelper.getCustomerManager().update(customer);
	  
	  return new Reply(); 
	  }
	 
	public List<Customer> getPopularCustomers() {
		String sql = "SELECT customer.companyname,project.projectname,COUNT(customer.id) from customer  "
				+ "inner join project  on customer.id=project.customer" + "group by  customer.companyname";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();

	}

	public List<Customer> getAllCostomers() {
		String sql = " SELECT  * FROM customer ";
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

	public List<Customer> popularCustomers() {
		String sql = " SELECT c.companyname,c.companynumber,c.contactname,c.contactname,c.email,c.phone, "
				+ " COUNT(c.id)  as 'projectCount' ,c.id,  c.companyname from customer c "
				+ " inner join project p on c.id=p.customer " 
				+" group by c.companyname"
				+ "  having count(c.id) > 3 ";
		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}
	public List<Customer> getActiveCustomers() {
		System.out.println("getActiveCustomers");
		String sql = "select c.id, c.companyname,c.companynumber,c.email,c.phone,c.contactname "
				+ "from customer c where (select count(p.id) from  project p where  p.customer = c.id "
				+ " and (current_date() < p.enddate))  ";

		return (List) entityManager.createNativeQuery(sql, Customer.class).getResultList();
	}

}
/*
 * public List<Customer> getActiveCustomers(){
 * System.out.println("getActiveCustomers"); String sql =
 * "select c.id, c.companyname," + "(select count(p.id) from project p" +
 * " where p.customer = c.id" +
 * " and (current_date() between p.startdate and p.enddate)" +
 * ")>0 as isActive from customer c"; return (List)entityManager.
 * createNativeQuery(sql,Customer.class).getResultList(); }
 */