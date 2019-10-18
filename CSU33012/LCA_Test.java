import static org.junit.Assert.*;
import org.junit.Test;


public class LCA_Test {

	LCA<Integer, Integer> tree = new LCA<Integer, Integer>();
	
	@Test
	public void testIsEmpty() 
	{
		assertEquals(tree.isEmpty(), true);		//test empty tree
		
		tree.put(1, 1);
		tree.put(2, 2);
		tree.put(3, 3);
		tree.put(4, 4);
		tree.put(5, 18);
		assertEquals(tree.isEmpty(), false);	//test tree with 5 nodes
	}
	
	@Test
	public void testSize()
	{
		assertEquals(tree.size(), 0);			//test empty tree

		tree.put(4, 4);
		tree.put(2, 2);
		tree.put(1, 1);
		tree.put(3, 3);
		tree.put(5, 18);
		assertEquals(tree.size(), 5);			//test populated tree
		
		tree.put(5, 5);
		assertEquals(tree.size(), 5);			//test same size, different value
	}
	
	//put function is tested implicitly
	
	@Test 
	public void testGet()
	{
		assertNull(tree.get(2));				//test standard case
		assertNull(tree.get(0));				//test 0 case
		assertNull(tree.get(null));				//test null case
		tree.put(1, 1);
		tree.put(2, 6);
		tree.put(3, 3);
		tree.put(4, 4);
		tree.put(5, 18);
		assertSame(tree.get(5), 18);			//test get function finds key 5 and returns value 18
		assertNull(tree.get(18));				//test get function does not find and return the value 18
	}
	
	@Test
	public void testSearch()
	{
		assertNull(tree.search(tree.root, 2, 4));			//test search returns null for an empty tree
		
		tree.put(1, 2);
		tree.put(2, 4);
		tree.put(3, 8);
		tree.put(4, 16);
		tree.put(5, 32);
		assertSame(tree.search(tree.root, 2, 4).key, 2);	//test search
		assertSame(tree.search(tree.root, 4, 5).key, 4);
		assertSame(tree.search(tree.root, 4, 4).key, 4);	//test same key returns itself
		assertNull(tree.search(tree.root, 3, 16));			//test if one node is not in the tree
		assertNull(tree.search(tree.root, 9, 7));			//test if both nodes are not in the tree
		assertNull(tree.search(tree.root, 9, 9));			//test if one repeated node is not in the tree
	}
}
