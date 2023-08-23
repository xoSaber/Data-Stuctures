package avengers;

/**
 * Given an adjacency matrix, use a random() function to remove half of the nodes. 
 * Then, write into the output file a boolean (true or false) indicating if 
 * the graph is still connected.
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 *    1. seed (long): a seed for the random number generator
 *    2. p (int): number of people (vertices in the graph)
 *    2. p lines, each with p edges: 1 means there is a direct edge between two vertices, 0 no edge
 * 
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency matrix for
 * an undirected graph. 
 * 
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the matrix, 0-1, 1-0, 0-2, 2-0).
 * 
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 * 
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) { 
 *     if (StdRandom.uniform() <= 0.5) { 
 *          delete vertex;
 *     }
 * }
 * Answer the following question: is the graph (after deleting random vertices) connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 * 
 * Note 1: a connected graph is a graph where there is a path between EVERY vertex on the graph.
 * 
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut (here, isConnected is true if the graph is connected,
 *   false otherwise):
 *     StdOut.setFile(outputfilename);
 *     StdOut.print(isConnected);
 * 
 * @author Yashas Ravi
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in predictthanossnap.out
*/

public class PredictThanosSnap {
    
    

    private static void dfs(int[][] adj , boolean[] marked, int v) {
        marked[v] = true;
        for(int i = 0; i < adj[0].length; i++){
            if(adj[v][i] == 1 && (!marked[i])) dfs(adj, marked, i);
        }
    }
	 
    public static void main (String[] args) {
 
        if ( args.length < 2 ) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }
        
    	// WRITE YOUR CODE HERE
        String PredictThanosSnapInputFile = args[0];
        String PredictThanosSnapOutputFile = args[1];
        StdIn.setFile(PredictThanosSnapInputFile);
        StdOut.setFile(PredictThanosSnapOutputFile);


        long seed = StdIn.readLong();
        int Nodes = StdIn.readInt();
        int r = Nodes;
        int c = Nodes;
        int[][] adjMatrix = new int[r][c];
        boolean[] markedIndex = new boolean[Nodes];

        //make adjacency matrix
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                adjMatrix[i][j] = StdIn.readInt();
            }
        }

        //random delete
        boolean[] deletedIndex = new boolean[Nodes];
        StdRandom.uniform(seed);
        for(int i = 0; i < Nodes; i++){
            if(StdRandom.uniform() <= 0.5){
                for(int j = 0; j < Nodes; j++){
                    deletedIndex[i] = true;
                    adjMatrix[i][j] = 0;
                    adjMatrix[j][i] = 0;
                }
            }
        }

        //takes first non deleted node and use for ur dfs
        int x = 0;
        while(x < deletedIndex.length){
            if(deletedIndex[x] == false){
                dfs(adjMatrix, deletedIndex, x);
                break;
            }
            x++;
        }
        
        //checks if the all the nodes are connected
        boolean connected = true;
        int y = 0;
        while(y < markedIndex.length){
            if(deletedIndex[y] == false && markedIndex[y] == false){
                connected = false;
                break;
            }
            y++;
        }
       
        StdOut.print(connected);



        

    }
}
