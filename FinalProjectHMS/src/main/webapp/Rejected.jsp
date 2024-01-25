<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html>
<f:view>
	<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8" />
<title>Insert title here</title>
<style>
.approved {
	text-align: center;
	color: #d9534f;
	transform: translateY(-50%);
	opacity: 0;
	transition: opacity 2s ease-in-out;
}
</style>
<script>
  setTimeout(function() {
    document.getElementById("approvedText").style.opacity = "1";
  }, 0); // Delay for appearance

  setTimeout(function() {
    document.getElementById("approvedText").style.opacity = "0";
  }, 2000); // Delay for disappearance (2000ms = 2s)
</script>
</head>
<body>
	<div><jsp:include page="ShowClaimHistory.jsp" /></div>

	<h2 class="approved" id="approvedText">
		<h:outputLabel value="Rejected ❌" /><br/>
		<h:outputLabel value="Reject message sent."></h:outputLabel>
	</h2>
</body>
</html>
</f:view>
