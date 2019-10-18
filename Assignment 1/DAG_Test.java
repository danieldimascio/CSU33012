import static org.junit.Assert.*;
import org.junit.Test;

public class DAG_Test {

	
	@Test 
	public void testV()
	{
		DAG test = new DAG(2);
		assertEquals(test.V(), 2);
	}
	
	@Test
	public void testE(){
		
		DAG test = new DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(3, 2);
		test.addEdge(4, 4);
		
		assertEquals(test.E(), 4);
	}
	
	@Test
	public void testDAG()
	{
		DAG test = new DAG(10);
		
		test.addEdge(1, 2);
		test.addEdge(2, 3);
		test.addEdge(2, 4);
		test.addEdge(3, 5);
		test.addEdge(3, 6);
		
		assertEquals(test.indegree(2), 1);
		assertEquals(test.outdegree(3), 2);
		assertEquals(test.E(), 5);
		assertEquals(test.V(), 10);
		String adjacent = "[5, 6]";
		assertEquals(test.adjacent(3).toString(), adjacent);
	}

	@Test
	public void addEdge()
	{
		DAG test = new DAG(5);
		
		test.addEdge(1,2);
		test.addEdge(-1, -2); 	//Will print an error as -2 < 0
		test.addEdge(3, 12);	//Will print an error as 12 > 5
		assertEquals(test.E(), 1);
	}
	
	@Test
	public void testAdjacent()
	{
		DAG test = new DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(3, 2);
		test.addEdge(4, 4);
		
		String adjacent = "[4]";
		assertEquals(test.adjacent(2).toString(), adjacent);
	}
	
	@Test
	public void testCycle()
	{
		DAG test = new DAG(5);
		
		test.addEdge(0, 1);
		test.addEdge(1, 2);
		test.addEdge(2, 0);
		
		test.doesCycle(0);
		assertTrue(test.cycles());	//DAG loops back, cycle = true
	}
	
	@Test
	public void testIndegree()
	{
		DAG test = new DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(4, 4);
		
		assertEquals(test.indegree(4), 2);		//works
		assertEquals(test.indegree(44), -1);	//doesn't work
	}
	
	@Test
	public void testOutdegree()
	{
		DAG test = new DAG(5);
		
		test.addEdge(1, 2);
		test.addEdge(2, 4);
		test.addEdge(4, 4);
		
		assertEquals(test.outdegree(4), 1);		//works
		assertEquals(test.outdegree(44), -1);	//doesn't work
	}

	public void testValidate()
	{
		DAG test = new DAG(5);
		
		assertFalse(test.validate(55));		//doesn't work as 55 > 5
		assertFalse(test.validate(-55));	//doesn't work as -55 < 0
		assertTrue(test.validate(3));		//works; 3 < 5 and 3 > 0
	}
}
