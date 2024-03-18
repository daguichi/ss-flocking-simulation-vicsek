package main.java.ar.edu.itba.ss.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Particle {

    public int id;

    public double x;

    public double y;

    public double r_c;

    Set<Particle> neighbors;

    public Particle(double x, double y, double r_c) {
        this.x = x;
        this.y = y;
        this.r_c = r_c;
    }

    public void setNeighbours(Set<Particle> candidates) {
        this.neighbors = new HashSet<>();
        candidates.stream().filter(this::isNeighbour).forEach(this::addNeighbour);
    }

    public boolean isNeighbour(Particle particle, double rc) {
        return !this.equals(particle) &&  rc >= this.calculateDistance(particle);
    }

    public boolean isNeighbour(Particle particle) {
        return this.isNeighbour(particle, r_c );
    }

    public void addNeighbour(Particle particle) {
        if (this.equals(particle)) {
            return;
        }
        this.neighbors.add(particle);
    }

    public double calculateDistance(Particle other){
        return Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Particle particle = (Particle) o;
        return id == particle.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

