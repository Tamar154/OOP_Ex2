import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame implements ActionListener, MouseListener {
    private DirectedWeightedGraphAlgorithms alg;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private MenuItem AlgoFunc1;
    private MenuItem AlgoFunc2;
    private MenuItem AlgoFunc3;
    private MenuItem AlgoFunc4;
    private MenuItem AlgoFunc5;
    private JLabel txt;

    public MyFrame(DirectedWeightedGraphAlgorithms alg) {
        this.alg = alg;
        BuildFrame();
        addMenu();
//    initPanel();
        this.addMouseListener(this);

    }

    private void BuildFrame() {
        this.setTitle("Directed weighted graph Calculator");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addMenu() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu(("File"));
        menuBar.add((menu));
        Menu Algo = new Menu(("Algorithms"));
        menuBar.add((Algo));
        Menu Draw = new Menu(("Draw"));
        Draw.addActionListener(this);
        menuBar.add((Draw));
        this.setMenuBar(menuBar);
        menuItem1 = new MenuItem(("Save"));
        menuItem2 = new MenuItem(("Load"));
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menu.add(menuItem1);
        menu.add(menuItem2);
        AlgoFunc1 = new MenuItem(("TSP"));
        AlgoFunc2 = new MenuItem(("Center"));
        AlgoFunc3 = new MenuItem(("isConnected"));
        AlgoFunc4 = new MenuItem(("shortestPathDist"));
        AlgoFunc5 = new MenuItem(("shortestPath"));
        AlgoFunc1.addActionListener(this);
        AlgoFunc2.addActionListener(this);
        AlgoFunc3.addActionListener(this);
        AlgoFunc4.addActionListener(this);
        AlgoFunc5.addActionListener(this);
        Algo.add(AlgoFunc1);
        Algo.add(AlgoFunc2);
        Algo.add(AlgoFunc3);
        Algo.add(AlgoFunc4);
        Algo.add(AlgoFunc5);
        txt = new JLabel("The answer will be here!");
        txt.setBounds(250, 100, 100, 100);
        this.add(txt);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if(e.getSource()==AlgoFunc1){
//            txt.setText(alg.tsp());
//        }

        if (e.getSource() == AlgoFunc2) {
            txt.setText(alg.center().toString());
        }

        if (e.getSource() == AlgoFunc3) {
            Boolean b = alg.isConnected();
            txt.setText(b.toString());
        }
        if (e.getSource() == menuItem2) {
            try {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Desktop.getDesktop().open(selectedFile);
//                    java.awt.Desktop.getDesktop().open(selectedFile);
                    Ex2.getGrapg(selectedFile.toString());
                }

            } catch (IOException ioe) {
                System.out.println("Wrong file, please try again!");
            }
        }
    }
        @Override
        public void mouseClicked (MouseEvent e){

        }

        @Override
        public void mousePressed (MouseEvent e){

        }

        @Override
        public void mouseReleased (MouseEvent e){

        }

        @Override
        public void mouseEntered (MouseEvent e){

        }

        @Override
        public void mouseExited (MouseEvent e){

        }
        private void initPanel () {
            JPanel mp = new MyPanel(alg);
            mp.setBackground(Color.white);
            this.add(mp);
        }
    }

