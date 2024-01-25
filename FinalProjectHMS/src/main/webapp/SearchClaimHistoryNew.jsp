<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsf/html" prefix="p"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<!-- DatePicker JavaScript & CSS -->
<!-- references to external JavaScript libraries (jQuery and jQuery UI) to enable datepicker functionality. -->
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
 
	$(document).ready(function() {
		$( ".datepicker" ).datepicker({
			dateFormat: 'yy-mm-dd',  <!--set the date format-->
			changeMonth: true,   <!--  allows users to change the month -->
			changeYear: true,  <!--  allows users to change the year -->
			yearRange: "1960:2060"  <!--restrict the selectable yrs -->
		
		});
	});
	</script>


<style>
body {
	font-family: Arial, sans-serif;
	background-color: #b8cda2;
	background-image: url('p3.jpg');
	background-size: cover;
}

h1 {
	color: black;
	text-align: center;
}

/* Center the form */
form {
	text-align: center;
}

/* Style for form input fields */
/* Existing styling for form input */
form input {
	width: 150px;
	padding: 6px;
	margin-bottom: 10px;
	margin-left: 5px;
	box-sizing: border-box;
}

/* Additional styling for form input (modify or add as needed) */
form input {
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 14px;
	background-color: #fff;
	color: #333;
}

/* Optional: Add hover effect */
form input:hover {
	border-color: #4CAF50;
}

form select {
	width: 150px; /* Set the width as needed */
	padding: 6px;
	margin-bottom: 10px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 14px;
	background-color: #fff;
	color: #333;
}

/* Style for form submit button */
form button {
	background-color: #4CAF50;
	color: white;
	padding: 10px 15px;
	border: none;
	border-radius: 15px;
	cursor: pointer;
}

.fromDate {
	margin-left: 80px; /* Adjust the margin as needed */
}

.button-submit, .button-reset {
	display: inline-block; /* Set display property to inline-block */
	margin-right: 10px;
	/* Adjust the margin as needed for spacing between buttons */
}

.button-submit {
	background-color: #4CAF50;
	color: white;
	width: 160px;
	height: 40px;
	font-size: 16px;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

/* Style class for reset button */
.button-reset {
	background-color: #f0565b;
	color: white;
	width: 160px;
	height: 40px;
	font-size: 16px;
	padding: 10px 15px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

/* Additional styling for hover effects (optional) */
.button-submit:hover, .button-reset:hover {
	opacity: 0.8;
	transform: scale(1.1);
}
/* Style for form labels */
form label {
	margin-bottom: 8px;
	font-weight: bold;
	color: #3573a9;
	font-size: 16px;
}

/* Increase specificity for the input with class "form-control" and "datepicker" */
input.form-control.datepicker {
	width: 150px;
	padding: 7px;
	margin-bottom: 10px;
	margin-right: 270px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 14px;
	background-color: #fff;
	color: #333;
}

/* Optional: Add hover effect */
input.form-control.datepicker:hover {
	border-color: #4CAF50;
}

.form-group {
	margin-left: 85px;
	display: inline-flex;
}

.form-group input {
	margin-right: 10px;
	/* Adjust the margin as needed for spacing between input fields */
}

.form-group2 {
	margin-right: -250px;
}

/* Style for the datepicker container */
.ui-datepicker {
	font-size: 14px; /* Set the font size */
}

/* Style for the datepicker header */
.ui-datepicker-header {
	background-color: #4c5daf; /* Set the background color */
	color: white; /* Set the text color */
	border: none; /* Remove the border */
}

/* Style for the title in the header */
.ui-datepicker-title {
	font-weight: bold; /* Make the title bold */
}

/* Style for the datepicker calendar */
.ui-datepicker-calendar {
	background-color: #f0f0f0;
	/* Set the background color for the calendar */
	border: 1px solid #ccc; /* Add a border */
	border-radius: 4px; /* Add border radius */
	padding: 2px; /* Add some padding */
}

/* Style for the datepicker days */
.ui-datepicker-calendar td {
	padding: 1px; /* Add some padding to the individual days */
	border: 1px solid #ddd; /* Add border to create grid lines */
}

/* Style for the selected date */
.ui-datepicker-calendar .ui-state-highlight {
	background-color: #20ad2b;
	/* Set the background color for the selected date */
	color: white; /* Set the text color for the selected date */
}

/* Style for the datepicker input field */
.datepicker {
	padding: 5px; /* Add some padding */
	border: 1px solid #ccc; /* Add a border */
	border-radius: 4px; /* Add border radius */
}

/* Style for the previous and next month buttons */
.ui-datepicker-prev, .ui-datepicker-next {
	background-color: #3a408f;
	/* Set the background color for the buttons */
	color: white; /* Set the text color for the buttons */
	border: none; /* Remove the border */
	padding: 0px 10px; /* Add padding */
	border-radius: 4px; /* Add border radius */
	cursor: pointer; /* Change cursor to pointer on hover */
}

.custom-table {
	border-collapse: collapse;
	/* Collapse borders to avoid spacing between cells */
	width: 100%;
	/* Make the table take up the full width of its container */
}

.custom-table th, .custom-table td {
	border: 1px solid #ddd; /* Add a border to table headers and cells */
	padding: 8px; /* Add padding to headers and cells */
	text-align: left; /* Align text to the left within cells */
}

.custom-table th {
	background-color: #f5f7f5;
	/* Set a background color for table headers */
	color: white; /* Set text color for table headers */
}

/* Style rows alternately for better readability */
.custom-table tr:nth-child(odd) td {
	background-color: #f2f2f2; /* Set a background color for odd rows */
}

.custom-table tr:nth-child(even) td {
	background-color: #f9f9f9; /* Set a background color for even rows */
}

/* Style for form labels in the "vert" div */
#vert label {
	display: block; /* Set display property to block */
	margin-bottom: 5px;
	/* Adjust the margin as needed for spacing between labels */
	font-weight: bold;
	color: #3573a9;
	font-size: 16px;
}

/* Style for form input fields in the "vert" div */
#vert input, #vert select {
	width: 150px; /* Set the width as needed */
	padding: 6px;
	margin-bottom: 10px;
	/* Adjust the margin as needed for spacing between input fields */
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 14px;
	background-color: #fff;
	color: #333;
}

/* Optional: Add hover effect */
#vert input:hover, #vert select:hover {
	border-color: #4CAF50;
}

.my-button {
	padding: 5px 4px;
	background-color: lavender;
	width: 40px;
}

/* Add this to your existing CSS file or create a new one */

/* Additional styling for the form */
form {
	margin: 20px; /* Add margin to the form */
}

/* Style for the form container */
#vert {
	background-color: rgba(245, 245, 245, 0.8);
	padding: 10px;
	border-radius: 8px;
	width: 70%;
	margin: auto;
}

/* Style for form labels */
form label {
	display: block;
	margin-bottom: 8px;
	font-weight: bold;
	color: #3573a9;
	font-size: 16px;
}

/* Style for form input fields */
form input, form select {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	box-sizing: border-box;
	border: 1px solid #ccc;
	border-radius: 4px;
	font-size: 14px;
	background-color: #fff;
	color: #333;
}

/* Style for form submit and reset buttons */
form button {
	margin-top: 15px;
}

/* Style for the search results section */
h1+h:panelGroup {
	margin-top: 20px;
}

/* Style for the data table */
.custom-table {
	margin-top: 20px;
}

/* Optional: Add hover effect to buttons */
.button-submit:hover {
	opacity: 0.8;
	transform: scale(1.1);
}

.button-reset:hover {
	background-color: #de2f34;
	opacity: 0.8;
	transform: scale(1.1);
}

.h1 {
	border: 4px solid white;
	background-color: white;
	width: 270px;
	border-radius: 5px;
}

.notFoundError {
	color: #fd031a;
	font-weight: 550;
	padding: 10px;
	font-size: 17px;
	font-weight:
}

.warning{
     text-align: center;
     color: red;
}
</style>

</head>
<body>

	<h1>
		<h:outputText value="Search Claim History " />
	</h1>

	<h:form>
		<div id="vert">

			<h:outputLabel for="claimId">Claim ID</h:outputLabel>
			<h:inputText id="claimId" value="#{controller.claim_Id}"
				maxlength="6" />
			<!--<h:message for="claimId" />-->
			<br />

			<h:outputLabel for="uhid">UHID</h:outputLabel>
			<h:inputText id="uhid" value="#{controller.uhId}"
				maxlength="6" />
			<!--<h:message for="uhid" />-->
			<br />

			<h:outputLabel for="status">Status</h:outputLabel>
			<h:selectOneMenu id="status" value="#{controller.status}">
				<f:selectItem itemLabel="Select Status" itemValue="" />
				<f:selectItem itemLabel="ACCEPTED" itemValue="ACCEPTED" />
				<f:selectItem itemLabel="REJECTED" itemValue="REJECTED" />
			</h:selectOneMenu>

			<div class="form-group">
				<h:outputLabel for="fromDate">BeginDate</h:outputLabel>
				<h:inputText id="fromDate"
					value="#{controller.fromDate}"
					styleClass="form-control datepicker" autocomplete="off">
					<f:convertDateTime pattern="yyyy-MM-dd" />
				</h:inputText>

				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

				<h:outputLabel for="toDate">EndDate</h:outputLabel>
				<h:inputText id="toDate" value="#{controller.toDate}"
					styleClass="form-control datepicker" autocomplete="off">
					<f:convertDateTime pattern="yyyy-MM-dd" />
				</h:inputText>

			</div>
		</div>

		<br />
		<h:messages styleClass="warning"/>
		<br></br>
		<h:commandButton value="Search"
			action="#{controller.searchClaimHistory(controller)}"
			styleClass="button-submit" />
		<h:commandButton value="Reset" action="#{hmsImpl.clear()}"
			styleClass="button-reset" />
		<center>

			<h:panelGroup rendered="#{not empty claimHistoryList}">

				<h2 class="h1">
					<h:outputText value="Claim History Records" />
				</h2>
				<div class="search_table">
					<h:dataTable value="#{pagination.getClaimHistoryList()}" var="e"
						border="3" styleClass="custom-table">

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Claim Id" />
							</f:facet>
							<h:outputText id="claimId" value="#{e.claim_Id}" />
							<h:message for="claimId" />
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Provider Id" />
							</f:facet>
							<h:outputText id="providerId" value="#{e.providerId}" />
						</h:column>

						

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Subscripton id" />
							</f:facet>
							<h:outputText id="subscriptionId" value="#{e.subscription_Id}" />
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="UHID" />
							</f:facet>
							<h:outputText id="uhid" value="#{e.uhId}" />
							<h:message for="uhid" />

						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="From Date" />
							</f:facet>
							<h:outputText id="fromDate" value="#{e.fromDate}">
								<f:convertDateTime pattern="MM/dd/yyyy" />
							</h:outputText>
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="To Date" />
							</f:facet>
							<h:outputText id="toDate" value="#{e.toDate}">
								<f:convertDateTime pattern="MM/dd/yyyy" />
							</h:outputText>
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Status" />
							</f:facet>
							<h:outputText id="status" value="#{e.status}" />
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Comments" />
							</f:facet>
							<h:outputText id="comments" value="#{e.comments}" />
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Reimbursement Amount" />
							</f:facet>
							<h:outputText id="reimbursementAmount"
								value="#{e.reimbursementAmount}" />
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Patient Responsibility" />
							</f:facet>
							<h:outputText id="patientResponsibility"
								value="#{e.patientResponsibility}" />
						</h:column>

						<h:column>
							<f:facet name="header">
								<h:outputLabel value="Claim Applied On" />
							</f:facet>
							<h:outputText id="claimAppliedOn" value="#{e.claimAppliedOn}">
								<f:convertDateTime pattern="MM/dd/yyyy" />
							</h:outputText>
						</h:column>
						
						<h:column>
							<f:facet name="header">
								<h:outputLabel value="prescription id" />
							</f:facet>
							<h:outputText id="prescriptionId" value="#{e.prescription_Id}" />
						</h:column>

                       <h:column>
							<f:facet name="header">
								<h:outputLabel value="Visit id" />
							</f:facet>
							<h:outputText id="visitId" value="#{e.visit_Id}" />
						</h:column>
					</h:dataTable>
				</div>

				<center>
					<!--The paging buttons-->
					<h:commandButton value="first" action="#{paginationDao.pageFirst}"
						disabled="#{paginationDao.firstRow == 0}" styleClass="my-button" />
					<h:commandButton value="prev"
						action="#{paginationDao.pagePrevious}"
						disabled="#{paginationDao.firstRow == 0}" styleClass="my-button" />
					<h:outputText value="&nbsp;" escape="false" />
					<h:commandButton value="next" action="#{paginationDao.pageNext}"
						disabled="#{paginationDao.firstRow + paginationDao.rowsPerPage >= paginationDao.totalRows}"
						styleClass="my-button" />
					<h:outputText value="&nbsp;" escape="false" />
					<h:commandButton value="last" action="#{paginationDao.pageLast}"
						disabled="#{paginationDao.firstRow + paginationDao.rowsPerPage >= paginationDao.totalRows}"
						styleClass="my-button" />

					<h:outputText
						value="Page #{paginationDao.currentPage} / #{paginationDao.totalPages}"
						styleClass="my-button" />
					<br />
				</center>

			</h:panelGroup>
			<br /> <br />
			<h:outputText value="#{notFoundErr}" styleClass="notFoundError" />
		</center>
	</h:form>
</body>
	</html>
</f:view>

