        // Greedy greedy1 = new Greedy(grafo);
		// Greedy greedy2 = new Greedy(grafo2);
		// Greedy greedy3 = new Greedy(grafo3);

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