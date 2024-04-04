package main.java.ar.edu.itba.ss.models;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Grid {
    public Cell[][] cells;
    public Set<Particle> particles;
    public  int m;
    public double l;

    public double n;

    public double r_c;

    public Grid(Set<Particle> particles, int m, double l, double r_c, double n) {
        this.particles = particles;

        if( l /m < r_c ){
            throw new RuntimeException("Invalid M value");
        }
        this.m = m;
        this.l = l;
        this.n = n;
        this.r_c = r_c;
        this.cells = new Cell[m][m];
        this.createAndAssignCells(this.particles);
    }

    private int getXCellIndex(Particle particle) {
        return (int) Math.floor(particle.x / ((double) l / m));
    }

    private int getYCellIndex(Particle particle) {
        return (int) Math.floor(particle.y / ((double) l / m));
    }


    public void createAndAssignCells(Set<Particle> particles){
        particles.forEach(p -> {
            int x = this.getXCellIndex(p);
            int y = this.getYCellIndex(p);
            if (x < 0 || x >= m || y < 0 || y >= m) {
                return;
            }
            if (cells[x][y] == null) {
                cells[x][y] = new Cell();
            }
            cells[x][y].addParticle(p);
        });
    }

    public void calculateNeighborsPeriodic() {
        int neighborCellReach = (int)Math.ceil(this.r_c / (l/m) );

        for (int cellI = 0; cellI < m; cellI++) {
            for (int cellJ = 0; cellJ < m; cellJ++) {
                if (cells[cellI][cellJ] == null) {
                    continue;
                }

                Set<Particle> relevantParticles = new HashSet<>(cells[cellI][cellJ].particles);

                for (int i = -neighborCellReach; i <= neighborCellReach; i++) {
                    for (int j = -neighborCellReach; j <= neighborCellReach; j++) {
                        int neighborI = (cellI + i + m) % m;
                        int neighborJ = (cellJ + j + m) % m;

                        if (cells[neighborI][neighborJ] != null) {
                            relevantParticles.addAll(cells[neighborI][neighborJ].particles);
                        }
                    }
                }

                for (Particle particle : cells[cellI][cellJ].particles) {
                    for (Particle candidateNeighbor : relevantParticles) {
                        if (particle.equals(candidateNeighbor)) {
                            continue;
                        }
                        particle.setPeriodicNeighbors(Collections.singleton(candidateNeighbor), this.r_c, this.l);
                    }
                }
            }
        }
    }
    public Set<Particle> moveParticles(int dt){
        particles.stream().parallel().forEach(p -> p.move(dt, n));
        this.cells = new Cell[m][m];
        return particles;
    }

}
