<?php

if (isset($_POST["user"])) $user = htmlspecialchars(trim($_POST["user"]));
else $user = "";

if (isset($_POST["pass"])) $pass = htmlspecialchars(trim($_POST["pass"]));
else $pass = "";

// connesione al DBMS
$config = parse_ini_file("../config/key.ini");
$connect = mysqli_connect($config["servername"], $config["username"], $config["password"], $config["dbname"]) or die("Connessione non riuscita" . mysqli_connect_error());
$query = "SELECT * FROM utente";
$result = mysqli_query($connect, $query) or die("Query fallita" . mysqli_error($connect) . " " . mysqli_error($connect));

$trovato = false;
while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)) // solo numerico
{
	$cryptopass = crypt($pass, '$5$idkanysus$');
	if ($row['username'] == $user && $row['password'] == $cryptopass) $trovato = true;
}

if ($trovato == true) {	//User founded
	//header('Location: ../pages/home.php?user=' . $user);
	header('Location: ../pages/home.html') ;
} else {	//User do not exist
	echo "<script>alert('Credenziali errate');</script>";
	echo "<center><a href='login.html' style='font-size: 3vw;'>Riprova</a></center>";
}
