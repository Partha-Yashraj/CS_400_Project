import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// --== CS400 File Header Information ==--
// Name: Partha Yashraj
// Email: pyashraj@wisc.edu
// Notes to Grader: <optional extra notes>

/**
 * This class extracts the data from an external file and creates a graph from the data
 * 
 * @author Partha Yashraj
 *
 */
public class Flights {
  // Graph representation of input data storing start, end, weight (cost)
  protected List<List<CityCostPair>> flightsList = new ArrayList<>();
  protected int[][] flightData = new int[15][3]; // flightData[i] has start, end and weight

  /**
   * Constructor for extracting the data from a file and storing into an array where each
   * flightData[i] contains start, end and weight.
   * 
   */
  public Flights() {
    File file;
    Scanner scnr = null;

    // Load from the txt file and store the data into an array
    try {
      file = new File("flightsdata.txt");
      scnr = new Scanner(file);

      for (int i = 0; i < flightData.length; i++) {
        for (int j = 0; j < flightData[i].length; j++) {
          flightData[i][j] = scnr.nextInt();
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } finally {
      scnr.close();
    }

    // Create connections between the cities
    // Create empty arraylist for each city
    for (int i = 0; i < 6; i++) {
      flightsList.add(new ArrayList<>());
    }

    // Store the respective connections for each city and weight between the two cities
    for (int[] f : flightData) {
      flightsList.get(f[0]).add(new CityCostPair(f[1], f[2]));
    }
  }
}
