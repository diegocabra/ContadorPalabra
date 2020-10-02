package Logica;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class LanguageConfig {
    private String filePath = null;
    private Properties properties = null;
    private final String Spanish = "Resources/Spanish.properties";
    private final String English = "Resources/English.properties";
    
    
    public void setSpanish() {
    	setLanguage(Spanish);
    }
    
    public void setEnglish() {
    	setLanguage(English);
    }
    
    /**
    *Dejamos seteado el properties segun el path pasado por parametro.
     */
    private void  setLanguage(String filePath ) {
        this.filePath = filePath;
        
        properties = new Properties();
        // open config file
        InputStream in = null;
        try {
            in = new FileInputStream( filePath );
            properties.load( in );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            if ( in != null ) {
                try {
                    in.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
    
   /**
    * 
    * Leemos el texto guardado en properties, segun la clave "KEY"
    */
    public String read( String key ) {
        return properties.getProperty( key );
    }
   
    
    public void write( String key, String value ) {
        properties.setProperty( key, value );
        
        OutputStream output = null;
        try {
        	
            output = new FileOutputStream( filePath );
            properties.store( output, null );
        } catch ( IOException ioe ) {
            ioe.printStackTrace();
        } finally {
            if ( output != null ) {
                try {
                    output.close();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}