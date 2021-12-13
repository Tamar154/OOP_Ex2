import api.DirectedWeightedGraphAlgorithms;
import api.GeoLocationImpl;
import api.NodeData;
import api.NodeDataImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements ActionListener, MouseListener {
    private DirectedWeightedGraphAlgorithms alg;
    private MenuItem Save;
    private MenuItem Load;
    private MenuItem Exit;
    private MenuItem AddNode;
    private MenuItem DeleteNode;
    private MenuItem AddEdge;
    private MenuItem DeleteEdge;
    private MenuItem TSP;
    private MenuItem center;
    private MenuItem isConnected;
    private MenuItem ShortestPathDist;
    private MenuItem ShortestPath;


    private void initPanel() {
        JPanel mp = new MyPanel(alg, this.getWidth(), this.getHeight());
        mp.setVisible(true);
        mp.setSize(this.getPreferredSize());
        this.add(mp);
    }

    public MyFrame(DirectedWeightedGraphAlgorithms alg) {
        this.alg = alg;
        BuildFrame();
        addMenu();
        initPanel();
        this.addMouseListener(this);

    }

    private void BuildFrame() {
        this.setTitle("Directed weighted graph Calculator");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight());
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu(("File"));
        menuBar.add((menu));
        Menu Algo = new Menu(("Algorithms"));
        menuBar.add((Algo));
        Menu Edit = new Menu(("Edit"));
        Edit.addActionListener(this);
        menuBar.add((Edit));
        AddNode = new MenuItem(("Add node"));
        DeleteNode = new MenuItem(("Delete node"));
        AddEdge = new MenuItem(("Add edge"));
        DeleteEdge = new MenuItem(("Delete edge"));
        Edit.add(AddNode);
        Edit.add(DeleteNode);
        Edit.add(AddEdge);
        Edit.add(DeleteEdge);
        AddNode.addActionListener(this);
        DeleteNode.addActionListener(this);
        AddEdge.addActionListener(this);
        DeleteEdge.addActionListener(this);
        this.setMenuBar(menuBar);
        Save = new MenuItem(("Save"));
        Load = new MenuItem(("Load"));
        Exit = new MenuItem(("Exit"));
        Save.addActionListener(this);
        Load.addActionListener(this);
        Exit.addActionListener(this);
        menu.add(Save);
        menu.add(Load);
        menu.add(Exit);
        TSP = new MenuItem(("TSP"));
        center = new MenuItem(("Center"));
        isConnected = new MenuItem(("isConnected"));
        ShortestPathDist = new MenuItem(("shortestPathDist"));
        ShortestPath = new MenuItem(("shortestPath"));
        TSP.addActionListener(this);
        center.addActionListener(this);
        isConnected.addActionListener(this);
        ShortestPathDist.addActionListener(this);
        ShortestPath.addActionListener(this);
        Algo.add(TSP);
        Algo.add(center);
        Algo.add(isConnected);
        Algo.add(ShortestPathDist);
        Algo.add(ShortestPath);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == center) {
            JOptionPane.showMessageDialog(this, alg.center().toString());
        }
        if (e.getSource() == isConnected) {
            Boolean b = alg.isConnected();
            JOptionPane.showMessageDialog(this, b);
        }
        if (e.getSource() == Load) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("data\\"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.setVisible(false);
                Ex2.runGUI(selectedFile.toString());
                JOptionPane.showMessageDialog(this, "File loaded successfully!");
            }
        }
        if (e.getSource() == Save) {
            String name = JOptionPane.showInputDialog(this, "Enter a file name to save as:");
            try {
                alg.save("data\\" + name + ".json");
            } catch (Exception j) {
                JOptionPane.showMessageDialog(this, "Operation failed! please try again.");
            }
            JOptionPane.showMessageDialog(this, "The file saved successfully!");
        }
        if (e.getSource() == Exit) {
            this.dispose();
        }
        if (e.getSource() == AddNode) {
            try {
                String Point = JOptionPane.showInputDialog(this, "Enter point x,y,z:");
                String Key = JOptionPane.showInputDialog(this, "Enter key:");
                GeoLocationImpl Mypoint = new GeoLocationImpl(Point);
                int MyKey = Integer.parseInt(Key);
                NodeData node = new NodeDataImpl(Mypoint, MyKey);
                alg.getGraph().addNode(node);
                JOptionPane.showMessageDialog(this, "Operation Succeed!");
                repaint();
            } catch (Exception c) {
                JOptionPane.showMessageDialog(this, "Operation failed! please try again.");
            }

        }
        if (e.getSource() == DeleteNode) {
            String key = JOptionPane.showInputDialog(this, "Enter a key of a node you want to remove");
            int remove = Integer.parseInt(key);
            NodeData n = alg.getGraph().removeNode(remove);
            if (n == null) {
                JOptionPane.showMessageDialog(this, "No such key! try again.");
            } else {
                repaint();
                JOptionPane.showMessageDialog(this, "Operation Succeed!");

            }
        }
        if (e.getSource() == AddEdge) {
            String Source = JOptionPane.showInputDialog(this, "Enter a source:");
            String Destination = JOptionPane.showInputDialog(this, "Enter a destination:");
            String Weight = JOptionPane.showInputDialog(this, "Enter a weight:");
            int src = Integer.parseInt(Source);
            int dst = Integer.parseInt(Destination);
            int W = Integer.parseInt(Weight);
            try {
                alg.getGraph().connect(src, dst, W);
                repaint();
                JOptionPane.showMessageDialog(this, "Operation Succeed!");
            } catch (Exception c) {
                JOptionPane.showMessageDialog(this, "Operation failed! try again.");
            }
        }
        if (e.getSource() == DeleteEdge) {
            String Source = JOptionPane.showInputDialog(this, "Enter a source:");
            String Destination = JOptionPane.showInputDialog(this, "Enter a destination:");
            int src = Integer.parseInt(Source);
            int dst = Integer.parseInt(Destination);
            try {
                alg.getGraph().removeEdge(src, dst);
                repaint();
                JOptionPane.showMessageDialog(this, "Operation Succeed!");
            } catch (Exception c) {
                JOptionPane.showMessageDialog(this, "Operation failed! try again.");
            }
        }

        if (e.getSource() == ShortestPathDist) {
            String source = JOptionPane.showInputDialog(this, "Enter a source:");
            String Destination = JOptionPane.showInputDialog(this, "Enter a destination:");
            int src = Integer.parseInt(source);
            int dst = Integer.parseInt(Destination);
            try {
                JOptionPane.showMessageDialog(this, alg.shortestPathDist(src, dst));
                repaint();
            } catch (Exception c) {
                JOptionPane.showMessageDialog(this, "Operation failed! try again.");
            }
        }
        if (e.getSource() == ShortestPath) {
            String source = JOptionPane.showInputDialog(this, "Enter a source:");
            String Destination = JOptionPane.showInputDialog(this, "Enter a destination:");
            int src = Integer.parseInt(source);
            int dst = Integer.parseInt(Destination);
            try {
                JOptionPane.showMessageDialog(this, alg.shortestPath(src, dst));
                repaint();
            } catch (Exception c) {
                JOptionPane.showMessageDialog(this, "Operation failed! try again.");
            }
        }
        if (e.getSource() == TSP) {
            List<NodeData> cities = new ArrayList<>();
            String str = "";
            try {
                while (!str.equals("x")) {
                    str = JOptionPane.showInputDialog(this, "Enter node key or 'x' to terminate: ");
                    if (!str.equals("x")) {
                        int key = Integer.parseInt(str);
                        cities.add(alg.getGraph().getNode(key));
                    }
                }
                JOptionPane.showMessageDialog(this, alg.tsp(cities));
            } catch (Exception c) {
                JOptionPane.showMessageDialog(this, "Operation failed! try again.");
            }
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


