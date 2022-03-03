import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.PlannerDetailsHelper;
import controller.StudentHelper;
import model.PlannerDetails;
import model.PlannerItem;
import model.Student;

public class PlannerDetailsTester {

	public static void main(String[] args) {
		Student colin = new Student("Colin");
		
		PlannerDetailsHelper pdh = new PlannerDetailsHelper();
		
		PlannerItem java = new PlannerItem("Java II", "Week 6 Assessment");
		PlannerItem dataStructures = new PlannerItem("Data Structures", "PriorityQueue");
		
		List<PlannerItem> colinsItems = new ArrayList<PlannerItem>();
		colinsItems.add(java);
		colinsItems.add(dataStructures);
		
		PlannerDetails colinsPlanners = new PlannerDetails("Colin's Planners", LocalDate.now(), colin);
		colinsPlanners.setListOfPlanners(colinsItems);
		
		pdh.insertNewPlannerDetails(colinsPlanners);
		
		List<PlannerDetails> allPlanners = pdh.getPlanners();
		for(PlannerDetails c : allPlanners) {
			System.out.println(c.toString());
		}
	}
}
