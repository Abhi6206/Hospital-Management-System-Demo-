<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<f:view>
	<html>
<head>
<link rel="stylesheet" href="Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
</head>
<body>
	<h1>Claim Request</h1>
	<div id="container">
		<h:form id="form">

			<h:panelGrid id="panel" columns="3" border="0">
				<h:outputLabel styleClass="OPL" value="Claim Id" />
				<h:inputText disabled="true" id="claim_Id" value="#{req.claim_Id}"
					styleClass="noBorders">
				</h:inputText>
				<h:message styleClass="errorMsg" for="form:claim_Id"></h:message>



				<h:outputLabel styleClass="OPL" value="Name" />
				<h:inputText disabled="true" id="pName" value="#{patient.firstName}"
					styleClass="noBorders">
				</h:inputText>
				<h:message styleClass="errorMsg" for="form:pName"></h:message>


				<h:outputLabel styleClass="OPL" value="DOB" />
				<h:inputText disabled="true" id="dOB" value="#{patient.dob}"
					styleClass="noBorders">
					<f:convertDateTime pattern="yyyy/MM/dd" />
				</h:inputText>
				<h:message styleClass="errorMsg" for="form:dOB"></h:message>


				<h:outputLabel styleClass="OPL" value="Provider Id" />
				<h:inputText disabled="true" id="providerid"
					value="#{req.providerid}" styleClass="noBorders" />
				<h:message styleClass="errorMsg" for="form:providerid"></h:message>


				<h:outputLabel styleClass="OPL" value="Subs Id" />
				<h:inputText disabled="true" id="subsId"
					value="#{req.subscription_Id}" styleClass="noBorders" />
				<h:message styleClass="errorMsg" for="form:subsId"></h:message>

				<h:outputLabel styleClass="OPL" value="UHID" />
				<h:inputText disabled="true" id="uHID" value="#{req.uHID}"
					styleClass="noBorders" />
				<h:message for="form:uHID"></h:message>
				
				
				<h:outputLabel styleClass="OPL" value="Applied Date" />
				<h:inputText disabled="true" id="ClaimAppliedOn"
					value="#{req.claimAppliedOn}" styleClass="noBorders">
					<f:convertDateTime pattern="yyyy/MM/dd" />
				</h:inputText>
				<h:message styleClass="errorMsg" for="form:ClaimAppliedOn"></h:message>
				
				<h:outputLabel styleClass="OPL" value="Admit Date" />
				<h:inputText disabled="true" id="admit_date"
					value="#{req.admit_date}" styleClass="noBorders">
					<f:convertDateTime pattern="yyyy/MM/dd" />
				</h:inputText>
				<h:message styleClass="errorMsg" for="form:admit_date"></h:message>


				<h:outputLabel styleClass="OPL" value="Discharge Date" />
				<h:inputText disabled="true" id="disc_date" value="#{req.disc_date}"
					styleClass="noBorders">
					<f:convertDateTime pattern="yyyy/MM/dd" />
				</h:inputText>
				<h:message styleClass="errorMsg" for="form:disc_date"></h:message>

				<h:outputLabel styleClass="OPL" value="Bill Amount" />
				<h:inputText disabled="true" id="bill_Amount"
					value="#{req.bill_Amount}" styleClass="noBorders" />
				<h:message styleClass="errorMsg" for="form:bill_Amount"></h:message>

				<h:outputLabel styleClass="OPL" value="Misc Amount" />
				<h:inputText disabled="true" id="mis_Amount"
					value="#{req.mis_Amount}" styleClass="noBorders" />
				<h:message styleClass="errorMsg" for="form:mis_Amount"></h:message>


				<h:outputLabel styleClass="OPL" value="Disease" />
				<h:inputText disabled="true" id="disease" value="#{req.disease}"
					styleClass="noBorders" />
				<h:message styleClass="errorMsg" for="form:disease"></h:message>

				<h:outputLabel styleClass="OPL" value="Comments" />
				<h:inputText id="comments" value="#{req.comments}"
					styleClass="noBorders" />
				<h:message styleClass="errorMsg" for="form:comments"></h:message>
			</h:panelGrid>
			<div id="buttons">
				<h:commandButton styleClass="buttonStyle"
					action="#{controller.addClaimHistoryCon()}" value="Validate" />
				<h:commandButton styleClass="rejectButton"
					action="#{hmsImpl.sendRejectMsg(req)}" value="Reject Message" />
			</div>
		</h:form>
	</div>
</body>
	</html>
</f:view>