package services;

import java.util.List;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.Customer;

import manager.ManagerHelper;
import manager.Reply;

@Path("/customer")
public class CustomerService {
	
		@GET
		@Path("/get")
		public Customer getCostomer(@QueryParam("id") int id) {
			return  ManagerHelper.getCustomerManager().get(id);

		}

		@GET
		@Path("getall")
		public List<Customer>  getAllCostomers() {
			return  ManagerHelper.getCustomerManager().getAllCostomers();
		}

		@GET
		@Path("getByCompanyName")
		public List<Customer> getByName(@QueryParam("companyname") String companyname) {
			return ManagerHelper.getCustomerManager().getByCompanyName(companyname);
			
		}
		@GET
		@Path("getCustomerByStatus")
		public List<Customer> getCustomerByStatus(@QueryParam("isActive")boolean isActive) {
			if(isActive == true){
				return ManagerHelper.getCustomerManager().getCustomerByStatus();
			}else{
				return ManagerHelper.getCustomerManager().getActiveCustomers();
			}
			
		}
		
		/*@GET
		@Path("updateCustomer")
		public String  updateCustomer(@QueryParam("id")int id,@QueryParam("companyname")String 
				companyname,@QueryParam("companynumer") String companynumber, @QueryParam("contactname")
		String contactname, @QueryParam("email")String email,@QueryParam("phone") String phone,@QueryParam("String")
				String username,@QueryParam("String") String password){
			
			return  ManagerHelper.getCustomerManager().updateCustomer(id,companyname,companynumber,contactname,email,phone,username,password);
	}*/
		@GET
		@Path("deleteCustomer")
		public Reply  deleteCustomer(@QueryParam("id")int id){
			
			return  ManagerHelper.getCustomerManager().deleteCustomer(id);
			
		}
 
		@GET
		@Path("create")
		public Customer createCustomer(@QueryParam("companyname")String companyname,
				@QueryParam("companynumber")String companynumber,
				@QueryParam("contactname")String contactname,@QueryParam("email")String email,
				@QueryParam("phone") String phone,
				@QueryParam("username") String username,
				@QueryParam("password") String password){
					return ManagerHelper.getCustomerManager().createCustomer(companyname, companynumber, 
							contactname, email, phone, username,password);
		}
		@GET
		@Path("popularCustomer")
		public List<Customer> popularCustomers(){
			return ManagerHelper.getCustomerManager().popularCustomers();
		}
 
		

/*@GET
		@Path("popularCustomer")
		public List<Customer> getPopularCustomers(){
			return ManagerHelper.getCustomerManager().getPopularCustomers();
		}
		@GET
		@Path("CreateCustomer")
		public String CreateCustomer(@QueryParam("companyname") String companyname,
				@QueryParam("companynumber") String companynumber, @QueryParam("contactname") String contactname,
				@QueryParam("email") String email, @QueryParam("phone") String phone,@QueryParam("user") int user) {
			return ManagerHelper.getCustomerManager().CreateCustomer(companyname, companynumber, contactname, email, phone,user);
		}
		
		*/

		 @GET
		@Path("UpdateCustomer")
		public Reply UpdateCustomer(@QueryParam("id") int id,@QueryParam("companyname") String companyname,
				@QueryParam("companynumber") String companynumber, @QueryParam("contactname") String contactname,
				@QueryParam("email") String email, @QueryParam("phone") String phone) {
			return ManagerHelper.getCustomerManager().updateCustomer(id, companyname, companynumber, contactname, email, phone);
		}
		}
		 
		
		
 