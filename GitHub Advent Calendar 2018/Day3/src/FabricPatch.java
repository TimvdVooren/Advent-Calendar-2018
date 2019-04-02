import java.util.ArrayList;

public class FabricPatch {
    private int claimID;
    private ArrayList<FabricCoordinates> coordinates;

    public FabricPatch(int claimID) {
        this.claimID = claimID;
        coordinates = new ArrayList<>();
    }

    public void addCoordinate(FabricCoordinates coordinate){
        this.coordinates.add(coordinate);
    }

    public ArrayList<FabricCoordinates> getCoordinates() {
        return coordinates;
    }

    public int getClaimID() {
        return claimID;
    }
}
