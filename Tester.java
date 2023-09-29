import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// --== CS400 File Header Information ==--
// Name: Partha Yashraj
// Email: pyashraj@wisc.edu
// Notes to Grader: <optional extra notes>

/**
* The input for the tests is in the
* format calculateTotalCost(bags, adult, children, start, end, stops)
*/
public class Tester{

protected Airline a; // Instance of the airline class

   /**
   * Before each test create a new instance
   * of the Airline class.
   */
   @BeforeEach
   public void createNewAirline(){
   a = new Airline();
  }

   /**
   * This method tests whether the correct cost is
   * calculated given a valid path from start to end 
   * within the stops.
   */
   @Test
   public void cheapFlight(){
   assertEquals(944, a.calculateTotalCost(0,0,0,2,4,4));
   }

   /**
   * This method tests whether we get a correct output
   * of (-1) if no such path exists and/or its not
   * possible within the number of stops.
   */
   @Test
   public void invalidFlight(){
   assertEquals(-1, a.calculateTotalCost(0,0,0,0,3,0));
   }

   /**
   * This method tests whether we get a correct output
   * for the total bag cost.
   */
   @Test
   public void testBagCost(){
   assertEquals(300, a.calculateTotalCost(10,0,0,0,0,0));
   }

   /**
   * This method tests whether we get a correct output
   * for the total adult ticket.
   */
   @Test
   public void testAdultTicketCost(){
   assertEquals(600, a.calculateTotalCost(0,6,0,0,0,0));
   }
	
   /**
   * This method tests whether we get a correct output
   * for the total children ticket.
   */
   @Test
   public void testChildrenTicketCost(){
   assertEquals(140, a.calculateTotalCost(0,0,2,0,0,0));
   }

   /**
   * This method tests whether we get a correct output
   * after taking number of bags, adults, children, start,
   * destination and the maximum number of stops.
   */
   @Test
   public void testAllInputTotalCost(){
   assertEquals(795, a.calculateTotalCost(4,2,2,0,4,0));
   }
}
