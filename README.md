# Cheapest Flight Booking Application

This application enables users to book flights by providing a comprehensive set of options. The data comprises a list of destinations, their connections, and the corresponding travel costs. Users can input their source and destination, the number of travelers, and the quantity of luggage, and specify whether it's a one-way or round trip. Furthermore, users can set a budget and determine the maximum number of stops they are willing to endure. Subsequently, the application calculates the total flight cost while considering these variables and determines the most economical route from the source to the destination. If no viable route exists (either due to the absence of a path or an unfeasible path within the specified number of stops), the application will issue an error message, allowing the user to modify their search parameters.

To achieve this functionality, Dijkstra's algorithm is employed. It begins with a selected node and evaluates all neighboring edge values, updating the neighboring nodes with their current shortest paths. This process continues until the algorithm identifies the shortest path. The algorithm uses a priority queue sorted according to the compareTo method for efficient traversal. Additionally, a hashtable keeps track of visited nodes, preventing the algorithm from exploring longer paths.

Here are the possible outcomes and actions for different scenarios:

Invalid Input: In the case of invalid input, the application does nothing but output an error message, prompting the user to refine their search criteria.

No Valid Path: If there is no valid path from the source to the destination, the application returns null or raises an exception, accompanied by an error message. The user is then encouraged to adjust their search parameters.

Cheapest Flight Not Possible: If finding the cheapest flight within the specified number of stops is impossible, the application returns null or raises an exception. An error message is displayed, and the user is given the opportunity to modify their search criteria.

Final Cost Exceeds Budget: If the calculated total cost surpasses the user's budget, the application displays the total cost, and the user's budget, and asks if the user wants to proceed or make changes to their flight plan.

Path Validation: The application checks whether the chosen path is valid, considering the directed nature of the graph, and verifies if it connects the specified start and end nodes.

By providing these functionalities and error-handling mechanisms, the Cheapest Flight Booking Application ensures a user-friendly experience for booking flights while accounting for various user preferences and constraints.
