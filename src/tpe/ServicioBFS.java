package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServicioBFS {

	private Grafo<?> grafo;
	private HashMap<Integer, Boolean> hashMapAux;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.hashMapAux = new HashMap<>();
	}
/*El costo computacional del Algoritmo BFS es de O(V+A) */
	public List<Integer> bfsForest() {
		Iterator<Integer> it = grafo.obtenerVertices();
		ArrayList<Integer> recorrido = new ArrayList<>();
		while(it.hasNext()) {
			Integer clave = it.next();
			this.hashMapAux.put(clave, false);
		}
		it = grafo.obtenerVertices();
		while(it.hasNext()) {
			int k = it.next();
			if(!hashMapAux.get(k)) {
				this.bfsVisit(k,recorrido);
			}
		}
		return recorrido;
	}
	/*El costo computacional del Algoritmo BFS es de O(V+A) */
	private void bfsVisit(int k, ArrayList<Integer> arr) {
		ArrayList<Integer> arrLevels = new ArrayList<>();
		hashMapAux.replace(k, true);
		arrLevels.add(k);
		Iterator<Integer> adyacentes;
		while(!arrLevels.isEmpty()) {
			adyacentes = grafo.obtenerAdyacentes(arrLevels.get(0));
			arr.add(arrLevels.get(0));
			arrLevels.remove(0);
			while(adyacentes.hasNext()) {
				int i = adyacentes.next(); 
				if(!hashMapAux.get(i)) {
					hashMapAux.replace(i, true);
					arrLevels.add(i);
				}
			}
		}
	
	}
	
}
