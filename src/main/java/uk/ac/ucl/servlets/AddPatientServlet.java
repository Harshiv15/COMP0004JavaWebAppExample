package uk.ac.ucl.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/addPatient")
public class AddPatientServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("/addPatient.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      Model model = ModelFactory.getModel();
      List<String> columnNames = model.getDataFrame().getColumnNames();

      String[] newRow = new String[columnNames.size()];
      // write each field ensuring the same order as the dataframe's columns
      for (int i = 0; i < columnNames.size(); i++) {
        String col = columnNames.get(i);
        String val = request.getParameter(col);
        newRow[i] = (val != null) ? val : "";
      }
      // load the array in as a vararg
      model.getDataFrame().addRow(newRow);
      // ai-assisted attempt to write to a temporary file because tomcat hosting doesn't let us modify the
      // actual filesystem. didn't work and couldn't figure out why in time for the submission deadline
      model.writeToFile(getServletContext().getRealPath("/WEB-INF/data/patients100_edited.csv"));
      // send us back to the patient list once we're done
      response.sendRedirect("patientList");
    } catch (Exception e) {
      request.setAttribute("errorMessage", "Error adding patient: " + e.getMessage());
      request.getRequestDispatcher("/error.jsp").forward(request, response);
    }
  }
}