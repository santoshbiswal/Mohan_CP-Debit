<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Registration</title>
	</head>
	<body>
		<h1>DTDC USER REGISTRATION</h1>
		<form:form modelAttribute="dtdcRegistration">
		
			<div id="error-div">
				<span style="color: red">
					<form:errors path="*"/>
				</span>
			</div>
			
			<h3>ADDRESS</h3>
			
			<div id="address-div">
				<table>
					<tr>
						<td>Address Line1</td>
						<td>
							<form:input path="addressLine1"/>
						</td>
					</tr>
					<tr>
						<td>Address Line2</td>
						<td>
							<form:input path="addressLine2"/>
						</td>
					</tr>
					<tr>
						<td>City</td>
						<td>
							<form:input path="city"/>
						</td>
					</tr>
					<tr>
						<td>State</td>
						<td>
							<form:input path="state"/>
						</td>
					</tr>
					<tr>
						<td>Country</td>
						<td>
							<form:input path="country"/>
						</td>
					</tr>
					<tr>
						<td>Zip</td>
						<td>
							<form:input path="zip"/>
						</td>
					</tr>
				</table>
			</div>
			
			<h3>USER</h3>
			
			<div id="user-div">
				<table>
					<tr>
						<td>Name</td>
						<td>
							<form:input path="name"/>
						</td>
					</tr>
					<tr>
						<td>Phone</td>
						<td>
							<form:input path="phone"/>
						</td>
					</tr>
					<tr>
						<td>Email</td>
						<td>
							<form:input path="email" type="email"/>
						</td>
					</tr>
					<tr>
						<td>DOB</td>
						<td>
							<form:input path="dob" type="date"/>
						</td>
					</tr>
				</table>
			</div>
			
			<h3>Login-Info</h3>
			
			<div id="login-info-div">
				<table>
					<tr>
						<td>Username</td>
						<td>
							<form:input path="username"/>
						</td>
					</tr>
					<tr>
						<td>Password</td>
						<td>
							<form:input path="password" type="password"/>
						</td>
					</tr>
					<tr>
						<td>Confirm Password</td>
						<td>
							<input type="password" name="confirmpass"/>
						</td>
					</tr>
					<tr>
						<td>User Type</td>
						<td>
							<form:select path="usertype">
								<form:option value="">select</form:option>
								<form:option value="corporate">corporate</form:option>
								<form:option value="channel-partner">channel-partner</form:option>
								<form:option value="delivery-partner">delivery-partner</form:option>
							</form:select>
						</td>
					</tr>
				</table>
			</div>
			
			<!-- <h3>Account</h3> -->
			
			<!--<div id="account-div">
					<table>
						<tr>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>  -->
			
			<div id="submit-div">
				<table>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><input type="submit" value="Register"></td>
						<td><input type="reset" value="Reset"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
		</form:form>
		
		<a href="/dtdc-home.htm">Back</a>
	</body>
</html>