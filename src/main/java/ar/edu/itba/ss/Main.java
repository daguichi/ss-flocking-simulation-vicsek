package main.java.ar.edu.itba.ss;


import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.utils.ParticleReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner myReader = null;
        try {
            File input = new File("staticN.txt");
            myReader = new Scanner(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int numberOfParticles = myReader.nextInt();
        double sideLength = myReader.nextDouble();
        int sqrtCellsAmount = myReader.nextInt();
        double radius = myReader.nextDouble();

        ParticleReader reader = new ParticleReader();
        if(args.length > 0 && Boolean.parseBoolean(args[0])){
            try{
                reader.generateRandomPositions(numberOfParticles, 0.5, sideLength);
            }catch (IOException e){
                return;
            }
        }
        Set<Particle> particles = reader.getParticles();

        int epocs = 10;
        Instant start = Instant.now();
        Main.simulate(epocs, reader, particles, numberOfParticles, sideLength, sqrtCellsAmount, radius);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));

        if(args.length > 1){
            //set circle position
            if(args[1].equals("pbc")){
                //count circle particles from particles. only once
            }else if (args[1].equals("obc")){
                //count circle every time
            }
            //output to specific file
        }




    }
    public static void simulate(int iterations,
                                ParticleReader reader,
                                Set<Particle> particles,
                                int numberOfParticles,
                                double sideLength,
                                int sqrtCellsAmount,
                                double radius){
        for(int i=0; i< iterations; i++){
            //set grid, conditions, etc
            //use cim_contour
            reader.printToOutput(i, particles, "output.txt");
            //particles move
        }
    }
}