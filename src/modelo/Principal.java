package modelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;







import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;


public class Principal extends JFrame{
	//Declaracion de variables
	JPanel panelContenedor;
	JLabel lb1;
	JLabel lb2;
	JTextField texto1;
	JTextField texto2;
	JButton boton1;
	
	public static String numero1="";
	public static String numero2="";
	public static String cinta[];
	
	public Principal(){//La ventana principal
		this.setBounds(300,200,300,200);
		
		panelContenedor=new JPanel();
		panelContenedor.setBackground(Color.gray);
		
		
		//Creación de los textos
		texto1=new JTextField();
		texto1.setBounds(115,20,80,25);
		panelContenedor.add(texto1, BorderLayout.NORTH);
		
		texto2=new JTextField();
		texto2.setBounds(115,50,80,25);
		panelContenedor.add(texto2, BorderLayout.NORTH);
		
		
		
		//creacion de los labels
		lb1=new JLabel("Primer número:");
		lb1.setBounds(10,20,110,25);
		panelContenedor.add(lb1, BorderLayout.NORTH);
		
		lb2=new JLabel("Segundo número:");
		lb2.setBounds(10,50,110,25);
		panelContenedor.add(lb2, BorderLayout.NORTH);
		
		
		
		//Creación de los botones
		//boton1
		boton1=new JButton("Restar");
		boton1.setBounds(105,90,100,25);
		panelContenedor.add(boton1);
		boton1.addActionListener(new ActionListener() {
			//Evento del botón
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(numero1.equals("")){
					JOptionPane.showMessageDialog(null,"Debe agregar un número a la caja de texto 1");
				}else if(numero2.equals("")){
					JOptionPane.showMessageDialog(null,"Debe agregar un número a la caja de texto 2");
				}else{
					int n1=Integer.parseInt(numero1);
					int n2=Integer.parseInt(numero2);
					LlenarCinta(n1,n2);
					MaquinaTuring();
					int resultado=getResultado();
					JOptionPane.showMessageDialog(null,"el resultado de la resta es: "+resultado);
				}
			}
		});
		
		
		
		
		this.setContentPane(panelContenedor);
		panelContenedor.setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.setVisible(true);
		
		
		//Métodos en el entry para el texto 1
		texto1.addKeyListener(new KeyAdapter() {
			
			//Evento para recibir el backspace y borrar un dato de la entrada
			public void keyPressed(KeyEvent event){ 
				if ((event.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (numero1.length()>0)) { 
					numero1=borrarDato(numero1);
				}
				}
			
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				//Se restringe que solo entren números
				if ((Character.isDigit(c))){
						numero1+=c;			
				}else{
						getToolkit().beep();
						e.consume();
					}		
				} 	
		});
		
		
		
		//Métodos en el entry para el texto 2
				texto2.addKeyListener(new KeyAdapter() {
					
					public void keyPressed(KeyEvent event){ 
						if ((event.getKeyCode() == KeyEvent.VK_BACK_SPACE) && numero2.length()>0) { 
							numero2=borrarDato(numero2);
						}
						}
					
					@Override
					public void keyTyped(KeyEvent e) {
						char c=e.getKeyChar();
						//Se restringe que solo entren números
						if ((Character.isDigit(c))){
								numero2+=c;			
						}else{
								getToolkit().beep();
								e.consume();
							}		
						} 	
				});
				
		
	}
	
	public static void main(String args[]){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	//Método para borrar del entry
	public static String borrarDato(String dato){
		String salida="";
		salida= dato.substring(0, dato.length()-1); 
		return salida;
	}
	
	//Método para crear la cinta
	public static void LlenarCinta(int n1,int n2){
		int tamañoCinta=n1+n2+2;
		cinta=new String[tamañoCinta];
		int i=0;
		while(i<n1){
			cinta[i]="1";
			i++;
		}
		cinta[i]="0";
		i++;
		while(i<tamañoCinta-1){
			cinta[i]="1";
			i++;
		}
		cinta[i]=" ";
	};
	
	
	//Método máquina de Turing
	public static void MaquinaTuring(){
		int cabezal=0;
		int estado = 0;
		while ((estado!=4)&&(estado!=5)){
			switch(estado){
			case 0:
				if(cinta[cabezal].equals("1")){
					cinta[cabezal]="x"; 
					cabezal++;	
					estado=1;
				}else{
					estado=5;
				}
				break;
			case 1:
				while(cinta[cabezal].equals("1")){
					cabezal++;	
				}
				cabezal++;
				estado=2;
				break;
			case 2:
				while(cinta[cabezal].equals("y")){
					cabezal++;	
				}
				cinta[cabezal]="y";
				cabezal++;	
				estado=3;
				break;
			case 3:
				if(cinta[cabezal].equals("x")){
					cabezal++;
					estado=0;
				}else if(cinta[cabezal].equals(" ")){
					estado=4;
				}else{
					while((cinta[cabezal].equals("1"))||(cinta[cabezal].equals("0"))||(cinta[cabezal].equals("y"))){
						cabezal=cabezal-1;	
					}
				}
				break;
			}	
		}
	}
	
	
	//Método retorna resulttado
	public static int getResultado(){
		int contador=0;
		int resultado=0;
		while(!cinta[contador].equals(" ")){
			if(cinta[contador].equals("1")){
				resultado++;
			}
			contador++;
		}
		return resultado;
	}

}
