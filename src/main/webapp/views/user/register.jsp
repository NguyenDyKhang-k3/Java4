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
                        <form class="custom-form ticket-form mb-5 mb-lg-0" action="register" method="post" role="form">
                            <h2 class="text-center mb-4">
                                <i class="fas fa-user-circle login-icon"></i> Register
                            </h2>
                            <div class="ticket-form-body">
                                <div class="row">
                                <input type="email" name="register-form-email" id="register-form-email"
                                        class="form-control" placeholder="Email Address" required>
                                
                                    <input type="text" name="register-form-name" id="register-form-name"
                                        class="form-control" placeholder="Username" required>

                                    <input type="password" name="register-form-password" id="register-form-password"
                                        class="form-control" placeholder="Password" required>

                                    <input type="password" name="register-form-confirm-password"
                                        id="register-form-confirm-password" class="form-control"
                                        placeholder="Confirm Password" required>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <button type="submit" class="form-control">Register</button>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12 text-center">
                                        <div class="line-container">
                                            <hr class="line">
                                            <p class="or-text">Or register with</p>
                                            <hr class="line">
                                        </div>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-6 text-end">
                                        <button type="button" class="btn btn-google">
                                            <i class="fab fa-google text-primary"></i>
                                            Register with Google
                                        </button>
                                    </div>
                                    <div class="col-6 text-start">
                                        <button type="button" class="btn btn-facebook">
                                            <i class="fab fa-facebook-f text-primary"></i>
                                            Register with Facebook
                                        </button>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <p class="text-center">Already have an account? <a href="login"
                                                class="text-decoration-none">Login</a></p>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-12">
                                        <p class="text-center">By registering, you agree to our <a href="#"
                                                class="text-decoration-none">Terms and Conditions</a>.</p>
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