package clases;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import implementacion.Juego;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class JugadorAnimado extends ItemJuego{

private int vidas;
private int xImagen;
private int yImagen;
private int anchoImagen;
private int altoImagen;
private String animacionActual;
private int direccion=1;
private HashMap<String,animacion>animaciones;


public JugadorAnimado(int x, int y, int velocidad, String nombreImagen, int vidas, String animacionActual) {
	super(x, y, velocidad, nombreImagen);
	this.vidas = vidas;
	this.animacionActual=animacionActual;
	animaciones=new HashMap<String,animacion>();
	inicializarAnimaciones();
}
public void inicializarAnimaciones() {
	Rectangle coordenadasCorrer[]= {
			new Rectangle(192,32,33,32),
			new Rectangle(226,32,33,30),
			new Rectangle(256,32,33,30)
	//new Rectangle(294,78,110,86),
	//new Rectangle(400,80,110,86),
	//new Rectangle(498,78,110,86),
	//new Rectangle(588,88,110,86),
	//new Rectangle(672,96,110,86),
	//new Rectangle(746,100,110,86),
	
	
	};
	animacion animacionCorrer=new animacion(0.09,coordenadasCorrer);
	animaciones.put("correr",animacionCorrer);
	
	Rectangle coordenadasCorrerD[]= {
			new Rectangle(194,65,33,32),
			new Rectangle(229,63,33,26),
			new Rectangle(259,65,31,27)
	//new Rectangle(294,78,110,86),
	//new Rectangle(400,80,110,86),
	//new Rectangle(498,78,110,86),
	//new Rectangle(588,88,110,86),
	//new Rectangle(672,96,110,86),
	//new Rectangle(746,100,110,86),
	
	
	};
	animacion animacionCorrerD=new animacion(0.09,coordenadasCorrerD);
	animaciones.put("correrD",animacionCorrerD);
	
	
	Rectangle coordenadasDescanso[]= {
			new Rectangle(226,0,31,27),
			new Rectangle(226,32,31,27),
			new Rectangle(227,63,31,27),
			new Rectangle(227,97,33,30),
		
			
	};
	
	animacion animacionDescanso=new animacion(0.4,coordenadasDescanso);
    animaciones.put("descanso",animacionDescanso);
    
    Rectangle coordenadasArriba[]={
    		new Rectangle(194,97,31,27),
    		new Rectangle(227,97,31,27),
    		new Rectangle(259,97,31,27)
    		
    };
    animacion animacionArriba=new animacion(0.09,coordenadasArriba);
    animaciones.put("arriba", animacionArriba);
    
    Rectangle coordenadasAbajo[]={
    		new Rectangle(194,0,31,27),
    		new Rectangle(226,0,31,27),
    		new Rectangle(259,0,31,27),
    	
    };
    animacion animacionAbajo=new animacion(0.09,coordenadasAbajo);
    animaciones.put("abajo", animacionAbajo);
}

public void calcularFrame(double t) {
	Rectangle coordenadas=animaciones.get(animacionActual).calcularFrameActual(t);
    this.xImagen=(int)coordenadas.getX();
    this.yImagen=(int)coordenadas.getY();
    this.altoImagen=(int)coordenadas.getWidth();
    this.anchoImagen=(int)coordenadas.getHeight();
}; 




public int getDireccion() {
	return direccion;
}
public void setDireccion(int direccion) {
	this.direccion = direccion;
}
public String getAnimacionActual() {
	return animacionActual;
}
public void setAnimacionActual(String animacionActual) {
	this.animacionActual = animacionActual;
}
public int getVidas() {
	return vidas;
}


public void setVidas(int vidas) {
	this.vidas = vidas;
}



@Override 
public void pintar(GraphicsContext graficos) {
	graficos.drawImage(Juego.imagenes.get(nombreImagen),xImagen,yImagen,anchoImagen,direccion*altoImagen,x,y,3*anchoImagen,altoImagen);
    graficos.setStroke(Color.RED);
	
}
@Override 
public void mover() {
	if (y<this.alto)
		y=640;
	if(y>640)
	y=640;
	if(Juego.derecha)
	x+=velocidad;
	if(Juego.izquierda)
	x-=velocidad;
	if(Juego.arriba)
		y-=velocidad;
	if(Juego.abajo) 
		y+=velocidad;
     
}


public Rectangle obtenerRectangulo() {
	
		return new Rectangle(x, y,direccion*altoImagen,3*anchoImagen);}
		
		
	



public Clip noSe() {
Clip clip=null;
try {
    InputStream Is=ClassLoader.getSystemResourceAsStream("cancion.wav");
    AudioInputStream ais=AudioSystem.getAudioInputStream(Is);
    DataLine.Info info=new DataLine.Info(Clip.class, ais.getFormat());
    clip=(Clip) AudioSystem.getLine(info);
    clip.open(ais); // 10000 milisegundos (10 segundos)
 } catch (Exception tipoError) {
   System.out.println("" + tipoError);
 }
return clip;
}}




//return new Rectangle(0, 0, 0, 0);}







