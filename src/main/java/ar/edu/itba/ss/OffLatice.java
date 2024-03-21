package main.java.ar.edu.itba.ss;

import main.java.ar.edu.itba.ss.models.Grid;
import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.utils.ParticleReader;

import java.util.Set;

public class OffLatice {

    public int DT= 1;

    public int iterations;

    public ParticleReader particleReader;

    Set<Particle> particles;

    public double n;

    public OffLatice(int iterations, ParticleReader particleReader, Set<Particle> initialParticles, double n) {
        this.iterations = iterations;
        this.particleReader = particleReader;
        this.particles = initialParticles;
        this.n = n;
    }

    public void simulate(double sideLength, int sqrtCellsAmount, double radius){

        for(int i=0; i< iterations; i++){
            Grid grid = new Grid(particles, sqrtCellsAmount, sideLength, radius, n);
            grid.calculateNeighborsPeriodic();
            particleReader.printToOutput(i, particles, "output.txt");
            particles = grid.moveParticles(DT);
        }
    }
}
