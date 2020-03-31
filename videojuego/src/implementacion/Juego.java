package implementacion;

import java.util.HashMap;

import clases.Jugador;
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
	private int x=0;
	private Jugador jugador;
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
				System.out.println(t);
				actualizarEstados();
				pintar();
			}
			
		};
		animationTimer.start();
	}
public void inicializarComponentes() {
	imagenes=new HashMap<String,Image>();
	cargarImagenes();
	jugador=new Jugador(20, 600, 3, "goku");
	root=new Group();
	escena=new Scene(root,840,720);
	lienzo=new Canvas(840,720);
	root.getChildren().add(lienzo);
	graficos=lienzo.getGraphicsContext2D();}

public void cargarImagenes() {
	imagenes.put("goku", new Image("implementacion/go.png"));
	imagenes.put("ssj", new Image("implementacion/oooo.jpg"));
}

public void actualizarEstados() {
	jugador.mover();
}

public void pintar() {
	
	graficos.drawImage(new Image("implementacion/a.jpg"), 0, 0);
	jugador.pintar(graficos);
}

public void eventos() {
	
	escena.setOnKeyPressed(new EventHandler<KeyEvent>() {

		
		public void handle(KeyEvent evento) {
			
			switch(evento.getCode().toString()) {
			case "RIGHT":
				derecha=true;
				break;
			case "LEFT":
				izquierda=true;
				break;
			case "UP":
			arriba=true;
			break;
			case"DOWN":
				abajo=true;
				break;
			case "SPACE":
			    jugador.setVelocidad(15);
			    jugador.setNombreImagen("ssj");
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
				break;
			case "LEFT":
				izquierda=false;
				break;
			case "UP":
			arriba=false;
			break;
			case"DOWN":
				abajo=false;
				break;
			case "SPACE":
			    jugador.setVelocidad(10);
			    jugador.setNombreImagen("goku");
			    break;
			}
			
		}
		
	});
}

}
