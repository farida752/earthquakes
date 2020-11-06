package com.example.soonami.Model;

public class Event {
    private long time;
    private String place;
    private double magnitude;
    private int tsunami;
    private String url;
    private boolean isExpandable;
    public Event(long time, String place, int tsunami,double magnitude,String url) {
        this.time = time;
        this.place = place;
        this.tsunami = tsunami;
        this.magnitude=magnitude;
        this.isExpandable=false;
        this.url= url;
    }

    public String getUrl() {
        return url;
    }

    public void setIsExpandable(boolean expandable) {
        this.isExpandable = expandable;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public long getTime() {
        return time;
    }


    public int getTsunami() {
        return tsunami;
    }

    public String getPlace() {
        return place;
    }

    public double getMagnitude() {
        return magnitude;
    }

    @Override
    public String toString() {
        return "Event{" +
                "time=" + time +
                ", place='" + place + '\'' +
                ", magnitude=" + magnitude +
                ", tsunami=" + tsunami +
                '}';
    }
}
