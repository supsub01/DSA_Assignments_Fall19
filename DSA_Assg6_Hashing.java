//DSA Assignment- Hashing
//READ ME: Running the program will create two dictionary hash tables- one with linear probing indexing and one with 
//quadratic probing indexing. It will then interact with a user to enter a word, to check whether the word is valid 
//(int the dictionary) or invalid (not in the dictionary). To exit the program, enter q. 
import java.util.*; 
import java.lang.*; 
import java.io.*;
import java.math.*;

public class Hashing{

    int flag;
    int word_count;
    String[] MapEntry;
    int table_size;
    int linear_collisions=0;
    int quadratic_collisions=0;
    static final double load_factor=0.5;
    
    public Hashing( int n){
        flag=n;     //if flag=0, do linear collision handling, else do quadratic collision handling.
    }

    //FUnction to create dictionary
    void create_dictionary(int t_size){
        System.out.println("Creating Dictionary...");
        System.out.println("\t Table Size= "+t_size);   
        word_count=0;
        table_size=t_size;
        linear_collisions=0;
        quadratic_collisions=0;
        MapEntry = new String[t_size];

        try {
            
            BufferedReader bufferedReader = new BufferedReader(new FileReader("dictionary.txt"));
            String line = null; // notes one line at a time
            while((line = bufferedReader.readLine()) != null) {
                int index=compute_hash(line);
                if(MapEntry[index]!= null){
                     if(flag==0) {
                        ++linear_collisions;
                        collision_linear(line,index);
                    }
                    else {
                        ++quadratic_collisions;
                        collision_quadratic(line,index);
                    }
                }

                else MapEntry[index]=line;
                ++word_count;
                if(word_count*2>=table_size) {
                    rehash(table_size);
                    return;   
                }
            }
            bufferedReader.close(); //close file        
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();//print error             
        }
        catch(IOException ex) {
            ex.printStackTrace();//print error
        }
        if(flag==0) System.out.println("\t Number of Linear collision= "+linear_collisions);
        else System.out.println("\t Number of Quadratic collision= "+quadratic_collisions);
    }

    //Function to compute the hashing value from the given word
    int compute_hash(String s) {
        
        int hash_value=0;

        for(int i=0;i<s.length();i++){
            hash_value=37*hash_value+s.charAt(i);
        }

         hash_value=hash_value%table_size;
            if(hash_value<0) /*In case overflow occurs*/
                hash_value=hash_value+table_size;

        return hash_value;
    }
	
    //Function to handle collision and find appropriate index to store word in using linear probing
    void collision_linear(String word, int index){

        while(MapEntry[index%table_size]!=null) ++index;
        MapEntry[index%table_size]=word;

    }
    //Function to handle collision and find appropriate index to store word in using quadratic probing
    void collision_quadratic(String word, int index){
        int i=1;
        while(MapEntry[(int)(index+Math.pow(i,2))%table_size]!=null) ++i;
        MapEntry[(int)(index+Math.pow(i,2))%table_size]=word;

    }

    //Function to find the size as next prime number after 2*tablesize, and create a dictionary with this new size.
    void rehash(int table_size){
        if(flag==0) System.out.println("\t Number of Linear collision= "+linear_collisions);
        else System.out.println("\t Number of Quadratic collision= "+quadratic_collisions);

        System.out.println("\t Need to Rehash!");
        
        int new_table_size=nextPrime(table_size*2);
        System.out.println("\t Increasing table size to "+new_table_size+"\n");
        create_dictionary(new_table_size);

    }
    /*Utility function for rehash new table size*/
    // Function that returns true if n  
    // is prime else returns false  
    static boolean isPrime(int n)  
    {  
        // Corner cases  
        if (n <= 1) return false;  
        if (n <= 3) return true;  
          
        // This is checked so that we can skip  
        // middle five numbers in below loop  
        if (n % 2 == 0 || n % 3 == 0) return false;  
          
        for (int i = 5; i * i <= n; i = i + 6)  
            if (n % i == 0 || n % (i + 2) == 0)  
            return false;  
          
        return true;  
    }  
    /*Utility function for rehash new table size*/
    static int nextPrime(int N)  
    {  
      
        // Base case  
        if (N <= 1)  
            return 2;  
      
        int prime = N;  
        boolean found = false;  
      
        // Loop continuously until isPrime returns  
        // true for a number greater than n  
        while (!found)  
        {  
            prime++;  
      
            if (isPrime(prime))  
                found = true;  
        }  
      
        return prime;  
    }
    //Function to check whether the input is in the dictionary (valid) or not (not valid)
    void get_linear(String key) 
    { 
        // Apply hash function to find index for given key 
        int hashIndex = compute_hash(key); 
        //finding the node with given key    
        while(MapEntry[hashIndex] != null) 
        {    int counter =0; 
             if(counter++>table_size){              //to avoid infinite loop
                System.out.println("Word is not valid!");
                return;
             }   
            //if node found return its value 
            if(MapEntry[hashIndex].equals(key)){

                System.out.println("Word is valid!");
                return;
            } 
 
            ++hashIndex; 
            hashIndex %= table_size; 
        } 
          
        //If not found return null 
        System.out.println("Word is not valid!");
        return;// null; 
    }

public static void main(String args[]){

	    int table_size=53;
        Hashing dictionary_linear = new Hashing(0);
        Hashing dictionary_quadratic = new Hashing(1);
        System.out.println("Starting Hashing with LINEAR collisions...");
        dictionary_linear.create_dictionary(table_size);
        //dictionary_linear.print_dict();                   //#DEBUG
        System.out.println("\n Starting Hashing with QUADRATIC collisions...");
        dictionary_quadratic.create_dictionary(table_size);

        while(true){

            System.out.println("\n Please enter a word to check: \n");
            String input;
            Scanner scanIn = new Scanner(System.in);
            input = scanIn.nextLine();
            if(input.equals("q")){
                System.out.println("GoodBye!");
              System.exit(0);  
            } 
            dictionary_linear.get_linear(input);
        }


        

}




}