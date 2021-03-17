import java.util.Random;

public class AutoNode {

    private char value;
    private AutoNode[] connections;
    private Random randomGenerator;

    public AutoNode(Random randomGenerator) {
        this.randomGenerator = randomGenerator;
    }

    public AutoNode(char value, Random randomGenerator) {
        this.value = value;
        this.randomGenerator = randomGenerator;
    }

    public AutoNode(char value, AutoNode[] connections, Random randomGenerator) {
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

    public void setConnections(AutoNode[] connections) {
        this.connections = connections;
    }

    public AutoNode getNextRandom() {
        int randomIndex = randomGenerator.nextInt(connections.length);
        return connections[randomIndex];
    }

    public AutoNode getNextByValue(char val) {
        for (AutoNode connetedNode : connections) {
            if (connetedNode.getValue() == val) {
                return connetedNode;
            }
        }
        return null;
    }
}
