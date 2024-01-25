<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%> 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%> 

<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h:form> 
   

        		<center>
                <h2><h:outputText value="Insurance Records"/></h2>
            </center>
        <h:dataTable value="#{ejbImpl.showClaimDetails()}" var="i" border="3">
              	     <h:column>
                    <f:facet name="header">
                    	<h:outputLabel value="Claim ID" />
                    </f:facet>
                    <h:outputText value="#{i.claim_Id}"/>
                </h:column>
                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Providerid" />
                    </f:facet>
                    <h:outputText value="#{i.providerid}"/>
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Subscription Id" />
                    </f:facet>
                    <h:outputText value="#{i.subscription_Id}"/>
                </h:column>
                
                                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="UHID" />
                    </f:facet>
                    <h:outputText value="#{i.uHID}"/>
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Admit_date" />
                    </f:facet>
                    <h:outputText value="#{i.admit_date}"/>
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="disc_date" />
                    </f:facet>
                    <h:outputText value="#{i.disc_date}"/>
                </h:column>
				     <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Bill_Amount" />
                    </f:facet>
                    <h:outputText value="#{i.bill_Amount}"/>
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Mis_Amount" />
                    </f:facet>
                    <h:outputText value="#{i.mis_Amount}"/>
                </h:column>
        		               <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Disease" />
                    </f:facet>
                    <h:outputText value="#{i.disease}"/>
                </h:column>
                      <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Comments" />
                    </f:facet>
                    <h:outputText value="#{i.comments}"/>
                </h:column>
                </h:dataTable>
        </h:form>
    </body>
</html>
</f:view>
