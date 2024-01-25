<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
 
<f:view>
<html>
    <head>
    <style>
     #header{
            	box-shadow: 0px 4px 14px 0px rgba(0, 0, 0, 0.05);
                color: white;
                text-align: center;
                padding: 10px;
                display:flex;
                flex-direction: row;
                justify-content: space-between;
                padding-top: 0px;
                padding-bottom: 0px;
                background-color: #FFFFFF;
                align-items: center;
                
                      }
                      #header, h2{
                      margin: 0px;
                      }
    
    body{
    background-image: url("wp8003033-hospitals-wallpapers.jpg");
    filter: drop-shadow(2px 4px 6px black);
    }
     

.covbox{
background-color:  #bbdefb;
}
        /* Add the blinking animation */
        @keyframes blink {
            50% {
                opacity: 0;   
            }
        }
 
       h2 {
        animation: shimmer 2s infinite , blink 4s infinite;
        border: 3px solid black;
        padding: 10px;
        display: inline-block;
        border-radius: 8px;
        font-style: bold;
        /* background-image: linear-gradient(45deg, #3498db, #e74c3c, #f39c12); */
        background-image: linear-gradient(to bottom right, red , blue); /* Multi-color gradient background */
        -webkit-background-clip: text;
        background-clip: text;
        color: transparent;
        
    }
 
    @keyframes shimmer {
        0% {
            background-position: -200%;
        }
        100% {
            background-position: 200%;
        }
    }
      @keyframes blink {
        0%, 50%, 100% {
            opacity: 1;
        }
        25%, 75% {
            opacity: 0;
        }
        
    }
    /* DataTable styling */
.funkyDataTable {
	
	opacity: 0.7;
    font-family: 'Arial', sans-serif;
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}
 
.funkyDataTable th, .funkyDataTable td {
    border: 1px solid #ddd;
    padding: 6px;
    text-align: left;
}
 
.funkyDataTable th {
    background-color: #f2f2f2;
    cursor: pointer;
}
 
.funkyDataTable th a {
	text-align:center;
    text-decoration: none;
    color: #333;
}
 
/* .funkyDataTable th:hover {
    background-color: #ddd;
} */
 
.funkyDataTable td {
    background-color: aliceblue;
}
 
.funkyDataTable tr:nth-child(even) {
    background-color: #f9f9f9;
}
/* Pagination buttons styling */
.funkyPagination {
	background-color: #4CAF50;
	color: white;
	padding: 5px 10px;
	border: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	transition-duration: 0.4s;
}
 
.funkyPagination:hover {
	background-color: #45a049;
}
 
.funkyPagination:disabled {
	background-color: #cccccc;
	cursor: not-allowed;
}

   </style>
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    
 <script>
        function convertToUppercase(UpperInput) {
        	UpperInput.value = UpperInput.value.toUpperCase();
        }
    </script>
        <script>
    function toggleInputText() {
        var selectedValue = document.getElementById("searchForm:searchParameter").value;
        var ProviderIDinput = document.getElementById("searchForm:ProviderIDinput");
        var Claiminput = document.getElementById("searchForm:Claiminput");
        var UHIDinput = document.getElementById("searchForm:UHIDinput");
        var Diseaseinput = document.getElementById("searchForm:Diseaseinput");
        var radioInput = document.getElementById("searchForm:radioInput");
        var StartDateinput = document.getElementById("searchForm:StartDateinput");
        var EndDateinput = document.getElementById("searchForm:EndDateinput");

        // Hide radio buttons initially
        radioInput.style.display = "none";
        ProviderIDinput.style.display = "none";
        Claiminput.style.display = "none";
        UHIDinput.style.display = "none";
        Diseaseinput.style.display = "none";
        StartDateinput.style.display = "none";
        EndDateinput.style.display = "none";

        
        if (selectedValue === "providerid") {
            ProviderIDinput.style.display = "inline-block";
            radioInput.style.display = "block";
        } else if (selectedValue === "claim_Id") {
            Claiminput.style.display = "inline-block";
            radioInput.style.display = "block";
        } else if (selectedValue === "uHID") {
            UHIDinput.style.display = "inline-block";
            radioInput.style.display = "block";
        } else if (selectedValue === "disease") {
            Diseaseinput.style.display = "inline-block";
            radioInput.style.display = "block";
        }
    

        // Show/Hide Start  Date input field based on the selected parameter
        StartDateinput.style.display = (selectedValue === "admit_date") ? "inline-block" : "none";

        // Show/Hide End Date input field based on the selected parameter
        EndDateinput.style.display = (selectedValue === "admit_date") ? "inline-block" : "none";
    }

    // Attach the toggleInputText function to the change event of the searchParameter dropdown
   // document.getElementById("searchForm:searchParameter").addEventListener('change', toggleInputText);

    // Initialize the input fields based on the default value
    document.addEventListener('DOMContentLoaded', toggleInputText);
</script>

     
        <script type="text/javascript">
        $(document).ready(function() {
     		$( ".datepicker" ).datepicker({
     			dateFormat: 'yy-mm-dd',
     			changeMonth: true,
     			changeYear: true,
     			yearRange: "1960:2050"
     		});
     	});
  
        </script>
        
        <script>
    var lastClickTime = 0;

    function toggleRadioButton(radioButton) {
        var currentTime = new Date().getTime();
        var elapsedTime = currentTime - lastClickTime;
        
        if (elapsedTime < 300) { // Assuming double-click within 300 milliseconds
            radioButton.checked = false; // Unselect the radio button on double click
        }

        lastClickTime = currentTime;
    }
</script>
   <script>
    function clearInput(input) {
        input.value = '';
    }
</script> 
    </head>
    <body>
    <!-- <div class="loader">></div> -->
    <div id="header">
        <img src="Capture.PNG" style="height:60px; width:101px;"/>
        <div style="display: flex; flex-direction: row-reverse; row; gap:20px;">
      <!--   <a href="/" style="text-decoration: none;"><p style="font-size: 18px; font-weight: 600; color:#333333; font-family: sans-serif; cursor: pointer;">Home</p></a> -->
 
          <a href="Menu.jsf" style="text-decoration: none;">
            <p style="font-size: 18px; font-weight: 600; color:#333333; font-family: sans-serif; cursor: pointer;">Home</p>
        </a>              
        </div>
        </div>

    <center>
        <h:form id="searchForm" >
        </br></br>
        <h2><h:outputText value="SEARCH PLANS"/></h2></br></br>
        <center></center>
        <h:selectOneMenu id="searchParameter" onchange="toggleInputText()"  style="background-color:#bbdefb; color:black; width:250px; height:27px">
            <f:selectItem itemValue="Select Parameter" />
            <f:selectItem itemValue="providerid" itemLabel="Provider ID" />
            <f:selectItem itemValue="claim_Id" itemLabel="Claim ID" />
            <f:selectItem itemValue="uHID" itemLabel="UHID" />
            <f:selectItem itemValue="disease" itemLabel="Disease" />
            <f:selectItem itemValue="admit_date" itemLabel="Admit Date Range" />
        </h:selectOneMenu>
        </center>
        <center>
        	
        <h:panelGroup id="ProviderIDinput" style= "display: #{(param.selectedValue eq 'providerid' or not empty insurance.providerid) ? 'inline-block' : 'none'};">
            <h:outputLabel for="providerid">Provider ID</h:outputLabel><br/>
            <h:inputText id="providerid" value="#{insurance.providerid}" autocomplete="off" onfocus="clearInput(this)" style="width:210px; height:27px" onkeyup="convertToUppercase(this)"/><br/>
            <b><h:messages for="providerid" style="color: red; font-size:0.5cm;" /></b>
        </h:panelGroup>
        
        <h:panelGroup id="Claiminput" style= "display: #{(param.selectedValue eq 'claim_Id' or not empty insurance.claim_Id) ? 'inline-block' : 'none'};">
            <h:outputLabel for="claim_Id">Claim ID</h:outputLabel><br/>
            <h:inputText id="claim_Id" value="#{insurance.claim_Id}" autocomplete="off" onfocus="clearInput(this)" style="width:210px; height:27px" onkeyup="convertToUppercase(this)"/><br/>
           <b></b><h:messages for="claim_Id" style="color: red; font-size:0.5cm;" /><b>
        </h:panelGroup>

        <h:panelGroup id="Diseaseinput" style= "display: #{(param.selectedValue eq 'disease' or not empty insurance.disease) ? 'inline-block' : 'none'};">
            <h:outputLabel for="disease">Disease</h:outputLabel><br/>
            <h:inputText id="disease" value="#{insurance.disease}" autocomplete="off" onfocus="clearInput(this)" style="width:210px; height:27px"/><br/>
             <b><h:messages for="disease" style="color: red; font-size:0.5cm;" /></b> 
        </h:panelGroup>
    
    <h:panelGroup id="UHIDinput" style= "display: #{(param.selectedValue eq 'uHID' or not empty insurance.uHID) ? 'inline-block' : 'none'};">
        <h:outputLabel for="uHID">UHID</h:outputLabel><br/>
        <h:inputText id="uHID" value="#{insurance.uHID}" autocomplete="off" onfocus="clearInput(this)" style="width:210px; height:27px" onkeyup="convertToUppercase(this)"/><br/>
       <b><h:messages for="uHID" style="color: red; font-size:0.5cm;" /></b>
    </h:panelGroup>

<h:panelGroup id="StartDateinput" style= "display: #{(param.selectedValue eq 'startdate' or not empty ejbImpl.startdate) ? 'inline-block' : 'none'};">
    <h:outputLabel for="startdate" value="Admit Start Date " /><br/>
    <h:inputText value="#{ejbImpl.startdate}" id="startdate" styleClass="form-control datepicker" autocomplete="off" onfocus="clearInput(this)"  onkeydown="return false"  style="width:210px; height:27px">
        <f:convertDateTime pattern="yyyy-MM-dd" />
    </h:inputText><br/><br/>
     <b><h:message for="startdate" style="color: red; font-size:0.5cm;" /></b> 
</h:panelGroup>
<br/>
<!-- Keep the end date input and error message outside of the StartDateinput panelGroup -->
<h:panelGroup id="EndDateinput" style= "display: #{(param.selectedValue eq 'enddate' or not empty ejbImpl.enddate) ? 'inline-block' : 'none'};">
    <h:outputLabel for="enddate" value ="Admit End Date " /><br/>
    <h:inputText value="#{ejbImpl.enddate}" id="enddate" styleClass="form-control datepicker" autocomplete="off" onfocus="clearInput(this)"  onkeydown="return false"  style="width:210px; height:27px">
        <f:convertDateTime pattern="yyyy-MM-dd" /><br/>
    </h:inputText><br/><br/>
		  <b><h:message for="enddate" style="color: red; font-size:0.5cm;" /></b> 
</h:panelGroup>

        <br/>
        <h:panelGroup id="radioInput" style= "display: #{(param.selectedValue eq 'matchingType' or not empty ejbImpl.matchingType) ? 'block' : 'none'};">
       <h:selectOneRadio value="#{ejbImpl.matchingType}" id="matchingType" onclick="toggleRadioButton(this)">
     <f:selectItem itemValue="startsWith" itemLabel="Starts With"/>
     <f:selectItem itemValue="contains" itemLabel="Contains"/>
     </h:selectOneRadio>
     </h:panelGroup>
     </center>
     <br/><br/>
		<center>
        <h:commandButton id="searchButton"  action="#{ejbImpl.allsearchClaimNew(ejbImpl.startdate,ejbImpl.enddate,insurance.providerid, insurance.claim_Id, insurance.uHID, insurance.disease,ejbImpl.matchingType)}" value="Search" style="background-color:#a5d6a7; color:black; width:160px; height:27px">
        
        </h:commandButton>
           <br/><br/>  <h:commandButton  action="#{ejbImpl.clear()}"  value="Reset" style="background-color:#e57373; color:black; width:160px; height:27px" /><br/><br/>
  	    </center>
     </h:form>
    <center>
    <b><h:messages id="messages" globalOnly="true" style="color: red; font-size:0.7cm;" /></b>
    </center>
    </center>
   <h:form id="claimForm">
        <div class="center">
            <h:panelGroup id="claimPanelGroup" rendered="#{not empty paginationDao.getInsuranceClaimList()}">
                    <!-- Only display the table when patientList is not empty -->
                    <center>
                    <h2><h:outputText value="Claim Records"/></h2>
                    </center>
                </center>
                <center>
                <h:dataTable id="claim" value="#{paginationDao.getInsuranceClaimList()}" var="i" border="3" styleClass="funkyDataTable">
                    <%-- <h:dataTable value="#{claimList}" var="i" border="3"> --%>
                    
                          <h:column>
                    <f:facet name="header">
                    	<%-- <h:outputLabel value="Claim ID" /> --%>
                    	<h:outputLabel> Claim ID
				 <h:commandLink action="#{paginationDao.sortbyClaimID()}"
							style="color: black">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.claim_Id}"/>
                </h:column>
                
                    <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="Providerid" /> --%>
                    	<h:outputLabel> Provider ID
					<h:commandLink action="#{paginationDao.sortbyProviderID()}"
							style="color: black">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					<%-- <h:commandLink action="#{paginationDao.sortProviderByProviderdesc()}" ></h:commandLink> --%>
					</h:outputLabel>
                    </f:facet>
                    <h:commandButton value="#{i.providerid}"
                     action="#{ejbImpl.SearchProvider(i.providerid)}" styleClass="commandButtonStyle"
                      style="background-color: grey; color: black; padding: 10px;"/>
                   
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="Subscription Id" /> --%>
                    	<h:outputLabel> Subscription ID
					<h:commandLink action="#{paginationDao.sortbySubscription()}"
							style="color: black">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.subscription_Id}"/>
                </h:column>
                
                                    <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="UHID" /> --%>
                    	<h:outputLabel> UHID
					<h:commandLink action="#{paginationDao.sortbyUHID()}"
							style="color: black">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:commandButton value="#{i.uHID}"
                     action="#{ejbImpl.SearchUHID(i.uHID)}" styleClass="commandButtonStyle" style="background-color: grey; color: black; padding: 10px;"/>
                    <%-- <h:outputText value="#{i.uHID}"/> --%>
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="Admit_date" /> --%>
                    	<h:outputLabel> Admit Date
				<h:commandLink action="#{paginationDao.sortbyAdmitDate()}" 
							style="color: black"> 
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.admit_date}"/>
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="disc_date" /> --%>
                    	<h:outputLabel> Discharge Date
				<h:commandLink action="#{paginationDao.sortbyDiscDate()}"
							style="color: black" styleClass="decoration">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.disc_date}"/>
                </h:column>
				     <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="Bill_Amount" /> --%>
                    	<h:outputLabel> Bill Amount
					<h:commandLink action="#{paginationDao.sortbyBill()}" 
							style="color: black">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.bill_Amount}"/>
                </h:column>
                                    <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="Mis_Amount" /> --%>
                    	<h:outputLabel> Miscellaneous Amount
				<h:commandLink action="#{ejbImpl.sortbyMisAmount()}"
							style="color: black">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.mis_Amount}"/>
                </h:column>
        		               <h:column>
                     <f:facet name="header">
                    	<%-- <h:outputLabel value="Disease" /> --%>
                    	<h:outputLabel> Disease
					<h:commandLink action="#{paginationDao.sortbyDisease()}"
							style="color: black">
							<span class="arrow" style="color:blue;">&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.disease}"/>
                </h:column>
                      <h:column>
                       <f:facet name="header">
                    	<%-- <h:outputLabel value="Comments" /> --%>
                    	<h:outputLabel> Comments
					<h:commandLink action="#{paginationDao.sortbyComments()}"
							style="color: black" styleClass="decoration">
							<span class="arrow" style="color:blue;" >&#8645;</span>
						</h:commandLink>
					</h:outputLabel>
                    </f:facet>
                    <h:outputText value="#{i.comments}"/>
                </h:column>
                         <h:column>
                     <f:facet name="header">
                    	<h:outputLabel value="Status" />
                    </f:facet>
                    <h:outputText value="#{i.status}"/>
                </h:column>
                      <h:column>
                       <f:facet name="header">
                    	<h:outputLabel value="Validate" />
                    </f:facet>
                    <h:commandButton action="#{hmsImpl.searchByClaimId(i.claim_Id)}"  value="Check" style="background-color: grey; color: black; padding: 10px;" />
                </h:column>
                    </h:dataTable>
            
            
              <h:commandButton value="first" action="#{paginationDao.pageFirst}"
                         disabled="#{paginationDao.firstRow == 0}" styleClass="funkyPagination"/>
        <h:commandButton value="prev" action="#{paginationDao.pagePrevious}"
                         disabled="#{paginationDao.firstRow == 0}" styleClass="funkyPagination" />
        
        <!-- Spacing between buttons -->
        <h:outputText value="&nbsp;" escape="false"/>

        <!-- Next and Last buttons on the right -->
        <h:commandButton value="next" action="#{paginationDao.pageNext}"
                         disabled="#{paginationDao.firstRow + paginationDao.rowsPerPage >= paginationDao.totalRows}" styleClass="funkyPagination"/>
        <h:commandButton value="last" action="#{paginationDao.pageLast}"
                         disabled="#{paginationDao.firstRow + paginationDao.rowsPerPage >= paginationDao.totalRows}" styleClass="funkyPagination" />
        
        <!-- Page information -->
        <h:outputText value="&nbsp;" escape="false"/>
        <h:outputText value="Page #{paginationDao.currentPage} / #{paginationDao.totalPages}" styleClass="page-info"/>
   
            
                </center>
                </h:panelGroup>
            </h:form>
   <script>
    // Function to clear the DataTable body using jQuery
    function clearDataTable() {
        var dataTableBody = $("#claimForm\\:claim tbody");
        dataTableBody.find("tr").remove();
    }

    // Function to clear the form fields using jQuery
    function clearForm() {
        // Modify this function based on your actual form clearing logic
        // For example, you might reset input values or hide/display certain sections
        $("#searchForm\\:providerid").val("");
        $("#searchForm\\:claim_Id").val("");
        $("#searchForm\\:uHID").val("");
        $("#searchForm\\:disease").val("");
        $("#searchForm\\:radioInput").val("");
        $("#searchForm\\:admit_date").val("");
    }

    // Function to check for error messages and clear the DataTable and form using jQuery
    function checkAndClear() {
        // Check if there are any error messages
        var errorMessages = $("#messages").text().trim();
        if (errorMessages !== "") {
            clearDataTable();
            clearForm();

            // Clear the panelGroup
            var panelGroup = $("#claimForm\\:claimPanelGroup");
            panelGroup.empty();
        }
    }

    // Call the function on page load
    $(document).ready(function() {
        checkAndClear();
    });
</script>
    </body>
</html>
</f:view>