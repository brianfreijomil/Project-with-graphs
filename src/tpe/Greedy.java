package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Greedy {

    private ArrayList<Arco> arcos;
    private Grafo grafo;
    private int ameArcos,ameNI;
    private HashMap<Integer,Integer> padres;
    private ArrayList<Arco> tuneles;
    private int metrica;
    private int distanciaTotal;

    Greedy(Grafo grafo) {
        this.padres = new HashMap();
        this.arcos = new ArrayList<Arco>();
        this.grafo = grafo;
        this.tuneles = new ArrayList<>();
        this.inicializoArcosPadres();
    }

    public void inicializoArcosPadres() {
        Iterator<Arco> itArcos = grafo.obtenerArcos();
        while (itArcos.hasNext()) {
            Arco arc = itArcos.next();
            if(!arcos.contains(arc)) {
                arcos.add(arc);
            }
        }
        Collections.sort(arcos, new ComparadorArcos());
        for (int i = 1; i <= grafo.cantidadVertices(); i++) {
            padres.put(i, i);
        }

    }

    public int greedy() {
        metrica = 0;
        ameArcos = 0;
        distanciaTotal = 0;
        ameNI = 0;
        int cantArcos = grafo.cantidadArcos();
        int cantVertices = grafo.cantidadVertices();
        while((ameArcos<cantVertices-1)&&(ameNI<cantArcos)) {
            Integer origen = arcos.get(ameNI).getVerticeOrigen();
            Integer destino = arcos.get(ameNI).getVerticeDestino();
            Integer distancia = (Integer)arcos.get(ameNI).getEtiqueta();
            if(find(origen)!=find(destino)) {
                tuneles.add(grafo.obtenerArco(origen, destino));
                unite(origen, destino);
                distanciaTotal += distancia;
                ameArcos++;
            }
            ameNI++;
        }
        if(ameArcos==cantVertices-1) {
            return distanciaTotal;
        }
        return 0;
    }

    public int find(int x) { //encuentra el mayor padre de vertice x
        metrica++;
        if (padres.get(x) == x) {
            return x;
        }
        return find(padres.get(x));
    }

    public void unite(int x, int y) { // este metodo une dos vertices
        int fx = find(x);
        int fy = find(y);
        if(fx == x) {
            padres.replace(fx,y);
        }
        else {
            if(fy == y) {
                padres.replace(y,x);
            }
            else {
              padres.replace(fy,y);
              padres.replace(y,x);
            }
        }
    }

    public ArrayList<Arco> getTuneles() {
        ArrayList<Arco> aux = new ArrayList();
        aux.addAll(tuneles);
        return aux;
    }

    public int getMetrica() {
        return metrica;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }
}
