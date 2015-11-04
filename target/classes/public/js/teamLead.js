// Run on page load
$(document).ready(function() {

    // Get the JSON from the backend 
    $.getJSON("/leaverequest/employee/{id}", function (data) {
        // For each object in the JSON...
        $.each(data, function (key, val) {
            $("#holiday-table tbody").append(
                "<tr>" +
                "<td>" + val.empName + "</td>" +
                "<td>" + val.projectName + "</td>" +
                "<td>" + val.startDate + "</td>" +
                "<td>" + val.endDate + "</td>" +
                "<td>" + val.description + "</td>" +
                "<td>" + val.approval + "</td>" +
                "<td class='group-btn'>" +
                + '<a href="#" class="btn btn-primary btn-xs">Approve<i class="fa fa-sign-in"></i></a>' +
                + "<br>" +
                '<a href="#" class="btn btn-primary btn-xs">Reject<i class="fa fa-sign-in"></i></a>' +
                "</td>" +
                "</tr>"
            );
        });
    });
});
    