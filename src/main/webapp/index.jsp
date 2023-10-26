<%--
  Created by IntelliJ IDEA.
  User: Pro
  Date: 22/04/2023
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Espace Connexion</title>
    <script src="https://cdn.jsdelivr.net/npm/animejs@3.0.1/lib/anime.min.js"></script>

</head>
<body>
<div class="navbar">
    <div class="container flex">
        <!-- Image Logo -->
        <a class="logo-image" href="index.jsp"><span style="color: #5e9693;">WorkTime</span><span style="color: #fff;">Manager</span></a>
        <!-- Text Logo - Use this if you don't have a graphic logo -->
        <!-- <a class="logo-text" href="index.html">Name</a> -->
    </div> <!-- end of container -->
</div>
<input type="hidden" id="status" value="<%=request.getAttribute("status") %>">
<div class="page" style="margin-top: 30px">
    <div class="container">
        <div class="left">
            <div class="login">Se connecter</div>
            <div class="eula">L'application qui fait du temps votre allié!</div>
        </div>
        <div class="right">
            <svg viewBox="0 0 320 300">
                <defs>
                    <linearGradient
                            inkscape:collect="always"
                            id="linearGradient"
                            x1="13"
                            y1="193.49992"
                            x2="307"
                            y2="193.49992"
                            gradientUnits="userSpaceOnUse">
                        <stop
                                style="stop-color:#DAAB54FF;"
                                offset="0"
                                id="stop876" />
                        <stop
                                style="stop-color:#044184;"
                                offset="1"
                                id="stop878" />
                    </linearGradient>
                </defs>
                <path d="m 40,120.00016 239.99984,-3.2e-4 c 0,0 24.99263,0.79932 25.00016,35.00016 0.008,34.20084 -25.00016,35 -25.00016,35 h -239.99984 c 0,-0.0205 -25,4.01348 -25,38.5 0,34.48652 25,38.5 25,38.5 h 215 c 0,0 20,-0.99604 20,-25 0,-24.00396 -20,-25 -20,-25 h -190 c 0,0 -20,1.71033 -20,25 0,24.00396 20,25 20,25 h 168.57143" />
            </svg>
            <div class="form">
                <form method="post" action="Servlets.LoginServlet">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email">
                    <label for="password">Mot de passe</label>
                    <input type="password" id="password" name="password">
                    <input  type="submit" id="submit" value="Submit">
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var current = null;
    document.querySelector('#email').addEventListener('focus', function(e) {
        if (current) current.pause();
        current = anime({
            targets: 'path',
            strokeDashoffset: {
                value: 0,
                duration: 700,
                easing: 'easeOutQuart'
            },
            strokeDasharray: {
                value: '240 1386',
                duration: 700,
                easing: 'easeOutQuart'
            }
        });
    });
    document.querySelector('#password').addEventListener('focus', function(e) {
        if (current) current.pause();
        current = anime({
            targets: 'path',
            strokeDashoffset: {
                value: -336,
                duration: 700,
                easing: 'easeOutQuart'
            },
            strokeDasharray: {
                value: '240 1386',
                duration: 700,
                easing: 'easeOutQuart'
            }
        });
    });
    document.querySelector('#submit').addEventListener('focus', function(e) {
        if (current) current.pause();
        current = anime({
            targets: 'path',
            strokeDashoffset: {
                value: -730,
                duration: 700,
                easing: 'easeOutQuart'
            },
            strokeDasharray: {
                value: '530 1386',
                duration: 700,
                easing: 'easeOutQuart'
            }
        });
    });
</script>
</body>
</html>
<style>
    @import url('https://rsms.me/inter/inter-ui.css');
    ::selection {
        background: #A7CAD1;
    }
    ::-moz-selection {
        background: #A7CAD1;
    }

    body{
        background-image:url("resources/images/image1.jpg");
        font-size:large ;
        background-size: cover;
        font-family: 'Inter UI', sans-serif;
        margin: 0;
        padding: 20px;
    }
    .page {
        display: flex;
        flex-direction: column;
        height: calc(100% - 40px);
        position: absolute;
        place-content: center;
        width: calc(100% - 40px);
    }
    @media (max-width: 767px) {
        .page {
            height: auto;
            margin-bottom: 20px;
            padding-bottom: 20px;
        }
    }
    .container {
        display: flex;
        height: 320px;
        margin: 0 auto;
        width: 640px;
    }
    @media (max-width: 767px) {
        .container {
            flex-direction: column;
            height: 630px;
            width: 320px;
        }
    }
    .left {
        background: white;
        height: calc(100% - 40px);
        top: 20px;
        position: relative;
        width: 50%;
        border-radius:1px;
    }
    @media (max-width: 767px) {
        .left {
            height: 100%;
            left: 20px;
            width: calc(100% - 40px);
            max-height: 270px;
            border-radius: 1px;
        }
    }
    .login {
        font-size: 50px;
        font-weight: 900;
        margin: 50px 40px 40px;
        color: #DAAB54;
    }
    .eula {
        color: black;
        font-size: 14px;
        line-height: 1.5;
        margin: 40px;
    }
    .right {
        background:#A7CAD1;
        box-shadow: 0px 0px 40px 16px rgba(0,0,0,0.22);
        color: #F1F1F2;
        position: relative;
        width: 50%;
        border-radius: 5px;
    }
    @media (max-width: 767px) {
        .right {
            border-radius: 5px;
            flex-shrink: 0;
            height: 100%;
            width: 100%;
            max-height: 350px;
        }
    }
    svg {
        position: absolute;
        width: 320px;
    }
    path {
        fill: none;
        stroke: url(#linearGradient);;
        stroke-width: 4;
        stroke-dasharray: 240 1386;
    }
    .form {
        margin: 40px;
        position: absolute;
    }
    label {
        color: white;
        display: block;
        font-size: 14px;
        height: 16px;
        margin-top: 20px;
        margin-bottom: 5px;
    }
    input {
        background: transparent;
        border: 0;
        color: #f2f2f2;
        font-size: 20px;
        height: 30px;
        line-height: 30px;
        outline: none !important;
        width: 100%;
    }
    input::-moz-focus-inner {
        border: 0;
    }
    #submit {
        color: #707075;
        margin-top: 40px;
        transition: color 300ms;
    }
    #submit:focus {
        color: #f2f2f2;
    }
    #submit:active {
        color: #d0d0d2;
    }

    ul {
        list-style-type: none;
    }

    .li-space-lg li {
        margin-bottom: 0.5rem;
    }

    a {
        color: #667077;
        text-decoration: underline;
    }

    a:hover {
        color: #667077;
        text-decoration: underline;
    }

    /**********************/
    /*     Navigation     */
    /**********************/
    .navbar {
        position: absolute;
        right: 0;
        left: 0;
        margin-top: -36px;
        color: #ffffff;
        height: 60px;
        backdrop-filter: blur(2px);
        background-color: rgba(255, 255, 255, .15);
    }

    .navbar .flex {
        flex-direction: column;
    }

    .navbar a {
        text-decoration: none;
    }

    .navbar .logo-image {
        margin-top: 50px !important;
        width: 134px;
        height: 30px;
        margin-top: 0.5rem;
        margin-left: -300px !important;
        margin-bottom: 1.25rem;
    }

    .navbar .logo-text {
        margin-bottom: 0.5rem;
        color: #ffffff;
        font-weight: 500;
        font-size: 2rem;
        line-height: 1.875rem;
    }

    .navbar ul {
        margin-top: 50px !important;
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        padding: 0.5rem 0.75rem 0 0.75rem;
        background-color: rgba(0, 0, 0, 0.2);
    }

    .navbar ul li {
        padding: 0 0.75rem 0.5rem 0.75rem;
    }

    .navbar ul a {
        color: #ffffff;
    }

    .navbar ul a:hover {
        border-bottom: 2px #ffffff solid;
    }

    /* Navigation */
    .navbar {
        top: 36px;
    }

    .navbar .flex {
        flex-direction: row;
        justify-content: space-between;
        margin-top: -20px;
    }

    .navbar .logo-image {
        margin: 0;
    }

    .navbar .logo-text {
        margin-bottom: 0;
    }

    .navbar ul {
        padding: 0;
        background: none;
    }

    .navbar ul li {
        margin-bottom: 0;
        padding-bottom: 0;
    }

    .navbar ul li:last-of-type a {
        margin-right: 0;
        padding-right: 0;
    }
    /* end of navigation */
</style>