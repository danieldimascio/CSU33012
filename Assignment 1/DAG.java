import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class DAG
{
	private boolean[] stack;
	private int vert;						//number of vertices
	private int edge;						//number of edges
	private ArrayList<Integer>[]adjacent;	// = adjacency list for vert
	private int[] inDeg;					// = in-degree of vert
	private boolean[] visited;				//list of visited vertices
	private boolean cycles;					//Does the graph have a cycle?			
	
	//Functions return current vertex and edge
		public int V()
		{
			return vert;
		}
		
		public int E()
		{
			return edge;
		}
		
	@SuppressWarnings("unchecked")
	public DAG(int vert)
	{
		if(vert < 0)
		{
			throw new IllegalArgumentException("Vertices count must be > 0");
		}
		
		this.vert = vert;
		this.edge = 0;
		inDeg = new int[vert];
		visited = new boolean[vert];
		stack = new boolean[vert];
		adjacent = (ArrayList<Integer>[]) new ArrayList[vert];
		
		for(int v = 0; v < vert; v++)
		{
			adjacent[v] = new ArrayList<Integer>();
		}
	}
	
	public Iterable<Integer> adjacent(int v)
	{
		return adjacent[v];
	}
	
	public boolean cycles()
	{
		return cycles;
	}
	
	boolean validate(int v)
	{
		if(v < 0 || v >= vert){ return false; }
		
		return true;
	}
	
	//Directed edges to v
	public int indegree(int v)
	{
		if(validate(v))
		{
			return inDeg[v];
		}
		else
			return -1;
		
	}
	
	//Directed edges from v
	public int outdegree(int v)
	{
		if(validate(v))
		{
			return adjacent[v].size();
		}
			return -1;
	}

	//Add directed edge from u to v
	public void addEdge(int u, int v)
	{
		if(validate(u) && validate(v))
		{
			adjacent[u].add(v);
			inDeg[v]++;
			edge++;
		}
		else{ System.out.println("Please only include figures from 1 to " + (vert-1)); }		
	}
	
	public void cycles(int v)
	{
		stack[v] = true;
		visited[v] = true;
		
		for(int u:adjacent(v))
		{
			if(!visited[u]){
				cycles(u);
			}
			
			else if(stack[u]){
				cycles = true;
				return;
			}
		}
		stack[v] = false;
	}
	
	public ArrayList<Integer> breadthFirst(int source) 				//print out a Breadth-First Search
	{
		ArrayList<Integer> arr = new ArrayList<Integer>();
		LinkedList<Integer> inQueue = new LinkedList<Integer>();
		boolean mark[] = new boolean[vert]; 						//Marks vertices as not visited
		
		mark[source] = true;
		inQueue.add(source);
		
		while(inQueue.size()!=0)
		{
			source = inQueue.poll(); 								//polls source
			arr.add(source);
			Iterator<Integer> i = adjacent[source].listIterator();	//Find, mark and queue adjacent vertices.
			
			while(i.hasNext())
			{
				int n = i.next();
				if(!mark[n])
				{
					mark[n] = true;
					inQueue.add(n);
				}
			}
		}
		return arr;
	}
	
	//Reverse DAG
	public DAG reverseDAG()
	{
		DAG reverseDAG = new DAG(vert);
		for(int v = 0; v <vert; v++)
		{
			for(int u:adjacent(v))
			{
				reverseDAG.addEdge(u, v);
			}		
		}
		return reverseDAG;
	}
	
	//LCA function
	public int findLCA(int u, int v)
	{
		cycles(0);
		
		if(cycles)
		{
			return -1;
		}
		else if(!validate(u) || !validate(v))
		{
			return -1;
		}
		else if(edge == 0) //empty graph 
		{
			return -1;
		}
		
		DAG reverseDAG = reverseDAG();
		
		ArrayList<Integer> inCommon = new ArrayList<Integer>();
		ArrayList<Integer> a1 = reverseDAG.breadthFirst(u);
		ArrayList<Integer> a2 = reverseDAG.breadthFirst(v);
		
		boolean isFound = false;
		
		for(int i = 0; i<a1.size(); i++)
		{
			for(int j = 0; j<a2.size(); j++)
			{
				if(a1.get(i) == a2.get(j))
				{
					inCommon.add(a1.get(i));
					isFound = true;
				}
			}
		}
		if(isFound)	//Return LCA
		{
			return inCommon.get(0);
		}
		return -1;
	}
}	
