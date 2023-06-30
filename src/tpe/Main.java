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

		Greedy greedy1 = new Greedy(grafo);
		Greedy greedy2 = new Greedy(grafo2);
		Greedy greedy3 = new Greedy(grafo3);

		//BackTracking_2 b = new BackTracking_2(grafo);
		//BackTracking_2 b2 = new BackTracking_2(grafo2);
		//BackTracking_2 b3 = new BackTracking_2(grafo3);

		// int poda1 = imprimirResultadoGreedy(greedy1);
		// System.out.println("-------------------------------------");
		// int poda2 = imprimirResultadoGreedy(greedy2);
		// System.out.println("-------------------------------------");
		// int poda3 = imprimirResultadoGreedy(greedy3);
		// System.out.println("-------------------------------------");
		// imprimirResultadoBackArcos(b, poda1);
		// System.out.println("-------------------------------------");
		// imprimirResultadoBackArcos(b2, poda2);
		// System.out.println("-------------------------------------");
		// imprimirResultadoBackVertices(grafo, backA, poda1);
		// System.out.println("-------------------------------------");
		// imprimirResultadoBackVertices(grafo2, backB, poda2);


		Kruskal k = new Kruskal(grafo);
		System.out.println("KRUSKAL GRAFO 1: "+k.metodoKruskal());
		for (Arco arco : k.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(k.getMetrica());
		System.out.println("-----------------------------------");
		Kruskal k2 = new Kruskal(grafo2);
		System.out.println("KRUSKAL GRAFO 2: "+k2.metodoKruskal());
		for (Arco arco : k2.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(k2.getMetrica());
		System.out.println("-----------------------------------");
		Kruskal k3 = new Kruskal(grafo3);
		System.out.println("KRUSKAL GRAFO 3: "+k3.metodoKruskal());
		for (Arco arco : k3.getTuneles()) {
            System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
        }
		System.out.println();
		System.out.println(k3.getMetrica());


		System.out.println("-----------------------------------");
		backConKruskal backKruskal = new backConKruskal(grafo);
		System.out.println(backKruskal.back());
		

		
	}
	// //este metodo imprime los resultados del servicio greedy y retorna los kms
	// public static int imprimirResultadoGreedy(Greedy servicio) {

	// 	int podaBack = servicio.resolucionGreedy(); //ejecuto metodo principal

	// 	System.out.println("- Servicio: Greedy");
	// 	System.out.print("- Tuneles: ");
	// 	for (Arco arco : servicio.getTuneles()) {
    //         System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
    //     }
	// 	System.out.println();
	// 	System.out.println("- Cantidad de Mts a construir: " + servicio.getMtsConstruccion());
	// 	System.out.println("- Metrica: " + servicio.getMetrica());

	// 	return podaBack;
		
	// }
	// //este metodo imprime los resultados del servicio back
	// public static void imprimirResultadoBackArcos(BackTracking_2 servicio, int podaGreedy) {

	// 	servicio.metodoBackTracking(podaGreedy); //ejecuto metodo principal

	// 	System.out.println("- Servicio: BackTracking");
	// 	System.out.print("- Tuneles: ");
	// 	for (Arco arco : servicio.getTuneles()) {
    //         System.out.print("(" + arco.getVerticeOrigen() + ", " + arco.getVerticeDestino() + ")");
    //     }
	// 	System.out.println();
	// 	System.out.println("- Cantidad de Mts a construir: " + servicio.getKms_construccion());
	// 	System.out.println("- Metrica: " + servicio.getMetrica());
	// }
}
