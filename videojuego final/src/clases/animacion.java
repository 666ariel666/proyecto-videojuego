package clases;

import java.util.ArrayList;

import javafx.scene.shape.Rectangle;



public class animacion {
private double duracion;
private Rectangle coordenadas[];
public animacion(double duracion, Rectangle[] coordenadas) {
	super();
	this.duracion = duracion;
	this.coordenadas = coordenadas;
}
public double getDuracion() {
	return duracion;
}
public void setDuracion(double duracion) {
	this.duracion = duracion;
}
public Rectangle[] getCoordenadas() {
	return coordenadas;
}
public void setCoordenadas(Rectangle[] coordenadas) {
	this.coordenadas = coordenadas;
}


public Rectangle calcularFrameActual(double t) {
	int cantidadFrames=this.coordenadas.length;
	int indiceFrameActual=(int)((t%(cantidadFrames*duracion)/duracion));
	return coordenadas[indiceFrameActual];
};


}
