import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;

public class DAG
{
	private boolean[] stack;
	private int vert;						//number of vertices
	private int edge;						//number of edges
	private ArrayList<Integer>[]adjacent;	//adjacent = adjacency list for vertex vert
	private int[] indegree;					//indegree = in-degree of vertex vert
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
		indegree = new int[vert];
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
				return indegree[v];
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
			indegree[v]++;
			edge++;
		}
		else{ System.out.println("Please only include figures from 1 to " + (vert-1)); }		
	}
}	
