package tpe;

public class Arco<T> {

	private int verticeOrigen;
	private int verticeDestino;
	private T etiqueta;

	public Arco(int verticeOrigen, int verticeDestino, T etiqueta) {
		this.verticeOrigen = verticeOrigen;
		this.verticeDestino = verticeDestino;
		this.etiqueta = etiqueta;
	}
	
	public int getVerticeOrigen() {
		return verticeOrigen;
	}
	
	public int getVerticeDestino() {
		return verticeDestino;
	}

	public T getEtiqueta() {
		return etiqueta;
	}

	public boolean equals(Object o) {
		try {
			Arco<T> nuevo = (Arco<T>)o;
			if(this.verticeOrigen == nuevo.verticeOrigen && this.verticeDestino == nuevo.verticeDestino) {
				return true;
			}
			else if(this.verticeOrigen == nuevo.verticeDestino && this.verticeDestino == nuevo.verticeOrigen) {
				return true;
			}
			else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
