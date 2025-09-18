public class DataObj implements Comparable<DataObj> {
    String name;
    double volume;
    int calories;
    int caffeine;
    String type;

    public DataObj() {
        name = "None";
        volume = -1.0;
        calories = -1;
        caffeine = -1;
        type = "None";
    }

    public DataObj(String name, double volume, int calories, int caffeine, String type) {
        this.name = name;
        this.volume = volume;
        this.calories = calories;
        this.caffeine = caffeine;
        this.type = type;
    }

    @Override
    public int compareTo(DataObj o) {
        //Placeholder
        return 0;
    }
}
