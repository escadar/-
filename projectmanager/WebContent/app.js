var userId;
var app = angular.module("myApp", ["ngRoute"]);
app.config(function($routeProvider) {
	$routeProvider.when("/ProjectManagement", {
		templateUrl : "ProjectManagement.html",
		controller : "ProjectManagement"

	}).when('/', {
		templateUrl : 'LoginPage.html',
		controller : "loginCtrl"

	}).when("/LogOut", {
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

	}).when("/MyReport", {
		templateUrl : "MyReport.html",
		controller : "MyReport"
			
	}).when("/CustomerManagement", {
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
	
	.when("/managerSettings", {
		templateUrl : "managerSettings.html",
		controller : "managerSettings"

	})
})
 
app.controller("managerSettings", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/managerSettings/getHours")
	.then(function(response) {
		console.log(response.data)
	     var managerHours = response.data;
		  $scope.arr = managerHours.split(',');
		  $scope.starttime = $scope.arr[0];
		  $scope.endtime = $scope.arr[1];
		  console.log($scope.starttime);
		  console.log($scope.endtime);
	});
	
	
	$scope.setHours = function ()  {
	$http.get("http://localhost/projectmanager/rest/managerSettings/setHours?starttime="+$("#starttime").val()+"&endtime="+$("#endtime").val())
	.then(function(response) {
		
		console.log(response.data)
		
		
		$http.get("http://localhost/projectmanager/rest/managerSettings/getHours")
		.then(function(response) {
			console.log(response.data)
		     var managerHours = response.data;
			  $scope.arr = managerHours.split(',');
			  $scope.starttime = $scope.arr[0];
			  $scope.endtime = $scope.arr[1];
			  console.log($scope.starttime);
			  console.log($scope.endtime);
		});
		
		   
	});
	};
	
	$scope.setDays = function (){
		
		
		$http.get("http://localhost/projectmanager/rest/managerSettings/setDays?days="+$scope.week.Sunday
				 +$scope.week.Monday+$scope.week.Tuesday 
				  +$scope.week.Wednesday+$scope.week.Thursday
			     +$scope.week.Friday+$scope.week.Saturday).then(function(response) {
			    	 $scope.setDays = response.date;
			    	 
		
				 
			 });
	};
		$scope.week = {
				
			Sunday   : 'false,' ,
		    Munday   : 'false,' ,
		    Tuesday   : 'false,',
		    Wednesday   : 'false,',
		    Thursday   : 'false,' ,
		    Friday   : 'false,' ,
		    Saturday   : 'false' 
		    	
		    
			
		};
		console.log($scope.week);
	 
});  

app.controller("ActiveProjectsByCustomer",function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/project/GetActiveProjectsByCustomer2?userId="+userId)
			.then(function(response) {
				$scope.ActiveProjectsbyC = response.data;
			});

});


app.controller("ProjectHourReport", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/hourreport/ProjectHourReport?userId="+userId).then(function(response) {
		$scope.ProjectHourReport= response.data;
		
       $http.get("http://localhost/projectmanager/rest/project/GetProjectsByCustomer?userId="+userId).then(function(response) {
		$scope.Project = response.data;
	}); 
	 
	$scope.submitcustomersearch  = function (){
	
		if($scope.project == undefined){  
			$scope.project = 0;
			
		}
	 
		$http.get("http://localhost/projectmanager/rest/hourreport/getHourReportsByYearAndMonthOfCustomer?yearAndMonth="
				+$("#yearandmonth").val()
				+"&project="+$scope.project+"&userId="+userId).then(function(response) {
			$scope.ProjectHourReport= response.data;
			console.log($scope.ProjectHourReport);
				 
		});
		
		
	};
});
});
	

 
app.controller("LogOut", function($scope,$location) {
	$(".customerpage").hide();
	$(".logOut").hide();
	$(".eroors").hide();
	 $(".employeepage").hide();
     $(".managerpage").hide();
	$location.path('/');
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
				

				    $scope.addProject = function() {
				  
				 	
					// console.log("the project: " +userId +" "+$scope.projectname+" "+$scope.startdate+" "+$scope.enddate+" "+$scope.customer+ " "+$scope.customerprojectmanager+" "+$scope.projectmanageremail+" "+$scope.projectmanagerphone+");
 				 
					var startdate = $('#datepicker2').datepicker({dateFormat: 'yyyy-mm-dd'}).val();
					var enddate = $('#datepicker1').datepicker({dateFormat: 'yyyy-mm-dd'}).val();
					/*console.log(endtime);
					console.log(starttime)*/;
					
		
					  $http.get("http://localhost/projectmanager/rest/project/createproject?projectname="+$scope.projectname
							  +"&customer="+$scope.customer
							  +"&customerprojectmanager="+$scope.customerprojectmanager
							  +"&projectmanageremail="+$scope.projectmanageremail
							  +"&projectmanagerphone="+$scope.projectmanagerphone
							  +"&startdate="+startdate
							   +"&enddate="+enddate)
					.then(function(response) {
					
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
						alert("are u sure u want to delete");
						if(confirm = 'ok'){
					$http.get("http://localhost/projectmanager/rest/project/deleteProject?id="+id).then(function(response) {
						$("#tr-" + id).remove();

					});
						};
					 
				};  
				
				function getSelectedIndex(id){
					
					for(var i=0; i<$scope.Project.length; i++)    
						if($scope.Project[i].id==id ) 
							return i;
					return -1;
						
			   
				};
			 $scope.updateProject = function(id){
			 
				 var index = getSelectedIndex(id);
				 var x = $scope.Project[index];
				 
				 
				    $scope.projectname = x.projectname;
				    $scope.customer =x.customer;
					$scope.customerprojectmanager = x.customerprojectmanager;
				    $scope.projectmanageremail =x.projectmanageremail;
					$scope.projectmanagerphone =x.projectmanagerphone ;
					$scope.startdate = x.startdate;
					$scope.enddate = x.enddate;
					
			 
			 $scope.saveEditedProject = function (){
				 
				
				  /*  var starttime = $('#datepicker2').datepicker({dateFormat: 'yyyy-mm-dd'}).val();
					var endtime = $('#datepicker1').datepicker({dateFormat: 'yyyy-mm-dd'}).val();*/
					
				 $http.get("http://localhost/projectmanager/rest/project/updateProjectoject?id="+id+
						 "&projectname="+$scope.projectname 
						 +"&customer="+$scope.customer
						 +"&customerprojectmanager="+$scope.customerprojectmanager 
						 +"&projectmanageremail="+$scope.projectmanageremail
						 +"&projectmanagerphone="+$scope.projectmanagerphone 
						 +"&startdate="+$scope.startdate
						 +"&enddate="+$scope.enddate)
				 		.then(function(response){  
				
				 $scope.Project[index].projectname = $scope.projectname;
			     $scope.Project[index].customer = $scope.customer;
				 $scope.Project[index].customerprojectmanager = $scope.customerprojectmanager;
			     $scope.Project[index].projectmanageremail = $scope.projectmanageremail;
				 $scope.Project[index].projectmanagerphone = $scope.projectmanagerphone;
				 $scope.Project[index].startdate = $scope.startdate;
				 $scope.Project[index].enddate = $scope.enddate;
				 
				 }); 
			 };
			 

			 };
		
			});
});

app.controller("MyReport",function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/hourreport/getAllEmployeeReport?userId="+userId)
.then(function(response) {
				$scope.MyReport = response.data;
				console.log(response.data);
				

	
	$http.get("http://localhost/projectmanager/rest/project/getProjectsByEmployee?userId="+userId)
	.then(function(response) {
				$scope.Project = response.data;
			});
	$scope.submitEmployeeSearch = function (){
		
		if($scope.project == undefined) { 
		 $scope.project = 0;
		
	} 
	$http.get("http://localhost/projectmanager/rest/hourreport/getHourReportsByYearAndMonthOfEmployee?yearAndMonth="
			 +$("#yearandmonth").val()+
			"&project="+$scope.project+"&userId="+userId).then(function(response) {
		$scope.MyReport= response.data;
		console.log($scope.MyReport);
		
	});
	};
});
});

               app.controller("EmployeeHourReport",function($scope, $http) {
                	$http.get("http://localhost/projectmanager/rest/hourreport/getWeeklyEmployeeReport?userId="+userId)
					                		.then(function(response) {					                			
													$scope.Employee = response.data;
													console.log($scope.Employee);
													
													$http.get("http://localhost/projectmanager/rest/managerSettings/getHours")
													.then(function(response) {
														console.log(response.data)
													     var managerHours = response.data;
														  $scope.arr = managerHours.split(',');
														  $scope.starttime = $scope.arr[0];
														  $scope.endtime = $scope.arr[1];
														  console.log($scope.starttime);
														  console.log($scope.endtime);
													});
													
									
									$scope.addNewHourReport = function (){
										 
										var date = $('#datepicker').datepicker({dateFormat: "yyyy-mm-dd"}).val();
										console.log("the userid: " +userId +" "+$scope.project+" "+date+" "+$scope.starttime+" "+$scope.endtime+" "+$scope.description);
										$http.get("http://localhost/projectmanager/rest/hourreport/createHourReport?"
												+"employee="+userId
												+"&project="+$scope.project
												+"&date="+date
												+"&starttime="+$("#starttime").val()
												+"&endtime="+$("#endtime").val()
												+"&description="+$scope.description )
						                .then(function(response) {
						                		console.log(response.data);
										        $scope.Employee.push({
										    	project: $scope.project,
										    	 starttime :$scope.starttime,
										    	endtime : $scope.endtime,
											    descrption :$scope.descrption  
											  
											
										        });
									    });
										
										
									};
									 $http.get("http://localhost/projectmanager/rest/hourreport/getWeeklyEmployeeReport?userId="+userId)
					                		.then(function(response) {					                			
													$scope.MyReport = response.data;
													console.log($scope.MyReport);
												
					                });
									 $http.get("http://localhost/projectmanager/rest/project/getProjectsByEmployee?userId="+userId)
										.then(function(response) {
											$scope.Project = response.data;
											 console.log($scope.Project);
								});
					                		 
									/* $scope.submitsearch = function (){
											
											if($scope.project == undefined){
												$scope.project = 0;
											 
											};
												 $http.get("http://localhost/projectmanager/rest/hourreport/getHourReportsByYearAndMonthOfEmployee?yearAndMonth="
														 +$("#yearandmonth").val()+"&project="+$scope.project
														+"&userId="+userId)
							                		.then(function(response) {					                			
															$scope.MyReport = response.data;
															console.log($scope.MyReport);
														
							                });
												
											 
							};*/
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
		
		$http.get("http://localhost/projectmanager/rest/project/getall").then(
				function(response) {
							$scope.Project = response.data;
				});
		$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
		.then(function(response) {
					$scope.EmployeeManagement = response.data;
		});
		
		$http.get("http://localhost/projectmanager/rest/customer/getall").then(
				function(response) {
					$scope.CustomerManagement = response.data;
				});
		$scope.submit = function (){
			
			
			if($scope.employee == undefined){
				$scope.employee = 0 ;
			}
			if($scope.project == undefined){
				$scope.project = 0 ;
			}
			if($scope.customer == undefined){
				$scope.customer = 0 ;
			}
			
			$http.get("http://localhost/projectmanager/rest/hourreport/getHourReportsByYearAndMonth?yearAndMonth="
					+$("#yearandmonth").val()+"&employee="+$scope.employee
					+"&project="+$scope.project+"&customer="+$scope.customer).then(function(response) {
				$scope.HourReports= response.data;
				console.log($scope.HourReports);
				
			});
			
			
			
		};
		
		/* $scope.deleteReport = function(id) {
			 alert("hrh");
				$http.get("http://localhost/projectmanager/rest/hourreport/delete?id="+id).then(function(response) {
					$("#tr-" + id).remove();
				});
		 };
			$http.get("http://localhost/projectmanager/rest/hourreport/getAllHourReports").then(function(response) {
				$scope.HourReports= response.data;
			});*/
			
			
				 
	});
});


app.controller("CustomerManagement", function($scope, $http) {
	$http.get("http://localhost/projectmanager/rest/customer/getall")
	.then(function(response) {
				$scope.CustomerManagement = response.data;
				
				
          $scope.deleteCustomer = function(id) {
        	  alert("are you sure you want to delete?");
        	  if( confirm = 'ok'){
					$http.get("http://localhost/projectmanager/rest/customer/deleteCustomer?id="+id).then(function(response) {
						$("#tr-" + id).remove();
						
					});
				 
          
				/*$http.get("http://localhost/projectmanager/rest/customer/getall").then(
						function(response) {
							$scope.CustomerManagement = response.data;
						});*/
        	  };
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
				
				function getSelectedIndex(id){
					
					for(var i=0; i<$scope.CustomerManagement.length; i++)    
						if($scope.CustomerManagement[i].id==id ) 
							return i;
					return -1;
				};
				
				$scope.updateCustomer = function(id){
					 var index = getSelectedIndex(id);
					 var x = $scope.CustomerManagement[index];
					 
					 $scope.companyname = x.companyname;
					 $scope.companynumber = x.companynumber;
					 $scope.contactname = x.contactname;
					 $scope.email = x.email;
					 $scope.phone = x.phone;
					
				
			 	$scope.saveNewCustomer = function  (){
					$http.get("http://localhost/projectmanager/rest/customer/UpdateCustomer?id="+id+"&companyname="+$scope.companyname+"&companynumber="+$scope.companynumber+"&contactname="+$scope.contactname+"&email="+$scope.email+"&phone="+$scope.phone)
							.then(function(response){
								var response = response.data;
							
							   	 $scope.CustomerManagement[index].companyname = $scope.companyname;
								 $scope.CustomerManagement[index].companynumber = $scope.companynumber;
								 $scope.CustomerManagement[index].contactname= $scope.contactname;
								 $scope.CustomerManagement[index].email= $scope.email;
								 $scope.CustomerManagement[index].phone=$scope.phone; 
								
			
							});
					};
				};
				
				$scope.submitcustomersearch = function (){
					
					if($scope.project = undefined){
						$scope.project = 0 ;
					};
					
					$http.get("http://localhost/projectmanager/rest/hourreport/getHourReportsByYearAndMonth?yearAndMonth="
							+$("#yearandmonth").val()+"&employee="+$scope.employee
							+"&project="+$scope.project+"&customer="+$scope.customer).then(function(response) {
						$scope.HourReports= response.data;
						console.log($scope.HourReports);
						
					});
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
						$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
						.then(function(response) {
							$scope.EmployeeManagement = response.data;
						});
						
						$scope.attachProjectToEmployee = function(){
							$http.get("localhost/projectmanager/rest/employeeproject/create?project="+$scope.project+"&employee="+$scope.employee)
							.then(function(response) {
							
							console.log(response.data);
							});
						}; 
						

						$scope.deleteemployee = function(id) {
							alert("are u sure u want to delete");
							if(confirm = 'ok'){
							$http.get("http://localhost/projectmanager/rest/employee/delete?id="+id).then(function(response) {
								$("#tr-" + id).remove();
						 	});
							
							};

					$http.get("http://localhost/projectmanager/rest/employee/getAllEmployees")
						.then(function(response) {
									$scope.EmployeeManagement = response.data;
									
						});	
						};
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
						
					 	$scope.attachProjectToEmployee = function (){
							$http.get("http://localhost/projectmanager/rest/employeeproject/create?project="+$scope.project+"&employee="+$scope.employee)
							.then(function(response){  
								 console.log(response.data);
							
							});
						};
						 
						
	                         function getSelectedIndex(id){
							  for(var i=0; i<$scope.EmployeeManagement.length; i++)    
								if($scope.EmployeeManagement[i].id==id ) 
									return i;
							return -1;
								
					   
						};
						
						$scope.editEmployeeInfo = function (id){
						   
							 var index = getSelectedIndex(id);
							 var x = $scope.EmployeeManagement[index];
							 console.log($scope.EmployeeManagement[index]);
						
							$scope.firstname = x.firstname;
							$scope.lastname =x.lastname;
							$scope.email = x.email;
							$scope.phone = x.phone;
							console.log($scope.id);
					
					$scope.SaveChanges = function (){
						
						$http.get("http://localhost/projectmanager/rest/employee/updateEmployee?id="+id+"&firstname="+$scope.firstname+"&lastname="+$scope.lastname+"&email="+$scope.email+"&phone="+$scope.phone).then(function(response){  
						
						 $scope.EmployeeManagement[index].firstname =$scope.firstname; 
						 $scope.EmployeeManagement[index].lastname = $scope.lastname;
						 $scope.EmployeeManagement[index].email = $scope.email;
						 $scope.EmployeeManagement[index].phone = $scope.phone;
						});
						
					};

				 
						};	
			});
});
				/*		FormEmployeeHourReport
						app.controller("FormEmployeeHourReport", function($scope, $http) {
							$http.get("http://localhost/projectmanager/rest/hourreport/createHourReport?employee="+$scope.userId)
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
						$(".customerpage").show();
						$(".logOut").show();
						$(".eroors").show();
						$location.path('/two');
					};
					
                   if (response.data.type =="e") {
                	    $(".employeepage").show();
						$(".logOut").show();
						$(".eroors").show();
					    $location.path('/one');
					};
					if (response.data.type =="m") {
					     $(".managerpage").show();
					  	 $(".logOut").show();
					  	$(".eroors").show();
					     $location.path('/three');
					};
				 if(response.data.type == null){
					 alert("invalid login detailes");
					 
				 };
					
				});
	};
	$scope.forgotpassword = function (){
		 $(".hidebtn").hide();
		$(".forgotpassword").show();
	};
		$scope.send = function (){ 
		$http.get("http://localhost/projectmanager/rest/user/forgotPassword?username="+$scope.username)
		 .then(function(response) {
		 
			 
		});
	};
	
});