package main.java.ar.edu.itba.ss;

import main.java.ar.edu.itba.ss.models.Grid;
import main.java.ar.edu.itba.ss.models.Particle;
import main.java.ar.edu.itba.ss.utils.ParticleReader;
import java.io.*;
import java.util.*;
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
    try {
        // imprimir las variables en consola asi veo que son
        // System.out.println("sideLength: " + sideLength);
        // System.out.println("sqrtCellsAmount: " + sqrtCellsAmount);
        // System.out.println("radius: " + radius);
        // System.out.println("n: " + n);
        // System.out.println("iterations: " + iterations);
        
        String fileName = String.format("output_N%d_L%f_eta%f_epocs%d.txt", particles.size(), sideLength, this.n, iterations);
        File myObj = new File(fileName);        
        FileWriter myWriter;
        if (myObj.createNewFile()) {
          System.out.println("File created: " + myObj.getName());
          myWriter = new FileWriter(fileName);
        } else {
          System.out.println("File already exists.");
          myWriter = new FileWriter(fileName, true);
        }
        PrintWriter printWriter = new PrintWriter(myWriter);
        printWriter.println(sideLength); // print sideLength as the first line

  //  ahora quiero para cada iteración, calcular el v_a como la raiz cuadradad de la suma de los módulos de las velocidades de las partículas
  // eso se hará en el metodo moveParticles de la clase Grid, pero primero necesito crear el archivo va_output_N%d_L%f_eta%d_epocs%d.txt

        String vaFileName = String.format("va_output_N%d_L%f_eta%f_epocs%d.txt", particles.size(), sideLength, this.n, iterations);
        File vaObj = new File(vaFileName);
        FileWriter vaWriter;
        if (vaObj.createNewFile()) {
          System.out.println("File created: " + vaObj.getName());
          vaWriter = new FileWriter(vaFileName);
        } else {
          System.out.println("File already exists.");
          vaWriter = new FileWriter(vaFileName, true);
        }
        PrintWriter vaPrintWriter = new PrintWriter(vaWriter);

        System.out.println(iterations);
        for(int i=0; i< iterations; i++){
            System.out.println(i);

          Grid grid = new Grid(particles, sqrtCellsAmount, sideLength, radius, n);
          grid.calculateNeighborsPeriodic();
          particleReader.printToOutput(i, particles, printWriter);
          particleReader.printVaToOutput(i, particles, vaPrintWriter);
          particles = grid.moveParticles(DT, sideLength);
        }
        

        vaWriter.close();
        myWriter.close();
        printWriter.close();
        vaPrintWriter.close();
    } catch (IOException e) {
        System.out.println("An error occurred.");
    }
}
}
