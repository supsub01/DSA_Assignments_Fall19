// Java program to insert, delete nodes in a BST and inorder traverse the tree and print values

class BinarySearchTree { 

	class Node { 
		int data; 
		Node left, right; 

		public Node(int val) { 
			data = val; 
			left = right = null; 
		} 
	} 

	Node root; 
	//Constructor
	BinarySearchTree() { 
		root = null; 
	} 

	//Traverse and print
	/*Calls the recursive traversal function, passing original root as argument*/
	 void inorder_trav()  { 
    	   inorder_trav_r(root); 
    }
	/*Recursive function to traverse BST inorder and print values*/
	void inorder_trav_r(Node root) { 
		if (root != null) { 
			inorder_trav_r(root.left); 
			System.out.println(root.data); 
			inorder_trav_r(root.right); 
		} 
	}

	//Insert Nodes
	/*Calls the recursive insert function, passing data and original root as arguments*/
	void insert(int data) { 
	root = insert_r(root, data); 
	} 
	/*Recursive function to insert new node in BST */
	Node insert_r(Node root, int data) { 

		/* If leaf has found its space, create root with data value */
		if (root == null) { 
			root = new Node(data); 
			return root; 		//remember, this return updates the value of root.left or root.right, not the original root
		} 						

		/* Otherwise, traverse the tree to find a suitable loaction for the node*/
		if (data < root.data) 
			root.left = insert_r(root.left, data); 
		else if (data > root.data) 
			root.right = insert_r(root.right, data); 

		/* return the original root */
		return root; 				//this is encountered when the orignal recursive call is 
									//returning to insert, it gives back the root value to store in the root value of insert() call
	} 

	//Delete node
 	/*Calls the recursive delete function, passing data and original root as arguments*/
    void delete(int data) 
    { 
        root = delete_r(root, data); 
    } 
  
    /* A recursive function to delete a node*/
    Node delete_r(Node root, int data) 
    { 
        /*empty tree*/
        if (root == null)  return root; 
  
        /* traverse tree till data found */
        if (data < root.data) 
            root.left = delete_r(root.left, data); 
        else if (data > root.data) 
            root.right = delete_r(root.right, data); 
  
        //data=root.data
        else
        { 
            // node w/ one or no children
            //the last iterative call will return the next 
            if (root.left == null) 
                return root.right; 		//will make the right leaf/node the new node.
            else if (root.right == null) 
                return root.left; 		//will make the left leaf/node the new node, if right not present
  
            // node w/ 2 children
            //finding the predecessor
            root.data = predecessor(root.left); 
  
            // Delete the inorder predecessor
            root.left = delete_r(root.left, root.data);
        } 
  
        return root; 
    } 
  	
  	//Predecessor:rightmost node of left subtree
    int predecessor(Node root) 
    { 
        while (root.right != null) 
        { 
            root = root.right; 
        } 
        return root.data; 

    } 
 
	public static void main(String[] args) { 
		BinarySearchTree tree = new BinarySearchTree(); 

		//100, 50, 200, 150, 300, 25, 75, 12, 37, 125, 175, 250, 320, 67, 87, 94, 89,92,88
		tree.insert(100); 
		tree.insert(50); 
		tree.insert(200); 
		tree.insert(150); 
		tree.insert(300); 
		tree.insert(25); 
		tree.insert(75);
		tree.insert(12);
		tree.insert(37);
		tree.insert(125);
		tree.insert(175);
		tree.insert(250);
		tree.insert(320);
		tree.insert(67);
		tree.insert(87);
		tree.insert(94);
		tree.insert(89);
		tree.insert(92);
		tree.insert(88); 

		// print inorder traversal of the BST 
		tree.inorder_trav(); 

		 tree.delete(100);
		 System.out.println("\n");
		 tree.inorder_trav(); 
	} 
} 
