package Logica;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeSet;
import TDAColaPrioridad.*;
public class Logica {
private HashMap <String,Integer> mapeoContador;
private Integer cantidadPalabras; 
public Logica() {
	 mapeoContador = new HashMap<>();	

}


/**
 * El metodo procesa el archivo, carga las palabras del texto y la cantidad en un HashMap
 * @param ruta
 * @throws InvalidKeyException
 */
    public void procesarArchivo(String ruta) throws InvalidKeyException {
        try (Scanner sc = new Scanner(new File(ruta))) {
            while (sc.hasNext()) {
            	  String entrada = sc.nextLine();
            	  StringTokenizer tokenizer = new StringTokenizer( entrada );

            
            	  while ( tokenizer.hasMoreTokens() ) 
            		 
            	  {

            	  String palabra = tokenizer.nextToken().toLowerCase(); 
            	  
            	  if ( mapeoContador.containsKey( palabra ) ) 

            	  {  int cuenta = mapeoContador.get( palabra );

            	  mapeoContador.put( palabra, cuenta + 1 );} 
            	  else

            		  mapeoContador.put( palabra, 1 ); 
            	  } 
           	 }
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
                	       
    }

    /**
     * 
     * @param mapeoContador
     * @return las Keys con sus respectivos Values ordenado de manera Descendiente
     * @throws InvalidKeyException
     */
	public  String OrdenNumerico() throws InvalidKeyException {
		
		
		PriorityQueue<Integer,String> Heap = new ColaPHeap <Integer,String> (new Comparador<Integer>());
		String Cadena ="";
        for (String i : mapeoContador.keySet()) {
        	  Heap.insert(mapeoContador.get(i),i);
        	}
        
        int i=1;
        while((Heap.size()>1) && (i<6))
      	{
    	  try {
      		
      		Cadena= Cadena+ Heap.min().getValue()+"            "+ Heap.removeMin().getKey();
      		//Double porcentaje = (double) Heap.removeMin().getKey()*100;
      		
      		Cadena=Cadena+"%\n";
		} catch (EmptyPriorityQueueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  i++;     	 	
        	
        }
        return Cadena;
	}

	

    
}
