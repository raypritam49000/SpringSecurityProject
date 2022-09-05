<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<%@include file="bootstrap.jsp" %>
</head>
<body>
<%@include file="login_navbar.jsp" %>

	<div class="container-fluid" style="background: url('/images/login.jpg'); height: 700px; background-repeat: no-repeat; background-position: center; background-size: cover;">
		<div class="row">
			<div class="col col-md-6 offset-md-3">
			
				<div class="card shadow mt-5">

					<div class="card-header bg-success text-white">
					
						<h6 class="text-center"><i class="fa fa-user-circle fa-2x fa-spin" aria-hidden="true"></i></h6>
						<h6 class="text-center">Login Here</h6>
					</div>

					<div class="card-body">

						<form action="/do_login" method="post">

							<div class="mb-3">
								<label for="username" class="form-label">Enter Username</label> <input
									type="text" class="form-control" id="username" name="username" placeholder="Please enter here" required="required">
							</div>

							<div class="mb-3">
								<label for="password" class="form-label">Enter Password</label> <input
									type="password" class="form-control" id="password" name="password" placeholder="Please enter here" required="required">
							</div>
							
							
							<div class="mt-2">
							<button type="submit" class="btn btn-outline-primary">Login</button>
                            <button type="reset" class="btn btn-outline-danger">Reset</button>
                            </div>
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

</html>