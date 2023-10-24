package gi3.ensate.gestiontempstravail.Servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "TestServlet", value = "/test")
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TestServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/test.jsp").forward(request, response);
    }



}
