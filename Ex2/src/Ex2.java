import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.DirectedWeightedGraphAlgorithmsImpl;

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

        // ****** Add your code here ******
        //
        // ********************************
        DirectedWeightedGraphAlgorithmsImpl graph = new DirectedWeightedGraphAlgorithmsImpl();
        graph.load(json_file);
        return graph.getGraph();
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        // ****** Add your code here ******
        //
        // ********************************
        DirectedWeightedGraphAlgorithms ans = new DirectedWeightedGraphAlgorithmsImpl();
        ans.load(json_file);
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        // ****** Add your code here ******
        //
        // ********************************
        DirectedWeightedGraphAlgorithms alg = new DirectedWeightedGraphAlgorithmsImpl();
        alg = getGrapgAlgo(json_file);
        MyFrame myFrame = new MyFrame(alg);
        myFrame.setVisible(true);
    }

    public static void main(String[] args) {
        args = new String[1];
        args[0] = "data\\G1.json";
        runGUI(args[0]);
    }

}


