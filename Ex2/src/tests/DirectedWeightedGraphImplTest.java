package tests;

import api.DirectedWeightedGraphImpl;
import api.GeoLocationImpl;
import api.NodeDataImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectedWeightedGraphImplTest {
    DirectedWeightedGraphImpl dwg = new DirectedWeightedGraphImpl();
    GeoLocationImpl g0, g1, g2, g3;
    NodeDataImpl n0, n1, n2, n3;

    public DirectedWeightedGraphImplTest() {
        g0 = new GeoLocationImpl("1,1,1");
        n0 = new NodeDataImpl(g0, 0);
        g1 = new GeoLocationImpl("2,1,1");
        n1 = new NodeDataImpl(g1, 1);
        g2 = new GeoLocationImpl("3,1,1");
        n2 = new NodeDataImpl(g2, 2);
        g3 = new GeoLocationImpl("4,1,1");
        n3 = new NodeDataImpl(g3, 3);
        dwg.addNode(n0);
        dwg.addNode(n1);
        dwg.addNode(n2);
        dwg.addNode(n3);
        dwg.connect(1, 2, 9);
        dwg.connect(1, 2, 5);
        dwg.connect(1, 3, 4);


    }

    @Test
    void getNode() {
        dwg.addNode(n0);
        dwg.addNode(n1);
        assertEquals(dwg.getNode(0), n0);
        assertEquals(dwg.getNode(1), n1);
    }

    @Test
    void getEdge() {
        dwg.connect(1, 2, 9);
        dwg.connect(1, 3, 2);
        assertEquals(dwg.getEdge(1, 2).toString(), "(1, 9.0, 2)");
        assertEquals(dwg.getEdge(1, 3).toString(), "(1, 2.0, 3)");
    }

    @Test
    void addNode() {
        GeoLocationImpl g3 = new GeoLocationImpl("5,4,9");
        NodeDataImpl n3 = new NodeDataImpl(g3, 3);
        dwg.addNode(n3);
        assertEquals(dwg.getNode(3), n3);
    }

    @Test
    void connect() {
        assertEquals(dwg.getEdge(1, 2).toString(), "(1, 2, 5.0)");
        assertEquals(dwg.getEdge(1, 3).toString(), "(1, 3, 4.0)");
    }

    @Test
    void nodeIter() {

    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
        DirectedWeightedGraphImpl dwgTemp = new DirectedWeightedGraphImpl();
        GeoLocationImpl gt0 = new GeoLocationImpl("1,1,1");
        NodeDataImpl nt0 = new NodeDataImpl(gt0, 0);
        GeoLocationImpl gt1 = new GeoLocationImpl("2,1,1");
        NodeDataImpl nt1 = new NodeDataImpl(gt1, 1);
        dwgTemp.addNode(nt0);
        dwgTemp.addNode(nt1);
        dwg.removeNode(2);
        assertEquals(dwg.getNode(2), dwgTemp.getNode(2)); // both return null
        assertEquals(dwg.getEdge(1, 2), null);
    }

    @Test
    void removeEdge() {

    }

    @Test
    void nodeSize() {
        assertEquals(dwg.nodeSize(), 4);
    }

    @Test
    void edgeSize() {
        assertEquals(dwg.edgeSize(), 2);
    }

    @Test
    void getMC() {
    }
}