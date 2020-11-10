<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script>
    function pay() {
        if (validate()) {
            $.post(
                "http://localhost:8080/cinema/payment",
                {
                    username: $('#username').val(),
                    phone: $('#phone').val(),
                    place: ${place}
                },
                window.location.href = "http://localhost:8080/cinema/"
            );
        }
    }

    function validate() {
        if ($('#username').val() === '' || $('#phone').val() === '') {
            alert('Заполните все поля')
            return false;
        }
        return true;
    }
</script>
<div class="container">
    <div class="row pt-3">
        <h3>
            ${headText}
        </h3>
    </div>
    <div class="row">
        <form>
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" id="username" name="user" class="form-control" placeholder="ФИО">
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" id="phone" name="phoneNumber" class="form-control" placeholder="Номер телефона">
            </div>
            <button type="button" class="btn btn-success" onclick="return pay()">Оплатить</button>
        </form>
    </div>
</div>
</body>
</html>