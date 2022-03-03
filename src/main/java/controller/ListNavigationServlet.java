package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PlannerDetails;

/**
 * Servlet implementation class ListNavigationServlet
 */
@WebServlet("/listnavigationServlet")
public class ListNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListNavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlannerDetailsHelper dao = new PlannerDetailsHelper();
		
		String act = request.getParameter("doThisToPlanner");
		
		if(act == null) {
			getServletContext().getRequestDispatcher("/viewAllPlannersServlet").forward(request, response);
		} else if(act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				PlannerDetails plannerToDelete = dao.searchForPlannerDetailsById(tempId);
				dao.deletePlanner(plannerToDelete);
			} catch(NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllPlannersServlet").forward(request, response);
			}
		} else if(act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				PlannerDetails plannerToEdit = dao.searchForPlannerDetailsById(tempId);
				request.setAttribute("plannerToEdit", plannerToEdit);
				
				request.setAttribute("month", plannerToEdit.getPlannerDate().getMonthValue());
				request.setAttribute("date", plannerToEdit.getPlannerDate().getDayOfMonth());
				request.setAttribute("year", plannerToEdit.getPlannerDate().getYear());
				
				PlannerItemHelper daoForItems = new PlannerItemHelper();
				
				request.setAttribute("allItems", daoForItems.showAllItems());
				
				if(daoForItems.showAllItems().isEmpty()) {
					request.setAttribute("allItems", " ");
				}
				
				getServletContext().getRequestDispatcher("/edit-planner.jsp").forward(request, response);
			} catch(NumberFormatException e) {
				getServletContext().getRequestDispatcher("/viewAllPlannersServlet").forward(request, response);
			}
		} else if(act.equals("add")) {
			getServletContext().getRequestDispatcher("/new-planner.jsp").forward(request, response);
		}
	}

}
