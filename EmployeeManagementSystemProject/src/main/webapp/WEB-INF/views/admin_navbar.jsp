<!-- Start Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark text-white">

	<div class="container-fluid">
		<a class="navbar-brand" href="/home">
		 <img alt="Qries" src="/images/manager.png" width="150" height="70">
		 Employee Management System
		</a>
		
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">

				<li class="nav-item"><a class="nav-link active" href="/home"> <i class="fa fa-home" aria-hidden="true"></i>Home</a> </li>

				<li class="nav-item"><a class="nav-link active" href="/logout"> <i class="fa fa-sign-in" aria-hidden="true"></i>Logout</a></li>

				<li class="nav-item"><a class="nav-link active" href="/register"> <i class="fa fa-address-card-o" aria-hidden="true"></i>Register</a></li>
				
         		<button class="btn btn-outline-success float-end" type="submit">${pageContext.request.userPrincipal.name}</button>
				
			</ul>

		</div>
	</div>
</nav>
