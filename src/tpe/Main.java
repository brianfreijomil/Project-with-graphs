package tpe;

import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {

		//grafos, servicios
        
        GrafoNoDirigido grafo = new GrafoNoDirigido();
		GrafoNoDirigido grafo2 = new GrafoNoDirigido();
		GrafoNoDirigido grafo3 = new GrafoNoDirigido();

		// informacion (estaciones,tuneles)
		String path1 = "./datasets/dataset1.txt";
		String path2 = "./datasets/dataset2.txt";
		String path3 = "./datasets/dataset3.txt";

		// inicializo readers
		CSVReader reader1 = new CSVReader(path1);
		CSVReader reader2 = new CSVReader(path2);
		CSVReader reader3 = new CSVReader(path3);

		// cargo grafos
		reader1.read(grafo);
		reader2.read(grafo2);
		reader3.read(grafo3);


		GreedyConKruskal k = new GreedyConKruskal(grafo);
		int poda = k.metodoKruskal();
		System.out.println("Greedy GRAFO 1: "+ poda);
		for (Arco arco : k.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(k.getMetrica());
		System.out.println("-----------------------------------");
		GreedyConKruskal k2 = new GreedyConKruskal(grafo2);
		System.out.println("Greedy GRAFO 2: "+k2.metodoKruskal());
		for (Arco arco : k2.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(k2.getMetrica());
		System.out.println("-----------------------------------");
		GreedyConKruskal k3 = new GreedyConKruskal(grafo3);
		System.out.println("Greedy GRAFO 3: "+k3.metodoKruskal());
		for (Arco arco : k3.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(k3.getMetrica());

		Timer  timer = new Timer();
		System.out.println("-----------------------------------");
		System.out.println("backtracking con kruskal");
		backConKruskal backKruskal = new backConKruskal(grafo,poda,timer);
		System.out.println(backKruskal.back());
		for (Arco arco : backKruskal.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(backKruskal.getMetrica());

		System.out.println("-----------------------------------");
		System.out.println("backtracking con kruskal");
		backConKruskal backKruskal2 = new backConKruskal(grafo2,135,timer); //despues cambio esto
		System.out.println(backKruskal2.back());
		for (Arco arco : backKruskal2.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(backKruskal2.getMetrica());

		System.out.println("-----------------------------------");
		System.out.println("backtracking con kruskal");
		backConKruskal backKruskal3 = new backConKruskal(grafo3,440,timer); //despues cambio esto
		System.out.println(backKruskal3.back());
		for (Arco arco : backKruskal3.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(backKruskal3.getMetrica());

		

		
	}
}
