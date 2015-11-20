import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidatorServlet
 */
@WebServlet("/ValidatorServlet")
public class ValidatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ValidatorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sSource = "";
		String sPattern = "";
		int v = 0, weight = 0;
		for (int i = 1; i <= 4; i++) {
			switch (i) {
			case 1:
				sSource = request.getParameter("username");
				sPattern = "[a-z A-Z]";
				weight = i;
				break;

			case 2:
				sSource = request.getParameter("email");
				sPattern = "[a-zA-Z0-9]{1,30}[.|_]?([a-zA-Z0-9]{1,30})?[@][a-zA-Z0-9]{1,30}[.][a-zA-Z]{2,3}";
				weight = i;
				break;

			case 3:
				sSource = request.getParameter("bday");
				sPattern = "\\d{2}/\\d{2}/\\d{4}";
				weight = i;
				break;

			case 4:
				sSource = request.getParameter("phone");
				sPattern = "[(]?\\d{3}[)]?-?\\d{3}-?\\d{4}";
				weight = i;
				break;
			}

			Pattern p = Pattern.compile(sPattern);
			Matcher m = p.matcher(sSource);

			if (m.find()) {
				v = v + weight;
			}
		}
		if(v==10)
			response.sendRedirect("Confirmation.jsp");
		
		else
		{
			int i = 10-v;
			switch (i) {
			case 1:
				request.setAttribute("errorCode","Invalid Username");
				getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
				break;

			case 2:
				request.setAttribute("errorCode","Invalid Email");
				getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
				break;

			case 3:
				request.setAttribute("errorCode","Invalid Birthday Entry");
				getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
				break;

			case 4:
				request.setAttribute("errorCode","Invalid Phone Number");
				getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
				break;
				
			default:
				request.setAttribute("errorCode","Multiple Errors");
			getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
			break;
			
				
			}
			
		}
	}

}
