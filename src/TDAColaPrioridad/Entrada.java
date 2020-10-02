package TDAColaPrioridad;

public class Entrada<K, V> implements Entry<K, V> {
	protected K key;
	protected V value;
	
	public Entrada(K k, V v) {
		key=k;
		value=v;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}
	

}
