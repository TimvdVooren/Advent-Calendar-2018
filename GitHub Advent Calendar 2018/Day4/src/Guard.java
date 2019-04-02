import java.util.TreeMap;

public class Guard {
    private int id;
    private int totalSleepingTime;
    private TreeMap<Integer, Integer> minutesAsleep;
    private Tuple<Integer, Integer> sleepiestMinute;

    public Guard(int id) {
        this.id = id;
        this.totalSleepingTime = 0;
        this.minutesAsleep = new TreeMap<>();
        this.sleepiestMinute = new Tuple<>(0, 0);
    }

    public void calculateSleepiestMinute(){
        for(Integer minute : this.getMinutesAsleep().keySet()){
            int sleepAmount = this.getMinutesAsleep().get(minute);
            if(sleepAmount >= sleepiestMinute.y){
                sleepiestMinute = new Tuple<>(minute, sleepAmount);
            }
        }
    }

    public Tuple<Integer, Integer> getSleepiestMinute() {
        return sleepiestMinute;
    }

    public int getId() {
        return id;
    }

    public int getTotalSleepingTime() {
        return totalSleepingTime;
    }

    public TreeMap<Integer, Integer> getMinutesAsleep() {
        return minutesAsleep;
    }

    public void addMinutesAsleep(int minute) {
        int sleepingCount = 0;
        if(minutesAsleep.containsKey(minute)){
            sleepingCount = minutesAsleep.get(minute);
        }
        minutesAsleep.put(minute, sleepingCount+1);

        totalSleepingTime = totalSleepingTime + 1;
    }
}
