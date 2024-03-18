package main.java.ar.edu.itba.ss.utils;
import main.java.ar.edu.itba.ss.models.Particle;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ParticleReader {
    public void generateRandomPositions(int numberOfParticles, double particle_radius, double sideLength) throws IOException {
        Set<Particle> positions = new HashSet<>();
        FileWriter fileWriter = new FileWriter("dynamic.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(0);
        for(int i=0; i< numberOfParticles; i++){
            Random r = new Random();
            printWriter.printf("%.7e %.7e\n", r.nextDouble(particle_radius, sideLength - particle_radius),  r.nextDouble(particle_radius, sideLength - particle_radius));
        }
        printWriter.close();
    }
    public Set<Particle> getParticles(){
        Set<Particle> set = new HashSet<>();
        Scanner myReader = null;
        try {
            File input = new File("dynamic.txt");
            myReader = new Scanner(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        int id = 1;
        int time_0 = myReader.nextInt();
        while(myReader.hasNextFloat()){
            set.add(new Particle(id++, myReader.nextDouble(), myReader.nextDouble(), 0.25));
        }
        return set;
    }

    public void printToOutput(long time, Set<Particle> particlePositions, String fileName){
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter myWriter = new FileWriter(fileName);
            PrintWriter printWriter = new PrintWriter(myWriter);

            printWriter.println(time);
            particlePositions.forEach(particle -> {
                printWriter.println(particle.id + " " +particle.neighbors.stream()
                        .map(neighbor -> neighbor.id)
                        .collect(Collectors.toSet()));
            });
            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
        }



    }


}
