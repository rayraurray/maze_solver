public class InformedNode extends Node{
    int cost;

    InformedNode(int[] position, String path, int cost) {
        super(position, path);
        this.cost = cost;
    }

    int getCost(){
        return cost;
    }
}
