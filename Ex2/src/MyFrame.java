import api.DirectedWeightedGraphAlgorithms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class MyFrame extends JFrame implements ActionListener, MouseListener {
    private DirectedWeightedGraphAlgorithms alg;
    private MenuItem Save;
    private MenuItem Load;
    private MenuItem TSP;
    private MenuItem center;
    private MenuItem isConnected;
    private MenuItem ShortestPathDist;
    private MenuItem ShortestPath;
    private  Dimension screenSize;
    private JLabel txt;


    private void initPanel () {
        JPanel mp = new MyPanel(alg,this.getWidth(),this.getHeight());
        mp.setSize(400,200);
        this.add(mp);
        mp.setVisible(true);
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
        this.setSize(1080,720);
        this.setLocationRelativeTo(null);
//        this.setResizable(false);
//        SwingUtilities.getWindowAncestor(this);
//        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setSize(screenSize.width/2+75,screenSize.height+75);
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
        Save = new MenuItem(("Save"));
        Load = new MenuItem(("Load"));
        Save.addActionListener(this);
        Load.addActionListener(this);
        menu.add(Save);
        menu.add(Load);
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
//        txt = new JLabel("The answer will be here!");
//        txt.setBounds(0, 100, 100, 100);
//        this.add(txt);
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
            fileChooser.setCurrentDirectory(new File("C:\\Users\\roey3\\IdeaProjects\\Ex2\\data"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                this.setVisible(false);
                Ex2.runGUI(selectedFile.toString());

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
    }


