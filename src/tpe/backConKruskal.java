package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class backConKruskal {
    
    private Grafo grafo;
    private ArrayList<Arco> arcos;
    private ArrayList<Arco> solucionTuneles;
    private int poda;
    private int distTotal;
    private int metrica;
    private int suma;
    private Timer time;

    public backConKruskal(Grafo grafo, int poda, Timer time) {
        this.grafo = grafo;
        this.poda = poda;
        this.arcos = new ArrayList<Arco>();
        this.solucionTuneles = new ArrayList<Arco>();
        this.suma = 0;
        this.time = time;
    }

    public int back() {
        distTotal=0;
        metrica=0;
        Iterator<Arco> it = grafo.obtenerArcos();
        while (it.hasNext()) {
            Arco arco = it.next();
            if(!arcos.contains(arco))
                arcos.add(arco);
        }

        ArrayList<Arco> actual = new ArrayList<Arco>();
        ArrayList<Integer> considerados = new ArrayList();

        int pos = 0;
        this.time.start();
        this.backRecursivo(actual,considerados,pos);
        System.out.println(this.time.stop());

        return distTotal;
    }

    private void backRecursivo(ArrayList<Arco> actual,ArrayList<Integer> considerados,int pos) {
        metrica++;
        if(this.metodoKruskal(actual,considerados)) { //solucion posible
            if(distTotal == 0) {
                distTotal = this.suma;
                solucionTuneles.addAll(actual);
            }
            else {
                if(distTotal > this.suma) {
                    distTotal = this.suma;
                    solucionTuneles.clear();
                    solucionTuneles.addAll(actual);
                }
            }
        }
        else { //recorro todas las posibilidades

            int i = pos;
            int j;
            while(i < arcos.size()) {
                j=i;
                Arco arco = arcos.get(i);
                if(!actual.contains(arco)) {
                    actual.add(arco); //agrego
                    if(getSumaArcos(actual) <= poda) {
                        j++;
                        this.backRecursivo(actual,considerados,j); //sigo explorando
                    }
                    actual.remove(arco); //elimino
                i++;
            }
        }
    }

    private boolean verificarConsiderados(ArrayList<Arco>list, ArrayList<Integer>considerados) {
        considerados.clear();
        for (Arco a : list) {
            Integer origen = a.getVerticeOrigen();
            Integer destino = a.getVerticeDestino();
            if(!considerados.contains(origen)) {
                considerados.add(origen);
            }
            if(!considerados.contains(destino)) {
                considerados.add(destino);
            } 
        }
        return considerados.size() == grafo.cantidadVertices();
    }

    private int getSumaArcos(ArrayList<Arco> list) {
        int suma = 0;
        for (Arco arco : list) {
            int dist = (Integer) arco.getEtiqueta();
            suma += dist;
        }
        return suma;
    }


     public boolean metodoKruskal(ArrayList<Arco> candidato,ArrayList<Integer>considerados) {
        if(verificarConsiderados(candidato,considerados)) {
            HashMap<Integer,Integer> padres = new HashMap<Integer,Integer>();
        for (int i = 1; i <= grafo.cantidadVertices(); i++) {
            padres.put(i, i);
        }
        int iArcos = 0;
        int i = 0;
        int cantArcos = candidato.size();
        int cantVertices = grafo.cantidadVertices();

        while((iArcos<cantVertices-1)&&(i<cantArcos)) {
            Integer origen = candidato.get(i).getVerticeOrigen();
            Integer destino = candidato.get(i).getVerticeDestino();
            if(find(origen,padres)!=find(destino,padres)) {
                unite(origen, destino,padres);
                iArcos++;
            }
            i++;
        }
        if(iArcos==cantVertices-1) {
            return true;
        }
        return false;
        }
        else {
            return false;
        }
    }
    int find(int x,HashMap<Integer,Integer>padres) {
        if (padres.get(x) == x) {
            return x;
        }
        return find(padres.get(x),padres);
    }
    void unite(int x, int y, HashMap<Integer,Integer>padres) {
        int fx = find(x,padres);
        int fy = find(y,padres);
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

    public int getMetrica() {
        return metrica;
    }

    public ArrayList<Arco> getTuneles() {
        ArrayList<Arco> ret = new ArrayList<Arco>();
        ret.addAll(solucionTuneles);
        return ret;
    }

}
