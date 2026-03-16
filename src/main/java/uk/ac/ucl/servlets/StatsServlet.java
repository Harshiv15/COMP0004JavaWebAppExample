package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.DataFrame;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.PatientOperations;
import java.io.IOException;

@WebServlet("/stats")
public class StatsServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      PatientOperations operations = new PatientOperations();
      DataFrame df = ModelFactory.getModel().getDataFrame();
      // show the data calculated in PatientOperations
      request.setAttribute("oldest", operations.oldestPatient(df));
      request.setAttribute("cityStats", operations.patientsPerPlace(df));
      request.setAttribute("sameBirthdays", operations.patientsWithSameBirthday(df));

      RequestDispatcher dispatch = request.getRequestDispatcher("/stats.jsp");
      dispatch.forward(request, response);
    } catch (Exception e) {
      request.setAttribute("errorMessage", "Error loading stats: " + e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}