

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<f:view>
	<html>
<head>
<title>Login V4</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="icon" type="image/png" href="images/icons/favicon.ico" />


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<link rel="stylesheet" type="text/css"
	href="vendor/daterangepicker/daterangepicker.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/main.css">

<meta name="robots" content="noindex, follow">
</head>
<h:form id="form">

	<div class="limiter">
		<div class="container-login100"
			style="background-image: url('image/img4	.jpg');">
			<div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-54">
				<div class="login100-form validate-form">
					<span class="login100-form-title p-b-49"> Admin Sign Up</span>

					<div class="wrap-input100 validate-input m-b-23"
						data-validate="Username is reauired">
						<h5 style="color: #666; margin-bottom: 5%;">Name</h5>
						<i class="fa fa-user"></i>
						<h:inputText id="name" value="#{admin.name}" />
					</div>
					<h:message for="form:name" style="color:red"></h:message>
					<div class="wrap-input100 validate-input m-b-23"
						data-validate="Username is reauired">
						<h5 style="color: #666; margin-bottom: 5%;">Email</h5>
						<i class="fa fa-envelope"></i>

						<div id="passInput">
							<h:inputText id="email" value="#{admin.email}" />
						</div>
					</div>
					<h:message for="form:email" style="color:red"></h:message>
					<div class="wrap-input100 validate-input m-b-23"
						data-validate="Username is reauired">
						<h5 style="color: #666; margin-bottom: 5%;">UserName</h5>
						<i class="fa fa-user"></i>
						<h:inputText id="username" value="#{admin.username}" />
					</div>
					<h:message for="form:username" style="color:red"></h:message>
					<div class="wrap-input100 validate-input m-b-23"
						data-validate="Password is required">
						<h5 style="color: #666; margin-bottom: 5%;">Password</h5>
						<i class="fa fa-lock"></i>
						<div class="icon">
							<i class="fa fa-eye-slash" id="showPass"></i> <i
								class="fa fa-eye" id="showPassSlash" style="display: none;"></i>
						</div>
						<div id="passInputWrapper">
							<h:inputSecret id="passInput" value="#{admin.password}"
								autocomplete="on" />
						</div>
					</div>
					<h:message for="form:passInput" style="color:red"></h:message>
					<div class="validate-input m-b-23"
						data-validate="Security Question is required">
						<h5 style="color: #666; margin-bottom: 5%;">Security Question</h5>
						<div class="field">
							<h:selectOneMenu id="securityQuestion"
								style="width: 390px;
    padding: 5px;
    text-align: center;
    border-radius: 10px;
    font-size: 17px;"
								value="#{admin.securityQuestion}">
								<f:selectItem itemLabel="Select Security Question" itemValue="" />
								<f:selectItem itemLabel="What is the name of your first pet?"
									itemValue="What is the name of your first pet?" />
								<f:selectItem itemLabel="What is your mother's maiden name?"
									itemValue="What is your mother's maiden name?" />
								<f:selectItem itemLabel="What is your favorite book?"
									itemValue="What is your favorite book?" />
								<f:selectItem itemLabel="What is the first school you attended?"
									itemValue="What is the first school you attended?" />
								<f:selectItem itemLabel="What is the birthplace of your father?"
									itemValue="What is the birthplace of your father?" />
							</h:selectOneMenu>
							<span class="message"><h:message
									for="form:securityQuestion" style="color:red"/></span>
						</div>
					</div>
					<div class="wrap-input100 validate-input m-b-23"
						data-validate="Password is required">
						<h5 style="color: #666; margin-bottom: 5%;">Security Answer</h5>

						<div id="passInputWrapper">
							<h:inputText id="ans" value="#{admin.securityAnswer}"
								autocomplete="on" />
						</div>
					</div>
					<h:message for="form:ans" style="color:red"></h:message>



					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<!-- <button class="login100-form-btn">
                                Login
                            </button> -->
							<h:commandButton action="#{adminController.adminSignInValidate()}"
								value="Sign In"
								style="height: 35px;
    outline: none;
    border: none;
    position: relative;
    background-color: rgba(0,0,0,-0.5);
    color: white;
    font-size: 20px;" />


						</div>

					</div>
					<div class="text-right p-t-8 p-b-31">
						<a href="AdminLogin.jsf">Already have an account?Login. </a>
					</div>


				</div>
			</div>
		</div>
	</div>
	<div id="dropDownSelect1"></div>


	<script>
		$(document).ready(function() {
			$('#showPass, #showPassSlash').on('click', function() {
				var passInput = $("#passInputWrapper input");
				var showPassIcon = $("#showPass");
				var showPassSlashIcon = $("#showPassSlash");

				if (passInput.attr('type') === 'password') {
					passInput.attr('type', 'text');
					showPassIcon.hide();
					showPassSlashIcon.show();
				} else {
					passInput.attr('type', 'password');
					showPassIcon.show();
					showPassSlashIcon.hide();
				}
			});
		});

		function retainPassword() {
			var passwordInput = document.getElementById('password');
			var passwordValue = passwordInput.value;

			// Store the password value as a data attribute
			passwordInput.setAttribute('data-previous-value', passwordValue);
			passwordInput.value = passwordValue;
		}
	</script>

</h:form>

	</html>
</f:view>
