package services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import manager.PropsHelper;
import manager.PropsServlet;

@Path("managerSettings")
public class SettingService {
	
	@GET
	@Path("getHours")
	public String getHours(){
		String hours = PropsHelper.get("hours");
		return hours;
	}
	
	@GET
	@Path("setHours")
	public String setHours(@QueryParam("starttime") String starttime,@QueryParam("endtime") String endtime){
		String val = starttime +","+ endtime ;
		PropsHelper.set("hours", val);
		return val;
	}
	
	@GET
	@Path("getDays")
	public String getDays(){
		String days = PropsHelper.get("days");
		return days;
	}
	@GET
	@Path("setDays")
	public String setDays(@QueryParam("days") String days){
		System.out.println(days); 
		PropsHelper.set("days", days);
		return days;
	}

}
