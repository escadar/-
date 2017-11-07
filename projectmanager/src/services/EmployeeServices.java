package services;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;
import entity.Employee;
import jdk.nashorn.internal.objects.annotations.Getter;
import manager.ManagerHelper;
import manager.Reply;



@Path("/employee")
public class EmployeeServices {

	

	@GET
	@Path("get")
	public Employee get(@QueryParam("id") int id) {
		return ManagerHelper.getEmployeeManager().get(id);

	}

	@GET
	@Path("getByName")
	public List<Employee> getByName(@QueryParam("firstname") String firstname) {
		return ManagerHelper.getEmployeeManager().getByName(firstname);
	}
	@GET
	@Path("getByLastName")
	public List<Employee> getByLastName(@QueryParam("lastname") String lastname) {
		return ManagerHelper.getEmployeeManager().getByLastName(lastname);
	}
	
	@GET
	@Path("delete")
	public Reply deleteEmployee(@QueryParam("id")int id){
		 return ManagerHelper.getEmployeeManager().deleteEmployee(id); 
		
	}
	@GET
	@Path("getAllEmployees")
	public List<Employee> getAllEmployees() {
		return ManagerHelper.getEmployeeManager().getAllEmployees();

	}
	@GET
	@Path("createEmployee")
	public Employee createEmployee(@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,
			@QueryParam("email")String email,@QueryParam("phone")String phone,
			@QueryParam("username") String username,@QueryParam("password") String password){
				return ManagerHelper.getEmployeeManager().createEmployee(firstname,lastname, 
						email, phone, username,password);
	}
	 
	@GET
	@Path("updateEmployee")
	public Employee updateEmployee(@QueryParam("id")int  id,@QueryParam("firstname")String firstname,
			@QueryParam("lastname")String lastname,
			@QueryParam("email")String email,@QueryParam("phone")String phone){
				return ManagerHelper.getEmployeeManager().updateEmployee(id,firstname,lastname, 
						email, phone);
	}
	
}
