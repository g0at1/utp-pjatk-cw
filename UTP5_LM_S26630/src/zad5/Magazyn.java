package zad5;

import java.util.ArrayList;

public class Magazyn {

    private ArrayList<Towar> towary;
    private boolean finished = false;

    public Magazyn(){
        this.towary = new ArrayList<>();
    }

    public synchronized ArrayList<Towar> getTowary(int start, int stop) {
        return new ArrayList<>(this.towary.subList(start, stop));
    }

    public synchronized void add(Towar towar){
        this.towary.add(towar);
    }

    public synchronized int getSize(){
        return this.towary.size();
    }

    public synchronized boolean isFinished(){
        return this.finished;
    }

    public synchronized void setFinished(){
        this.finished = true;
    }

}
