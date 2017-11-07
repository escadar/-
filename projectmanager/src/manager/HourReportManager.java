package manager;

import java.util.List;


import javax.persistence.EntityManager;


import entity.Employee;
import entity.HourReport;
import entity.Project;

import org.apache.openjpa.persistence.EntityManagerImpl;

public class HourReportManager {

	private final EntityManager entityManager;

	public HourReportManager(EntityManager entityManager) {
		this.entityManager = entityManager;
		((EntityManagerImpl) this.entityManager).getBroker().setAllowReferenceToSiblingContext(true);
	}

	public void update(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.merge(hourReport);
		entityManager.getTransaction().commit();
	}

	public void create(HourReport hourReport) {
		entityManager.getTransaction().begin();

		entityManager.persist(hourReport);
		entityManager.getTransaction().commit();

	}

	public void delete(HourReport hourReport) {
		entityManager.getTransaction().begin();
		entityManager.remove(hourReport);
		entityManager.getTransaction().commit();
	}

	public HourReport get(Integer id) {
		return entityManager.find(HourReport.class, id);
	}

	public List<HourReport> getAllHourReports() {
		System.out.println("getAllHourReports");
		String sql = "SELECT h.id, e.firstname,p.projectname,"
				+ "  h.description, date_format(h.date, '%Y-%m-%d') "
				+ "as 'date', date_format(h.starttime, '%H:%i')"
				+ " starttime, date_format(h.endtime, '%H:%i')"
				+ " endtime from hourreport h inner join"
				+ " employee e on e.id = h.employee inner join project p"
				+ " on p.id = h.project inner join customer c on c.id = p.customer ";

		System.out.println(sql);
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}

	public List<HourReport> getAllProjecteports(int project) {

		String sql = "SELECT h.id, e.firstname, p.projectname, h.starttime, p.id,"
				+ " h.endtime, h.description FROM hourreport h    FROM hourreport  where p.id = ' ";

		return (List) entityManager.createNativeQuery(sql + project + "'", HourReport.class).getResultList();
	}

	public HourReport createHourReport(int employee, int project, String date, String starttime, String endtime,
			String description) {

		System.out.println("createHourReport" + employee);
		System.out.println("project" + project);
		System.out.println("date" + date);
		System.out.println("starttime" + starttime);
		System.out.println("endtime" + endtime);
		System.out.println("description" + description);

		String starting = date + " " + starttime;
		String ending = date + " " + endtime;

		System.out.println("starttime----" + starttime + " " + "endtime----" + endtime);
		System.out.println("The id of employee from client: " + employee);

		String sql = "select * from employee e " + "inner join user u on e.user = u.id " + "where u.id =" + employee;
		System.out.println("the user id " + "" + sql);

		try {

			Employee e = (Employee) entityManager.createNativeQuery(sql, Employee.class).getSingleResult();
			Project p = ManagerHelper.getProjectManager().get(project);

			HourReport hourReport = new HourReport(e, p,date, starting, ending, description);

			entityManager.getTransaction().begin();
			entityManager.persist(hourReport);
			entityManager.getTransaction().commit();

			return hourReport;
		} catch (Exception e) {
			e.printStackTrace();

			return new HourReport();

		}
	}

	
	public Reply updateHourReport(int id, int employee, int project, String date,String starttime, String endtime,
			String description) {

		Employee e = ManagerHelper.getEmployeeManager().get(employee);
		Project p = ManagerHelper.getProjectManager().get(project);

		HourReport hourReport = new HourReport(id, e, p, date,starttime, endtime, description);
		ManagerHelper.getHourReportManager().update(hourReport);
		return new Reply();
	}

	public Reply deleteHourReport(int id) {
		try {
			HourReport hourReport = get(id);
			entityManager.getTransaction().begin();
			entityManager.remove(hourReport);
			entityManager.getTransaction().commit();
			return new Reply();
		} catch (Exception e) {
			Reply reply = new Reply();
			reply.setId(Reply.FAIL_ID);
			reply.setMsg(e.getMessage());
			return reply;
		}
	}

	public List<HourReport> getWeeklyHourReport() {
		String sql = "SELECT * FROM projectmanager.hourreport where endtime >= curdate()"
				+ " - INTERVAL DAYOFWEEK(curdate())+6 DAY "
				+ "AND endtime < curdate() - INTERVAL DAYOFWEEK(curdate())-7 DAY";

		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}

	public List<HourReport> getWeeklyEmployeeReport(int userId) {
		String sql = "select h.id, h.employee, h.project, h.description,"
				+ " substring(h.starttime,1,10) as 'date', date_format(h.starttime, '%H:%i') starttime,"
				+ " date_format(h.endtime, '%H:%i') endtime from projectmanager.hourreport h"
				+ " inner join projectmanager.employee e on e.id = h.employee inner join"
				+ " projectmanager.project p on p.id = h.project inner join projectmanager.user"
				+ " u on u.id= e.user where  h.endtime >= now() - interval 7 day"
				+ " and  h.endtime <= now() + interval  1 day  and u.id =" + userId;
		System.out.println("getWeeklyEmployeeReport" + sql);

		return (List<HourReport>) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}

	public List<HourReport> getproject(int customer) {
		String sql = "SELECT h.employee,p.projectname,h.starttime,h.endtime,h.description,p.id,e.firstname "
				+ " from hourreport h " + " inner join project p on h.project=p.id "
				+ " inner join  customer c  on p.customer= c.id " + "where c.id = ' ";
		return (List) entityManager.createNativeQuery(sql + customer + "'", HourReport.class).getResultList();
	}

	public List<HourReport> ProjectHourReport(int userId) {
		String sql = "SELECT h.employee, h.project, h.starttime, h. endtime, h.description, p.id,c.id FROM projectmanager.hourreport h "
				+ " inner join project p on p.id =h.project  inner join customer  c on c.id = p.customer "
				+ "inner join user u  on u.id=c.user where u.id=" + userId;
		System.out.println("ProjectHourReport" + sql);
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}

	public List<HourReport> getAllEmployeeReport(int userId) {
		String sql = "SELECT  h.id, h.employee, h.description, h.project, "
				+ "date_format(h.date, '%Y-%m-%d') as 'date', date_format(h.starttime, '%H:%i') starttime, date_format(h.endtime, '%H:%i') endtime "
				+ "from projectmanager.hourreport h inner join projectmanager.employee e on e.id = h.employee "
				+ "inner join projectmanager.user  u  on u.id = e.user  where u.id =" + userId;
		System.out.println("getAllEmployeeReport" + sql);
		return (List) entityManager.createNativeQuery(sql, HourReport.class).getResultList();
	}


public List<HourReport> getHourReportsByYearAndMonth(String yearAndMonth, int employee, int project, int customer) {
	
	String sql = "SELECT h.id, e.firstname,p.projectname,"
			+ " h.description, date_format(h.date, '%Y-%m-%d') as 'date',"
			+ " date_format(h.starttime, '%H:%i') starttime, date_format(h.endtime, '%H:%i') endtime"
			+ " from projectmanager.hourreport h inner join projectmanager.employee e on e.id = h.employee"
			+ " inner join projectmanager.project p on p.id = h.project inner join"
			+ " projectmanager.customer c on c.id = p.customer where month(h.starttime)= month('"+yearAndMonth+"-01')"
					+ " and year(h.starttime) = year('"+yearAndMonth+"-01')"; 
			
			if(employee != 0){
				sql += " and h.employee = " + employee;
			}
			
			if( project != 0){
				sql +=" and  p.id =  " + project;
			}
			
			 if(customer != 0){
				sql += " and c.id =  " + customer;
			}
	
			sql += " order by h.starttime desc";
			
						
	return (List)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
}




public List<HourReport> getHourReportsByYearAndMonthOfCustomer(String yearAndMonth, int project,int userId) {
	
	String sql = "SELECT h.id,p.projectname, h.starttime, h.endtime, h.description from hourreport h"
			
			+ " inner join project p on p.id = h.project"
			+ " inner join customer c on c.id = p.customer"
			+ " inner join user u on u.id = c.user"
			 
			+ " where month(h.starttime)= month('"+yearAndMonth+"')"
			+ " and year(h.starttime) = year('"+yearAndMonth+"')"
			+" and u.id= "+userId; 
			
			
			if( project != 0){
				sql +=" and  p.id =  " + project;
			}
			
			 
	
			sql += " order by h.starttime desc";
			
						
	return (List)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
}

public List<HourReport> getHourReportsByYearAndMonthOfEmployee(String yearAndMonth, int project, int userId) {
	 
String sql = "SELECT h.id, e.firstname,p.projectname, h.starttime, h.endtime, h.description from hourreport h"
			+ " inner join employee e on e.id = h.employee"
			+ " inner join project p on p.id = h.project"
			+ " inner join user u on u.id = e.user"
			+ " where month(h.starttime)= month('"+yearAndMonth+"')"
			+ " and year(h.starttime) = year('"+yearAndMonth+"')"
			+" and u.id= "+userId; 
			
			
			if( project != 0){
				sql +=" and  p.id =  " + project;
			}
			
			 
	
			sql += " order by h.starttime desc";
			
						
	return (List)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
}

public List<HourReport> getit() {
	 String sql = "SELECT  h.project, from hourreport h "
             + " inner join customer  ";
	 return (List)entityManager.createNativeQuery(sql, HourReport.class).getResultList();
}
}
