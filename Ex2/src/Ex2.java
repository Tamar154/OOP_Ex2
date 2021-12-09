import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.DirectedWeightedGraphAlgorithmsImpl;

import javax.swing.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        DirectedWeightedGraphAlgorithmsImpl graph = new DirectedWeightedGraphAlgorithmsImpl();
        graph.load(json_file);
        ans = graph.copy();
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithmsImpl();
        // ****** Add your code here ******
        //
        // ********************************
        ans.load(json_file);
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
        JFrame frame = new JFrame(); //creates a frame
        frame.setTitle("OOP Recitation 7 "); //sets title of frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit from application
        frame.setResizable(false); //prevent frame from being resized
        frame.setSize(420, 420); //sets the x-dimension, and y-dimension of frame
        frame.setVisible(true); //make frame visible
//        JMenuBar menuBar = new JMenuBar();
//        frame.setJMenuBar(menuBar);

        JButton b = new JButton("click here");
        b.setBounds(50,100,95,30);
        frame.add(b);
        frame.setSize(400,400);
        frame.setLayout(null);
        frame.setVisible(true);


    }

    public static void main(String[] args) {
        runGUI("C:\\Users\\TamarD\\IdeaProjects\\Ex2\\data\\G1.json");
    }
}