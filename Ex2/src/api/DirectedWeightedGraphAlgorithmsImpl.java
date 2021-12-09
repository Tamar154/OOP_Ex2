package api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgorithmsImpl implements DirectedWeightedGraphAlgorithms {
    public DirectedWeightedGraphImpl g;
    final double INF = 99999.0;

    public DirectedWeightedGraphAlgorithmsImpl() { // **check**
        g = new DirectedWeightedGraphImpl();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        g = new DirectedWeightedGraphImpl();
    }


    @Override
    public DirectedWeightedGraph getGraph() {
        return g;
    }

    /*
    deep copy
     */
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraphImpl ans = new DirectedWeightedGraphImpl();
        Iterator<NodeData> nodeIterator = g.nodeIter();
        while (nodeIterator.hasNext()) {
            ans.addNode(nodeIterator.next());
        }
        for (int i : g.Edges.keySet()) {
            for (int k : g.Edges.get(i).keySet()) {
                int src = g.Edges.get(i).get(k).getSrc();
                int dest = g.Edges.get(i).get(k).getDest();
                double weight = g.Edges.get(i).get(k).getWeight();
                ans.connect(src, dest, weight);
            }
        }
        return ans;
    }

    @Override
    public boolean isConnected() {
        int n = g.Nodes.size();
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
        Iterator<EdgeData> iter = g.edgeIter();
        while (iter.hasNext()) {
            int src = iter.next().getSrc();
            int dest = iter.next().getDest();
            adjList.get(src).add(dest);
        }

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            DFS(adjList, n, visited);
            for (boolean b : visited) {
                if (!b) {
                    return false;
                }
            }
        }
        return true;
    }

    private void DFS(List<List<Integer>> adjList, int v, boolean[] visited) {
        visited[v] = true;
        for (int u : adjList.get(v)) {
            if (!visited[u]) {
                DFS(adjList, u, visited);
            }
        }
    }

    /**
     * Computes the length of the shortest path between src to dest
     * Note: if no such path --> returns -1
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        double[][] mat = floydWarshallAlgorithm();
        if (mat[src][dest] != INF)
            return mat[src][dest];
        return -1;
    }

    /**
     * Computes the the shortest path between src to dest - as an ordered List of nodes:
     * src--> n1-->n2-->...dest
     * see: https://en.wikipedia.org/wiki/Shortest_path_problem
     * Note if no such path --> returns null;
     *
     * @param src  - start node
     * @param dest - end (target) node
     * @return
     */

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        List<NodeData> ans = new LinkedList<>();
        int n = g.nodeSize();
        double[][] dis = new double[n][n];
        int[][] next = new int[n][n];
        double[][] mat = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {                if (i == j)
                    mat[i][j] = 0;
                else
                    mat[i][j] = INF;
            }
        }
        Iterator<EdgeData> iter = g.edgeIter();
        while (iter.hasNext()) {
            EdgeData temp = iter.next();
            mat[temp.getSrc()][temp.getDest()] = temp.getWeight();
        }
//        initialize:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dis[i][j] = mat[i][j];
                if (mat[i][j] == INF)
                    next[i][j] = -1;
                else
                    next[i][j] = j;
            }
        }

//        floyd:
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dis[i][k] + dis[k][j] < dis[i][j]) {
                        dis[i][j] = dis[i][k] + dis[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }

        if (next[src][dest] == -1)
            return null;
        ans.add(g.getNode(src));
        while (src != dest) {
            src = next[src][dest];
            ans.add(g.getNode(src));
        }
        return ans;
    }
//    @Override
//    public List<NodeData> shortestPath(int src, int dest) {
//        List<NodeData> ans = new LinkedList<>();
//        NodeData start = g.Nodes.get(src);
//        NodeData end = g.Nodes.get(dest);
//
//        HashMap<NodeData, NodeData> changedAt = new HashMap<>();
//        changedAt.put(start, null);
//
//        HashMap<NodeData, Double> shortestPathMap = new HashMap<>();
////        HashMap<Integer, HashMap<NodeData,Double>> shortestPathMap = new HashMap<>();
//
//        HashMap<Integer, Boolean> visited = new HashMap<>();
//        Iterator<NodeData> nodeIter = g.nodeIter();
//        while (nodeIter.hasNext()) {
//            NodeData temp = nodeIter.next();
//            visited.put(temp.getKey(), false);
//        }
//
//
//        for (NodeData node : g.Nodes.values()) {
//            if (node.equals(start)) {
//                ans.add(node);
//                shortestPathMap.put(start, 0.0);
//            } else
//                shortestPathMap.put(node, Double.MAX_VALUE);
//        }
//
//        Iterator<EdgeData> iter = g.edgeIter();
//        while (iter.hasNext()) {
//            EdgeData edge = iter.next();
//            shortestPathMap.put(g.getNode(edge.getDest()), edge.getWeight());
//            changedAt.put(g.getNode(edge.getDest()), start);
//        }
//
//        visited.replace(start.getKey(), true);
//
//        while (true) {
//            NodeData currentNode = closestReachableUnvisited(shortestPathMap, visited);
//            if (currentNode == null) {
//                return null;
//            }
//            if (currentNode.equals(end)) {
////                ans.add(currentNode);
//                NodeData child = end;
//                ans.add(g.getNode(dest));
//                while (true) {
//                    NodeData parent = changedAt.get(child);
//                    if (parent != null) {
//                        ans.add(parent);
//                        child = parent;
//                    } else
//                        return ans;
//                }
////                return ans;
//            }
//            visited.replace(currentNode.getKey(), true);
//
//            iter = g.edgeIter();
//            while (iter.hasNext()) {
//                EdgeData edge = iter.next();
//                if (visited.get(edge.getDest()))
//                    continue;
//
//                if (shortestPathMap.get(currentNode) + edge.getWeight() < shortestPathMap.get(g.getNode(edge.getDest()))) {
//                    shortestPathMap.put(g.getNode(edge.getDest()), shortestPathMap.get(currentNode) + edge.getWeight());
//                    changedAt.put(g.getNode(edge.getDest()), currentNode);
//                }
//            }
//            break;
//        }
//        return ans;
//    }

    private NodeData closestReachableUnvisited(HashMap<NodeData, Double> shortestPathMap, HashMap<Integer, Boolean> visited) {
        double shortestDistance = Double.MAX_VALUE;
        NodeData closestReachableNode = null;
        for (NodeData node : g.Nodes.values()) {
            if (visited.get(node.getKey()))
                continue;

            double currentDistance = shortestPathMap.get(node);
            if (currentDistance == Double.MAX_VALUE)
                continue;

            if (currentDistance < shortestDistance) {
                shortestDistance = currentDistance;
                closestReachableNode = node;
            }
        }
        return closestReachableNode;
    }

    /**
     * Finds the NodeData which minimizes the max distance to all the other nodes.
     * Assuming the graph isConnected, elese return null. See: https://en.wikipedia.org/wiki/Graph_center
     *
     * @return the Node data to which the max shortest path to all the other nodes is minimized.
     */
    @Override
    public NodeData center() {
        int n = g.Nodes.size();
        double[][] D = floydWarshallAlgorithm();
        double[] vm = new double[n];
        for (int i = 0; i < n; i++) {
            vm[i] = 0;
            for (int j = 0; j < n; j++) {
                if (vm[i] < D[i][j]) {
                    vm[i] = D[i][j];
                }
            }
        }
        double minVm = INF;
        for (int i = 0; i < n; i++) {
            if (minVm > vm[i])
                minVm = vm[i];
        }
        for (int i = 0; i < n; i++) {
            if (vm[i] == minVm) {
                return g.getNode(i);
            }
        }
        return null;
    }

    private double[][] floydWarshallAlgorithm() {
        int n = g.nodeSize();
        double[][] ans = new double[n][n];
//        int i, j, k;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    ans[i][j] = 0;
                else
                    ans[i][j] = INF;
            }
        }
        Iterator<EdgeData> iter = g.edgeIter();
        while (iter.hasNext()) {
            EdgeData temp = iter.next();
            ans[temp.getSrc()][temp.getDest()] = temp.getWeight();
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (ans[i][k] + ans[k][j] < ans[i][j]) {
                        ans[i][j] = ans[i][k] + ans[k][j];
                    }
                }
            }
        }
        return ans;

    }

    /**
     * Computes a list of consecutive nodes which go over all the nodes in cities.
     * the sum of the weights of all the consecutive (pairs) of nodes (directed) is the "cost" of the solution -
     * the lower the better.
     * See: https://en.wikipedia.org/wiki/Travelling_salesman_problem
     */
    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        double[][] tsp = floydWarshallAlgorithm();
//        int sum = 0;
        int counter = 0;
        int j = 0, i = 0;
        double min = Double.MAX_VALUE;
        List<NodeData> visitedRouteList = new ArrayList<>();
        visitedRouteList.add(cities.get(0));
        int[] route = new int[tsp.length];
        while (i < tsp.length && j < tsp[i].length) {
            if (counter >= tsp[i].length - 1) {
                break;
            }
            if (j != i && !(visitedRouteList.contains(cities.get(j)))) {
                if (tsp[i][j] < min) {
                    min = tsp[i][j];
                    route[counter] = j + 1;
                }
            }
            j++;
            if (j == tsp[i].length) {
//                sum += min;
                min = Integer.MAX_VALUE;
                visitedRouteList.add(cities.get(route[counter] - 1));
                j = 0;
                i = route[counter] - 1;
                counter++;
            }
        }
        for (j = 0; j < tsp.length; j++) {
            if ((i != j) && tsp[i][j] < min) {
                min = tsp[i][j];
                route[counter] = j + 1;
            }
        }
//        sum += min;
        return visitedRouteList;
    }


    public boolean save(String file) {
        JsonObject graph = new JsonObject();
        JsonArray Nodes = new JsonArray();
        Iterator<NodeData> nodeIter = g.nodeIter();
        while (nodeIter.hasNext()) {
            NodeData temp = nodeIter.next();
            JsonObject node = new JsonObject();
//            node.addProperty("pos", temp.getLocation().toString());
            node.addProperty("id", temp.getKey());
            Nodes.add(node);
        }
        JsonArray Edges = new JsonArray();
        Iterator<EdgeData> edgeIter = g.edgeIter();
        while (edgeIter.hasNext()) {
            EdgeData temp = edgeIter.next();
            JsonObject edge = new JsonObject();
            edge.addProperty("src", temp.getSrc());
            edge.addProperty("w", temp.getWeight());
            edge.addProperty("dest", temp.getDest());
            Edges.add(edge);
        }
        graph.add("Edges", Edges);
        graph.add("Nodes", Nodes);
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            FileWriter w = new FileWriter(file);
            gson.toJson(graph, w);
            w.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean load(String file) {
        try {
            this.init(g);
            Gson gson = new Gson();
            FileReader r = new FileReader(file);
            JsonObject graph = gson.fromJson(r, JsonObject.class);
            JsonArray Nodes = graph.get("Nodes").getAsJsonArray();
            for (int i = 0; i < Nodes.size(); i++) {
                JsonObject node = Nodes.get(i).getAsJsonObject();
                String str = node.get("pos").getAsString();
                NodeData n = new NodeDataImpl(new GeoLocationImpl(str), node.get("id").getAsInt());
                this.g.addNode(n);
            }
            JsonArray Edges = graph.get("Edges").getAsJsonArray();
            for (int i = 0; i < Edges.size(); i++) {
                JsonObject edge = Edges.get(i).getAsJsonObject();
                int src = edge.get("src").getAsInt();
                double w = edge.get("w").getAsDouble();
                int dest = edge.get("dest").getAsInt();
                this.g.connect(src, dest, w);
            }
            r.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }
}
