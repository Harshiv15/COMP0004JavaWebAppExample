package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import java.io.IOException;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Model model = ModelFactory.getModel();
      // delete the row and attempt to write it to a csv file, see AddPatientServlet for path explanation
      model.getDataFrame().deleteRow(Integer.parseInt(request.getParameter("row")));
      model.writeToFile(getServletContext().getRealPath("/WEB-INF/data/patients100_edited.csv"));
      // send us back to the patient list once we're done
      response.sendRedirect("patientList");
    } catch (Exception e) {
      request.setAttribute("errorMessage", "Error deleting patient: " + e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}
