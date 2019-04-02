import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.TreeMap;

public class GuardPredictor {
    public static void main(String[] args) {
        GuardPredictor predictor = new GuardPredictor();
        predictor.initializeScanner();
        predictor.partOne();
    }

    private ArrayList<String> guardShifts;
    private ArrayList<Guard> guards;
    private Guard currentGuard;
    private int sleepTime;

    public void partOne(){
        guards = new ArrayList<>();
        for(String shift : guardShifts){
            String[] shiftParts = shift.split(" ");
            if(shift.contains("shift")){
                Guard guard = new Guard(Integer.parseInt(shiftParts[3].substring(1, shiftParts[3].length())));

                boolean alreadyIn = false;
                for(Guard existingGuard : guards){
                    if(guard.getId() == existingGuard.getId()){
                        currentGuard = existingGuard;
                        alreadyIn = true;
                        break;
                    }
                }

                if(!alreadyIn) {
                    guards.add(guard);
                    currentGuard = guard;
                }

            } else if(shift.contains("asleep")){
                sleepTime = Integer.parseInt(shift.substring(15, 17));

            } else if(shift.contains("wakes")){
                int wakeTime = Integer.parseInt(shift.substring(15, 17));
                int minutes = wakeTime-sleepTime;

                for(int i = 0; i < minutes; i++){
                    currentGuard.addMinutesAsleep(sleepTime + i);
                }
            }
        }

        TreeMap<Integer, Integer> sleepiestMinutes = new TreeMap<>();

        Guard longestSleeper = new Guard(1333337);
        for(Guard guard : guards){
            if(guard.getTotalSleepingTime() > longestSleeper.getTotalSleepingTime()){
                longestSleeper = guard;
            }

            guard.calculateSleepiestMinute();

            sleepiestMinutes.put(guard.getSleepiestMinute().x, guard.getSleepiestMinute().y);
        }

        Tuple<Integer, Integer> sleepiestMinute = new Tuple<>(0,0);
        for(Integer minute : longestSleeper.getMinutesAsleep().keySet()){
            int sleepAmount = longestSleeper.getMinutesAsleep().get(minute);
            if(sleepAmount > sleepiestMinute.y){
                sleepiestMinute = new Tuple<>(minute, sleepAmount);
            }
        }

        System.out.println(longestSleeper.getId() + "*" + sleepiestMinute.x + "=" + longestSleeper.getId()*sleepiestMinute.x);

        sleepiestMinute = new Tuple<>(0,0);
        for(Integer minute : sleepiestMinutes.keySet()){
            int sleepAmount = sleepiestMinutes.get(minute);
            if(sleepAmount > sleepiestMinute.y){
                sleepiestMinute = new Tuple<>(minute, sleepAmount);
            }
        }

        Guard sleepiestGuard = new Guard(133347);
        for(Guard guard : guards){
            if(guard.getSleepiestMinute().x == sleepiestMinute.x){
                if(guard.getSleepiestMinute().y > sleepiestGuard.getSleepiestMinute().y){
                    sleepiestGuard = guard;
                }
            }
        }

        System.out.println(sleepiestGuard.getId() + "*" + sleepiestMinute.x + "=" + sleepiestGuard.getId()*sleepiestMinute.x);
    }

    private void initializeScanner() {

        try {
            guardShifts = new ArrayList<>();
            Scanner scanner = new Scanner(new File("C:/Users/Tim/Desktop/Programming/Advent-Calendar-2018/GitHub Advent Calendar 2018/Day4/guard_schedule"));
            while(scanner.hasNextLine()){
                guardShifts.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(guardShifts, (o1, o2) -> {
            int month1 = Integer.parseInt(o1.substring(6, 8));
            int month2 = Integer.parseInt(o2.substring(6, 8));
            int day1 = Integer.parseInt(o1.substring(9, 11));
            int day2 = Integer.parseInt(o2.substring(9, 11));
            int hour1 = Integer.parseInt(o1.substring(12, 14));
            int hour2 = Integer.parseInt(o2.substring(12, 14));
            int minute1 = Integer.parseInt(o1.substring(15, 17));
            int minute2 = Integer.parseInt(o2.substring(15, 17));

            if (month1 < month2) return -1;
            if (month1 > month2) return +1;
            if (day1 < day2) return -1;
            if (day1 > day2) return +1;
            if (hour1 < hour2) return -1;
            if (hour1 > hour2) return +1;
            if (minute1 < minute2) return -1;
            if (minute1 > minute2) return +1;
            return 0;
        });

//        for(String date : guardShifts){
//            System.out.println(date);
//        }
    }
}
