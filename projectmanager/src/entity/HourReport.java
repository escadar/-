package entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class HourReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  id;
	
	@ManyToOne
	@JoinColumn (name="employee")
	 private Employee employee;
	
	@ManyToOne
	@JoinColumn (name="project")
	private Project project;
	private String date;
	private String starttime;
	private String endtime;
	private String description;

	public HourReport() {
	}

	public HourReport(Employee employee, Project project,String date, String starttime, String endtime, String description) {
	 this.employee=employee;
		this.project = project;
		this.date=date;
		this.starttime = starttime;
		this.endtime = endtime;
		this.description = description;

	}

	public HourReport(int id,Employee employee, Project project, String date,String starttime, String endtime, String description) {
		 this.employee=employee;
		this.project = project;
		this.date=date;
		this.starttime = starttime;
		this.endtime = endtime;
		this.description = description;
		this.id=id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public HourReport(Project project, String starttime, String endtime, String description) {
		this.project = project;
		this.starttime = starttime;
		this.endtime = endtime;
		this.description = description;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getstarttime() {
		return starttime;
	}

	public void setstarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getendtime() {
		return endtime;
	}

	public void setendtime(String endtime) {
		this.endtime = endtime;
	}

	
}
