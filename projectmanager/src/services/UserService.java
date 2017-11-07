package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import entity.HourReport;
import entity.User;
import manager.ManagerHelper;
import manager.Reply;

@Path("/user")
public class UserService {
	@GET
	@Path("/get")
	public User getCostomer(@QueryParam("id") int id) {
		return ManagerHelper.getUserManager().get(id);

	}

	@GET
	@Path("getall")
	public List<User> getAllUsers() {
		return ManagerHelper.getUserManager().getAllUsers();
	}

	@GET
	@Path("getuser")
	public User getUser(@QueryParam("username") String username, @QueryParam("password") String password) {
		return (User) ManagerHelper.getUserManager().getUser(username, password);

	}

	@GET
	@Path("sendPassword")
	public User sendPassword(@QueryParam("username") String username) {
		return (User) ManagerHelper.getUserManager().sendPassword(username);

	}

	@GET
	@Path("deleteUser")
	public Reply deleteUser(@QueryParam("id") int id) {
		return (Reply) ManagerHelper.getUserManager().deleteUser(id);

	}
	@GET
	@Path("forgotPassword")
	public Reply forgotPassword(@QueryParam("username") String username) {
		return  ManagerHelper.getUserManager().forgotPassword(username);
	}

	/*
	 * @GET
	 * 
	 * @Path("createUser") public User createUser(@QueryParam("id") int
	 * id,@QueryParam("username") String username,@QueryParam("password")String
	 * password,
	 * 
	 * @QueryParam("type")String type){
	 * 
	 * 
	 * return ManagerHelper.getUserManager().createUser(id, username,
	 * password,type); }
	 */
}
