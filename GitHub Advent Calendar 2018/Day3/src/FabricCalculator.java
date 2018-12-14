import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FabricCalculator {
    public static void main(String[] args) {
        FabricCalculator calc = new FabricCalculator();
        calc.partOne();
    }

    private ArrayList<String> claims;
    private TreeMap<FabricCoordinates, Integer> fabrics;
    private ArrayList<FabricPatch> patches;

    public void partOne(){
        claims = new ArrayList<>();
        fabrics = new TreeMap<>();
        patches = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("C:/Users/Tim/Desktop/Programming/Advent-Calendar-2018/GitHub Advent Calendar 2018/Day3/fabric_claims"));
            while(scanner.hasNextLine()){
                claims.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for(String claim : claims){
            String[] claimParts = claim.split(" ");
            int claimID = Integer.parseInt(claimParts[0].substring(1, claimParts[0].length()));
            String[] edgeDistances = claimParts[2].split(",");
            int fabricX = Integer.parseInt(edgeDistances[0]) + 1;
            int fabricY = Integer.parseInt(edgeDistances[1].substring(0, edgeDistances[1].length()-1)) + 1;
            String[] lengthWidth = claimParts[3].split("x");
            int fabricWidth = Integer.parseInt(lengthWidth[0]);
            int fabricLength = Integer.parseInt(lengthWidth[1]);

            FabricPatch patch = new FabricPatch(claimID);
            for(int x = 0; x < fabricWidth; x++){
                for(int y = 0; y < fabricLength; y++){
                    FabricCoordinates fabricCoordinates = new FabricCoordinates(fabricX + x, fabricY + y);

                    int fabricCount = 0;
                    if(fabrics.containsKey(fabricCoordinates)){
                        fabricCount = fabrics.get(fabricCoordinates);
                    }
                    fabrics.put(fabricCoordinates, fabricCount+1);
                    patch.addCoordinate(fabricCoordinates);
                }
            }
            patches.add(patch);
        }

        int overlappingCount = 0;
        for(Integer amount : fabrics.values()){
            if(amount > 1 || amount == null){
                overlappingCount++;
            }
        }
        System.out.println(overlappingCount);

        for(FabricPatch patch : patches){
            int notOverlapping = 0;
            for(FabricCoordinates coordinates : patch.getCoordinates()){
                if(fabrics.get(coordinates) > 1)
                    break;
                else
                    notOverlapping++;
            }
            if(notOverlapping == patch.getCoordinates().size()){
                System.out.println(patch.getClaimID());
                break;
            }

        }
    }
}
