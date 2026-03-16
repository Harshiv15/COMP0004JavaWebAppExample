package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.JSONWriter;

import java.io.IOException;

@WebServlet("/saveJson")
public class SaveJsonServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      // see AddPatientServlet for filepath explanation
      String jsonPath = getServletContext().getRealPath("/WEB-INF/data/patients_exported.json");
      new JSONWriter().writeJSON(ModelFactory.getModel().getDataFrame(), jsonPath);
      // tried this to verify the program was referencing the right filepath but no traces of the file or data
      // directory within WEB-INF were found when i tried navigating to what was displayed
      request.setAttribute("errorMessage", "Success! JSON saved to server at: " + jsonPath);
      request.getRequestDispatcher("/patientList.jsp").forward(request, response);
    } catch (Exception e) {
      request.setAttribute("errorMessage", "Error saving JSON: " + e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}