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
		<title>Login</title>
	</head>
	<body>
		<h1>DTDC USER LOGIN</h1>
		<form:form modelAttribute="dtdcLogin">
			<div id="error-div">
				<span style="color: red">
					<form:errors path="*"/>
				</span>
			</div>
			<div id="login-div">
				<table>
					<tr>
						<td>UserName</td>
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
						<td>UserType</td>
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
			<div id="login-submit-div">
				<table>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><input type="submit" value="login"></td>
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