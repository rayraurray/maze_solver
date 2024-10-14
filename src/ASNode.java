public class ASNode extends InformedNode{
    int gValue;

    ASNode(int[] position, String path, int cost, int gValue) {
        super(position, path, cost);
        this.gValue = gValue;
    }
}
