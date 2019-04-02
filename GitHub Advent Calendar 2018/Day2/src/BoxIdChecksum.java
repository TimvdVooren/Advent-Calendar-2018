import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BoxIdChecksum {

    public static void main(String[] args) {
        BoxIdChecksum test = new BoxIdChecksum();
        test.partTwo();
    }

    private int doubles = 0;
    private int triples = 0;

    public BoxIdChecksum() {
        try {
            Scanner scanner = new Scanner(new File("C:/Users/timva/Desktop/TI Jaar 2/GitHub Advent Calendar 2018/Day2/box_ids"));

            while(scanner.hasNextLine()){
                String boxID = scanner.nextLine();
                String[] partOfId = boxID.split("");

                String presentDouble = "";
                String presentTriple = "";
                ArrayList<String> checkedLetters = new ArrayList<>();


                for(int i = 0; i < partOfId.length; i++){
                    if(!checkedLetters.contains(partOfId[i])) {
                        int letterCount = 0;
                        for (int j = 0; j < partOfId.length; j++) {

                            if (i != j && partOfId[i].equals(partOfId[j])) {
                                letterCount++;
                                if (presentDouble.equals("")) {
                                    doubles++;
                                    presentDouble = partOfId[i];
                                } else if (!presentDouble.equals("") && presentTriple.equals("")) {
                                    if (presentDouble.equals(partOfId[i])) {
                                        doubles--;
                                        presentDouble = "";
                                    }
                                    if(letterCount == 2){
                                        triples++;
                                        presentTriple = partOfId[i];
                                    }
                                }

                            }
                        }
                        checkedLetters.add(partOfId[i]);
                        }
                    }
                }
            System.out.println(doubles + "*" + triples + "=" + doubles*triples);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void partTwo(){
        try {
            Scanner scanner = new Scanner(new File("C:/Users/timva/Desktop/TI Jaar 2/GitHub Advent Calendar 2018/Day2/box_ids"));
            ArrayList<String> allIDs = new ArrayList<>();
            while(scanner.hasNextLine()){
                allIDs.add(scanner.nextLine());
            }

            for(String ID : allIDs){
                String[] letters = ID.split("");
                    for (String otherID : allIDs) {
                        int wrongCount = 0;
                        for(int i = 0; i < letters.length; i++) {
                            if(wrongCount < 2 && !otherID.equals(ID)) {
                                if (letters[i].equals(otherID.substring(i, i + 1))) {

                                } else {
                                    wrongCount++;
                                }
                            }
                        }
                        if(wrongCount == 1){
                            System.out.println(ID);
                            System.out.println(otherID);
                        }
                    }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
