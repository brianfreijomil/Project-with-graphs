package tpe;

import java.util.Collections;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) {

		//grafos
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

		// cargo grafos con la informacion de los datasets
		reader1.read(grafo);
		reader2.read(grafo2);
		reader3.read(grafo3);

		//Instancio servicio greedy
		Greedy g1 = new Greedy(grafo);
		Greedy g2 = new Greedy(grafo2);
		Greedy g3 = new Greedy(grafo3);

		//resultados servicio greedy
		//reutilizo los resultados de greedy como poda del servicio Backtracking
		int podaBack1 = imprimirResultadosGreedy(g1);
		int podaBack2 = imprimirResultadosGreedy(g2);
		int podaBack3 = imprimirResultadosGreedy(g3);

		//instancio servicio backtracking
		BackTracking b1 = new BackTracking(grafo,podaBack1);
		BackTracking b2 = new BackTracking(grafo2,podaBack2);
		BackTracking b3 = new BackTracking(grafo3,podaBack3);

		//resultados servicio backtracking
		imprimirResultadoBackTracking(b1);
		imprimirResultadoBackTracking(b2);
		imprimirResultadoBackTracking(b3);
		
	}
	//imprime los resultados de el servicio greedy y retorna la distancia obtenida
	public static int imprimirResultadosGreedy(Greedy g) {
		int poda = g.greedy();
		System.out.println("- Greedy");
		System.out.print("- ");
		for (Arco arco : g.getTuneles()) {
            System.out.print(arco.toString());
        }
		System.out.println();
		System.out.println("- "+poda+" Kms");
		System.out.println("- "+g.getMetrica()+" Metrica");
		System.out.println("----------------------------------------------------------------");
		return poda;
	}
	//imprime los resultados de el servicio backtracking
	public static void imprimirResultadoBackTracking(BackTracking b) {
		int resultKms = b.backTracking();
		System.out.println("- BackTracking");
		System.out.print("- ");
		for (Arco arco : b.getTuneles()) {
            System.out.print(arco.toString());
        }
		System.out.println();
		System.out.println(resultKms+" Kms");
		System.out.println("- "+b.getMetrica()+" Metrica");
		System.out.println("----------------------------------------------------------------");
	}
}
