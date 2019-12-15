//Java file to heap sort an array of N random numbers

public class HeapSort 
{ 
	/*Function to heap sort given an array*/
	static void h_sort(int arr[]){
		int n=arr[0];
		
		System.out.println("Fixing Tree.... \n");
		fix_tree(arr);
		System.out.println("Building Max Heap....");	

		//Build max heap
		for(int parent=n/2;parent>0;parent--){
			perc_down(arr,parent);
		}
		
		printArray(arr);

		System.out.println("Sorting...");

		swap(arr,1,n);
		//Percolate down; swap top element with bottom of array; decremenet size of array
		while(n!=1){

			perc_down(arr,1);

			//at the end of each pass, we swap the top element to the end of the array
			swap(arr,1,n);

			//update size of array
			n=n-1;				
			arr[0]=n;					
		}
		
	}

	/*Function to percolate down*/
	static void perc_down(int arr[], int par_index){

		if(par_index>=arr[0] || par_index*2>arr[0] ) return;
		
		int l_child_index=par_index*2;
		int r_child_index=par_index*2+1;

		int swapped=-2;

		//left child is bigger than parent and right child
		if(arr[par_index]<arr[l_child_index] && (r_child_index>=arr[0]||arr[l_child_index]>arr[r_child_index])) {
			swap(arr,par_index,l_child_index);
			swapped=l_child_index;	
		}

		//if right child doesn't exist, doesn't compare parent with right child.
		else if(r_child_index<arr[0]){
			//right child is greater than parent
			if (arr[par_index]<arr[r_child_index]){
				swap(arr, par_index,r_child_index);
				swapped=r_child_index;
			}	
		} 
		//recursively heapify for the children
		if(swapped!=-2) perc_down(arr, swapped);	

	}

	/*Function to fix tree into a complete tree*/
	static void fix_tree(int arr[]){
		int last_index=arr.length-1;
		
		for(int i=1;i<last_index+1;i++){
			if(arr[i]==-1){
				
				swap(arr,i,last_index);
				last_index--;
				
			}
		}

		System.out.println("The tree has been fixed to a complete tree:");
		printArray(arr);
		
	}

	/*Function to swap two elements, given their indices*/
	static void swap(int arr[], int a, int b){
		int temp=arr[a];
		arr[a]=arr[b];
		arr[b]=temp;
		return;
	}

	/*Function to print the array*/
	static void printArray(int arr[]) 
	{ 
		int n = arr.length; 
		for (int i=0; i<n; ++i) 
			System.out.print(arr[i]+" "); 
		System.out.println(); 
	} 

	public static void main(String args[]) 
	{ 
		//index 0 of the array contains the number of integers in the heap
		int arr[] = {20,10,38,1,83,25,39,92,37,88,64,43,-1,62,81,95,78,84,69,19,26,2}; 
		
		System.out.println("Note: index 0 of the array contains the number of integers in the heap"); 
		System.out.println("Original array is"); 
		printArray(arr);

		HeapSort new_heap = new HeapSort(); 
		new_heap.h_sort(arr); 

		System.out.println("Sorted array is"); 
		printArray(arr); 
	} 
} 
