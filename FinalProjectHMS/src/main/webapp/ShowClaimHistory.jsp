<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<f:view>
	<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
body {
	font-family: Arial, sans-serif;
}

h2 {
	color: #333;
	text-align: center;
}

h:dataTable {
	width: 80%;
	margin: 0 auto; /* Center the table */
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #f5f5f5;
}


</style>
</head>
<body>
	<h2>
		<h:outputText value="Claim History" />
	</h2>
	<h:dataTable value="#{hmsImpl.showClaimHistory()}" var="e" border="3">
		<div>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="History Id" />
				</f:facet>
				<h:outputText value="#{e.claimHistoryId}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Claim Id" />
				</f:facet>
				<h:outputText value="#{e.claim_Id}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Provider Id" />
				</f:facet>
				<h:outputText value="#{e.providerId}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Subs Id" />
				</f:facet>
				<h:outputText value="#{e.subscription_Id}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="UH Id" />
				</f:facet>
				<h:outputText value="#{e.uhId}" />
			</h:column>

			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Applied Date" />
				</f:facet>
				<h:outputText value="#{e.claimAppliedOn.toString().substring(0,10)}" />
			</h:column>

			<h:column>
				<f:facet name="header">
					<h:outputLabel value="From" />
				</f:facet>
				<h:outputText value="#{e.fromDate.toString().substring(0,10)}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="To" />
				</f:facet>
				<h:outputText value="#{e.toDate.toString().substring(0,10)}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Reimburse Amount" />
				</f:facet>
				<h:outputText value="#{e.reimbursementAmount}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Patient Res" />
				</f:facet>
				<h:outputText value="#{e.patientResponsibility}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Status" />
				</f:facet>
				<h:outputText value="#{e.status}" />
			</h:column>
			<h:column>
				<f:facet name="header">
					<h:outputLabel value="Comments" />
				</f:facet>
				<h:outputText value="#{e.comments}" />
			</h:column>
		</div>
	</h:dataTable>
</body>
	</html>
</f:view>