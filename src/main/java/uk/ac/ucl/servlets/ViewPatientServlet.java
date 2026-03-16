package uk.ac.ucl.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.DataFrame;

import java.io.IOException;

@WebServlet("/patientInfo")
public class ViewPatientServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    try {
      int row = Integer.parseInt(request.getParameter("row"));
      DataFrame df = ModelFactory.getModel().getDataFrame();

      request.setAttribute("df", df);
      request.setAttribute("row", row);
      request.setAttribute("columnNames", df.getColumnNames());

      RequestDispatcher dispatch =
          request.getRequestDispatcher("/patientInfo.jsp");
      dispatch.forward(request, response);
    } catch (Exception e) {
      request.setAttribute("errorMessage", e.getMessage());
      RequestDispatcher dispatch =
          request.getRequestDispatcher("/error.jsp");
      dispatch.forward(request, response);
    }
  }
}
