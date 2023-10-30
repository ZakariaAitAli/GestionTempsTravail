<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DAO.Rapport" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" href="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\css\bootstrap.min.css.map" />

    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
    />
    <link rel="stylesheet" href="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\css\dataTables.bootstrap5.min.css" />
    <link rel="stylesheet" href="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\css\style.css" />
    <title> WorkTimeManager</title>
  </head>
  <body>
    <!-- top navigation bar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
      <div class="container-fluid">
        <button
          class="navbar-toggler me-2"
          type="button"
          data-bs-toggle="offcanvas"
          data-bs-target="#offcanvasExample"
          aria-controls="offcanvasExample"
        >
          <span class="navbar-toggler-icon" data-bs-target="#offcanvasExample"></span>
        </button>
        <a
          class="navbar-brand me-auto ms-lg-0 ms-3 text-uppercase fw-bold"
          href="#"
          >WorkTimeManager</a
        >

        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#topNavBar"
          aria-controls="topNavBar"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="topNavBar">
          <form class="d-flex ms-auto my-3 my-lg-0">
            <div class="input-group">
              <input
                class="form-control"
                type="search"
                placeholder="Search"
                aria-label="Search"
              />
              <button class="btn btn-primary" type="submit">
                <i class="bi bi-search"></i>
              </button>
            </div>
          </form>
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a
                class="nav-link dropdown-toggle ms-2"
                href="#"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                <i class="bi bi-person-fill"></i>
              </a>
              <ul class="dropdown-menu dropdown-menu-end">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li>
                  <a class="dropdown-item" href="#">Something else here</a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- top navigation bar -->
    <!-- offcanvas -->
    <div
      class="offcanvas offcanvas-start bg-dark text-white sidebar-nav"
      tabindex="-1"
      id="offcanvasExample"
      aria-labellebdy="offcanvasExampleLabel"
    >
      <div class="offcanvas-body p-0">
        <nav class="navbar-dark">
          <ul class="navbar-nav">
            <li>
              <div class="text-muted small fw-bold text-uppercase px-3">
                CORE
              </div>
            </li>
            <li>
              <a href="#" class="nav-link px-3 active">
                <span class="me-2"><i class="bi bi-speedometer2"></i></span>
                <span>Dashboard</span>
              </a>
            </li>
            <li class="my-4"><hr class="dropdown-divider bg-light" /></li>
            <li>
              <div class="text-muted small fw-bold text-uppercase px-3 mb-3">
                Interface
              </div>
            </li>
            <li>
              <a
                class="nav-link px-3 sidebar-link"
                data-bs-toggle="collapse"
                href="#layouts"
              >
                <span class="me-2"><i class="bi bi-layout-split"></i></span>
                <span>Layouts</span>
                <span class="ms-auto">
                  <span class="right-icon">
                    <i class="bi bi-chevron-down"></i>
                  </span>
                </span>
              </a>
              <div class="collapse" id="layouts">
                <ul class="navbar-nav ps-3">
                  <li>
                    <a href="#" class="nav-link px-3">
                      <span class="me-2"
                        ><i class="bi bi-speedometer2"></i
                      ></span>
                      <span>Dashboard</span>
                    </a>
                  </li>
                </ul>
              </div>
            </li>
            <li>
              <a href="#" class="nav-link px-3">
                <span class="me-2"><i class="bi bi-book-fill"></i></span>
                <span>Pages</span>
              </a>
            </li>
            <li class="my-4"><hr class="dropdown-divider bg-light" /></li>
            <li>
              <div class="text-muted small fw-bold text-uppercase px-3 mb-3">
                Addons
              </div>
            </li>
            <li>
              <a href="#" class="nav-link px-3">
                <span class="me-2"><i class="bi bi-graph-up"></i></span>
                <span>Charts</span>
              </a>
            </li>
            <li>
              <a href="#" class="nav-link px-3">
                <span class="me-2"><i class="bi bi-table"></i></span>
                <span>Tables</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
    <!-- offcanvas -->
    <main class="mt-5 pt-3">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <h4>Dashboard</h4>
          </div>
        </div>


              <div class="row">
                  <!-- Graphique des pauses -->
                  <div class="col-md-6 mb-3">
                    <div class="card h-100">
                      <div class="card-header">
                        <span class="me-2"><i class="bi bi-bar-chart-fill"></i></span>
                        Graphique des pauses
                      </div>
                      <div class="card-body">
                        <canvas id="pauseChart" width="70" height="40"></canvas>
                      </div>
                    </div>
                  </div>

                  <!-- Graphique de l'humeur -->
                  <div class="col-md-6 mb-3">
                    <div class="card h-100">
                      <div class="card-header">
                        <span class="me-2"><i class="bi bi-bar-chart-fill"></i></span>
                        Graphique de l'humeur
                      </div>
                      <div class="card-body">
                        <canvas id="moodChart" width="70" height="40"></canvas>
                      </div>
                    </div>
                  </div>
                </div>


        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
        // Fonction d'initialisation des graphiques
        // Fonction d'initialisation des graphiques
        function initializeCharts() {
          // Récupérez les données transmises depuis la servlet pour le graphique des pauses
          var matinaleCount = <%= request.getAttribute("matinaleCount") %>;
          var apresMidiCount = <%= request.getAttribute("apresMidiCount") %>;
          var dejeunerCount = <%= request.getAttribute("dejeunerCount") %>;

          // Configuration du graphique des pauses
          var pauseOptions = {};

          // Sélectionnez le canvas du graphique des pauses
          var pauseCtx = document.getElementById('pauseChart').getContext('2d');

          // Créez le graphique des pauses avec les données transmises
          var pauseChart = new Chart(pauseCtx, {
            type: 'bar',
            data: {
              labels: ["Matinale", "Après-midi", "Déjeuner"],
              datasets: [{
                label: "Nombre d'employés",
                data: [matinaleCount, apresMidiCount, dejeunerCount],
                backgroundColor: ["#A7CAD1", "#4169E1", "#DAAB54"]
              }]
            },
            options: pauseOptions
          });

          // Récupérez les données transmises depuis la servlet pour le graphique de l'humeur
          var bonneCount = <%= request.getAttribute("bonneCount") %>;
          var mauvaiseCount = <%= request.getAttribute("mauvaiseCount") %>;
          var stableCount = <%= request.getAttribute("stableCount") %>;

          // Configuration du graphique de l'humeur
          var moodOptions = {};

          // Sélectionnez le canvas du graphique de l'humeur
          var moodCtx = document.getElementById('moodChart').getContext('2d');

          // Créez le graphique de l'humeur avec les données transmises
          var moodChart = new Chart(moodCtx, {
            type: 'bar',
            data: {
              labels: ["Bonne", "Mauvaise", "Stable"],
              datasets: [{
                label: "Nombre d'employés",
                data: [bonneCount, mauvaiseCount, stableCount],
                backgroundColor: ["#A7CAD1", "#4169E1", "#DAAB54"]
              }]
            },
            options: moodOptions
          });
        }

        // Appelez la fonction d'initialisation une fois que le document est chargé
        document.addEventListener("DOMContentLoaded", initializeCharts);

        </script>



        <div class="row">
          <div class="col-md-12 mb-3">
            <div class="card">
              <div class="card-header">
              <span><i class="bi bi-table me-2"></i></span> Liste des rapports de l'employé : ${param.nom} ${param.prenom}

              </div>
              <div class="card-body">
                <div class="table-responsive">
                  <table
                    id="example"
                    class="table table-striped data-table"
                    style="width: 100%"
                  >
                    <thead>
                    <tr>
                     <th>Numéro Rapport</th>
                     <th>Date de Génération</th>
                     <th>Heures de travail</th>
                     <th>Chemin (Path)</th>
                    </tr>
                    </thead>
                   <tbody>
                                <c:forEach var="rapport" items="${rapports}">
                                   <tr>
                                   <td>${rapport.id_rapport}</td>
                                   <td>${rapport.date_generation}</td>
                                   <td>${rapport.worktime_total}</td>
                                   <td><a href="${rapport.path}">${rapport.path}</a></td>

                                   </tr>
                                 </c:forEach>
                           </tbody>
                    </tfoot>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
    <script src="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\js\bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@3.0.2/dist/chart.min.js"></script>
    <script src="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\js\jquery-3.5.1.js"></script>
    <script src="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\js\jquery.dataTables.min.js"></script>
    <script src="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\js\dataTables.bootstrap5.min.js"></script>
    <script src="C:\Users\Admin\Downloads\ProjetJEE\GestionTempsTravail\src\main\webapp\js\script.js"></script>
  </body>
</html>
