package manager;

import entity.Customer;
import entity.Employee;
import entity.User;

import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;

import org.apache.openjpa.persistence.EntityManagerImpl;

public class UserManager {

	private final EntityManager entityManager;

	public UserManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true); 
	}

	public void update(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	public void create(User user) {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
	}

	public void delete(User user) {
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	public User get(Integer id) {
		return entityManager.find(User.class, id);
	}

	public List<User> getAllUsers() {
		String sql = " SELECT * from user ";
		return (List) entityManager.createNativeQuery(sql, User.class).getResultList();
	}

	public User getUser(String username,String password) {
		try{
			 String sql = " SELECT  * from user"
	                  +" where username ='"+username+"' and password ='"+password+"' ";
			 System.out.println(sql);
			 return (User)entityManager.
						createNativeQuery(sql, User.class).getSingleResult();
			}catch (Exception e ){
				return null;
				
			}
	}

	
	public User  sendPassword(String username ){
		String sql = "SELECT id,password,username,type from user where username like '";
		return (User)entityManager.
				createNativeQuery(sql+username+"'", User.class).getSingleResult();	
		 
	}
	
	
public User createUser(int id, String username, String password, String type) {
			User user = new User ( id, username,password,type);
			 ManagerHelper.getUserManager().create(user);
		return user;
	}
public Reply deleteUser(int id) {
	System.out.println("user" + id);
	try {
		User user = get(id);
		System.out.println(user);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
		return new Reply();
	} catch (TransactionRequiredException  e) {
		Reply r = new Reply();
		r.setId(Reply.FAIL_ID);
		r.setMsg(e.getMessage());
		return new Reply();
	}
}

public Reply forgotPassword(String username) {
	
	String sql ="select * from projectmanager.user  where username = '"+username+"' ";
	User user = (User)entityManager.createNativeQuery(sql,User.class).getSingleResult();
	
			
	if(user.getType().equals("employee")){
		
		String sqlEmployeeForget = "select * from projectmanager.employee e inner join user u on e.user=u.id where u.username ='"+username+"' ";
		
		Employee employee = (Employee) entityManager.createNativeQuery(sqlEmployeeForget,Employee.class).getSingleResult();
		
		try{
			MailHelper.sendMail(employee.getEmail(),"Password To Hourreport Site",
			"User Name : " + user.getUsername() + " , " + " Password :" +user.getPassword());
			
		}catch (MessagingException e){
			e.getMessage();
		}
		}else if (user.getType().equals("customer")) {
			
			String customerForgot = "select * from projectmanager.customer c inner join user u on c.user=u.id " + "where u.username ='"+username+"' ";
			
			Customer customer = (Customer) entityManager.createNativeQuery(customerForgot,Customer.class).getSingleResult();
		
		try{
			
			MailHelper.sendMail(customer.getEmail(), ("Password To Hourreport Site"),
			"User Name : " + user.getUsername() + " , " + " Password :" +user.getPassword());
		}catch (MessagingException e){
			e.getMessage();
		}
		}else{
			try{
				MailHelper.sendMail("escadar1610@gmail.com","Password To Hourreport Site",
				user.getUsername() + " , " + user.getPassword());
			}catch (MessagingException e){
				e.getMessage();
			}
		}
				
	return new Reply();


}

}
