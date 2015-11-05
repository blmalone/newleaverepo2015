var newLeaveApp = angular.module('newLeaveApp', ['ngLoadingSpinner']);

newLeaveApp.constant('baseURL','');

newLeaveApp.controller('LeaveRequestController', function (baseURL, $scope, $http, $location, $anchorScroll) {
    $scope.user;
    $scope.loggedIn = false;
    $scope.isTeamLead = false;
    $scope.noLeaveSelected = true;
    $scope.leaveBeingUpdated = false;
    $scope.requestingLeave = false;
    $scope.approvingLeave = false;

    $scope.hasLeaveRequests = function() {
      return $scope.leaveRequests.length > 0;
    }
    
    function fetchLeave() {
        function storeLeave(data) {
            $scope.leaveRequests = data;
        }
        function storeLeaveForApproval(data) {
            $scope.leaveRequestsForApproval = data;
        }
        function storeCurrentProjects(data) {
            $scope.currentProjects = data;
        }
        $http.get(baseURL+"/leaverequest/employee/" + $scope.user.id).success(storeLeave);
        if($scope.isTeamLead) {
            $http.get(baseURL + "/leaverequest/teamlead/" + $scope.user.id).success(storeLeaveForApproval);
            $http.get(baseURL + "/project/teamlead/" + $scope.user.id).success(storeCurrentProjects);
        }
    }
    $scope.beginUpdateOfLeave = function(leave) {
        $scope.leaveBeingUpdated = angular.copy(leave);
    };
    $scope.updateLeave = function() {
        function resetForm() {
            $scope.LeaveBeingUpdated = false;
            fetchLeave();
        }
        var leave = $scope.leaveBeingUpdated;
        $http.put(baseURL + "", leave).success(resetForm);
    };
    $scope.selectLeave = function(leave) {
        $scope.noLeaveSelected = false;
        $scope.selectedLeave = leave;
    };
    $scope.deleteLeave = function(leave) {
      $http.delete(baseURL + ""); 
      $scope.noLeaveSelected = true;
      fetchLeave();
    };
    $scope.addLeave = function() {
      console.log("adding leave");
       function resetForm() {
           fetchLeave();
       }
       convertDatesInLeaveObject();
       $scope.newLeave.status="PENDING";
       var leave = $scope.newLeave;
       leave.employeeId = $scope.user.id;
       console.log(leave);
       $http.post(baseURL + "/leaverequest/new", leave).success(function(data) {
          console.log(data);
          resetForm();
       });
    };

    $scope.createProject = function() {
      $location.hash('nav');
      $anchorScroll();
      //usSpinnerService.spin();
      $scope.newProject.teamLeadId = $scope.user.id;
      $scope.newProject.startDate = convertDateToInteger($scope.newProject.startDate.toISOString());
      $scope.newProject.endDate = convertDateToInteger($scope.newProject.endDate.toISOString());
      $http.post(baseURL + "/project/new", $scope.newProject).success(function(data) {
          console.log(data);
          fetchLeave();
          //usSpinnerService.stop();
       });
    }

    $scope.showApproveLeave = function() {
       $scope.requestingLeave = false;
       $scope.approvingLeave = true;
    };
    $scope.showRequestLeave = function() {
       $scope.requestingLeave = true;
       $scope.approvingLeave = false;
    };

    $scope.approveRequest = function(requestId) {
        $http.post(baseURL + "/leaverequest/" + requestId + "/approve").success(fetchLeave);
    };

    $scope.rejectRequest = function(requestId) {
        $http.post(baseURL + "/leaverequest/" + requestId + "/reject").success(fetchLeave);
    };

    function convertDatesInLeaveObject() {
        var startDate = $scope.newLeave.startDate;
        var endDate = $scope.newLeave.endDate;
        $scope.newLeave.startDate = convertDateToInteger(startDate.toISOString());
        $scope.newLeave.endDate = convertDateToInteger(endDate.toISOString());
    }

    function convertTypeToEnum(typeAsString) {
        return typeAsString.toUpperCase().replace(" ", "_");
    }

    function convertDateToInteger(dateAsString) {
        //requires that the string be in the format '2015-12-30T00:00:00.000Z'
        var yearString = dateAsString.slice(0, 4);
        var monthString = dateAsString.slice(5, 7);
        var dayString = dateAsString.slice(8, 10);
        var completeString = yearString + monthString + dayString;
        console.log(completeString);
        return parseInt(completeString);
    }

    $scope.loginEmployee = function() {
        $http.post(baseURL + '/session/login', $scope.email).success(function(data) {
            if(data) {
               $scope.loggedIn = true;
               $scope.user = data;
               if($scope.user.accessLevel != "TEAM_MEMBER") {
                    $scope.isTeamLead = true;
               }
               $scope.fetchLeave = fetchLeave();
               $scope.showRequestLeave();
            }
        });

    };
});