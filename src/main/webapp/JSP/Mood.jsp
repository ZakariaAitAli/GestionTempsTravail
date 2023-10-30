<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css"><body>
<style>
.body{
    background-image: url("../resources/images/Mood.png");

}
.aligner {´
display: inline-block; }

.btn{
  background-image: linear-gradient(92.88deg, #455EB5 9.16%, #5643CC 43.89%, #673FD7 64.72%);
  border-radius: 8px;
  border-style: none;
  box-sizing: border-box;
  color: #FFFFFF;
  cursor: pointer;
  flex-shrink: 0;
  font-family: "Inter UI","SF Pro Display",-apple-system,BlinkMacSystemFont,"Segoe UI",Roboto,Oxygen,Ubuntu,Cantarell,"Open Sans","Helvetica Neue",sans-serif;
  font-size: 16px;
  font-weight: 500;
  height: 4rem;
  padding: 0 1.6rem;
  text-align: center;
  text-shadow: rgba(0, 0, 0, 0.25) 0 3px 8px;
  transition: all .5s;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  margin:40px;
}


.btn:hover {
  box-shadow: rgba(80, 63, 205, 0.5) 0 1px 30px;
  transition-duration: .1s;
}

@media (min-width: 768px) {
  .btn{
    padding: 0 2.6rem;
  }
}
</style>


<center><h3 style="margin-top=40px;"> Quel est votre humeur Aujourd'hui ?</h3>
</center>
<center>
<div class="aligner">
<p class="container">
<form action="Servlets.Mood" method="post">
<button class="btn" type="submit" name="humeur" value="Heureux"> <i class="bi bi-emoji-smile-fill" ></i> Heureux</button>
<button class="btn" type="submit" name="humeur" value="En colère"> <i class="bi bi-emoji-angry-fill"></i> En colère</button>
<button class="btn" type="submit" name="humeur" value="Neutre"> <i class="bi bi-emoji-expressionless-fill"></i> Neutre</button>
<button class="btn" type="submit" name="humeur" value="Triste"> <i class="bi bi-emoji-frown-fill"></i> Triste</button>
</form>
</div>
</center>
</>
<br/>

</body>
</html>