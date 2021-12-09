package api;

import java.util.*;

public class DirectedWeightedGraphImpl implements DirectedWeightedGraph {
    public HashMap<Integer, HashMap<Integer, EdgeData>> Edges; // <src, <dst, edge>>
    public HashMap<Integer, NodeData> Nodes;


    public DirectedWeightedGraphImpl() {
        Edges = new HashMap<>();
        Nodes = new HashMap<>();

    }

    @Override
    public NodeData getNode(int key) {
        return Nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return Edges.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        Nodes.put(n.getKey(), n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (Nodes.containsKey(src) && Nodes.containsKey(dest)) {
            EdgeDataImpl edge = new EdgeDataImpl(src, w, dest);
            HashMap<Integer, EdgeData> temp = new HashMap<>();
            temp.put(dest, edge);
            if (Edges.containsKey(src))
                Edges.get(src).put(dest, edge);
            else
                Edges.put(src, temp);
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() { // **** check about runtime exception ****
        Iterator<NodeData> ans = Nodes.values().iterator();
        return ans;
    }

    @Override
    public Iterator<EdgeData> edgeIter() { // **** check about runtime exception ****
        List<EdgeData> ll = new LinkedList<>();
        for (int i : Edges.keySet()) {
            for (int k : Edges.get(i).keySet()) {
                ll.add(Edges.get(i).get(k));
            }
        }
        Iterator<EdgeData> ans = ll.iterator();
        return ans;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) { // **** check about runtime exception ****
        HashMap<Integer, EdgeData> temp = Edges.get(node_id);
        return temp.values().iterator();
    }
    
    @Override
    public NodeData removeNode(int key) {
        NodeData ans = Nodes.get(key);
        Nodes.remove(key);
        Edges.remove(key);
        for (int i : Edges.keySet()) {
            if (Edges.get(i).containsKey(key))
                Edges.get(i).remove(key);
        }
        return ans;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData ans = Edges.get(src).get(dest);
        Edges.get(src).remove(dest);
        return ans;
    }

    @Override
    public int nodeSize() {
        return Nodes.size();
    }

    @Override
    public int edgeSize() {
        int ans = 0;
        for (int i : Edges.keySet()) {
            ans += Edges.get(i).size();
        }
        return ans;
    }

    @Override
    public int getMC() {
        return 0;
    }

    public String toString() {
        String str = "nodes: ";
        Iterator<NodeData> nodeIterator = this.nodeIter();
        while (nodeIterator.hasNext()) {
            str += nodeIterator.next();
            str += ", ";
        }
        str += "\n";
        Iterator<EdgeData> edgeIterator = this.edgeIter();
        while (edgeIterator.hasNext()) {
            str += edgeIterator.next();
            str += ", ";
        }
        return str;
    }

}
