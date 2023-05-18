/**
 * 
 */
package graph;

import java.util.*;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {
	private final Map<Integer, List<Integer>> adjacencyList;

	public CapGraph() {
		this.adjacencyList = new HashMap<>();
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		adjacencyList.put(num, new ArrayList<>());
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		if (!adjacencyList.containsKey(from) || !adjacencyList.containsKey(to)) {
			throw new IllegalArgumentException("Invalid vertex");
		}

		adjacencyList.get(from).add(to);
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		CapGraph egonetGraph = new CapGraph();
		egonetGraph.addVertex(center);
		for (Integer to : this.adjacencyList.get(center)) {
			egonetGraph.addVertex(to);
			egonetGraph.addEdge(center, to);
		}

		return egonetGraph;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
