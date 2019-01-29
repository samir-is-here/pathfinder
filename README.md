# City Path Finder

This service accepts 2 inputs: orgin and destination in the URL Query params, and returns 'yes' if there exists a path between origin and destination, else it will return 'no'


## Quick Start
1. Make sure you have maven installed on your machine. You can download it from this [link](https://maven.apache.org/download.cgi)
2. Follow installation steps for maven from this [link](https://maven.apache.org/install.html)
3. Download the code from github and unpack it in a directory
4. Run following command from the root directory where pom.xml exists
>`mvn install` - This will install the dependencies required and build the project and run Unit tests
5. To run the application prepare an input file in csv format, which has pairs of cities which are connected by a road

Example city.txt:

    Boston, New York
    Philadelphia, Newark
    Newark, Boston
    Trenton, Albany

6. Use following command format to bring the server
>`java -jar target\pathfinder-0.0.1-SNAPSHOT.jar <path-to-input-csv-file>`
    
    Where <path-to-input-csv-file> has the file path of city.txt. 

Example: 
>`java -jar .\target\pathfinder-0.0.1-SNAPSHOT.jar "C:\city.txt"`

This command will pickup input file and bring up the server

7. To Test the REST API - use URL in following format

    http://localhost:8080/connected?origin=city1&destination=city2



### High Level Design
1. When the application starts, input file will be loaded and a Graph will be created for the paths between the cities
2. Controller will route the REST API requests to `/connected` path and parse the query parameters to call the service
>Jersey framework is used to make routing configuration easier
3. Service will check if there exists a path between two nodes in the graph using Breadth First Search algorithm. It will return a boolean value.
>Time complexity for BFS algorithm is O(n+e), where n = number of node and e= number of edges
4. Spring-boot Caching (using @EnableCaching and @Cacheable annotations) is used to cache the response of the service once the response for a pair of cities is calculated.


### Limitations
1. This service does not allow for modifying the paths once the file is loaded
2. Everytime the path needs to be changed the server has to be bounced and the graph will be recreated




