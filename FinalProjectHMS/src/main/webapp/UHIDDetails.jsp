<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
 
<f:view>
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Details Page</title>
         <style>
  
            /* Add your CSS styles here */
            body {
            background-image: url("OIP.jpg");
            background-size: cover;
            filter: drop-shadow(2px 4px 6px black);
                font-family: Arial, sans-serif;
                background-color: #f2f2f2;
                margin: 0;
                padding: 0;
            } 
 
       h2 {
                color: #0074cc;
             
            }
  
            .center {
                text-align: center;
            }
 
            .form-container {
                background-color: #ffffff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
            }
 


</style>
</head>
<body>
	 <h:form>
        <center>
          <h2><br/><br/>
		<h:outputText value="UHID Detail"/></br></br>
	</h2>
          <div class="container">
          <!-- <div class="circle">
               <img src="profile.jpg" alt="Image Description">
          </div> -->
          
        <div class="name">
           <b> <h:outputLabel >Name:- </h:outputLabel></b>
          <h:outputText value="#{patient.firstName}"/>&nbsp;<h:outputText value="#{patient.lastName}"/>  <br/>
        </div>  <br/>
     <b>   <h:outputLabel>UHID:- </h:outputLabel></b>
          <h:outputText value="#{patient.uHID}" style="background-color: red;"/>  <br/><br/>
        
                   <b> <h:outputLabel>DOB:- </h:outputLabel> </b>
          
          <h:outputText value="#{patient.dob}">
          <f:convertDateTime pattern="yyyy-MM-dd" /> </h:outputText>   <br/><br/>
                   <b>  <h:outputLabel>Gender:- </h:outputLabel></b>
           
          <h:outputText value="#{patient.gender}"/>    <br/><br/>
                 <b>  <h:outputLabel>UserName:- </h:outputLabel></b>
         
          <h:outputText value="#{patient.userName}"/>    <br/><br/>
                  <b>   <h:outputLabel>PhoneNo:- </h:outputLabel></b>
          
          <h:outputText value="#{patient.phone_no}"/>   <br/><br/>
                  <b>   <h:outputLabel>Email:- </h:outputLabel> </b>
          
          <h:outputText value="#{patient.email}"/>   <br/><br/>
          
           <b> <h:outputLabel>Status:- </h:outputLabel></b>
          
          <h:outputText value="#{patient.status}"/>    <br/><br/>
          
          <b> <h:outputLabel>Medical History:- </h:outputLabel></b>
          
          <h:outputText value="#{patient.medHistory}"/>   <br/><br/>
          
          <b> <h:outputLabel>Cause:- </h:outputLabel></b>
          
          <h:outputText value="#{patient.cause}"/>   <br/><br/>
          
         <b>  <h:outputLabel>Address:- </h:outputLabel> </b>
          
          <h:outputText value="#{patient.address}"/>    <br/><br/><br/>
          
          </div>
          </center>
          <center>
          <h:commandLink action="FinalSearch.jsp?faces-redirect=true" value="Back" />
          </center>
        </h:form>
</body>
	</html>
</f:view>