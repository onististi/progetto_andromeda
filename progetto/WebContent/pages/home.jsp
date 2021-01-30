<!DOCTYPE html>
<html lang="it">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="../assets/css/home.css">
	<title>Home</title>

</head>

<body>
	<header id="navbar_header">
	</header>

	<script>
		$("#navbar_header").load('../components/navbar-home.html');
	</script>

	<div class="background">
		<form>
			<div class="form-box">
				<select class="search-field text-box" name="cars">
					<option value="" disabled selected>Partenza</option>
				</select>
				<select class="search-field text-box" name="cars">
					<option value="" disabled selected>Arrivo</option>
				</select>
				<input type="date" class="search-field date-time-box">
				<input type="time" class="search-field hour-time-box">

				<button class="search-field search-btn" type="button" onclick="window.location.href='ticket.html'"><img src="../assets/img/search.png" alt="Search" width="30px"></button>
			</div>
		</form>
	</div>

	<center>
		<div>
			<h2 class="title">Compagnie supportate</h2>
			<div class="carriers-logos-container">
				<div class="carriers-row">
					<img src="../assets/img/logo_vcotrasporti.png" class="brand">
					<img src="../assets/img/logo_comazzi.png" class="brand">
					<img src="../assets/img/logo-saf.png" class="brand">
				</div>
			</div>
		</div>
	</center>

	<footer id="footer">
	</footer>

	<script>
		$("#footer").load('../components/footer.html');
	</script>
</body>

</html>
<!-- <input type="text" class="search-field text-box" placeholder="Partenza">
<input type="text" class="search-field text-box" placeholder="Arrivo"> -->