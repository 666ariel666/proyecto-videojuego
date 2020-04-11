package implementacion;

import java.io.File;

import java.io.BufferedInputStream;
import java.io.InputStream;

import java.util.ArrayList;

import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import clases.Fondo;
import clases.JugadorAnimado;
import clases.Tile;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Juego extends Application{
	private GraphicsContext graficos;
	private Group root;
	private Scene escena;
	private Canvas lienzo;
	private int a=0,b=0;
	private boolean verificarMovimiento=true;
	//private Jugador jugador;
	private JugadorAnimado jugadorAnimado;
	private Fondo fondo;
	private ArrayList<Tile> tiles;

	
	private int tilemap [][]= { 
			
			{13,15,17,0,0,34,36,0,0,0,0,0,19,27},
			{14,16,0,21,0,35,37,0,28,0,21,0,20,27},
			{1,3,5,7,9,11,0,0,29,0,0,0,21,27},
			{2,4,6,8,10,12,0,0,28,0,0,18,0,27},
			{17,0,0,0,0,0,0,0,0,29,0,0,0,27},
			{13,15,0,0,21,0,0,18,0,0,0,0,0,27},
			{14,16,0,0,0,0,0,0,0,0,0,0,0,27},
			{30,30,30,30,30,30,30,30,30,0,0,0,27},
			{31,31,31,31,31,31,31,31,31,0,30,32,33,27},
			{0,0,0,0,0,0,0,0,13,15,31,30,0,27},
			{0,0,0,0,0,0,18,0,14,16,30,31,0,27},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0}
		
			};
	public static boolean arriba,abajo,izquierda,derecha;
	public static HashMap <String, Image>imagenes;
	
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage ventana) throws Exception {
		
		inicializarComponentes();
		eventos();				
		ventana.setScene(escena);
		ventana.setTitle("Last Good");
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
	cargarImagenes();
	
	//jugador=new Jugador(20, 600, 3,"goku",0);
	jugadorAnimado=new JugadorAnimado(0,640,1,"leon",0,"descanso");
	fondo=new Fondo(0, 0, 1, "fondo","fondo1");
	
	root=new Group();
	escena=new Scene(root, 896, 1024);
	lienzo=new Canvas(896,1024);
	inicializarTiles();
	root.getChildren().add(lienzo);
	graficos=lienzo.getGraphicsContext2D();}

public void inicializarTiles() {
	 tiles = new ArrayList<Tile>();
	for(int i=0;i<tilemap.length;i++) {
		for (int j=0;j<tilemap[i].length;j++) {
			if(tilemap[i][j]!=0)
				this.tiles.add(new Tile(tilemap[i][j],j*64,i*64,1,"tile",64,64));
			
		}
		
	}
}

public void cargarImagenes() {
	imagenes.put("goku", new Image("go.png"));
	imagenes.put("ssj", new Image("oooo.jpg"));
	imagenes.put("fondo", new Image("fondo.jpg"));
    imagenes.put("fondo1",new Image("fondo1.jpg"));
    imagenes.put("tile", new Image("tileset.png"));
    imagenes.put("leon",new Image("leon.png"));
}

public void actualizarEstados(double t) {
	//jugador.mover();
	jugadorAnimado.calcularFrame(t);
	jugadorAnimado.mover();
	fondo.mover();
	for (int i=0; i<tiles.size();i++)
	tiles.get(i).mover();
	for(int i=0;i<tiles.size();i++)
	tiles.get(i).verificarColision(jugadorAnimado, graficos);
		
}

public void pintar() {
	
	fondo.pintar(graficos);
	for(int i=0;i<tiles.size();i++)
		this.tiles.get(i).pintar(graficos);

	//jugador.pintar(graficos);
	jugadorAnimado.pintar(graficos);
	graficos.fillText("vidas"+jugadorAnimado.getVidas(), 20, 640);
	
	//Clip cancion=jugadorAnimado.noSe();
	//cancion.start();
	//cancion.stop();
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
				
				jugadorAnimado.setAnimacionActual("arriba");
			arriba=true;
			break;
			case"DOWN":
				abajo=true;
			
				jugadorAnimado.setAnimacionActual("abajo");
				break;
			case "SPACE":
			    jugadorAnimado.setVelocidad(15);
			    jugadorAnimado.setNombreImagen("ssj");
			    break;
			
			}
		}
		
	});
	escena.setOnKeyReleased(new EventHandler<KeyEvent>() {

		@Override
		public void handle(KeyEvent evento) {
			switch(evento.getCode().toString()) {
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
			    jugadorAnimado.setVelocidad(1);
			    jugadorAnimado.setNombreImagen("personaje");
			    break;
			}
			
		}
		
	});

}




}