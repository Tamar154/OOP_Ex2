import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

public class MyPanel extends JPanel implements MouseListener {
    private DirectedWeightedGraphAlgorithms alg;

    public MyPanel(DirectedWeightedGraphAlgorithms alg){
        this.alg= alg;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Iterator<NodeData> iter=alg.getGraph().nodeIter();
        while(iter.hasNext()){
            NodeData tmp=iter.next();
            g.fillOval((int)tmp.getLocation().x(),(int)tmp.getLocation().y(),100,100);
        }
        Iterator<EdgeData> iter2= alg.getGraph().edgeIter();
        while(iter2.hasNext()){
            EdgeData tmp1=iter2.next();
            int x1 = (int) alg.getGraph().getNode(tmp1.getSrc()).getLocation().x();
            int y1 = (int) alg.getGraph().getNode(tmp1.getSrc()).getLocation().y();
            int x2 = (int) alg.getGraph().getNode(tmp1.getDest()).getLocation().x();
            int y2 = (int) alg.getGraph().getNode(tmp1.getDest()).getLocation().y();
            g.drawLine(x1,y1,x2,y2);
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
