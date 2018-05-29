<%-- 
    Document   : search_doctor
    Created on : May 26, 2018, 9:40:42 PM
    Author     : hai06
--%>

<%@page import="Model.Doctor"%>
<%@page import="java.util.List"%>

<%@include file="header.jsp" %>
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
    <div class="flex-center flex-column" style="padding-top: 50px">
           <div class="col-md-8">
                
       


<table class="table table-hover">

    <!--Table head-->
    <thead class="mdb-color darken-3">
        <tr class="text-white">
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Degree</th>
            <th>Accepted Insurance</th>
            <th>Office Hours</th>
            <th>Languages</th>
            <th>Hospital ID</th>
            <th>Rating</th>
            <%
                if (user != null && user.getType().equals("Patient")) {
            %>
            <th>Bookmark</th>
            <%
                }
                else if (user != null && user.getType().equals("Admin")) {
            %>
            <th>Enabled</th>
            <th>Edit</th>
            <th>Delete</th>
            <%
                }
            %>
        </tr>
    </thead>
    <!--Table head-->

    <!--Table body-->
    <tbody>
    <%
        List<Doctor> doctorList = (List) request.getAttribute("doctorList");
        if (doctorList != null) {
            for (Doctor doctor : doctorList) {
    %>
            <tr>
                <th scope="row">1</th>
                <td><%= doctor.getFirstName() %></td>
                <td><%= doctor.getLastName() %></td>
                <td><%= doctor.getGender() %></td>
                <td><%= doctor.getDegree() %></td>
                <td><%= doctor.isAcceptedInsurance() %></td>
                <td><%= doctor.getOfficeHours() %></td>
                <td><%= doctor.getLanguages() %></td>
                <td><%= doctor.getHospitalId() %></td>
                <th>Rating value</th>
                <%
                    if (user != null && user.getType().equals("Patient")) {
                %>
                <td><input type="checkbox" id="<%= doctor.getId() %>" onclick="bookmark(<%= doctor.getId() %>)"/></td>
                <%
                    }
                    else if (user != null && user.getType().equals("Admin")) {
                %>
                <td><input type="checkbox" id="<%= doctor.getId() %>" onclick="enable(<%= doctor.getId() %>)"/></td>
                <td><input type="button" id="<%= doctor.getId() %>" value="Edit" onclick="edit(<%= doctor.getId() %>)"/></td>
                <td><input type="button" id="<%= doctor.getId() %>" value="Delete" onclick="delete(<%= doctor.getId() %>)"/></td>
                <%
                    }
                %>
            </tr>
    <%
            }
        }
    %>
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
</body>

</html>

<%@include file="footer.html" %>