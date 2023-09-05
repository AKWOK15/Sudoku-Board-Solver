/*
 * Aidan Kwok
 * Creates random graph to simulate pursuit evasion 
 */
public class Driver {
    /*
     * Creates random graph to simulate pursuit evasion
     */
    public Driver(int n, double p) throws InterruptedException {
        // Create a random graph on which to play
        Graph graph = new Graph(n, p);

        // Create the pursuer and evader
        AbstractPlayerAlgorithm pursuer = new MoveTowardsPlayerAlgorithm(graph);
        AbstractPlayerAlgorithm evader = new MoveAwayPlayerAlgorithm(graph);
        // Have each player choose a starting location
        pursuer.chooseStart();
        // Since the evader has a harder objective, they get to play second
        // and see where the pursuer chose
        evader.chooseStart(pursuer.getCurrentVertex());

        // Make the display
        GraphDisplay display = new GraphDisplay(graph, pursuer, evader, 40);
        // display.repaint();
        // Play the game until the pursuer captures the evader
        while (pursuer.getCurrentVertex() != evader.getCurrentVertex()) {
            Thread.sleep(500);
            pursuer.chooseNext(evader.getCurrentVertex());
            display.repaint();
            if (pursuer.getCurrentVertex() != evader.getCurrentVertex()) {
                Thread.sleep(500);
                evader.chooseNext(pursuer.getCurrentVertex());
                display.repaint();
            }
        }
    }

    /*
     * Tests
     */
    public static void main(String[] args) throws InterruptedException {
        // int n = 10;
        // double p = .3;
        int n = 5;
        double p = 0.4;
        new Driver(n, p);
    }
}
