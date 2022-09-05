<%@page import="com.employee.management.system.helper.Message"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<%@include file="bootstrap.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
	<div class="container-fluid" style="background: url('/images/register.jpg'); height: 800px; background-repeat: no-repeat; background-position: center; background-size: cover;">
		<div class="row mt-0">
			<div class="col col-md-6 offset-md-3">
			
				<div class="card shadow mt-5">

					<div class="card-header bg-success text-white">
					
					     <!--Start Alert-->
                       
                       <%
                         Message message = (Message)request.getSession().getAttribute("message");
                        if(message != null){
                    	%>
                    	<div class="alert alert-danger" role="alert ${message.type}">
                            <p class="text-center"><c:out value="${message.content}"></c:out></p>
                        </div>
                       <%
                         request.getSession().removeAttribute("message");
                       }   
                       %>
                       
                        <!--End Alert-->
					
						<h6 class="text-center"><i class="fa fa-user-circle fa-2x fa-spin" aria-hidden="true"></i></h6>
						<h6 class="text-center">Register Here</h6>
					</div>

					<div class="card-body">

						<form action="/do_register" method="post">

							<div class="mb-3">
								<label for="username" class="form-label">Enter Username</label>
								
								<input type="text" class="${result.hasFieldErrors('username') ? 'is-invalid form-control' : 'form-control'}" 
								id="username" name="username" placeholder="Please enter here">
								
								<c:forEach var="error" items="${result.getFieldErrors('username')}">
								  <div class="invalid-feedback"><c:out value="${error.getDefaultMessage()}"></c:out></div>
								</c:forEach>
							</div>

							
							<div class="mb-3">
								<label for="email" class="form-label">Enter Email</label> 
								<input type="email" class="${result.hasFieldErrors('email') ? 'is-invalid form-control' : 'form-control'}" 
									id="email" name="email" placeholder="Please enter here">
									
							    <c:forEach var="error" items="${result.getFieldErrors('email')}">
								  <div class="invalid-feedback"><c:out value="${error.getDefaultMessage()}"></c:out></div>
								</c:forEach>
							</div>

							<div class="mb-3">
								<label for="role" class="form-label">Enter Role</label> 
								<input type="text" class="${result.hasFieldErrors('role') ? 'is-invalid form-control' : 'form-control'}" 
								id="role" name="role" placeholder="Please enter here" >
								
								<c:forEach var="error" items="${result.getFieldErrors('role')}">
								  <div class="invalid-feedback"><c:out value="${error.getDefaultMessage()}"></c:out></div>
								</c:forEach>
							</div>

							<div class="mb-3">
								<label for="password" class="form-label">Enter Password</label> 
								<input type="password" class="${result.hasFieldErrors('password') ? 'is-invalid form-control' : 'form-control'}"  
								id="password" name="password" placeholder="Please enter here" >
								
								<c:forEach var="error" items="${result.getFieldErrors('password')}">
								  <div class="invalid-feedback"><c:out value="${error.getDefaultMessage()}"></c:out></div>
								</c:forEach>
								
							</div>
							
							 <!-- terms and conditions -->

                      		<div class="form-group form-check">
                          	<input type="checkbox" id="agreement" class="form-check-input" name="agreement">
                          	<label for="agreement">Accepts terms and conditions</label>
							</div>
							
							<div class="mt-2">
							<button type="submit" class="btn btn-outline-primary">Register</button>
                            <button type="reset" class="btn btn-outline-danger">Reset</button>
							</div>
                            
						</form>

					</div>
				</div>
			</div>
		</div>
	</div>
</html>