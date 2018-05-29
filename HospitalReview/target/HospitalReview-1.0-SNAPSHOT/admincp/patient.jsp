<%-- 
    Document   : patient
    Created on : May 29, 2018, 4:11:39 PM
    Author     : hai06
--%>

<%@page import="Model.Patient"%>
<%-- 
    Document   : search_doctor
    Created on : May 26, 2018, 9:40:42 PM
    Author     : hai06
--%>

<%@page import="Model.Doctor"%>
<%@page import="java.util.List"%>

<%@include file="../header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Material Design Bootstrap</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <div class="flex-left flex-column" style="padding-top: 50px">
           <div class="col-md-8">
                
       


<table class="table table-hover">

    <!--Table head-->
    <thead class="mdb-color darken-3">
        <tr class="text-white">
            <th>#</th>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Language</th>
            <th>Address</th>
            <th>Enabled</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
    </thead>
    <!--Table head-->

    <!--Table body-->
    <tbody>
    <%
        List<Patient> patientList = (List) request.getAttribute("patientList");
        if (patientList != null) {
            for (Patient patient : patientList) {
    %>
            <tr>
                <th scope="row"><%= patient.getId() %></th>
                <td id="email_<%= patient.getId() %>"><%= patient.getEmail() %></td>
                <td id="fname_<%= patient.getId() %>"><%= patient.getFirstName() %></td>
                <td id="lname_<%= patient.getId() %>"><%= patient.getLastName() %></td>
                <td id="gender_<%= patient.getId() %>"><%= patient.getGender() %></td>
                <td id="lang_<%= patient.getId() %>"><%= patient.getLanguages() %></td>
                <td id="address_<%= patient.getId() %>"><%= patient.getAddress() %></td>
                <td id="enabled_<%= patient.getId() %>"><%= patient.isActivated() %></td>
                <td><input type="button" id="edit_<%= patient.getId() %>" value="Edit" onclick="edit(<%= patient.getId() %>)"/></td>
                <td><input type="button" id="delete_<%= patient.getId() %>" value="Delete" onclick="del(<%= patient.getId() %>)"/></td>
    <%
            }
        }
    %>
            <tr>
                <th scope="row"></th>
                <td id="email"><input type="text" size="10"/></td>
                <td id="fname"><input type="text" size="10"/></td>
                <td id="lname"><input type="text" size="10"/></td>
                <td id="gender"><input type="text" size="10"/></td>
                <td id="lang"><input type="text" size="10"/></td>
                <td id="address"><input type="text" size="10"/></td>
            </tr>
    </tbody>
    <!--Table body-->

</table>
           </div></div>
<!--Table-->

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
    
    <script type="text/javascript">
        enable = function(id) {
            var enabled = document.getElementById("enabled_" + id).checked;
            var fname = document.getElementById("fname_" + id).innerHTML;
            var lname = document.getElementById("lname_" + id).innerHTML;
            var gender = document.getElementById("gender_" + id).innerHTML;
            var degree = document.getElementById("degree_" + id).innerHTML;
            var ins = document.getElementById("insurance_" + id).innerHTML;
            var officeH = document.getElementById("officeHours_" + id).innerHTML;
            var lang = document.getElementById("lang_" + id).innerHTML;
            var hosid = document.getElementById("hosID_" + id).innerHTML;
            $.post("DoctorServlet",
            {
                query: "update",
                id: id,
                firstName: fname,
                lastName: lname,
                gender: gender,
                degree: degree,
                acceptedInsurance: ins,
                officeHours: officeH,
                languages: lang,
                hospitalId: hosid,
                enabled: enabled
            },
            function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            });
        };
    </script>
</body>

</html>

<%@include file="../footer.html" %>
