import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

// --== CS400 File Header Information ==--
// Name: Partha Yashraj
// Email: pyashraj@wisc.edu
// Notes to Grader: <optional extra notes>

/**
 * This class is the online flight booking for for Air airlines, it provides the user with various
 * locations the airline flies to and prompts the user to pick a start and destination, the number
 * of maximum stops the user is willing to take, the number of bags, number of adult and children
 * traveling. Afterwards, it will find the cheapest flight from start to the destination, if no such
 * path exists the user is able to reconfigure their inputs. Finally, it will calculate the total
 * cost including the bags, number of adults and children and the flight cost.
 * 
 * @author Partha Yashraj
 *
 */
public class Airline {
  private int totalCost, start, end, stops, bags, adults, children; // The final total cost, the
                                                                    // starting city, the
                                                                    // destination, the number of
                                                                    // maximum stops the user has
                                                                    // input, number of bags the
                                                                    // user is bringing, the number
                                                                    // of adults traveling and the
                                                                    // number of children traveling
  private final int BAG_COST = 30; // Per bag price
  private final int ADULT_TICKET_COST = 100; // Ticket price for adults
  private final int CHILDREN_TICKET_COST = 70; // Ticket price for children
  // The cities that the airline currently travels to
  private String[] cityList =
      new String[] {"Tokyo", "Delhi", "Rio De Janeiro", "Moscow", "Madison", "Shanghai"};
  public static Scanner scnr = new Scanner(System.in); // Scanner for taking user input

  /**
   * This method takes in the starting city, the end destination and the number of maximum stops the
   * user is willing to take. It will calculate and return the shortest (cheapest) flight cost from
   * start to end using Dijkstra's shortest path. If no such path exists and/or if the path exits
   * but it cannot be done in the maximum number of stops return -1.
   * 
   * @param start The starting city
   * @param end   The destination city
   * @param stops The maximum number of stops the user is willing to take
   * @return The cheapest flight cost, else return -1 if no such path exists and/or if the path
   *         exists but it cannot be done in the number of stops
   */
  private int calculateCheapestFlight(int start, int end, int stops) {
    Flights flight = new Flights();

    // Storing and sorting the cities by the compareTo method defined
    PriorityQueue<City> queue =
        new PriorityQueue<>((City city1, City city2) -> city1.cost - city2.cost);

    // Setting the start node distance and cost with 0
    queue.add(new City(0, start, 0));

    // Storing data and its current cheapest flight
    HashMap<Integer, Integer> visited = new HashMap<>();

    while (!queue.isEmpty()) {
      City current = queue.poll();

      // If the visited hashmap contains the city and the distance is greater, then continue, else
      // add/update the visited hashmap
      if (visited.containsKey(current.city) && visited.get(current.city) <= current.distance) {
        continue;
      }
      visited.put(current.city, current.distance);

      // Shortest/cheapest flight from start to end
      if (current.city == end) {
        return current.cost;
      }

      // If the distance is greater than the stops then continue, no such path exist from
      if (current.distance > stops) {
        continue;
      }

      // Add other outgoing edges from the current city into the priority queue
      List<CityCostPair> edgesLeaving = flight.flightsList.get(current.city);
      for (CityCostPair edge : edgesLeaving) {
        queue.add(new City(current.distance + 1, edge.city, current.cost + edge.cost));
      }
    }
    return -1; // No such route found given start and end
  }

  /**
   * Calculates and returns the total bag cost for the user
   * 
   * @param bags The number of bags the user is bringing
   * @return The cost of the total bags
   */
  private int calculateBagCost(int bags) {
    return bags * BAG_COST;
  }

  /**
   * Calculates and returns the total adult ticket cost
   * 
   * @param adult The number of adults traveling
   * @return The total adult ticket cost
   */
  private int calculateAdultTicketCost(int adult) {
    return adult * ADULT_TICKET_COST;
  }

  /**
   * Calculates and returns the total children ticket cost
   * 
   * @param children The number of adults traveling
   * @return The total adult ticket cost
   */
  private int calculateChildrenTicketCost(int children) {
    return children * CHILDREN_TICKET_COST;
  }

  /**
   * Calculates and returns the total cost after calculating the bag, adult, children, and the
   * flight cost.
   * 
   * @param bags     The number of bags
   * @param adult    The number of adults
   * @param children The number of children
   * @param start    The starting city
   * @param end      The end city
   * @param stops    The maximum number of stops the user is willing to take
   * @return The total cost for the entire flight planF
   */
  public int calculateTotalCost(int bags, int adult, int children, int start, int end, int stops) {
    return calculateBagCost(bags) + calculateAdultTicketCost(adult)
        + calculateChildrenTicketCost(children) + calculateCheapestFlight(start, end, stops);
  }

  /**
   * Prompts the user until a valid input is given and sets the starting city
   */
  public void setStart() {
    for (int i = 0; i < cityList.length; i++) {
      System.out.println(i + ". " + cityList[i]);
    }
    do {
      System.out
          .println("Please enter a number in range (0-5) corresponding to your starting city.");
      while (!scnr.hasNextInt()) {
        System.out.println("Please enter a valid number.");
        scnr.next();
      }
      this.start = scnr.nextInt();
    } while (this.start < 0 || this.start > 5);
  }

  /**
   * Prompts the user until a valid input is given and sets the ending city
   */
  public void setEnd() {
    do {
      System.out.println("Please enter a number(not the same as the starting city) in range (0-5) "
          + "corresponding to your destination city.");
      while (!scnr.hasNextInt()) {
        System.out.println("Please enter a valid number.");
        scnr.next();
      }
      this.end = scnr.nextInt();
    } while (this.end < 0 || this.end > 5 || this.end == this.start);
  }

  /**
   * Prompts the user until a valid input is given and sets the maximum number of stops the user is
   * willing to take
   */
  public void setMaxStops() {
    do {
      System.out.println("Please enter the maximum number of stops you are "
          + "willing to take in range from (0-4)");
      while (!scnr.hasNextInt()) {
        System.out.println("Please enter a valid number.");
        scnr.next();
      }
      this.stops = scnr.nextInt();
    } while (this.stops < 0 || this.stops > 4);
  }

  /**
   * Prompts the user until a valid input is given and sets the number of bags the user is bringing
   */
  public void setNumberOfBags() {
    do {
      System.out.println("Please enter the number of bags you are bringing "
          + "(0 if you are not bringing any bags). Each bag costs " + this.BAG_COST + " USD.");
      while (!scnr.hasNextInt()) {
        System.out.println("Please enter a valid number.");
        scnr.next();
      }
      this.bags = scnr.nextInt();
    } while (this.bags < 0);
  }

  /**
   * Prompts the user until a valid input is given and sets the number of adults traveling
   */
  public void setNumberOfAdults() {
    do {
      System.out.println("Please enter the number of adults traveling. Each adult ticket costs "
          + this.ADULT_TICKET_COST + " USD.");
      while (!scnr.hasNextInt()) {
        System.out.println("Please enter a valid number.");
        scnr.next();
      }
      this.adults = scnr.nextInt();
    } while (this.adults < 0);
  }

  /**
   * Prompts the user until a valid input is given and sets the number of children traveling
   */
  public void setNumberOfChildren() {
    do {
      System.out
          .println("Please enter the number of children traveling. Each children ticket costs "
              + this.CHILDREN_TICKET_COST + " USD.");
      while (!scnr.hasNextInt()) {
        System.out.println("Please enter a valid number.");
        scnr.next();
      }
      this.children = scnr.nextInt();
    } while (this.children < 0);
  }


  /**
   * The main method runs the entire program and loops for when the user wishes to change and update
   * some information for their flight
   * 
   * @param args Not used
   */
  public static void main(String[] args) {
    Airline a = new Airline();
    String userConfirmation = null;

    System.out.println(
        "Hello! Welcome to the Air airlines online booking. Please see and choose from the "
            + "following cities your start and your destination.");
    do {
      do {
        // Set start
        a.setStart();

        // Set end (Destination)
        a.setEnd();

        // Number of maximum stops
        a.setMaxStops();

        // Find the cheapest flight and update totalCost
        a.totalCost = a.calculateCheapestFlight(a.start, a.end, a.stops);
        if (a.totalCost == -1) {
          System.out
              .println("No such path exist, please modify, your start, destination, and/or the "
                  + "maximum number of stops.");
        }
      } while (a.totalCost == -1);

      // Sets number of user bags
      a.setNumberOfBags();

      // Sets number of adults
      a.setNumberOfAdults();

      // Sets number of children
      a.setNumberOfChildren();

      // Calculate total cost
      a.totalCost = a.calculateTotalCost(a.bags, a.adults, a.children, a.start, a.end, a.stops);

      // Display all the information that the user input
      System.out.println("Here is the overview of your flight:\nStart: " + a.cityList[a.start]
          + "\nDestination: " + a.cityList[a.end] + "\nStops: " + a.stops + "\nNumber of adults: "
          + a.adults + "\nNumber of children: " + a.children + "\nNumber of bags: " + a.bags
          + "\nTotal cost: " + a.totalCost + " USD");

      // Prompt the user for confirmation until a "y" or "n" is input, if yes then display a final
      // message and terminate the program, if no then loop back to the top and let the user change
      // their options
      System.out.println("\nShould we continue with this flight plan? If you choose no, "
          + "you will be able to change your choices from before.");
      do {
        System.out.println("Please enter \"y\" for yes or \"n\" for no");
        while (!scnr.hasNext()) {
          System.out.println("Please enter \"y\" or \"n\"");
          scnr.next();
        }

        userConfirmation = scnr.next();
      } while (!userConfirmation.equalsIgnoreCase("y") && !userConfirmation.equalsIgnoreCase("n"));

    } while (userConfirmation.equalsIgnoreCase("n"));

    System.out.println("Thank you for booking wth Air airlines, you will soon be emailed your "
        + "receipt and all necessary information.\nHave a safe flight!");

    // Close the scanner
    scnr.close();
  }
}
