package implementacion;





import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//import java.io.BufferedInputStream;
//import java.io.InputStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;
//import com.sun.javafx.application.PlataformImpl;
//import com.sun.javafx.application.PlatformImpl;

import clases.Disparo;
import clases.Enemigo;
import clases.Fondo;
import clases.JugadorAnimado;
import clases.Pigsi;
import clases.Puntos;
import clases.Recolectables;
import clases.Soldado;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Juego extends Application {

	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private String nombre="";
//	private boolean verifica=false;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	private Fondo fondo;
	private Soldado isac;
	private ArrayList<Tile> tiles;
	private Recolectables cosas;
    private Enemigo enemigo;
    private Pigsi manhunt;
    private Disparo ataque;
    private boolean atacar=false;
   private boolean correcto=false;
   private ArrayList<Puntos> puntuaciones;
 

	private int tilemap [][]= { 
			{13,15,17,0,0,34,36,0,0,0,0,0,19,27},
			{14,16,0,21,0,35,37,0,28,0,15,0,21,27},
			{1,3,5,7,9,11,24,0,29,0,0,0,0,27},
			{2,4,6,8,10,12,0,0,28,0,0,18,0,27},
			{17,0,0,0,18,0,18,0,0,29,0,17,0,27},
			{13,15,0,22,16,0,0,18,0,0,22,21,0,27},
			{14,16,0,0,0,24,0,21,0,22,0,21,0,27},
			{20,0,21,0,17,0,0,16,0,0,0,21,0,27,27},
			{16,0,0,21,18,16,0,0,24,23,0,32,33,27},
			
			{13,15,17,23,0,34,36,0,0,0,0,0,19,27},
			{14,16,0,21,0,35,37,24,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,0,29,23,0,024,0,27},
			{2,4,6,8,10,12,0,0,28,0,0,18,0,27},
			{17,0,0,0,18,0,18,022,24,29,22,17,0,27},
			{13,15,0,0,16,0,0,18,0,22,0,21,0,27},
			{14,16,0,0,22,0,0,21,0,0,0,21,0,27},
			{20,0,21,0,17,23,0,16,0,23,0,21,0,27,27},
			{16,0,24,21,18,16,0,22,0,21,0,32,33,27},
			
			{13,15,17,24,0,34,36,24,0,23,0,023,19,27},
			{14,16,0,21,0,35,37,0,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,0,29,022,24,024,0,27},
			{2,4,6,8,10,12,0,022,28,0,22,18,0,27},
			{17,0,0,24,18,0,18,0,0,29,0,17,0,27},
			{13,15,0,0,16,0,0,18,0,23,0,21,0,27},
			{14,16,0,0,23,0,22,21,24,24,0,21,0,27},
			{20,0,21,0,17,0,0,16,0,0,0,21,0,27,27},
			{16,0,0,21,18,16,0,0,024,21,0,32,33,27},
			
			{13,15,17,0,0,34,36,0,0,0,0,0,19,27},
			{14,16,0,21,0,35,37,0,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,23,29,0,23,0,0,27},
			{2,4,6,8,10,12,0,0,28,0,22,18,0,27},
			{17,0,0,0,18,24,18,024,0,29,0,17,0,27},
			{13,15,0,0,16,0,0,18,0,0,0,21,0,27},
			{14,16,0,0,0,0,0,21,024,0,0,21,0,27},
			{20,0,21,0,17,0,0,16,0,0,024,21,0,27,27},
			{16,0,0,21,18,16,024,0,0,21,0,32,33,27},
			
			{13,15,17,0,24,34,36,0,0,0,0,0,19,27},
			{14,16,0,21,23,35,37,0,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,024,29,0,0,024,0,27},
			{2,4,6,8,10,12,0,23,28,0,0,18,0,27},
			{17,0,0,0,18,24,18,0,24,29,024,17,0,27},
			{13,15,0,0,16,0,0,18,0,0,0,21,0,27},
			{14,16,0,0,0,0,23,0,0,23,0,21,0,27},
			{20,0,21,0,17,0,0,16,0,22,0,21,0,27,27},
			{16,0,0,21,18,16,0,0,0,21,0,32,33,27},
			{0,0,18,0,0,24,21,0,13,15,0,18,0,30},
			{0,0,0,024,0,0,24,18,14,16,0,0,0,31},
			{0,0,0,022,24,0,0,0,024,0,0,0,0,30},
			{0,0,0,0,0,0,24,0,0,0,0,0,0,0},
			{0,0,0,0,0,24,0,022,0,024,0,0,0,022},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,022},
			{13,15,17,0,24,34,36,0,0,024,0,0,19,27},
			{14,16,0,21,0,35,37,0,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,0,29,24,24,0,0,27},
			{2,4,6,8,10,12,0,24,28,22,0,18,0,27},
			{17,0,0,24,18,0,18,0,0,29,0,17,0,27},
			{13,15,0,0,16,0,0,18,0,024,0,21,0,27},
			{14,16,0,0,0,0,0,21,0,0,0,21,0,27},
			{20,0,21,24,17,24,0,16,0,0,24,21,0,27,27},
			{16,0,0,21,18,16,0,0,0,21,0,23,23,27},
			
			{13,15,17,0,0,34,36,0,0,0,0,23,19,27},
			{14,16,0,21,0,35,37,0,28,23,15,0,21,27},
			{1,3,5,7,9,11,0,023,29,24,0,024,0,27},
			{2,4,6,8,10,12,0,0,28,0,24,18,0,27},
			{17,0,024,0,18,0,18,24,0,29,0,17,0,27},
			{13,15,0,0,16,0,24,18,22,0,0,21,0,27},
			{14,16,0,0,0,24,0,21,0,024,0,21,0,27},
			{20,0,21,23,17,0,0,16,0,0,0,21,0,27,27},
			{16,0,0,21,18,16,024,0,0,0,0,32,33,27},
			
			{13,15,17,0,0,34,36,0,0,0,0,024,19,27},
			{14,16,23,21,0,35,37,22,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,0,29,24,0,0,0,27},
			{2,4,6,8,10,12,0,0,28,23,023,18,0,27},
			{17,0,23,0,18,0,18,23,0,29,0,17,0,27},
			{13,15,0,0,16,23,0,18,0,0,0,21,0,27},
			{14,16,0,0,024,0,0,21,0,0,0,21,0,27},
			{20,0,21,0,17,0,0,16,0,023,0,21,0,27,27},
			{16,0,0,21,18,16,0,0,0,21,0,32,33,27},
			
			{13,15,17,024,024,34,36,024,0,024,0,0,19,27},
			{14,16,0,21,0,35,37,0,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,0,29,0,0,023,0,27},
			{2,4,6,8,10,12,0,22,28,0,0,18,0,27},
			{17,0,0,0,18,0,18,0,0,29,024,17,0,27},
			{13,15,0,0,16,0,0,18,0,0,024,21,0,27},
			{14,16,0,0,024,0,0,21,0,0,0,21,0,27},
			{20,0,21,0,17,0,0,16,0,0,023,21,0,27,27},
			{16,0,0,21,18,16,0,024,0,0,0,32,33,27},
			
			{13,15,17,0,0,34,36,0,24,0,0,0,19,27},
			{14,16,0,21,23,35,37,24,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,0,29,0,0,0,0,27},
			{2,4,6,8,10,12,0,22,28,0,24,18,0,27},
			{17,0,0,0,18,0,18,0,0,29,0,17,0,27},
			{13,15,0,024,16,0,0,18,24,0,0,21,0,27},
			{14,16,0,0,24,0,0,0,24,0,0,0,0,27},
			{20,0,0,24,17,0,0,16,0,0,024,21,0,27,27},
			{16,0,0,0,18,16,0,0,0,0,22,0,33,27},
			
			{13,15,17,0,0,34,36,0,0,0,0,0,19,27},
			{14,16,0,21,0,35,37,0,28,024,15,0,21,27},
			{1,3,5,7,9,11,0,22,29,0,024,0,0,27},
			{2,4,6,8,10,12,0,0,28,023,0,18,0,27},
			{17,0,0,0,18,0,18,024,0,29,0,17,0,27},
			{13,15,0,0,16,0,22,18,0,0,0,21,0,27},
			{14,16,0,024,0,0,0,0,24,0,0,0,0,27},
			{20,0,21,0,17,24,0,16,24,0,0,0,0,27,27},
			{16,0,0,21,18,16,0,0,0,21,0,32,33,27},
			
			{13,15,17,023,0,34,36,0,0,0,0,0,19,27},
			{14,16,0,21,0,35,37,0,28,0,15,0,21,27},
			{1,3,5,7,9,11,0,22,29,0,0,23,23,27},
			{2,4,6,8,10,12,0,0,28,23,023,18,0,27},
			{17,0,0,0,18,0,18,0,24,29,0,17,0,27},
			{13,15,0,023,16,0,0,18,0,0,0,21,0,27},
			{14,16,0,0,0,24,0,21,0,23,0,21,0,27},
			{20,0,21,0,17,0,0,16,0,0,024,21,0,27,27},
			{16,0,0,21,18,16,0,0,0,21,0,32,33,27},
			};


		
//private  int trampas[][]={
//		{0,0,0,0,0,0,0,(int)(Math.random()*4),0,0,0,0,0,0},
//		{0,(int)(Math.random()*4),0,0,0,0,(int)(Math.random()*4),0,0,0,0,0,(int)(Math.random()*4),0},
//		{0,0,0,0,0,(int)(Math.random()*4),0,0,0,0,0,0,0,0},
//		{0,0,(int)(Math.random()*4),0,0,0,0,0,(int)(Math.random()*4),0,0,0,0,0},
//		{0,(int)(Math.random()*4),0,0,(int)(Math.random()*4),0,0,0,0,(int)(Math.random()*4),0,0,0,0},
//		{0,0,(int)(Math.random()*4),0,0,0,0,0,(int)(Math.random()*4),0,0,(int)(Math.random()*4),0,0},
//		{0,0,(int)(Math.random()*4),0,0,0,(int)(Math.random()*4),0,0,0,0,(int)(Math.random()*4),0,0},
//		{0,0,0,0,(int)(Math.random()*4),0,0,0,(int)(Math.random()*4),0,0,0,0,0},
//		{0,0,(int)(Math.random()*4),0,0,(int)(Math.random()*4),0,0,0,(int)(Math.random()*4),0,0,0,0},
//		{0,0,(int)(Math.random()*4),0,0,0,0,0,0,0,0,(int)(Math.random()*4),0,0},
//	
//	};
	public static boolean arriba,abajo,izquierda,derecha;
	public static HashMap <String, Image>imagenes;
	
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage ventana) throws Exception {
	
	
		inicializarComponentes();
		TextInputDialog tid = new TextInputDialog();
		tid.setHeaderText(null);
		tid.setTitle("Informacion");
		tid.setContentText("Introduce tu nombre");
		Optional<String> h= tid.showAndWait();
		this.nombre=h.get(); 
		  Alert alert = new Alert(Alert.AlertType.WARNING);
		    alert.setHeaderText(null);
		    alert.setTitle("Mejores Puntajes");
		    alert.setContentText(mostrarPuntaje());
		    java.awt.Toolkit.getDefaultToolkit().beep();
		    alert.initStyle(StageStyle.UTILITY);  
		    alert.showAndWait();	
		
//		nombre = JOptionPane.showInputDialog("Nombre");
		eventos();				
		ventana.setScene(escena);
		ventana.setTitle("THE FIEND");
		ventana.show();
		cicloJuego();
		
		
	}
	public void cicloJuego() {
		long tiempoinicial=System.nanoTime();
		AnimationTimer animationTimer= new AnimationTimer() {

			@Override
			public void handle(long tiempoACtual) {
				
				double t=(tiempoACtual-tiempoinicial)/1000000000.0;
				//System.out.println(t);
				actualizarEstados(t);
				pintar();
				
			}
		
		};
		animationTimer.start();
		
		
	}
public void inicializarComponentes() {
	imagenes=new HashMap<String,Image>();
    ReproducirSonido("src/cancion4.wav");
	puntuacion();
	cargarImagenes();
	//jugador=new Jugador(20, 600, 3,"goku",0);
	jugadorAnimado=new JugadorAnimado(0,640,1,"leon",3,"descanso", 100,"ataque",0);
	ataque=new Disparo(jugadorAnimado.getX()+50,jugadorAnimado.getY(), 1, "ataque");
	enemigo=new Enemigo(50,30,1,"enemigo1",8,"abajo",100);
    manhunt= new Pigsi(-1000,-5500,1,"final",1,"abajo",100);
    isac=new Soldado(-3000, -4000, 1, "isac", 1, "abajo", 500);
	fondo=new Fondo(0, 0, 1, "fondo","fondo1");
	root=new Group();
	escena=new Scene(root, 896, 1024);
	lienzo=new Canvas(896,1024);
	cosas=new Recolectables((int)(Math.random()*300), (int)(Math.random()*600), 1, "kratos", 1,"arma");
	inicializarTiles();
	
	root.getChildren().add(lienzo);
	graficos=lienzo.getGraphicsContext2D();}


public void inicializarTiles() {
	 tiles = new ArrayList<Tile>();
	for(int i=0;i<tilemap.length;i++) {
		for (int j=0;j<tilemap[i].length;j++) {
			if(tilemap[i][j]!=0)
				this.tiles.add(new Tile(tilemap[i][j],j*64,i*-64,1,"tile",64,64));
			
		}
		
	}
}



public void cargarImagenes() {

	imagenes.put("fondo", new Image("fondo.jpg"));
    imagenes.put("fondo1",new Image("fondo1.jpg"));
    imagenes.put("tile", new Image("tileset.png"));
    imagenes.put("leon",new Image("leon.png"));
    imagenes.put("malo",new Image("enemigo.png"));
    imagenes.put("trampa", new Image("Sin.png"));
    imagenes.put("kratos", new Image("vida.png"));
    imagenes.put("arma", new Image("objeto.png"));
    imagenes.put("transforma", new Image("transforma.png"));
    imagenes.put("poder", new Image("poder.png"));
    imagenes.put("ataque", new Image("ataque.png"));
    imagenes.put("pigsi",new Image("pigsi.png"));
    imagenes.put("isac",new Image("enemigo4.png"));
    imagenes.put("final",new Image("final.png"));
    imagenes.put("enemigo1",new Image("enemigo1.png"));
}

public void actualizarEstados(double t) {
	puntuacion();
	jugadorAnimado.calcularFrame(t);
	jugadorAnimado.mover();
	isac.calcularFrame(t);
	enemigo.calcularFrame(t);
	manhunt.calcularFrame(t);
	for (int i=0; i<tiles.size();i++) {
		tiles.get(i).mover();
	;}
	fondo.mover();
    cosas.mover();
	for(int i=0;i<tiles.size();i++) {
	tiles.get(i).verificarColision18(jugadorAnimado)	
		;}
for(int i=0;i<tiles.size();i++) {
		tiles.get(i).verificarColision21(jugadorAnimado);
		}

enemigo.mover1(jugadorAnimado);
enemigo.verificarcolision(ataque, jugadorAnimado);
enemigo.verificarcolision1(jugadorAnimado);
ataque.mover();
muerte();
ganar();
if(jugadorAnimado.verificarPoder(cosas, jugadorAnimado)==true ) {
	eventos();
	atacar=true;
	jugadorAnimado.setVelocidad(3);
	fondo.setVelocidad(3);
	cosas.setVelocidad(3);
	for(int i=0;i<tiles.size();i++)
		this.tiles.get(i).setVelocidad(3);;
	}

if (enemigo.getVidaActual()==0)
	ReproducirSonido("src/cancion.wav");
manhunt.mover1(jugadorAnimado);
manhunt.verificarcolision(ataque, jugadorAnimado);
manhunt.verificarcolision1(jugadorAnimado);
isac.mover1(jugadorAnimado);
isac.verificarcolision(ataque, jugadorAnimado);
isac.verificarcolision1(jugadorAnimado);
if(isac.getVidaActual()==0)
	ReproducirSonido("src/cancion3.wav");
for(int i=0;i<tiles.size();i++)
if(tiles.get(i).verificarColision24(jugadorAnimado)==true)
	jugadorAnimado.setCapturado2(false);
eliminarPunto();
}

public void pintar() {
	
	fondo.pintar(graficos);
//	for(int i=0;i<tiles.size();i++)
//		this.tiles.get(i).pintar(graficos);   
	for(int i=0;i<tiles.size();i++)
		this.tiles.get(i).pintar(graficos);  
	graficos.fillText("vidas "+jugadorAnimado.getVidas(),20,20 );
	graficos.fillText("llaves "+jugadorAnimado.getLlaves(),100,20 );
	graficos.fillText("vida actual "+jugadorAnimado.getVidaActual(),500,20 );
	graficos.fillText("Puntaje "+jugadorAnimado.getPuntos(),300,20 );
	manhunt.textovidas(graficos);
	jugadorAnimado.pintar(graficos);
	jugadorAnimado.textovidas(graficos);
	enemigo.pintar(graficos);
	 manhunt.pintar(graficos);
	 isac.pintar(graficos);
	 isac.textovidas(graficos);
   cosas.pintar1(graficos, jugadorAnimado);
   cosas.verificarColision18(jugadorAnimado);
   cosas.pintar2(graficos, jugadorAnimado);
   if(correcto==true)
		  ataque.pintar1(graficos, jugadorAnimado);
enemigo.textovidas(graficos);


enseñar();
}


public void eventos() {
	
	escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

		
		public void handle(KeyEvent evento) {
			
			switch(evento.getCode().toString()) {
			case "RIGHT":
				
				derecha=true;
				
				jugadorAnimado.setAnimacionActual("correrD");
				
				break;
			case "LEFT":
				
				izquierda=true;
				jugadorAnimado.setAnimacionActual("correr");
				break;
			case "UP":
	
				arriba=true;
				jugadorAnimado.setAnimacionActual("arriba");
				
			break;
			case"DOWN":
		
				abajo=true;
			
				jugadorAnimado.setAnimacionActual("abajo");
				break;
			case "SPACE":
			
				if(ataque.pintar1(graficos, jugadorAnimado)==true && atacar==true) {
					ReproducirSonido2("src/cancion2.wav");
					correcto=true;
					
				}
			    break;
			
			}
			
			
		}
		
	}); 
	escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent evento1) {
			switch(evento1.getCode().toString()) {
			case "RIGHT":
				derecha=false;
				
				jugadorAnimado.setAnimacionActual("descanso");
				break;
			case "LEFT":
				izquierda=false;
			
				jugadorAnimado.setAnimacionActual("descanso");
				break;
			case "UP":
				
				jugadorAnimado.setAnimacionActual("descanso");
			arriba=false;
			break;
			case"DOWN":
				
				jugadorAnimado.setAnimacionActual("descanso");
				abajo=false;
				break;
			case "SPACE":
			    jugadorAnimado.setNombreImagen("leon");
			    if(ataque.pintar1(graficos, jugadorAnimado)==false) {
			    correcto=false;
			    ataque.setX(jugadorAnimado.getX()+50);
			    ataque.setY(jugadorAnimado.getY());}
			    break;
			}
			
		}
		
	});

}

public boolean muerte() {
	if(jugadorAnimado.getVidas()==0) {
		
		ReproducirSonido1("src/terror.wav");
		JOptionPane.showMessageDialog(null, "          YOU ARE DEAD\n"
				+ "	CONSEJO SI MUERES SEGUIDO\n"
				+ "      APRENDE A JUGAR");
		JOptionPane.showMessageDialog(null,"\n\nPUNTAJE:                      "+String.format("%.2f", (double)(jugadorAnimado.getPuntos()))
				, "LET ME IN",JOptionPane.ERROR_MESSAGE);
		return true;}
		
	 return false;

//		mostrarPuntaje();
	
	
	
	
	}


public void ReproducirSonido(String nombreSonido){

    try {
     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
     Clip clip = AudioSystem.getClip();
     clip.open(audioInputStream);
     clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
      System.out.println("Error al reproducir el sonido.");
    }
  }

public void ReproducirSonido1(String nombreSonido){

    try {
     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
     Clip clip = AudioSystem.getClip();
     clip.open(audioInputStream);
     clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
      System.out.println("Error al reproducir el sonido.");
    }
  }

public void ReproducirSonido2(String nombreSonido){

    try {
     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(nombreSonido).getAbsoluteFile());
     Clip clip = AudioSystem.getClip();
     clip.open(audioInputStream);
     clip.start();
    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
      System.out.println("Error al reproducir el sonido.");
    }
  }
public void eliminarPunto() {
	for(int i=0;i<tiles.size();i++) {
		if(this.tiles.get(i).verificarColision24(jugadorAnimado)==true || this.tiles.get(i).verificarColisionllave(jugadorAnimado)==true || this.tiles.get(i).verificarColisioncofre(jugadorAnimado, enemigo, graficos, manhunt, isac)==true) 
	tiles.remove(i);}
jugadorAnimado.setCapturado2(false);
jugadorAnimado.setCapturado3(false);
jugadorAnimado.setCapturado4(false);

}

public void puntuacion() {
	puntuaciones = new ArrayList<Puntos>();
	try {
		BufferedReader archivoLectura = 
				new BufferedReader(new FileReader("src/puntaje.txt"));
		String linea="";
		while((linea = archivoLectura.readLine())!=null) {
			String partes[] = linea.split(":");
			String numero = partes [1];
			double decimal = Double.parseDouble(numero);	
			puntuaciones.add(new Puntos(partes[0],decimal));
		}	
	} catch (IOException e) {
		System.out.println("El archivo no existe");
	}
}
public String mostrarPuntaje() {
	Collections.sort(puntuaciones, new Comparator<Puntos>() {

		@Override
		public int compare(Puntos o1, Puntos o2) {
			return new Integer ( (int) o2.getPuntuacion()).compareTo(new Integer ((int) ( o1.getPuntuacion())));
		}
		
	});
	String hola="";
	if(!puntuaciones.isEmpty()) {
	for(int i =0; i<5;i++) {
		hola+=puntuaciones.get(i)+"\n";}
	return hola;
	}
	
	else
		return new String("No hay puntajes");
}
public void escribir() {
	try {
		BufferedWriter archivo = new BufferedWriter(new FileWriter("src/puntaje.txt",true));
		archivo.write(this.nombre + ":  "+(double)this.jugadorAnimado.getPuntos()+"\n");
		archivo.close();
		
	} catch (IOException e) {
		e.printStackTrace();
	}
}
public boolean ganar() {
	if(manhunt.getVidaActual()<=0) {
	
			
			jugadorAnimado.setPuntos(jugadorAnimado.getPuntos()+1000);
		
		JOptionPane.showMessageDialog(null,
				"Felicidades Completaste el Juego");
		
		return true;
	}else return false;
}
public void enseñar() {
	if(ganar()==true || muerte()==true) {
		escribir();
		puntuacion();
		JOptionPane.showMessageDialog(null, mostrarPuntaje());

		System.exit(0);}
}

}




