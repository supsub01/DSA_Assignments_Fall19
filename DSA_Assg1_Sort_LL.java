// Java program to sort singly linked list in ascending order by traversing the list and using insertion sort 

public class DSA_Sort_LinkedList 
{ 
	node head; 

	class node 
	{ 
		int val; 
		node next; 

		public node(int val) 
		{ 
			this.val = val; 
		} 
	} 

	/*Function to add new node to head*/
	void add(int val) 
	{ 
		node n = new node(val); 
		n.next = head; 
		head = n; 
	} 

	/*Function to sort*/
	public node sort(node list_head){

		System.out.println(" \n Sorting the list....");

		if(list_head.next==null) return list_head;

		node current=list_head.next;
		node prev=list_head;
		node index_to_swap_to=list_head;
		node index_to_swap_to_prev=null;
		node min=list_head;
		node min_prev=null;

		while(index_to_swap_to.next!=null){

			min=index_to_swap_to;
			current=index_to_swap_to.next;
			prev=index_to_swap_to;

			while(current!=null){

				if(current.val<min.val){
					min=current;
					min_prev=prev;
				}
				current=current.next;
				prev=prev.next;
			}
			
			swap(index_to_swap_to_prev,index_to_swap_to,min_prev,min);

			/*Resetting the values of the references*/
			index_to_swap_to=min;

			if(index_to_swap_to==head) index_to_swap_to_prev=head;
			else index_to_swap_to_prev=index_to_swap_to_prev.next;

			index_to_swap_to=index_to_swap_to.next;
		}
		return head;
	}
			

	public void swap(node to_swap_prev, node to_swap, node N2_prev, node N2){
		if(to_swap.val==N2.val) return;
		
		/*Case1: if to_swap==head*/
		if(to_swap==head){
			node dummy=N2.next;

			//Fix N2 in place
			if(to_swap.next==N2) N2.next=to_swap;
			else N2.next=to_swap.next;	//unless to_swap.next=N2
			head=N2;
			
			//Fix to_swap in place
			if(N2_prev==to_swap) N2_prev.next=dummy;
			else N2_prev.next=to_swap; //unless N2_prev=to_swap
			to_swap.next=dummy;

		}

		else{
			
			node dummy=N2.next;

			//Fix N2 in place
			to_swap_prev.next=N2;
			if(to_swap.next==N2) N2.next=to_swap;
			else N2.next=to_swap.next; //unless to_swap.next==N2

			//Fix to swap in place 
			if(N2_prev!=to_swap) N2_prev.next=to_swap; //unless N2_prev==to_swap
			to_swap.next=dummy;

		}


	}


	/* Function to print linked list */
	void print_list(node list_head) 
	{ 
		while (list_head != null) 
		{ 
			System.out.print(list_head.val + " "); 
			list_head = list_head.next; 
		} 
		System.out.print("\n");
	} 
	
	/*Main Testing program*/ 
	public static void main(String[] args) 
	{ 
		DSA_Sort_LinkedList list = new DSA_Sort_LinkedList(); 

		list.add(5);
		list.add(15);
		list.add(3);
		list.add(6);
		list.add(2);
		list.add(10); 
		list.add(100); 
		list.add(3); 
		list.add(13); 
		list.add(2); 
		list.add(11); 
		list.add(5); 
		list.add(9); 
		list.add(8); 
		list.add(20); 
		list.add(1);  
		
		System.out.println("Linked List before Sorting.."); 
		list.print_list(list.head); 
		list.sort(list.head); 
		System.out.println("\nLinkedList After sorting"); 
		list.print_list(list.head); 
	} 
} 

 
