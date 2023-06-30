package tpe;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;
    private List<List<Integer>> caminos;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
        this.caminos = new ArrayList<List<Integer>>();
	}

    //Retorna una lista con todos los caminos posibles desde Origen a Destino pasando por un limite de Arcos
	public List<List<Integer>> caminos() {
		this.caminos.clear();
        ArrayList<Integer> camino = new ArrayList<Integer>();
        this.caminos(origen,camino);
        return this.caminos;
	}
    //agrega a una lista todos los caminos posibles desde un origen a un destino sin pasar por un limite de arcos
    private void caminos(Integer origen,ArrayList<Integer> camino) {
        camino.add(origen);
        if(origen != destino) {
            Iterator<Integer> it = this.grafo.obtenerAdyacentes(origen);
            while(it.hasNext()) {
                int k = it.next();
                if(!camino.contains(k)) {
                    lim--;
                    if(lim != -1) {
                        this.caminos(k,camino);
                    }
                    lim++;
                }
            }
            camino.remove(camino.size()-1);
        }
        else {
            ArrayList<Integer> aux = new ArrayList<>();
            aux.addAll(camino);
            caminos.add(aux);
            camino.remove(camino.size()-1);
        }

    }
}


