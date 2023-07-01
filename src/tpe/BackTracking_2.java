package tpe;

import java.util.ArrayList;

import java.util.Iterator;

public class BackTracking_2 {
    
    private Integer kms_construccion;
    private ArrayList<Arco> tuneles;
    private int metrica;
    private int podaGreedy;
    private Grafo grafo;

    public BackTracking_2(Grafo grafo) {
        this.grafo = grafo;
        this.tuneles = new ArrayList<>();
    }

    public void metodoBackTracking(int poda) {
        tuneles.clear();
        kms_construccion = 0;
        metrica = 1;
        ArrayList<Arco> tunelesAux = new ArrayList<>();

        this.metodoBackTracking(tunelesAux,poda);

        kms_construccion = this.getSumaArcos(tuneles);

    }

    private void metodoBackTracking(ArrayList<Arco> tunelesAux, int poda) {
        if(this.isGrafoConexo(tunelesAux)) { //solucion posible (Grafo conexo)
            if(tuneles.isEmpty()) { //si no tengo tuneles agrego solucion actual
                tuneles.addAll(tunelesAux);
            }
            else {
                int sumaTuneles = this.getSumaArcos(tuneles);
                int sumaAux = this.getSumaArcos(tunelesAux);
                if(sumaTuneles > sumaAux) { //comparo solucion anterior con actual
                    tuneles.clear();
                    tuneles.addAll(tunelesAux);
                }
            }
        }
        else { //sigo explorando arbol de posibilidades

            Iterator<Arco> itArcos = grafo.obtenerArcos();
            while (itArcos.hasNext()) {

                Arco tunel = itArcos.next();
                int dest = tunel.getVerticeDestino();

                if(!this.contieneTunel(tunelesAux,tunel) && !isConsiderado(dest, tunelesAux)) { //verifico no haberlo considerado antes
                    tunelesAux.add(tunel); //agreo arco a solucion actual
                    if(this.getSumaArcos(tunelesAux) <= poda) {
                        if(tunelesAux.size() == 12) {
                            System.out.println(true);
                        }
                        metrica++;
                        this.metodoBackTracking(tunelesAux,poda); //llamo a recursividad
                    }
                    tunelesAux.remove(tunelesAux.size()-1); //remuevo ultimo arco agregado
                }
            }
        }
    }

    //chequea que una lista de arcos contenga o no un arco determinado, y luego revirtiendo los vertices
    private boolean contieneTunel(ArrayList<Arco> list, Arco a) {
        Integer v1 = a.getVerticeOrigen();
        Integer v2 = a.getVerticeDestino();
        Arco a2 = grafo.obtenerArco(v2, v1);
        if(list.contains(a) || list.contains(a2)) {
            return true;
        }
        else {
            return false;
        }
    }

    // chequea si un vertice ya existe en un arco de la lista de arcos 
    private boolean isConsiderado(Integer v,ArrayList<Arco> list) {
        for (Arco arco : list) {
            if(arco.getVerticeOrigen() == v || arco.getVerticeDestino() == v) {
                return true;
            }
        }
        return false;
    }

    //chequea si una lista de arcos puede generar un grafo conexo?
    private boolean isGrafoConexo(ArrayList<Arco> listArcos) {

        int limite = grafo.cantidadVertices();

        if(listArcos.size() >= limite-1) {
            ArrayList<Integer> vConsiderados = new ArrayList<Integer>();
            for (Arco arco : listArcos) {

                Integer v1 = arco.getVerticeOrigen();
                Integer v2 = arco.getVerticeDestino();

                if(!vConsiderados.contains(v1) && vConsiderados.size() < limite) {
                    vConsiderados.add(v1);
                
                }
                if(!vConsiderados.contains(v2) && vConsiderados.size() < limite) {
                    vConsiderados.add(v2);
                }
            }
            //si la cant de vertices considerados es igual al limite(cant vertices) es una solucion posible
            return vConsiderados.size() == limite;
        }
        return false;
    }

    // obtiene la suma de todos los arcos de una lista
    private Integer getSumaArcos(ArrayList<Arco> list) {
        Integer distTotal = 0;
        for (Arco arco : list) {
            Integer distancia = (Integer) arco.getEtiqueta();
            distTotal += distancia;
        }
        return distTotal;
    }

    // obtengo kms
    public Integer getKms_construccion() {
        return kms_construccion;
    }

    //obtengo tuneles
    public ArrayList<Arco> getTuneles() {
        ArrayList<Arco> aux = new ArrayList<Arco>();
        aux.addAll(tuneles);
        return aux;
    }

    //obtengo metrica
    public int getMetrica() {
        return metrica;
    }

    public static void main(String[] args) {
        GrafoNoDirigido grafo2 = new GrafoNoDirigido();
		String path2 = "./datasets/dataset2.txt";

		// inicializo readers
		CSVReader reader2 = new CSVReader(path2);

		// cargo grafos
		reader2.read(grafo2);
        BackTracking_2 b = new BackTracking_2(grafo2);
        b.metodoBackTracking(135);
        System.out.println(b.getMetrica());
        System.out.println(b.getKms_construccion());
        for (Arco arco : b.getTuneles()) {
            System.out.println(arco.getVerticeOrigen());
            System.out.println(arco.getVerticeDestino());
        }
        System.out.println("-------------------------");
        GrafoNoDirigido grafo3 = new GrafoNoDirigido();
		String path3 = "./datasets/dataset3.txt";

		// inicializo readers
		CSVReader reader3 = new CSVReader(path3);

		// cargo grafos
		reader3.read(grafo3);
        BackTracking_2 b2 = new BackTracking_2(grafo3);
        b2.metodoBackTracking(440);
        System.out.println(b2.getMetrica());
        System.out.println(b2.getKms_construccion());
        for (Arco arco : b2.getTuneles()) {
            System.out.println(arco.getVerticeOrigen());
            System.out.println(arco.getVerticeDestino());
        }

    }
}
