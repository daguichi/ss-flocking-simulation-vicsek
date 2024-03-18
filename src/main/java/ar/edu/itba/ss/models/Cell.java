package main.java.ar.edu.itba.ss.models;

import java.util.HashSet;
import java.util.Set;

public class Cell {
    private Set<Particle> particles;

    public Cell() {
        this.particles = new HashSet<>();
    }

    public void addParticle(Particle particle) {
        this.particles.add(particle);
    }
}
