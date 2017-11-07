var userId;
var app = angular.module("myApp", [ "ngRoute" ]);
app.config(function($routeProvider) {
	$routeProvider.when("/ProjectManagement", {
		templateUrl : "ProjectManagement.html",
		controller : "ProjectManagement"

	}).when('/', {
		templateUrl : 'LoginPage.html',
		controller : "loginCtrl"

	}).when('/LogOut', {
		templateUrl : 'LoginPage.html',
		controller : "LogOut"
	}).when("/one", {
		templateUrl : "EmployeeHourReport.html",
		controller : "EmployeeHourReport"

	}).when("/two", {
		templateUrl : "ActiveProjects.html",
		controller : "ActiveProjectsByCustomer"

	}).when("/three", {
		templateUrl : "ProjectManagerList.html",
		controller : "ProjectManagerList"

	})/*.when("/FormEmployeeHourReport", {
		templateUrl : "FormEmployeeHourReport.html",
		controller : "FormEmployeeHourReport"
	})*/.when("/CustomerManagement", {
		templateUrl : "CustomerManagement.html",
		controller : "CustomerManagement"

	}).when("/EmployeeManagement", {
		templateUrl : "EmployeeManagement.html",
		controller : "EmployeeManagement",

	})
	.when("/HourReports", {
		templateUrl : "HourReports.html",
		controller : "HourReports"

	})
	.when("/ProjectHourReport", {
		templateUrl : "ProjectHourReport.html",
		controller : "ProjectHourReport"

	})
});
app.controller("ProjectHourReport", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/hourreport/ProjectHourReport?userId="+userId).then(function(response) {
		$scope.ProjectHourReport= response.data;
		 alert('ProjectHourReport');
		console.log(response.data);

	});
});
app.controller("ActiveProjectsByCustomer",function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/project/GetActiveProjectsByCustomer2?userId="+userId)
			.then(function(response) {
				$scope.ActiveProjectsbyC = response.data;
			});
});



app.controller("LogOut", function($scope) {
	$location.path('/LoginPage');
	$(".firsttr").css("visibility", "hidden");
	$(".secondtr").css("visibility", "hidden");

});

app.controller("ProjectManagement", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/project/getall").then(
			function(response) {
				$scope.Project = response.data;
				
				$http.get("http://localhost/projectmanager/rest/customer/getall").then(
						function(response) {
							$scope.CustomerManagement = response.data;
							console.log(response.data);
						});
				
				$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees").then(
						function(response) {
							$scope.EmployeeManagement = response.data;
							console.log(response.data);
						});
				$scope.addProject = function() {
				 	alert("start of addproject");
				 	
					//console.log("the project: " +userId +" "+$scope.projectname+" "+$scope.startdate+" "+$scope.enddate+" "+$scope.customer+ "" +$scope.customerprojectmanager+ ""+$scope.projectmanageremail+ "" +$scope.projectmanageremail+ "" +$scope.projectmanagerphone+ ""+startdate+ "" +enddate);*/
					alert("middel of addproject");
					var startdate = $('#datepicker2').datepicker({dateFormat: 'yyyy-mm-dd'}).val();
					var enddate = $('#datepicker1').datepicker({dateFormat: 'yyyy-mm-dd'}).val();
					console.log(enddate);
					
		
                $http.get("http://localhost/projectmanager/rest/project/createproject?projectname="+$scope.projectname+"&customer="+$scope.customer+"&customerprojectmanager="+$scope.customerprojectmanager+"&projectmanageremail="+$scope.projectmanageremail+"&projectmanagerphone="+$scope.projectmanagerphone+"&projectmanageremail="+$scope.projectmanageremail+"&startdate="+startdate+"&enddate="+enddate)
					.then(function(response) {
						alert("end of addproject");
					console.log(response.data);

				     	$scope.Project.push({
						projectname : $scope.projectname,
						customer : $scope.customer,
						customerprojectmanager : $scope.customerprojectmanager,
						projectmanageremail : $scope.projectmanageremail,
						projectmanagerphone : $scope.projectmanagerphone,
                        startdate : $scope.startdate,
						enddate : $scope.enddate

					});

				});
				};
				  $scope.removeProject = function(id) {
					$http.get("http://localhost/projectmanager/rest/project/deleteProject?id="+ id).then(function(response) {
						$("#tr-" + id).remove();

					});
				};  
				

			 $scope.updateProject = function(id){
				 var index = getSelectedIndex(id);
				 var x = $scope.Project[index];
				 
				    $scope.id = x.id;
				    $scope.projectname = x.projectname;
				    $scope.customer =x.customer;
					$scope.customerprojectmanager = x.customerprojectmanager;
				    $scope.projectmanageremail =x.projectmanageremail;
					$scope.projectmanagerphone =x.projectmanagerphone ;
					$scope.startdate = x.startdate;
					$scope.enddate = x.enddate;
			 };
			 
			 $scope.saveEditedProject = function (){
				 
				 $http.get("http://localhost/projectmanager/rest/project/updateProjectoject?id="+$scope.id+"&projectname="+$scope.projectname+
						 "&customer="+$scope.customer+"&customerprojectmanager="+$scope.customerprojectmanager+
						 "&projectmanageremail="+$scope.projectmanageremail+"&projectmanagerphone="+$scope.projectmanagerphone+
						 "&startdate="+$scope.startdate+"&enddate="+$scope.enddate)
				 		.then(function(response){  
				 $scope.Project[index].id = $scope.id;
				 $scope.Project[index].projectname = $scope.projectname;
			     $scope.Project[index].customer = $scope.customer;
				 $scope.Project[index].customerprojectmanager = $scope.customerprojectmanager;
			     $scope.Project[index].projectmanageremail = $scope.projectmanageremail;
				 $scope.Project[index].projectmanagerphone = $scope.projectmanagerphone;
				 $scope.Project[index].startdate = $scope.startdate;
				 $scope.Project[index].enddate = $scope.enddate;
				 
				 }); 
			 };
			 

			function getSelectedIndex(id){
				
				for(var i=0; i<$scope.Project.length; i++)    
					if($scope.Project[i].id==id ) 
						return i;
				return -1;
					
		   
			};
		
			});
});

                app.controller("EmployeeHourReport",function($scope, $http) {
                	$http.get("http://localhost/projectmanager/rest/hourreport/getWeeklyEmployeeReport?userId="+userId)
                .then(function(response) {
								$scope.Employee = response.data;
								console.log(response.data);
								
								
									$http.get("http://localhost/projectmanager/rest/project/getall")
											.then(function(response) {
												$scope.Project = response.data;
											});
									$scope.addNewHourReport = function (){
										$http.get("localhost/projectmanager/rest/hourreport/createHourReport?employee="+$scope.employee+"&project="+$scope.project+"&startdate="+$.datepicker.formatDate("yy-mm-dd",$scope.startdate)+"&enddate="+$.datepicker.formatDate("yy-mm-dd",$scope.enddate))
						                .then(function(response) {
										$scope.Employee.push({
											 employee: $scope.employee,
										     project: $scope.project,
											 startdate : $scope.startdate,
											 enddate : $scope.enddate,
											 description : $scope.description 
											
										});
									 });
										
									};
								
							});
				});


app.controller("ProjectManagerList",function($scope, $http) {
					$http.get("http://localhost/projectmanager/rest/project/GetAllActiveProjects")
							.then(function(response) {
								$scope.ActiveProjects = response.data;
							});

					$http.get("http://localhost/projectmanager/rest/customer/popularCustomer")
							.then(function(response) {
								$scope.BigCustomers = response.data;
							});

					$http.get("http://localhost/projectmanager/rest/customer/getCustomerByStatus?isActive=true")
							.then(function(response) {
								$scope.ActiveCustomers = response.data;

							});

					$http.get("http://localhost/projectmanager/rest/project/getProjectsAboutToFinish")
							.then(function(response) {
								$scope.ProjectsAboutToFinish = response.data;

							});
				});
app.controller("HourReports", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/hourreport/getAllHourReports").then(function(response) {
		$scope.HourReports= response.data;

	});
});


app.controller("CustomerManagement", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/customer/getall").then(
			function(response) {
				$scope.CustomerManagement = response.data;
          $scope.deleteCustomer = function(id) {
					$http.get("http://localhost/projectmanager/rest/customer/deleteCustomer?id="+ id).then(function(response) {
						$("#tr-" + id).remove();
					});
				};

				$scope.addCustomer = function() {
					 $http.get("http://localhost/projectmanager/rest/customer/create?companyname="+$scope.companyname+"&companynumber="+$scope.companynumber+"&contactname="+$scope.contactname+"&email="+$scope.email+"&phone="+$scope.phone+"&username="+$scope.username+"&password="+$scope.password)
							.then(function(response) {
								console.log(response.data);
					     $scope.CustomerManagement.push({
					     companyname:$scope.companyname,
					     companynumber :$scope.companynumber,
						 contactname : $scope.contactname,
						 email: $scpoe.email,
						 phone : $scope.phone,
						 username : $scope.username,
						 password: $scope.password
					});
				});
				};
				$scope.updateCustomer = function(id){
					 var index = getSelectedIndex(id);
					 var x = $scope.CustomerManagement[index];
					 
					
					 $scope.id = $scope.CustomerManagement[index].id;
					 $scope.companyname = $scope.CustomerManagement[index].companyname;
					 $scope.companynumber = $scope.CustomerManagement[index].companynumber;
					 $scope.contactname = $scope.CustomerManagement[index].contactname;
					 $scope.email = $scope.CustomerManagement[index].email;
					 $scope.phone = $scope.CustomerManagement[index].phone;
					
				};
			 	$scope.saveNewCustomer = function  (){
					$http.get("http://localhost/projectmanager/rest/customer/UpdateCustomer?id="+$scope.id+"&companyname="+$scope.companyname+"&companynumber="+$scope.companynumber+"&contactname="+$scope.contactname+"&email="+$scope.email+"&phone="+$scope.phone)
							.then(function(response){
								var response = response.data;
								$scope.CustomerManagement[index].id = $scope.id;
							   	$scope.CustomerManagement[index].companyname = $scope.companyname;
								 $scope.CustomerManagement[index].companynumber = $scope.companynumber;
								 $scope.CustomerManagement[index].contactname= $scope.contactname;
								 $scope.CustomerManagement[index].email= $scope.email;
								 $scope.CustomerManagement[index].phone=$scope.phone; 
								
							});
					   $scope.CustomerManagement.push({
						     companyname:$scope.companyname,
						     companynumber :$scope.companynumber,
							 contactname : $scope.contactname,
							 email: $scpoe.email,
							 phone : $scope.phone,
							 username : $scope.username,
							 password: $scope.password
						});
					
					};
				
					function getSelectedIndex(id){
						
						for(var i=0; i<$scope.CustomerManagement.length; i++)    
							if($scope.CustomerManagement[i].id==id ) 
								return i;
						return -1;
							
				   
					};
					
			});
});
app.controller("EmployeeManagement", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
			.then(function(response) {
						$scope.EmployeeManagement = response.data;
						$scope.selecteEmployee;
						
						$http.get("http://localhost/projectmanager/rest/project/getall")
						.then(function(response) {
							$scope.Project = response.data;
						});
						$scope.addProject = function(){
							$http.get("http://localhost/projectmanager/rest/employeeproject/create?employee="+$scope.employee+"&project="+$scope.project)
							.then(function(response) {
							
							console.log(response.data);
							});
						};

						$scope.deleteemployee = function(id) {
							$http.get("http://localhost/projectmanager/rest/employee/delete?id="+id).then(function(response) {
								$("#tr-" + id).remove();

							});
						};
						$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
						.then(function(response) {
									$scope.EmployeeManagement = response.data;
									
						});	
						$scope.addemployee = function() {
							  $http.get("http://localhost/projectmanager/rest/employee/createEmployee?firstname="+$scope.firstname+
								"&lastname="+$scope.lastname+"&email="+$scope.email+"&phone="+$scope.phone+"&username="+$scope.username+"&password="+$scope.password) 
							     .then(function(response) { 
								  console.log(response.data);
							  	  $scope.EmployeeManagement.push({
	                                firstname : $scope.firstname,
									lastname : $scope.lastname,
									email : $scope.email,
									phone : $scope.phone,
									username : $scope.username,
									password : $scope.password
							
							   });
							});
							  
						};
						$scope.editEmployeeInfo = function (id){
							 var index = getSelectedIndex(id);
							 var x = $scope.EmployeeManagement[index];
							 console.log(x);
							$scope.id = x.id;
							$scope.firstname = x.firstname;
							$scope.lastname =x.lastname;
							$scope.email = x.email;
							$scope.phone = x.phone;
							console.log($scope.id);
					};
					$scope.SaveChanges = function (){
						
						$http.get("http://localhost/projectmanager/rest/employee/updateEmployee?id="+$scope.id+"&firstname="+$scope.firstname+"&lastname="+$scope.lastname+"&email="+$scope.email+"&phone="+$scope.phone).then(function(response){  
							$scope.EmployeeManagement[index].id = $scope.id;
						 $scope.EmployeeManagement[index].firstname =$scope.firstname; 
						 $scope.EmployeeManagement[index].lastname = $scope.lastname;
						 $scope.EmployeeManagement[index].email = $scope.email;
						 $scope.EmployeeManagement[index].phone = $scope.phone;
						});
						
					};

				  	function getSelectedIndex(id){
							
							for(var i=0; i<$scope.EmployeeManagement.length; i++)    
								if($scope.EmployeeManagement[i].id==id ) 
									return i;
							return -1;
								
					   
						};
						
			});
});
						/*FormEmployeeHourReport*/
						/*app.controller("FormEmployeeHourReport", function($scope, $http) {
							$http.get("http://localhost/projectmanager/rest/hourreport/createHourReport?employee="$scope.userId)
									.then(function(response) {
												$scope.FormEmployeeHourReport = response.data;
												 
						
});
});*/

app.controller("loginCtrl", function($scope, $http, $location) {
	$scope.getuser = function() {
		$http.get("http://localhost/projectmanager/rest/user/getuser?username="+$scope.username+"&password="+$scope.password)
				.then(function(response) {
					
					console.log(response.data);
					   userId = response.data.id;
					if (response.data.type == "c") {
						
						$(".projectPage").css("visibility", "visible");
						$(".firsttr").css("visibility", "visible");
						$(".employeePage").css("visibility", "hidden");
						$(".managerPage").css("visibility", "hidden");
						$(".LogOut").css("visibility", "visible");
						$location.path('/two');
					};
					
                   if (response.data.type == "e") {
                	  
						$(".employeePage").css("visibility", "visible");
						$(".LogOut").css("visibility", "visible");
						$(".firsttr").css("visibility", "visible");
						$(".projectPage").css("visibility", "hidden");
						$(".managerPage").css("visibility", "hidden");
						$location.path('/one');
					};
					if (response.data.type == "m") {
						
						$(".managerPage").css("visibility", "visible");
						$(".firsttr").css("visibility", "visible");
						$(".LogOut").css("visibility", "visible");
						$(".employeePage").css("visibility", "hidden");
						$(".projectPage").css("visibility", "hidden");
						$location.path('/three');
					};
				 
				});
	};
});