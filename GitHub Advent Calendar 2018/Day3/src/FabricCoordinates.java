public class FabricCoordinates implements Comparable<FabricCoordinates> {
    private int x;
    private int y;

    public FabricCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(FabricCoordinates o) {
        if (y < o.y) return -1;
        if (y > o.y) return +1;

        if (x < o.x) return -1;
        if (x > o.x) return +1;
        return 0;
    }

    @Override
    public String toString() {
        return "FabricCoordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
