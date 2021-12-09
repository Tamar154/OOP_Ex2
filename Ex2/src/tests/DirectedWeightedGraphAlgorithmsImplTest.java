package tests;

import api.DirectedWeightedGraphAlgorithmsImpl;
import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectedWeightedGraphAlgorithmsImplTest {
    api.DirectedWeightedGraphAlgorithmsImpl g1 = new DirectedWeightedGraphAlgorithmsImpl();
    api.DirectedWeightedGraphAlgorithmsImpl g2 = new DirectedWeightedGraphAlgorithmsImpl();
    api.DirectedWeightedGraphAlgorithmsImpl g3 = new DirectedWeightedGraphAlgorithmsImpl();
    api.DirectedWeightedGraphAlgorithmsImpl g4 = new DirectedWeightedGraphAlgorithmsImpl();
    api.DirectedWeightedGraphAlgorithmsImpl g5 = new DirectedWeightedGraphAlgorithmsImpl();


    public DirectedWeightedGraphAlgorithmsImplTest() {
        g1.load("data\\G1.json");
        g2.load("data\\G2.json");
        g3.load("data\\G3.json");
        g4.load("aa.json");
        g5.load("dist.json");


    }

    @Test
    void init() {
        g1.init(g1.getGraph());
        assertEquals(g1.getGraph().nodeSize(), 0);
        assertEquals(g1.getGraph().edgeSize(), 0);
    }

    @Test
    void getGraph() {
        int n = g1.getGraph().nodeSize();
        double[][] ans = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
//                if (i == j)
                ans[i][j] = 0;
//                else
//                    ans[i][j] = 99999.0;
            }
        }
        Iterator<EdgeData> iter = g1.getGraph().edgeIter();
        while (iter.hasNext()) {
            EdgeData temp = iter.next();
            ans[temp.getSrc()][temp.getDest()] = temp.getWeight();
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(ans[i]));
        }

    }

    @Test
    void copy() {
    }

    @Test
    void isConnected() {
    }

    @Test
    void shortestPathDist() {
        assertEquals(2, g5.shortestPathDist(0, 1));
        assertEquals(18, g5.shortestPathDist(3, 2));
        assertEquals(5.836343, g1.shortestPathDist(0, 13));
    }

    @Test
    void shortestPath() {
        List<NodeData> ans = g1.shortestPath(0, 13);
//         0⇒16⇒15⇒14⇒13
        List<NodeData> expected = new LinkedList<>();
        expected.add(g1.getGraph().getNode(0));
        expected.add(g1.getGraph().getNode(16));
        expected.add(g1.getGraph().getNode(15));
        expected.add(g1.getGraph().getNode(14));
        expected.add(g1.getGraph().getNode(13));
        assertEquals(expected, ans);
    }

    @Test
    void center() {
        assertEquals("8", g1.center().toString());
        assertEquals("0", g2.center().toString());
        assertEquals("40", g3.center().toString());
    }

    @Test
    void tsp() {
        List<NodeData> cities = new LinkedList<>();
        Iterator<NodeData> iter = g1.getGraph().nodeIter();
        while (iter.hasNext()) {
            cities.add(iter.next());
        }
        List<NodeData> ans = g1.tsp(cities);
        System.out.println(ans);
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}