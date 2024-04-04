package main.java.ar.edu.itba.ss.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class Particle {

    public int id;

    public double x;

    public double y;

    public double r_c;

    public Set<Particle> neighbors;

    public Velocity currentVelocity;


    public Particle(int id, double x, double y, double r_c, Velocity velocity) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.r_c = r_c;
        this.currentVelocity = velocity;
        this.neighbors = new HashSet<>();
    }

    public void setPeriodicNeighbors(Set<Particle> candidates, double rc, double sideLength){
        candidates.stream().filter(particle -> isPeriodicNeighbor(particle, rc, sideLength)).forEach(this::addNeighbor);

    }

    public boolean isPeriodicNeighbor(Particle particlePosition, double rc, double sideLength){

        final double auxDeltaX = Math.abs(this.x - particlePosition.x);
        final double deltaX = Double.min(auxDeltaX, sideLength - auxDeltaX);

        final double auxDeltaY = Math.abs(this.y - particlePosition.y);
        final double deltaY = Double.min(auxDeltaY, sideLength- auxDeltaY);

        return  !this.equals(particlePosition) &&  rc >= Math.sqrt(deltaX * deltaX + deltaY * deltaY);

    }

    public void addNeighbor(Particle particle) {
        if (this.equals(particle)) {
            return;
        }
        this.neighbors.add(particle);
    }

    public void move(double dt, double n) {
        x = x + this.currentVelocity.getXVelocityModule() * dt;
        y = y + this.currentVelocity.getYVelocityModule() * dt;
        currentVelocity = this.calculateNewVelocity(n);
        neighbors = new HashSet<>();
    }

    public Velocity calculateNewVelocity(double n) {
        final Set<Particle> particles = new HashSet<>(this.neighbors);
        particles.add(this);

        final double noise = new Random().nextDouble(-n/2, n/2);
        double y = particles.stream()
                .map(p -> p.currentVelocity.angle)
                .map(Math::sin)
                .collect(Collectors.averagingDouble(a -> a));
        double x = particles.stream()
                .map(p -> p.currentVelocity.angle)
                .map(Math::cos)
                .collect(Collectors.averagingDouble(a -> a));
        double angle = Math.atan2(y, x) + noise;
        return new Velocity(this.currentVelocity.module, angle);
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

