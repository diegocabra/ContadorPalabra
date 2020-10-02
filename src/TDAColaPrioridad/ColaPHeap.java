package TDAColaPrioridad;

import java.util.Comparator;

public class ColaPHeap<K, V> implements PriorityQueue<K, V> {

	protected Entrada<K, V>[] a;
	protected Comparator<K> comp;
	protected int size;

	public ColaPHeap(Comparator<K> c) {
		a = (Entrada<K, V>[]) new Entrada[30];
		comp = c;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public Entry<K, V> min() throws EmptyPriorityQueueException {
		if (size == 0)
			throw new EmptyPriorityQueueException("Cola con p vacia");
		return a[1];
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		Entrada<K, V> n = new Entrada<K, V>(key, value);
		if (key == null)
			throw new InvalidKeyException("Clave invalida");
		if(size==a.length-1)
			reHash();
		a[++size] = n;
		if (size > 1) {
			boolean fin = false;
			int padre = size / 2;
			int hijo = size;
			while (!fin)
				if (comp.compare(key, a[padre].getKey()) < 0) {
					Entrada<K, V> aux = a[padre];
					a[padre] = a[hijo];
					a[hijo] = aux;
					hijo = padre;
					if (padre > 1)
						padre = padre / 2;
					else
						fin = true;
				} else
					fin = true;
		}
		return n;
	}

	public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
		if (size == 0)
			throw new EmptyPriorityQueueException("Cola con P vacia");
		Entrada<K, V> r = a[1];
		a[1] = a[size];
		a[size--] = null;
		int i = 1;
		boolean fin = false;
		while (!fin) {
			int hi = 2 * i;
			int hd = 2 * i + 1;
			if (hi > size)
				fin = true;
			else {
				int menor = hi;
				if ((hd <= size) && (comp.compare(a[hi].getKey(), a[hd].getKey()) > 0))
					menor = hd;
				if (comp.compare(a[menor].getKey(), a[i].getKey()) < 0) {
					Entrada<K, V> aux = a[i];
					a[i] = a[menor];
					a[menor] = aux;
					i = menor;
				} else
					fin = true;
			}
		}
		return r;
	}
	private void reHash() {
		Entrada<K,V> [] aux=(Entrada<K,V>[]) new Entrada[a.length*2];
		for(int i=0;i<a.length;i++)
			aux[i]=a[i];
		a=aux;
	}

}
