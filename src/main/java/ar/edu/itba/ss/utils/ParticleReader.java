package main.java.ar.edu.itba.ss.utils;
import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.models.Velocity;

import java.io.*;
import java.util.*;

public class ParticleReader {

    public String dynamicPath;

    public ParticleReader(String dynamicPath) {
        this.dynamicPath = dynamicPath;
    }

    public void generateRandomPositions(int numberOfParticles, double particle_radius, double sideLength) throws IOException {
        Set<Particle> positions = new HashSet<>();
        FileWriter fileWriter = new FileWriter(this.dynamicPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(0);
        double velocity_module = 0.03;
        for(int i=0; i< numberOfParticles; i++){
            Random r = new Random();
            printWriter.printf("%.7e %.7e %.7e %.7e\n", r.nextDouble(particle_radius, sideLength - particle_radius),  r.nextDouble(particle_radius, sideLength - particle_radius), velocity_module, r.nextDouble(0, 2* Math.PI ));
        }
        printWriter.close();
    }
    public Set<Particle> getParticles(){
        Set<Particle> set = new HashSet<>();
        Scanner myReader = null;
        try {
            File input = new File(dynamicPath);
            myReader = new Scanner(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int id = 1;
        int time_0 = myReader.nextInt();
        while(myReader.hasNextFloat()){
            set.add(new Particle(id++, myReader.nextDouble(), myReader.nextDouble(), 0.25, new Velocity(myReader.nextDouble(), myReader.nextDouble())));
        }
        return set;
    }

    public void printToOutput(long time, Set<Particle> particlePositions, PrintWriter printWriter){
      printWriter.println("t"+time);
      particlePositions.forEach(particle -> {
        List<String> line = new ArrayList<>();
        line.add(String.valueOf(particle.x));
        line.add(String.valueOf(particle.y));
        line.add(String.valueOf(particle.currentVelocity.module));
        line.add(String.valueOf(particle.currentVelocity.angle));
        String formattedLine = String.join(" ", line);
        printWriter.println(formattedLine);
      });
    }


}
