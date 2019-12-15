//DFS topological ordering

import java.io.*; 
import java.util.*; 
public class DFS_topo_ordering{
	public static boolean FLAG=false;
	public static final int NUM_VERTICES = 10;
	private int graph[][];		//Input graph
	boolean visited[];			//Array to keep track of nodes that have been visited
	boolean done[];				//Array to keep track of nodes that are done
	int output[];				
	private LinkedList<Integer> neighbors[]; // Adjacency List 

public DFS_topo_ordering(){

	graph = new int[NUM_VERTICES][NUM_VERTICES ];
	visited = new boolean[NUM_VERTICES]; 
	done = new boolean[NUM_VERTICES];
	output=new int[NUM_VERTICES];
	neighbors = new LinkedList[NUM_VERTICES];
}

	/*Function to call recursive Topoplogical Sort Function*/
    void topo_sort(int input_graph[][]) 
    { 

    	 for (int i = 0; i < NUM_VERTICES; i++){
            for (int j = 0; j < NUM_VERTICES; j++){

                graph[i][j] = input_graph[i][j];
            }
        }

        Stack output = new Stack(); 
  
        //Initialising visited and done arrays to false
        //Building and storing adjacency lists for each vertex in adj array
        for (int i = 0; i < NUM_VERTICES; i++){
        	visited[i] = false;
        	done[i]=false;
        	neighbors[i] = new LinkedList();
        	get_neighbors(i);		
        } 
       
        // Call recursive Topological Sort function, for each node (in case not fully connected) 
        for (int i = 0; i < NUM_VERTICES; i++){

            if (visited[i] == false){
            	topo_sort_r(i, visited, done, output);	
             } 
            if(FLAG==true) return;
            
        }
  
        // Print contents of stack 
        System.out.println("Topological Sort: ");
        while (output.empty()==false) System.out.print(output.pop() + " "); 
      
    } 

	// Recursive function used by topo_sort 
    void topo_sort_r(int current, boolean visited[], boolean done[], Stack output) 
    { 
    	if(FLAG==true) return;
        // Mark the current node as visited. 
        visited[current] = true; 			
        int i=0; 
  
        //Iterate all neighbors of the vertex
        Iterator<Integer> neighbor_list = neighbors[current].iterator(); 
        while (neighbor_list.hasNext()) 
        { 
            i = neighbor_list.next();
            if(visited[i]==true && done[i]!= true){
            	System.out.println("CYCLE IDENTIFIED");
            	FLAG=true;
            	System.out.println("Topological Sort: (so far) ");
            	while (output.empty()==false) 
            	System.out.print(output.pop() + " "); 
            	return;
            } 
            if (visited[i]==false) 
            	topo_sort_r(i, visited, done, output); 
        } 
  		done[current]=true;
        // Push current vertex to stack which stores result 
        output.push(current); 
       
        
    } 

    /*Function to add neighbors to adjacency list*/
    private void get_neighbors(int parent)
    {
         
        for (int i = 0; i < NUM_VERTICES; i++)
        {
            if(graph[parent][i]==1) neighbors[parent].add(i);
        }
 		return;
    }
  
    
	/*A utility function to print the connected vertices pairs*/ 
    void print_graph(int graph[][]) 
    { 
        System.out.println("Pair of Vertices :"); 
        for (int i = 0; i < NUM_VERTICES; i++){
            for(int j=0;j<NUM_VERTICES ;j++){

            if(graph[i][j]!=0) System.out.println("("+i +","+ j +")"); 

            }
        }
    }

public static void main(String args[]){

	int graph[][] = new int[][]           	 {	 { 0, 1, 0, 0, 0, 1, 0, 0, 0, 0}, 
                                                 { 0, 0, 1, 1, 0, 1, 0, 0, 0, 0},
                                                 { 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                                                 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                                 { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                                                 { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                                                 { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                                                 { 0, 0, 0, 1, 0, 1, 0, 0, 1, 0},
                                                 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                                 { 0, 0, 0, 1, 1, 0, 0, 1, 0, 0}	}; 


     DFS_topo_ordering t=new DFS_topo_ordering();

     System.out.println("ORIGINAL GRAPH \n");
     t.print_graph(graph);

     //Topological Ordering
     t.topo_sort(graph);
}

}

