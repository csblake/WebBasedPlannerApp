package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PlannerDetails;
import model.PlannerItem;
import model.Student;

/**
 * Servlet implementation class EditPlannerServlet
 */
@WebServlet("/editPlannerDetailsServlet")
public class EditPlannerDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditPlannerDetailsServlet() {
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
		PlannerItemHelper pih = new PlannerItemHelper();
		StudentHelper ph = new StudentHelper();
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		PlannerDetails plannerToUpdate = dao.searchForPlannerDetailsById(tempId);
		
		String newPlannerName = request.getParameter("plannerName");
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		String studentName = request.getParameter("studentName");
		
		Student newStudent = ph.findStudent(studentName);
		
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		try {
			String[] selectedItems = request.getParameterValues("allItemsToAdd");
			List<PlannerItem> selectedItemsInPlanner = new ArrayList<PlannerItem>();
			
			for(int i = 0; i < selectedItems.length; i++) {
				System.out.println(selectedItems[i]);
				PlannerItem c = pih.searchForItemById(Integer.parseInt(selectedItems[i]));
				selectedItemsInPlanner.add(c);
			}
			
			plannerToUpdate.setListOfPlanners(selectedItemsInPlanner);
		} catch(NullPointerException ex) {
			List<PlannerItem> selectedItemsInPlanner = new ArrayList<PlannerItem>();
			plannerToUpdate.setListOfPlanners(selectedItemsInPlanner);
		}
		
		plannerToUpdate.setPlannerName(newPlannerName);
		plannerToUpdate.setPlannerDate(ld);
		plannerToUpdate.setStudent(newStudent);
		
		dao.updatePlanner(plannerToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllPlannersServlet").forward(request, response);
	}

}
