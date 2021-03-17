import java.util.Random;

public class AutoNodeSol {

    private char value;
    private AutoNodeSol[] connections;
    private Random randomGenerator;

    public AutoNodeSol(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public AutoNodeSol(char value, Random randomGenerator) {
        this.value = value;
        this.randomGenerator = randomGenerator;
    }

    public AutoNodeSol(char value, AutoNodeSol[] connections, Random randomGenerator) {
        this.value = value;
        this.connections = connections;
        this.randomGenerator = randomGenerator;
    }

    public char getValue() {
        return value;
    }

    public void setValue(char val) {
        this.value = val;
    }

    public void setConnections(AutoNodeSol[] connections) {
        this.connections = connections;
    }

    public AutoNodeSol getNextRandom() {
        int randomIndex = randomGenerator.nextInt(connections.length);
        return connections[randomIndex];
    }

    public AutoNodeSol getNextByValue(char val) {
        for (AutoNodeSol connetedNode : connections) {
            if (connetedNode.getValue() == val) {
                return connetedNode;
            }
        }
        return null;
    }
}
