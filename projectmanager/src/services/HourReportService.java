

package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;


import entity.HourReport;
import manager.ManagerHelper;
import manager.Reply;

@Path("hourreport")
public class HourReportService {

	@GET
	@Path("get")
	public HourReport getHourReport(@QueryParam("id") int id) {
		return  ManagerHelper.getHourReportManager().get(id);

	}

	@GET
	@Path("getAllHourReports")
	public List<HourReport> getAllHourReports(){
		return  ManagerHelper.getHourReportManager().getAllHourReports();
	}
	@GET
	@Path("getProjecteports")
	public List<HourReport> getAllProjecteports(@QueryParam("project") int project){
		return  ManagerHelper.getHourReportManager().getAllProjecteports(project);
	}

	/*@GET
	@Path("getreports")
	public List<HourReport> getHourReports
	(@QueryParam("projectid") int projectid,
	@QueryParam("yearAndMonth")String yearAndMonth,
	@QueryParam("employee")int employee,
	@QueryParam("customerid")int customerid){
 
		return ManagerHelper.getHourReportManager().getHourReports(projectid, yearAndMonth,
				employee,customerid);
			
}*/
	@GET
	@Path("createHourReport")
	public HourReport   createHourReport(@QueryParam("employee")int employee,
			@QueryParam("project")int project,
			  @QueryParam("date")String date,
			 @QueryParam("starttime")String starttime,
	          @QueryParam("endtime")String endtime,
	          @QueryParam("description")String description){
		return ManagerHelper.getHourReportManager().createHourReport(employee,project,date,starttime, 
				endtime,description);
		
		
} 
	@GET
	@Path("updateHourReport")
	public Reply updateHourReport(@QueryParam("id") int id,
			@QueryParam("employee") int employee,
			@QueryParam("project")int project,
			@QueryParam("date")String date,
			 @QueryParam("starttime")String starttime,
	        @QueryParam("endtime")String endtime, 
	        @QueryParam("description")String description) {
		
		return  ManagerHelper.getHourReportManager().updateHourReport(id,employee, project,date,starttime, 
				endtime, description);
	}
	@GET
	@Path("delete")
	public Reply deleteHourReport(@QueryParam("id")int id){
		
		return ManagerHelper.getHourReportManager().deleteHourReport(id);
		
	}
	@GET
	@Path("getWeeklyHourReport")
	public List<HourReport> getWeeklyHourReport(){
		return ManagerHelper.getHourReportManager().getWeeklyHourReport();
	}
	@GET
	@Path("getWeeklyEmployeeReport")
	public List<HourReport> getWeeklyEmployeeReport(@QueryParam("userId")int userId){
		return ManagerHelper.getHourReportManager().getWeeklyEmployeeReport(userId);
	}
	
	@GET
	@Path("getproject")
	public List<HourReport> getproject(@QueryParam("customer")int  customer){
		return ManagerHelper.getHourReportManager().getproject(customer);
	}
	@GET
	@Path("ProjectHourReport")
	public List<HourReport> ProjectHourReport(@QueryParam("userId")int  userId){
		return ManagerHelper.getHourReportManager().ProjectHourReport(userId);
	}
	
	/*@GET
	@Path("getProjectReport")
	public List<HourReport> getProjectReport(@QueryParam("project")int  project){
		return ManagerHelper.getHourReportManager().getProjectReport(project);
	}*/
	@GET
	@Path("getAllEmployeeReport")
	public List<HourReport> getAllEmployeeReport(@QueryParam("userId")int userId){
		return ManagerHelper.getHourReportManager().getAllEmployeeReport(userId);
	}
	@GET
	@Path("getHourReportsByYearAndMonth")
	public List<HourReport> getHourReportsByYearAndMonth(
			@QueryParam("yearAndMonth")String yearAndMonth,
			@QueryParam("employee")int employee,
			@QueryParam("project")int project,
			@QueryParam("customer")int customer){
		return ManagerHelper.getHourReportManager().getHourReportsByYearAndMonth(yearAndMonth, employee, project, customer);

	}
	
	
	@GET
	@Path("getHourReportsByYearAndMonthOfCustomer")
	public List<HourReport> getHourReportsByYearAndMonthOfCustomer(
			@QueryParam("yearAndMonth")String yearAndMonth,
			@QueryParam("project")int project,
			@QueryParam("userId")int userId){
		return ManagerHelper.getHourReportManager().getHourReportsByYearAndMonthOfCustomer(yearAndMonth, project, userId);
	}
	

	@GET
	@Path("getHourReportsByYearAndMonthOfEmployee")
	public List<HourReport> getHourReportsByYearAndMonthOfEmployee(
			@QueryParam("yearAndMonth")String yearAndMonth,
			@QueryParam("project")int project,
			@QueryParam("userId")int userId){
		return ManagerHelper.getHourReportManager().getHourReportsByYearAndMonthOfEmployee(yearAndMonth, project, userId);
	}

	@GET
	@Path("my")
	public List<HourReport> getit(){
		return ManagerHelper.getHourReportManager().getit();
	}
}

	
 