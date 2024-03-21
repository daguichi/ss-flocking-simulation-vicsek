package main.java.ar.edu.itba.ss.models;

public class Velocity {

    public double module;

    public double angle;

    public double getXSpeed() {
        return this.module * Math.cos(this.angle);
    }

    public double getYSpeed() {
        return this.module * Math.sin(this.angle);
    }

    public Velocity(double module, double angle) {
        this.module = module;
        this.angle = angle;
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "speed=" + module +
                ", angle=" + angle +
                '}';
    }

}
