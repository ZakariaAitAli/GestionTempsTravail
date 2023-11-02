<%--
  Created by IntelliJ IDEA.
  User: OY
  Date: 25/10/2023
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <title>Bienvenue</title>
</head>
<body>
<!-- As a heading -->
<nav class="navbar bg-body-tertiary">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1" style="color: #5e9693;">WorkTime Manager</span>
    </div>
</nav>
<h4>Bienvenue </h4>
<div class="formulaire">
    <form method="post" action="Servlets.workTimeServlet">
        <div class="name-field">
            <div>
                <label> Heure d'entrée</label>
                <input type="time" name="start_time">
            </div>
            <div>
                <label> Heure de sortie</label>
                <input type="time" name="end_time">
            </div>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="1" name="pause" >
            <label class="form-check-label" > pause matinale 10h00 - 10h20</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="2"  name="pause" checked>
            <label class="form-check-label" > pause déjeuner 13h00 - 14h00</label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="checkbox" value="3" name="pause" >
            <label class="form-check-label" > pause après-midi 16h00 - 16h15</label>
        </div>
        <input type="submit" class="btn btn-primary" value="Envoyer"/>

    </form>
</div>









</body>
</html>
<style>
    *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        background-image:url("/resources/images/image1.jpg");


    }


    .formulaire{
        display: flex;
        align-items: center;
        justify-content: center;
        height: 80vh;
    }
    form{
        display: flex;
        flex-direction: column;
        background-color: #fff;
        padding: 10px;
        border-radius: 6px;
        box-shadow: 0 0 10px rgba(0,0,0,0.2);
    }
    h4{
        text-align:center;
        background-color: #5e9693;
        border: 0;
        height: 1px;
        width: 100%;
    }
    .name-field {
        display: flex;
        width: 100%;
        justify-content: space-between;
    }
    .name-field div {
        display: flex;
        flex-direction: column;
    }
    .name-field div{
        width: 49%;
    }
    label{
        margin-bottom: 6px;
    }
    input{
        margin-bottom: 5px;
        padding: 5px;
        outline: 0;
        border: 1px solid rgba(0,0,0,0.4);
    }
    input:focus{
        border: 1px solid #5e9693;
    }
    input[type="submit"]{
        margin-top: 15px;
        background-color: #5e9693;
        color: #fff;
        border: 1px solid #5e9693;
        cursor: pointer;
    }
</style>