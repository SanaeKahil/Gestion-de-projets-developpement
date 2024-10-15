<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Authentification</title>
  <link rel="stylesheet" href="../css/sideBar.css">
  <link rel="stylesheet" href="../css/bootstrap.min.css">
  <script src="../js/all.min.js"></script>
  <script src="../js/bootstrap.bundle.min.js"></script>
  <script src="../js/jquery.js"></script>
  <script src="../js/all.min.js"></script>
  <script src="../js/ClassApp/GestionProjet.js"></script>
</head>
<body class="bg-light">
    <div class="container mt-5 ">
        <form action="/GestionProjets/login" method="POST" class="col-md-6 offset-md-3 p-4 bg-white rounded shadow">
            <div class="form-group">
                <label class="form-label" for="email">Email</label>
                <input type="email" name="email" id="email" class="form-control" required />
            </div>
            <div class="form-group mt-2m">
                <label class="form-label" for="motDePasse">Mot de passe</label>
                <input type="password" name="motDePasse" id="motDePasse" class="form-control" required />
            </div>
            <div class="text-center mt-3">
                <button type="submit" class="btn btn-primary">Se connecter</button>
            </div>
        </form>
    </div>
</body>
</html>
