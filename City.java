// --== CS400 File Header Information ==--
// Name: Partha Yashraj
// Email: pyashraj@wisc.edu
// Notes to Grader: <optional extra notes>

/**
 * This class stores the information about a city: the distance from the start and the cost from the
 * start source
 * 
 * @author Partha Yashraj
 *
 */
public class City {
  int distance, city, cost; // Keep track of distance from start, the city and the cost from the
                            // start

  /**
   * Constructor for initializing the variables
   * 
   * @param distance set the distance from start
   * @param city     the city
   * @param cost     set the cost form the start
   */
  City(int distance, int city, int cost) {
    this.distance = distance;
    this.city = city;
    this.cost = cost;
  }

}
