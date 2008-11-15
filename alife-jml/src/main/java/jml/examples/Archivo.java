package jml.examples;

public class Archivo {
	
	private String data;
	private String isNumer;
	private String isSpace;
	private String position;
    
    public Archivo() {
        data = "";
        isNumer = "";
        isSpace = "";
        position = "";
    }
    
    public String getdato() { return data; }
    
    public void setdato(String dato) { this.data = dato; }
    
    public String getesNumero() { return isNumer; }
    
    public void setesNumero(String esNumero) { this.isNumer = esNumero; }
    
    public String getesEspacio() { return isSpace; }
    
    public void setesEspacio(String esEspacio) { this.isSpace = esEspacio; }
    
    public String getPosicion() { return position; }
    
    public void setPosicion(String posicion) { this.position = posicion; }
}
