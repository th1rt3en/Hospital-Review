<%-- 
    Document   : search_doctor
    Created on : May 26, 2018, 9:40:42 PM
    Author     : hai06
--%>

<%@page import="Model.Doctor"%>
<%@page import="java.util.List"%>
<%@include file="../header.jsp" %>
<% 
    if (user == null || !user.getType().equals("Admin"))
        response.sendRedirect("/");
%>
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
            <th>First Name</th>
            <th>Last Name</th>
            <th>Gender</th>
            <th>Degree</th>
            <th>Accepted Insurance</th>
            <th>Office Hours</th>
            <th>Languages</th>
            <th>Hospital ID</th>
            <th>Edit</th>
            <th>Delete</th>
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
                <th scope="row"><%= doctor.getId() %></th>
                <td id="fname_<%= doctor.getId() %>"><%= doctor.getFirstName() %></td>
                <td id="lname_<%= doctor.getId() %>"><%= doctor.getLastName() %></td>
                <td id="gender_<%= doctor.getId() %>"><%= doctor.getGender() %></td>
                <td id="degree_<%= doctor.getId() %>"><%= doctor.getDegree() %></td>
                <td id="insurance_<%= doctor.getId() %>"><%= doctor.isAcceptedInsurance() %></td>
                <td id="officeHours_<%= doctor.getId() %>"><%= doctor.getOfficeHours() %></td>
                <td id="lang_<%= doctor.getId() %>"><%= doctor.getLanguages() %></td>
                <td id="hosID_<%= doctor.getId() %>"><%= doctor.getHospitalId() %></td>
                <td><input type="button" id="edit_<%= doctor.getId() %>" value="Edit" onclick="edit(<%= doctor.getId() %>)"/></td>
                <td><input type="button" id="delete_<%= doctor.getId() %>" value="Delete" onclick="del(<%= doctor.getId() %>)"/></td>
            </tr>
    <%
            }
        }
    %>
            <tr>
                <th scope="row"></th>
                <td><input type="text" id="firstName" size="10"></td>
                <td><input type="text" id="lastName" size="10"></td>
                <td><select id="gender"><option>male</option><option>female</option></select></td>
                <td><input type="text" id="degree" size="10"></td>
                <td><input type="checkbox" id="acceptedInsurance"></td>
                <td><input type="text" id="officeHours" size="10"></td>
                <td><input type="text" id="languages" size="10"></td>
                <td><input type="text" id="hospitalId" size="10"></td>
                <td><input type="button" value="Add" onclick="add()"/></td>
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
        edit = function(id) {
            var fname = document.getElementById("fname_" + id);
            fname.id = " ";
            fname.innerHTML = "<input type='text' id='fname_" + id + "' size='10' value='" + fname.innerHTML + "'/>";
            var lname = document.getElementById("lname_" + id);
            lname.id = " ";
            lname.innerHTML = "<input type='text' id='lname_" + id + "' size='10' value='" + lname.innerHTML + "'/>";
            var gender = document.getElementById("gender_" + id);
            gender.id = " ";
            var selectedM = (gender.innerHTML == 'male') ? " selected=selected" : "";
            var selectedF = (gender.innerHTML == 'female') ? " selected=selected" : "";
            gender.innerHTML = "<select id='gender_" + id + "'>" +
            "<option" + selectedM + ">male</option>" +
            "<option" + selectedF + ">female</option></select>";
            
            var degree = document.getElementById("degree_" + id);
            degree.id = " ";
            degree.innerHTML = "<input type='text' id='degree_" + id + "' size='10' value='" + degree.innerHTML + "'/>";
            var ins = document.getElementById("insurance_" + id);
            ins.id = " ";
            var accepted = (ins.innerHTML == "true") ? " checked=checked" : "";
            ins.innerHTML = "<input type='checkbox'" + accepted + " id='insurance_" + id + "'/>";
            var officeH = document.getElementById("officeHours_" + id);
            officeH.id = " ";
            officeH.innerHTML = "<input type='text' id='officeHours_" + id + "' size='10' value='" + officeH.innerHTML + "'/>";
            var lang = document.getElementById("lang_" + id);
            lang.id = " ";
            lang.innerHTML = "<input type='text' id='lang_" + id + "' size='10' value='" + lang.innerHTML + "'/>";
            var hosid = document.getElementById("hosID_" + id);
            hosid.id = " ";
            hosid.innerHTML = "<input type='text' id='hosID_" + id + "' size='10' value='" + hosid.innerHTML + "'/>";
            var button = document.getElementById("edit_" + id);
            button.id = "commit_" + id;
            button.value = "Commit";
            button.setAttribute("onclick", "commit(" + id  + ")");
        };
        
        del = function(id) {
            $.post("DoctorServlet",
            {
                query: "delete",
                id: id
            },
            function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            });
        };
        
        commit = function(id) {
            var fname = document.getElementById("fname_" + id).value;
            var lname = document.getElementById("lname_" + id).value;
            var gender = document.getElementById("gender_" + id).value;
            var degree = document.getElementById("degree_" + id).value;
            var ins = document.getElementById("insurance_" + id).checked;
            ins = (ins == "true") ? true : false;
            var officeH = document.getElementById("officeHours_" + id).value;
            var lang = document.getElementById("lang_" + id).value;
            var hosid = document.getElementById("hosID_" + id).value;
            alert(hosid);
            var button = document.getElementById("commit_" + id);
            button.id = "edit_" + id;
            button.value = "Edit";
            button.setAttribute("onclick", "edit(" + id  + ")");
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
                hospitalId: hosid
            },
            function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            });
        };
        
        add = function() {
            var fname = document.getElementById("firstName").value;
            var lname = document.getElementById("lastName").value;
            var gender = document.getElementById("gender").value;
            var degree = document.getElementById("degree").value;
            var ins = document.getElementById("acceptedInsurance").checked;
            ins = (ins == "true") ? true : false;
            var officeH = document.getElementById("officeHours").value;
            var lang = document.getElementById("languages").value;
            var hosid = document.getElementById("hospitalId").value;
            $.post("DoctorServlet",
            {
                query: "insert",
                firstName: fname,
                lastName: lname,
                gender: gender,
                degree: degree,
                acceptedInsurance: ins,
                officeHours: officeH,
                languages: lang,
                hospitalId: hosid
            },
            function(data, status){
                alert("Data: " + data + "\nStatus: " + status);
            });
        };
    </script>
</body>

</html>

<%@include file="../footer.html" %>