package avengers;


/**
 * 
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0), 
 * modify the edge weights using the functionality values of the vertices that each edge 
 * connects, and then determine the minimum cost to reach Titan (vertex n-1) from Earth (vertex 0).
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 *    1. g (int): number of generators (vertices in the graph)
 *    2. g lines, each with 2 values, (int) generator number, (double) funcionality value
 *    3. g lines, each with g (int) edge values, referring to the energy cost to travel from 
 *       one generator to another 
 * Create an adjacency matrix for g generators.
 * 
 * Populate the adjacency matrix with edge values (the energy cost to travel from one 
 * generator to another).
 * 
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by DIVIDING it 
 * by the functionality of BOTH vertices (generators) that the edge points to. Then, 
 * typecast this number to an integer (this is done to avoid precision errors). The result 
 * is an adjacency matrix representing the TOTAL COSTS to travel from one generator to another.
 * 
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and Titan. 
 * Output this number into your output file!
 * 
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, minCost represents the minimum cost to 
 *   travel from Earth to Titan):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(minCost);
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 * 
 * @author Yashas Ravi
 * 
 */

public class LocateTitan {

    private static int getMinCostNode(int[] minVals, boolean[] visted) {
    int min = 0;
    int x = Integer.MAX_VALUE;
    int i = 0;
    while(i < minVals.length) {
      if (visted[i] != true && minVals[i] < x) {
        min = i;
        x = minVals[i];
      }
      i++;
    }
    return min;
  }
	
    public static void main (String [] args) {
    	
        if ( args.length < 2 ) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

    	// WRITE YOUR CODE HERE
        String LocateTitanInputFile = args[0];
        String LocateTitanOutputFile = args[1];

        StdIn.setFile(LocateTitanInputFile);
        StdOut.setFile(LocateTitanOutputFile);  
        
        //set vertex values
        int Nodes = StdIn.readInt();
        double[] values = new double[Nodes];
        for(int i = 0; i < Nodes; i++){
            values[i] = StdIn.readDouble(); 
            values[i] = StdIn.readDouble();
        }

        //make adjMatrix
        int[][] adjMatrix = new int[Nodes][Nodes];
        for(int i = 0; i < Nodes; i++){
            for(int j = 0; j < Nodes; j++){
                adjMatrix[i][j] = StdIn.readInt(); 
            }
        } 

       int[] minCost = new int[Nodes];
       boolean[] DijkstraSet = new boolean[Nodes];

        for(int i = 0; i < minCost.length; i++){
            if(i == 0) minCost[i] = 0;    
            else minCost[i] = Integer.MAX_VALUE; 
        }


        for (int row = 0; row < adjMatrix.length; row++) {
            for (int col = 0; col < adjMatrix[0].length; col++) {
                adjMatrix[row][col] = (int) (((double) adjMatrix[row][col]) / (values[row] * values[col]));
            }
        }

        for (int i = 0; i < minCost.length; i++) {
          if (i == 0) minCost[i] = 0;
          else minCost[i] = Integer.MAX_VALUE;
        }

      for (int i = 0; i < minCost.length - 1; i++) {
        int source = getMinCostNode(minCost, DijkstraSet);
        DijkstraSet[source] = true;
        int j = 0; 
        while (j < minCost.length) {
          if (adjMatrix[source][j] > 0) {
            if (DijkstraSet[j] == false && minCost[source] != Integer.MAX_VALUE && minCost[source] + adjMatrix[source][j] < minCost[j]) minCost[j] = minCost[source] + adjMatrix[source][j];
          }
          j++;
        }
      } 

      int finalMin = minCost[Nodes - 1];

      StdOut.print(finalMin);
    }
}
