package avengers;

/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of the 
 * vertices that connect to the Mind Stone. 
 * List the names of these neurons in the output file.
 * 
 * 
 * Steps to implement this class main method:
 * 
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 *    1. v (int): number of neurons (vertices in the graph)
 *    2. v lines, each with a String referring to a neuron's name (vertex name)
 *    3. e (int): number of synapses (edges in the graph)
 *    4. e lines, each line refers to an edge, each line has 2 (two) Strings: from to
 * 
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as args[1]
 * Identify the vertices that connect to the Mind Stone vertex. 
 * Output these vertices, one per line, to the output file.
 * 
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are 
 * no edges leaving the vertex.
 * 
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
 * 
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 * 
 *   To read from a file use StdIn:
 *     StdIn.setFile(inputfilename);
 *     StdIn.readInt();
 *     StdIn.readDouble();
 * 
 *   To write to a file use StdOut:
 *     StdOut.setFile(outputfilename);
 *     //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone neuron (vertex)
 *  
 * Compiling and executing:
 *    1. Make sure you are in the ../InfinityWar directory
 *    2. javac -d bin src/avengers/*.java
 *    3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 * 
 */


public class MindStoneNeighborNeurons {

    public static void main (String [] args) {
    	
    	// WRITE YOUR CODE HERE\
        String MindStoneNeighborInputFile = args[0];
        String MindStoneNeighborOutputFile = args[1];

        StdIn.setFile(MindStoneNeighborInputFile);
        StdOut.setFile(MindStoneNeighborOutputFile);

        
        //reads the number of neurons
        int vertices = StdIn.readInt(); 
        String[] vertex = new String[vertices];
        

        //puts neurons in a string array
        for(int i = 0; i < vertices; i++){
            vertex[i] = StdIn.readString();
        }
        
        //reads number of edges
        int e = StdIn.readInt();
        String[][] edges = new String[e][2]; 
        for(int i = 0; i < edges.length; i++){
            edges[i][0] = StdIn.readString();
            edges[i][1] = StdIn.readString();
        }

        //used to find the center node
        String centralVertex = "";
        int x = 0;
        while(x < edges.length){
            for(int y = 0; y < vertex.length; y++){
                if(edges[x][0] != vertex[y]) centralVertex = vertex[y];
            }
        x++;
        }

        //prints connections to center
        int xo = 0; 
        while(xo < edges.length){
            boolean equals = centralVertex.equals(edges[xo][1]);
            if(equals) StdOut.println(edges[xo][0]);
            xo++;
        }
        
        if ( args.length < 2 ) {
            StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>");
            return;
        }
    	
    }
    
}
