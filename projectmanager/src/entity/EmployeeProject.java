package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class EmployeeProject {
	  @Id 
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
      @ManyToOne
	  @JoinColumn(name="employee")
	  private Employee  employee;
	  
	 @ManyToOne
	  @JoinColumn (name="project")
	  private Project project;

	 
	 
	 public EmployeeProject (){
		 
		 
	 }
 public EmployeeProject (Project project ,Employee employee){
		 this.project=project;
		 this.employee=employee;
		 
	 } 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	  

	  

  

	
}
