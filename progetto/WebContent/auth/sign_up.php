<?php

if (isset($_POST["user"])) $user = htmlspecialchars(trim($_POST["user"]));
else $user = "";

if (isset($_POST["pass"])) $pass = htmlspecialchars(trim($_POST["pass"]));
else $pass = "";

if (isset($_POST["retype_pass"])) $retype_pass = htmlspecialchars(trim($_POST["retype_pass"]));
else $retype_pass = "";

//check pass
if ($pass == $retype_pass) {	//if the pass is right ill connect into DB		
	$config = parse_ini_file("../config/key.ini");
	$connect = mysqli_connect($config["servername"], $config["username"], $config["password"], $config["dbname"]) or die("Connessione non riuscita" . mysqli_connect_error());
	$cryptpass = crypt($pass, '$5$idkanysus$');
	$query = "INSERT INTO utente (username,password)
			VALUES ('$user','$cryptpass')";

	if (mysqli_query($connect, $query)) {	//if success
		echo "<script>alert('Registrato correttamente');</script>";
		echo "<center><a href='login.html' style='font-size: 5vw;'>Login</a></center>";
	} else {	//if theres any problem
		echo "Error: " . $query . ":-" . mysqli_error($connect);
		echo "<center><a href='sign_up.html' style='font-size: 5vw;'>Riprova</a></center>";
	}
} else {	//if the password is wrong
	echo "<script>alert('Le password non corrispondono');</script>";
	echo "<center><a href='sign_up.html' style='font-size: 5vw;'>Riprova</a></center>";
}