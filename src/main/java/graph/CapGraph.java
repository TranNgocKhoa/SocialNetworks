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
    private final HashMap<Integer, HashSet<Integer>> adjacencyList;

    public CapGraph() {
        this.adjacencyList = new HashMap<>();
    }

    /* (non-Javadoc)
     * @see graph.Graph#addVertex(int)
     */
    @Override
    public void addVertex(int num) {
        adjacencyList.put(num, new HashSet<>());
    }

    /* (non-Javadoc)
     * @see graph.Graph#addEdge(int, int)
     */
    @Override
    public void addEdge(int from, int to) {
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

        this.adjacencyList.forEach((integer, integers) -> {
            if (integer != center) {
                if (egonetGraph.adjacencyList.get(integer) != null) {
                    for (Integer to : integers) {
                        if (egonetGraph.adjacencyList.get(to) != null) {
                            egonetGraph.addEdge(integer, to);
                        }
                    }
                }
            }
        });

        return egonetGraph;
    }

    /* (non-Javadoc)
     * @see graph.Graph#getSCCs()
     */
    @Override
    public List<Graph> getSCCs() {
        Stack<Integer> finishedStack = dfs(this, new ArrayList<>(this.adjacencyList.keySet()), new ArrayList<>());

        CapGraph transpose = this.getTranspose();
        List<Graph> sccs = new ArrayList<>();

        dfs(transpose, finishedStack, sccs);

        return sccs;
    }


    private Stack<Integer> dfs(CapGraph graph, List<Integer> vertices, List<Graph> sccs) {
        Stack<Integer> visited = new Stack<>();
        Stack<Integer> finished = new Stack<>();

        while (!vertices.isEmpty()) {
            Integer v = vertices.get(vertices.size() - 1);
            vertices.remove(vertices.size() - 1);

            if (!visited.contains(v)) {
                CapGraph scc = new CapGraph();
                sccs.add(scc);
                dfsVisit(graph, v, visited, finished, scc);
            }
        }

        return finished;
    }

    private void dfsVisit(CapGraph graph, int v, Stack<Integer> visited, Stack<Integer> finished, CapGraph scc) {
        visited.add(v);
        scc.addVertex(v);
        Set<Integer> neighbors = graph.adjacencyList.get(v);
        for (Integer neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                scc.addEdge(v, neighbor);
                dfsVisit(graph, neighbor, visited, finished, scc);
            }
        }
        finished.add(v);
    }

    private CapGraph getTranspose() {
        CapGraph transpose = new CapGraph();
        this.adjacencyList.keySet().forEach(transpose::addVertex);

        this.adjacencyList.forEach((key, value) -> {
            for (Integer to : value) {
                transpose.addEdge(to, key);
            }
        });

        return transpose;
    }

    /* (non-Javadoc)
     * @see graph.Graph#exportGraph()
     */
    @Override
    public HashMap<Integer, HashSet<Integer>> exportGraph() {
        return this.adjacencyList;
    }

}
