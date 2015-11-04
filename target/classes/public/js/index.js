var newLeaveApp = angular.module('newLeaveApp', []);

newLeaveApp.constant('baseURL','');

newLeaveApp.controller('LeaveRequestController', function (baseURL, $scope,$http) {
    fetchLeave();
    $scope.user;
    $scope.loggedIn = false;
    $scope.isTeamLead = false;
    $scope.noLeaveSelected = true;
    $scope.leaveBeingUpdated = false;
    
    function fetchLeave() {
        function storeLeave(data) {
            $scope.leaveRequests = data;
        }
        $http.get(baseURL+"/leaverequest/employee/1").success(storeLeave);
    }
    $scope.fetchLeave = fetchLeave();
    
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
       function resetForm() {
           fetchLeave();
       }
       var leave = $scope.newLeave;
       $http.put(baseURL + "/leaverequest/new", leave).success(resetForm);
    };
    $scope.loginEmployee = function() {
        $http.post(baseURL + '/session/login', $scope.email).success(function(data) {
            console.log(data);
            if(data) {
               $scope.loggedIn = true;
               $scope.user = data;
               if(!$scope.user.accessLevel==="TEAM_MEMBER") {
                    $scope.isTeamLead = true;
               }
            }
        });

    };
});