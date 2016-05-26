<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/j_spring_security_check" var="loginUrl" />
<div class="login-container">
	<div class="panel panel-default">
		<div class="panel-heading">
			<strong>Please sign insnn</strong>
		</div>
		<div class="panel-body">
			<form class="form-signin" name='loginForm'
				 method='POST' login-processing-url="/j_spring_security_check" authentication-failure-url="/login?error" username-parameter="username" password-parameter="password"/>
				<div class="form-group">
					<div class="input-group">
						<input type="text" id="username" name="username"
							class="form-control" placeholder="Username" required=""
							autofocus="" />
					</div>
				</div>
				<div class="form-group">
					<div class="input-group">
						<input type="password" id="password" name="password"
							class="form-control" placeholder="Password" required="" />
					</div>
				</div>
				<div class="form-group">
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<button class="btn btn-lg btn-primary btn-block btn-signin"
						type="submit">Sign in</button>
				</div>
			</form>
		</div>
	</div>
</div>