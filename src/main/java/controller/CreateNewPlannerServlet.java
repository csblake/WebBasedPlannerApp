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
 * Servlet implementation class CreateNewPlannerServlet
 */
@WebServlet("/createNewPlannerServlet")
public class CreateNewPlannerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewPlannerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlannerItemHelper pih = new PlannerItemHelper();
		String plannerName = request.getParameter("plannerName");
		System.out.println("Planner Name: " + plannerName);
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String studentName = request.getParameter("studentName");
		LocalDate ld;
		
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		String[] selectedItems = request.getParameterValues("allItemsToAdd");
		List<PlannerItem> selectedItemsInList = new ArrayList<PlannerItem>();
		
		if(selectedItems != null && selectedItems.length > 0) {
			for(int i = 0; i < selectedItems.length; i++) {
				System.out.println(selectedItems[i]);
				PlannerItem c = pih.searchForItemById(Integer.parseInt(selectedItems[i]));
				selectedItemsInList.add(c);
			}
		}
		
		Student student = new Student(studentName);
		PlannerDetails sld = new PlannerDetails(plannerName, ld, student);
		
		sld.setListOfPlanners(selectedItemsInList);
		PlannerDetailsHelper slh = new PlannerDetailsHelper();
		slh.insertNewPlannerDetails(sld);
		
		System.out.println("Success!");
		System.out.println(sld.toString());
		
		getServletContext().getRequestDispatcher("/viewAllPlannersServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
