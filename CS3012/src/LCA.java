public class LCA <Key extends Comparable<Key>, Value>{
	public Node root;
	
	public class Node{
		public Key key;
		public Value val;
		public Node left, right;
		public int N;
		
		public Node(Key key, Value val){
			this.val = val;
			this.key = key;
		}
	}
	
	//Is empty implementation
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	public int size() 
	{
	  return(size(root)); 
	}
	
	private int size(Node node) 
	{ 
	  if (node == null) return(0); 
	  else { 
	    return(size(node.left) + 1 + size(node.right)); 
	  } 
	} 
	
	//Put method, inserts value into tree
	public void put(Key key, Value val){
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		if(x == null)
		{
			return new Node(key, val);
		}
		int comp = key.compareTo(x.key);
		if(comp < 0)
		{
			x.left = put(x.left, key, val);
		}
		else if(comp > 0)
		{
			x.right = put(x.right, key, val);
		}else
		{
			x.val = val;
		}
		x.N = 1 + size(x.left) + size(x.right);
		return x;
 	}
	
	//Get method, search by key order
	public Value get(Key key){
		Node x = root;
		
		while(x != null){
			
			int comp = key.compareTo(x.key);
			if(comp < 0)
			{
				x = x.left;
			}
			else if(comp > 0)
			{
				x = x.right;
			}
			else{
				return x.val;
			}
		}
		return null;
	}
	
	//Function to find lowest common ancestor
	public Node search(Node root, Key value1, Key value2)
	{
		if(root != null &&(get(value1)!=null &&get(value2)!=null))
		{
			
			if(root.key.compareTo(value1) == 0 || root.key.compareTo(value2) == 0)
			{
				return root;
			}

			Node leftBranch = search(root.left, value1,value2);
			Node rightBranch = search(root.right,value1,value2);
			//If they both have an answer this is the LCA 
			
			if(leftBranch != null && rightBranch != null)
			{
				return root;
			}
			//Find & return node with value or return null if doesn't exist
			if(leftBranch != null) 
			{
				return leftBranch;

			}
			else if(rightBranch != null)
			{
				return rightBranch;
			}
			else 
				return null;
		}
		return null;
	}
}