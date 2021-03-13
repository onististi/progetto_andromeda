<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- ===== CSS ===== -->
    <link rel="stylesheet" href="assets/css/navbar.css">

    <title>Navbar</title>
</head>

<body>
    <header class="header">
        <a href="pages/home.jsp" class="header__logo">PianetaBus</a>
        <ion-icon name="menu-outline" class="header__toggle" id="nav-toggle"></ion-icon>

        <nav class="nav" id="nav-menu">
            <div class="nav__content bd-grid">

                <ion-icon name="close-outline" class="nav__close" id="nav-close"></ion-icon>

                <div class="nav__perfil">
                    <img src="assets/img/logo-logo.png" style="width: 40px; height: 40px; margin-right: 9%;">
                    <div>
                        <a href="pages/home.jsp" class="nav__name">PianetaBus</a>
                        <span class="nav__profesion">Search Engine</span>
                    </div>
                </div>

                <div class="nav__menu">
                    <ul class="nav__list">
                        <li class="nav__item"><a href="pages/home.jsp" class="nav__link active">Home</a></li>
                        <li class="nav__item"><a href="pages/about.html" class="nav__link">Chi siamo</a></li>
                        <li class="nav__item"><a href="pages/contact.html" class="nav__link">Contattaci</a></li>
                    </ul>
                </div>

                <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
           		     <a class="nav-link" style="color:white"><% if(session.getAttribute("username")!=null){ out.println(session.getAttribute("username"));} %></a>&nbsp;
                    <a class="nav-link" href="auth/login.html"><img src="assets/img/user.png" width="40px"></a>
                </div>
            </div>
        </nav>
    </header>

    <!-- ===== IONICONS ===== -->
    <script src="https://unpkg.com/ionicons@5.1.2/dist/ionicons.js"></script>

    <!--===== MAIN JS =====-->
    <script src="assets/js/main.js"></script>
</body>

</html>