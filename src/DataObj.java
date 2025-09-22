public class DataObj implements Comparable<DataObj> {
    String name;
    Double volume;
    int calories;
    Integer caffeine;
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

    public int caffeine() {
        return this.caffeine;
    }

    public double volume() {
        return this.volume;
    }

    public int calories() {
        return this.calories;
    }

    public String type() {
        return this.type;
    }

    public String name() {
        return this.name;
    }

    public boolean equals(DataObj o) {
        return o.caffeine() == this.caffeine
                && Math.abs(o.volume() - this.volume) < 0.0001
                && o.name().equals(this.name)
                && o.type().equals(this.type)
                && o.calories() == (this.calories)
                ;
    }

    @Override
    public int compareTo(DataObj o) {
        //if caffeine amount is equal, compare volume
        if (this.caffeine.compareTo(o.caffeine()) == 0) return this.volume.compareTo(o.volume());
        //else return caffeine
        return this.caffeine.compareTo(o.caffeine());
    }

    @Override
    public String toString() {
        return String.format(
                "Name: %s, Volume: %f, Calories: %d, Caffeine: %d, Type: %s",
                this.name,
                this.volume,
                this.calories,
                this.caffeine,
                this.type
        );
    }
}
