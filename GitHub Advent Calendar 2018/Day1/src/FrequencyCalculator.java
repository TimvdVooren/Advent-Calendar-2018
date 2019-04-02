import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FrequencyCalculator {
    private int totalFrequency;
    private ArrayList<Integer> allRepeats;
    private ArrayList<Integer> allFrequencies;

    public static void main(String[] args) {
        FrequencyCalculator frequencyCalculator = new FrequencyCalculator();
    }

    public FrequencyCalculator(){
        totalFrequency = 0;
        allRepeats = new ArrayList<>();
        allFrequencies = new ArrayList<>();

        try {
            Scanner scanner1 = new Scanner(new File("C:/Users/timva/Desktop/TI Jaar 2/GitHub Advent Calendar 2018/Day1/frequencies"));
            boolean firstRepeat = false;

            while (scanner1.hasNext()) {
                int frequency = scanner1.nextInt();
                allFrequencies.add(frequency);
            }

            while(!firstRepeat) {
                for(Integer frequency : allFrequencies){
                    totalFrequency = totalFrequency + frequency;

                    for(Integer repeat : allRepeats){
                        if(repeat == totalFrequency){
                            System.out.println(totalFrequency);
                            firstRepeat = true;
                            break;
                        }
                    }
                    if(firstRepeat)
                        break;

                    allRepeats.add(totalFrequency);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(totalFrequency);
    }
}
