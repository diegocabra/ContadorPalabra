package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import TDAColaPrioridad.InvalidKeyException;
import Logica.LanguageConfig;
import Logica.Logica;
 
public class GUI extends JFrame {
	
    private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
    private JButton btnCargar;
    private JButton btnComenzar; 
    private JButton btnIngles;
    private JButton btnEspañol;
    private JLabel label;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI frame = new GUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    public GUI() {
    	super("Winter is Coming");
    	
   	   	Logica logica = new Logica();
   	   	
   	   	LanguageConfig resource= new LanguageConfig();
   	   	resource.setSpanish();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(150, 150, 720	, 500);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
 
        
 
        JButton btnCargar = new JButton(resource.read("Cargar"));
        btnCargar.setBounds(288, 25, 180, 40);
        contentPane.add(btnCargar);
 
        JButton btnComenzar = new JButton(resource.read("Comenzar"));
        btnComenzar.setBounds(500,25,180,40);
        contentPane.add(btnComenzar);
        
        JButton btnEspañol = new JButton(resource.read("Español"));
        btnEspañol.setBounds(500,300,100,35);
        contentPane.add(btnEspañol);
        
        JButton btnIngles = new JButton(resource.read("Ingles"));
        btnIngles.setBounds(600,300,100,35);
        contentPane.add(btnIngles);
        
        
        JLabel label = new JLabel();
        label.setBounds(188, 25, 180, 40);
        label.setText(resource.read("Bienvenido"));
        contentPane.add(label);
        
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(52, 76, 400, 206);
       
        JScrollPane scroll=new JScrollPane(textArea);
        scroll.setBounds(52, 76, 400, 206);
        contentPane.add(scroll);
 
        btnCargar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
            	JFileChooser fc=new JFileChooser();
            	//Armamos un  filtro , para seleccionar solo TXT, y solo directorios 
            	FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
            	 
                	fc.setFileFilter(filtro);
            	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            	int seleccion=fc.showOpenDialog(contentPane);
            	 
              	if(seleccion==JFileChooser.APPROVE_OPTION){
            	 
            	          	    File fichero=fc.getSelectedFile();
            	 
            	             	 
            	    try {
						Files.walk(Paths.get(fichero.getAbsolutePath())).forEach(ruta-> {
						    if (Files.isRegularFile(ruta)) {
						    	try {
						    		logica.procesarArchivo(ruta.toString());
				    		
								} catch (InvalidKeyException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
						        
						    }
						});
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	           
            	}
            }
        });
        
        btnComenzar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
            	           	 
                  String Cadena ="";
				try {
					
					Cadena = logica.OrdenNumerico();
				} catch (InvalidKeyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					if(Cadena == "")
						JOptionPane.showMessageDialog(null,resource.read("FallaDirectorio"));
					else textArea.setText(Cadena);
                 
            }
 
    });
        
        
        btnEspañol.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){  
            	resource.setSpanish();
            	btnCargar.setText(resource.read("Cargar"));
                btnComenzar.setText(resource.read("Comenzar")); 
                btnIngles.setText(resource.read("Ingles"));
                btnEspañol.setText(resource.read("Español"));
                label.setText(resource.read("Bienvenido"));
                
            } 
});
        
        btnIngles.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){  
            	resource.setEnglish();
            	btnCargar.setText(resource.read("Cargar"));
                btnComenzar.setText(resource.read("Comenzar")); 
                btnIngles.setText(resource.read("Ingles"));
                btnEspañol.setText(resource.read("Español"));
                label.setText(resource.read("Bienvenido"));

            } 
});
}
   
	
}