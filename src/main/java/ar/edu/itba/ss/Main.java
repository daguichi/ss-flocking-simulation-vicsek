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
        String staticPath = "";
        String dynamicPath = "";
        if (args.length > 1 && args[0]!=null && args[1]!=null) {
            staticPath = args[0];
            dynamicPath = args[1];
        }else{
            throw new RuntimeException("Paths not provided");
        }
        try {
            File input = new File(staticPath);
            myReader = new Scanner(input);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int numberOfParticles = myReader.nextInt();
        double sideLength = myReader.nextDouble();
        int sqrtCellsAmount = myReader.nextInt();
        double radius = myReader.nextDouble();
        int epocs = myReader.nextInt();
        double n = myReader.nextDouble();

        ParticleReader reader = new ParticleReader(dynamicPath);
        if(args.length > 2 && Boolean.parseBoolean(args[2])){
            try{
                reader.generateRandomPositions(numberOfParticles, 0.5, sideLength);
            }catch (IOException e){
                return;
            }
        }
        Set<Particle> particles = reader.getParticles();

        Instant start = Instant.now();
        OffLatice offLatice = new OffLatice(epocs, reader, particles, n);
        offLatice.simulate(sideLength, sqrtCellsAmount, radius);
        Instant end = Instant.now();
        System.out.println("\nTime elapsed: "+ Duration.between(start, end).toMillis() +"ms \n");
    }

}