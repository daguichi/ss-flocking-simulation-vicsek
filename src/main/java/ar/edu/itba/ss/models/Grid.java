package main.java.ar.edu.itba.ss.models;

import java.util.HashSet;
import java.util.Set;

public class Grid {
    public Cell[][] cells;
    public Set<Particle> particles;
    public  int m;
    public int l;
    public boolean isPeriodic;

    public Grid(Set<Particle> particles, int m, int l, boolean isPeriodic, double r_c) {
        this.particles = particles;

        if( (double) l /m < r_c ){
            throw new RuntimeException("Invalid M value");
        }

        this.m = m;
        this.l = l;
        this.isPeriodic = isPeriodic;
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

    private void calculateNeighborsPeriodic() {
        this.particles.stream().parallel().forEach(p -> {
            int x = this.getXCellIndex(p);
            int y = this.getYCellIndex(p);

            Set<Particle> candidates = new HashSet<>();
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    int mI = (i + m) % m;
                    int mJ = (j + m) % m;
                    if (cells[mI][mJ] == null) {
                        continue;
                    }
                    candidates.addAll(cells[mI][mJ].particles);
                }
            }
            p.setNeighbours(candidates);
        });
    }
}
