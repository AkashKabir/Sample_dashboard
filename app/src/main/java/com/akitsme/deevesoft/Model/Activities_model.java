package com.akitsme.deevesoft.Model;

/**
 * Created by AKASH on 09-04-2017.
 */

public class Activities_model {
    public String calories,description,distance,duration,starttime,steps,name;

    public String getName() {
        return name;
    }

    public Activities_model(String calories, String description, String distance, String duration, String starttime, String steps, String name){
        this.calories=calories;
        this.description=description;
        this.distance=distance;
        this.duration=duration;
        this.starttime=starttime;
        this.steps=steps;
        this.name=name;

    }

    public String getCalories() {
        return calories;
    }

    public String getDescription() {
        return description;
    }

    public String getDistance() {
        return distance;
    }

    public String getDuration() {
        return duration;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getSteps() {
        return steps;
    }
}
