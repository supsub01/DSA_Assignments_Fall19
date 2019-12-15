//Directed graph. From Row vertice to Column vertice. i.e row value is always the parent 
import java.util.*; 
import java.lang.*; 
import java.io.*; 
 

public class DPQ
{
    public static final int NUM_VERTICES = 10;

    private int graph[][];
    private Set<Integer> settled;
    private int dist[];
    private int par[];
    private int edge_length[];
    private PriorityQueue<Node> priorityQueue;
    
 
    public  DPQ()
    {
        graph = new int[NUM_VERTICES][NUM_VERTICES ];
        settled = new HashSet<Integer>();
        dist = new int[NUM_VERTICES];
        par = new int[NUM_VERTICES];
        edge_length = new int[NUM_VERTICES];
        priorityQueue = new PriorityQueue<Node>(NUM_VERTICES,new Node());
    }
    
    /*Function to execute Dijkstra's algorithm*/
    public void dijkstra(int input_graph[][], int source)
    {       
        for (int i = 0; i < NUM_VERTICES; i++){
            for (int j = 0; j < NUM_VERTICES; j++){

                graph[i][j] = input_graph[i][j];
            }
        }
        
        //setting all distances to initial infinity
        for (int i = 0; i < NUM_VERTICES; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            par[i]=0;
            edge_length[i]=0;
        }
        
        //Adding source to heap, with distance 0
        priorityQueue.add(new Node(source, 0));
        //setting distance of source to 0
        dist[source] = 0;

        //while queue not empty, remove vertice with shortest distance, add to settled list, add its neighbors to the queue
        while (!priorityQueue.isEmpty())
        {
            int cur_node = priorityQueue.remove().node;
            settled.add(cur_node);
            get_neighbors(cur_node);

        }

        //replace the graph with the right values
        for(int i=0;i<NUM_VERTICES;i++){
            int p=par[i];
            for(int j=0;j<NUM_VERTICES;j++){
                //iterating vertically
                if(j==p){
                  input_graph[p][i]=edge_length[i]; 
                }
                else input_graph[j][i]=0;
            }    
        }
    } 
 
    /*Function to add neighbors to priority queue, and update their distances*/
    private void get_neighbors(int parent)
    {
        int new_dist = 0;
 
        for (int i = 0; i < NUM_VERTICES; i++)
        {
            if (!settled.contains(i) && graph[parent][i] !=0)      
            {
                //calculating the new distance from the source and replacing it with old distance if it is smaller
                new_dist = dist[parent] + graph[parent][i];
                if (new_dist < dist[i]){

                    dist[i] = new_dist;
                    par[i]=parent;
                    edge_length[i]= graph[parent][i];
                }                      
                priorityQueue.add(new Node( i , dist[i] ) ) ;
                   
            }
        }
    }

    // A utility function to print the constructed distance array 
    void print_graph(int graph[][]) 
    { 
        System.out.println("Pair of Vertices \t Distance from Parent"); 
        for (int i = 0; i < NUM_VERTICES; i++){
            for(int j=0;j<NUM_VERTICES ;j++){

            if(graph[i][j]!=0) System.out.println("("+i +","+ j +")"+ " \t\t\t " + graph[i][j]); 

            }
        }
    }


 
    public static void main(String[] args)
    {
        int source = 0;

        int graph[][] = new int[][]            { { 0, 10, 20, 0, 53, 0, 8, 84, 0, 0}, 
                                                 { 0, 0, 0, 50, 10, 0, 0, 84, 0,  0}, 
                                                 { 0, 0, 0, 20, 33, 0, 0, 0, 57, 0}, 
                                                 { 0, 0, 0, 0, 20, 2, 0, 0, 0, 95},
                                                 { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                                                 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 80},
                                                 { 0, 0, 21, 0, 0, 0, 0, 0, 63, 0},
                                                 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 75},
                                                 { 0, 0, 0, 0, 0, 21, 0, 0, 0, 0},
                                                 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};  
        
            DPQ t = new DPQ();
            System.out.println("ORIGINAL GRAPH \n");
            t.print_graph(graph);
            t.dijkstra(graph, source);
            System.out.println("REMAINING GRAPH AFTER DIJKSTRAS ALGORITHM \n");
            t.print_graph(graph);

            System.out.println(" \nThe Distances from the source are: ");
            for (int i = 0; i <= t.dist.length - 1; i++)
            {
                System.out.println(source + " to " + i + " is " + t.dist[i]);
            }
        
        
    }	
}

//Class node that implements a user defined compare which ensures lowest distance on top of queue
class Node implements Comparator<Node> { 
    public int node; 
    public int cost; 

    public Node() 
    { 
    } 

    public Node(int node, int cost) 
    { 
        this.node = node; 
        this.cost = cost; 
    } 

    @Override
    public int compare(Node node1, Node node2) 
    { 
        if (node1.cost < node2.cost) 
            return -1; 
        if (node1.cost > node2.cost) 
            return 1; 
        return 0; 
    } 
}