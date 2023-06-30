package tpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GrafoDirigido<T> implements Grafo<T> {

	private int cantArcos;
	private HashMap<Integer, ArrayList<Arco<T>>> listVertices;

	public GrafoDirigido() {
		this.cantArcos = 0;
		this.listVertices = new HashMap<Integer, ArrayList<Arco<T>>>();
	}

	/*El costo computacional de agregarVertice es O(1) 
	 ya que agregar un nuevo vertice no requiere realizar ningun recorrido previo
	 solo realizar un "put" en nuestro HashMap para agregar el nuevo vertice
	 */
	@Override
	public void agregarVertice(int verticeId) {
		if(!this.contieneVertice(verticeId)) {
		 	ArrayList<Arco<T>> arcos = new ArrayList<>();
			this.listVertices.put(verticeId, arcos);
		}
	}

	/*El costo de borrarVertice() es O(n)
	 * ya que necesito 1 ingreso para borrar el vertice y luego recorrer todos los arcos del grafo
	 * para localizar los arcos donde su destino sea el vertice a borrar
	 */
	@Override
	public void borrarVertice(int verticeId) {
		Iterator<Arco<T>> itArco = this.obtenerArcos();
		while(itArco.hasNext()) {
			Arco<T> arco = itArco.next();
			if(arco.getVerticeDestino() == verticeId) {
				borrarArco(arco.getVerticeOrigen(), verticeId);
			}
		}
		this.listVertices.remove(verticeId);
	}

	/*El costo de agregarArco() es O(1) ya que contieneVertice() tiene un costo de o(1)
	*/
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (!this.existeArco(verticeId1,verticeId2) && this.contieneVertice(verticeId1) && this.contieneVertice(verticeId2)) {
			Arco<T> newArco = new Arco<>(verticeId1, verticeId2, etiqueta);
			this.listVertices.get(verticeId1).add(newArco);
			this.cantArcos++;
		}

	}

	/*el costo de borrarArco() es de O(n)
	 ya que utilizar obtenerArco() el cual tiene un costo de O(n), debido a que 
	 debe realizar un recorrido del arreglo de arcos de un vertice
	 */
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if(this.listVertices.containsKey(verticeId1)) {
			this.listVertices.get(verticeId1).remove(this.obtenerArco(verticeId1, verticeId2));
			this.cantArcos--;
		}
	}

	/*el costo de contieneVertice() es de O(1) ya que solo necesita un ingreso a memoria
	 * para consultar si la lista de vertices contiene el vertice pasado por parametro
	 */
	@Override
	public boolean contieneVertice(int verticeId) {
		return this.listVertices.containsKey(verticeId);
	}

	/*el costo computacional de existeArco() es O(n)*/
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		return obtenerArco(verticeId1, verticeId2) != null;
	}

	/*el costo computacional de obtenerArco() es O(n)*/
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		ArrayList<Arco<T>> arcos = this.listVertices.get(verticeId1);
		Arco<T> arcoAux = new Arco<T>(verticeId1, verticeId2, null);
		if(arcos != null && arcos.contains(arcoAux)) {
			return arcos.get(arcos.indexOf(arcoAux));
		}
		return null;
	}

	/*el costo computacional de cantidadVertices() es O(1) ya que consta de un acceso al hashMap*/
	@Override
	public int cantidadVertices() {
		return this.listVertices.size();
	}

	/*el costo de cantidadArcos() es O(1) porque solo accede al atributo de la propia clase */
	@Override
	public int cantidadArcos() {
		return this.cantArcos;
	}

	/*el costo de obtenerVertices() es O(1) ya que todos los metodos que involucran a HashMap
	 * constan de un solo ingreso.
	 */
	@Override
	public Iterator<Integer> obtenerVertices() {
		return this.listVertices.keySet().iterator();
	}

	/*el costo computacional de obtenerAdyacentes() es de O(n) ya que utiliza un iterador que en el peor 
	 * de los casos debera ser totalmente recorrido
	 */
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		Iterator<Arco<T>> listArcos = this.obtenerArcos(verticeId);
		ArrayList<Integer> adyac = new ArrayList<>();
		while(listArcos.hasNext()) {
			adyac.add(listArcos.next().getVerticeDestino());
		}
		return adyac.iterator();
	}

	/*el costo de obtenerArcos() es de O(n) ya que se utiliza un for que itera sobre todas las claves
	 * del HashMap
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		ArrayList<Arco<T>> nueva = new ArrayList<Arco<T>>();
		for (ArrayList<Arco<T>> arcos : this.listVertices.values()) {
			for (Arco<T> arco : arcos) {
				nueva.add(arco);
			}
		}
		// for (Map.Entry<Integer, ArrayList<Arco<T>>> entrada : this.listVertices.entrySet()){
		// 	for (Arco<T> arco : entrada.getValue()) {
		// 		nueva.add(arco);
		// 	}
		// }
		return nueva.iterator();
	}

	/*el costo computacional de obtenerArcos(int) es de O(1) ya que solo ingresa una vez a memoria 
		para acceder al arreglo de arcos de un vertice y devolver un iterador del mismo
	 */
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		return this.listVertices.get(verticeId).iterator();
	}
}
