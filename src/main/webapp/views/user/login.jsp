<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Online Entertainment</title>
<%@ include file="/common/head.jsp"%>

</head>

<style>
.line-container {
	display: flex;
	align-items: center;
}

.line {
	flex-grow: 1;
	border: none;
	border-top: 1px solid #ccc;
}

.or-text {
	margin: 0 10px;
	font-weight: bold;
	font-family: Arial, sans-serif;
	color: #555;
}
</style>

<body>
	<main>
		<%@ include file="/common/header.jsp"%>
		<section class="ticket-section section-padding">
			<div class="section-overlay"></div>

			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-10 mx-auto">
						<form class="custom-form ticket-form mb-5 mb-lg-0" action="login"
							method="post" role="form">
							<h2 class="text-center mb-4">
								<i class="fas fa-user-circle login-icon"></i> Login
							</h2>
							<div class="ticket-form-body">
								<div class="row">
									<input type="text" name="username" id="login-form-username"
										class="form-control" placeholder="Username" required>
									<input type="password" name="password" id="login-form-password"
										class="form-control mb-1" placeholder="Password" required>
								</div>

								<div class="row">
									<div class="col-6">
										<div class="form-check form-control border-0 my-1">
											<input class="form-check-input" type="checkbox" value=""
												id="remember-checkbox"> <label
												class="form-check-label" for="remember-checkbox">
												Remember me </label>
										</div>
									</div>
									<div class="col-6 text-end">
										<div class="form-check form-control border-0 my-1">
											<a href="forgot-pass" class="text-decoration-none">Forgot
												password?</a>
										</div>
									</div>
								</div>

								<div class="row mt-3">
									<div class="col-12">
										<button type="submit" class="form-control">Login</button>
									</div>
								</div>

								<div class="row mt-3">
									<div class="col-12 text-center">
										<div class="line-container">
											<hr class="line">
											<p class="or-text">Or login with</p>
											<hr class="line">
										</div>
									</div>
								</div>

								<div class="row mt-3">
									<div class="col-6 text-end">
										<a
											href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Asm_java4/login&response_type=code
    &client_id=934083761507-99hammgf443ud4sdugc5ls6jlv7p5b03.apps.googleusercontent.com&approval_prompt=force"
											type="button" class="btn btn-google"> <i
											class="fab fa-google text-primary"></i> Login with Google
										</a>
									</div>
									<div class="col-6 text-start">
										<button type="button" class="btn btn-facebook">
											<i class="fab fa-facebook-f text-primary"></i> Login with
											Facebook
										</button>
									</div>
								</div>

								<div class="row mt-3">
									<div class="col-12">
										<p class="text-center">
											Don't have an account? <a href="register"
												class="text-decoration-none">Register</a>
										</p>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</section>

	</main>

	<%@ include file="/common/footer.jsp"%>

</body>

</html>