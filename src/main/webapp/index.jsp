<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title> Log in </title>

    <style>
        html, body, div, span, applet, object, iframe,
        h1, h2, h3, h4, h5, h6, p, blockquote, pre,
        a, abbr, acronym, address, big, cite, code,
        del, dfn, em, img, ins, kbd, q, s, samp,
        small, strike, strong, sub, sup, tt, var,
        b, u, i, center,
        dl, dt, dd, ol, ul, li,
        fieldset, form, label, legend,
        table, caption, tbody, tfoot, thead, tr, th, td,
        article, aside, canvas, details, embed,
        figure, figcaption, footer, header, hgroup,
        menu, nav, output, ruby, section, summary,
        time, mark, audio, video {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            font: inherit;
            vertical-align: baseline;
        }
        /* All */
        body {
            background-color: bisque;
        }
        header {
            margin-left: 20%;
            height: 140px;
            width: 60%;
            text-align: center;
            margin-top: 5%;
        }
        h1 {
            font-size: 600%;
            font-family:cursive;
        }

        .registration-form{
            margin-left: 20%;
            width: 60%;
            height: 200px;
        }

        .info-place{
            width: 100%;
            height: 15%;
            text-align: center;
        }
        .info-place p{
            font-size: 180%;
            font-family: Century Gothic;
            font-weight: 300;
        }
        .login-form{
            width: 100%;
            height: 35%;
        }
        .password-form{
            width: 100%;
            height: 35%;
        }
        input[type=text], input[type=password] {
            width: 50%;
            padding: 5px;
            margin: 2% 0 0 0;
            margin-left: 25%;
            height: 60%;
            display: inline-block;
            border-style: solid;
            border-color: black;
            border-radius: 8px;
            background: rgb(255, 249, 242);
            font-size:24px;
            font-family: Century Gothic;
        }
        input[type=text]:focus, input[type=password]:focus {
            background-color: rgb(255, 249, 242);
            outline: none;
        }
        /* Button */
        .button-link{
            margin-top: 2%;
            width: 100%;
            height: 15%;
        }
        .button-link input{
            margin-left: 69%;
            font-size:20px;
            font-family: Century Gothic;
            color: black;
            text-decoration: none;
            background-color: bisque;
            border: none;
            outline: none;
        }
        .button-link input:hover{
            color: rgb(128, 103, 72);
        }
    </style>
</head>

    <body>
        <form action="index.jsp">
            <div class="button-link" style="margin-left: 14%;"> <input type="submit" value="Log out"> </div>
        </form>
        <header>
            <h1> KNU Eatery </h1>
        </header>

        <div class="registration-form">
            <div class="info-place">
                <p> Login page </p>
            </div>
            <form action="hello-servlet">
                <div class="login-form"> <input type="text" name="login" placeholder="login" required> </div>
                <div class="password-form"> <input type="text" name="password" placeholder="password" required> </div>
                <div class="button-link"> <input type="submit" value="Log In"> </div>
            </form>
        </div>
    </body>
</html>