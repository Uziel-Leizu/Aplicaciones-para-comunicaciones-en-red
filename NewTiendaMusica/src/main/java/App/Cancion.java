package App;

import java.io.Serializable;

public class Cancion implements Serializable{
    private int id;
    private String nombre;
    private double precio;
    private String año;
    private String artista;
    private String imagen;
    
    public Cancion(int id, String nombre, double precio, String año, String artista, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.año = año;
        this.artista = artista;
        this.imagen = imagen;
    }

    
    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setAño(String año) {
        this.año = año;
    }


    public void setArtista(String artista) {
        this.artista = artista;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    //Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public String getAño() {
        return año;
    }


    public String getArtista() {
        return artista;
    }

    public String getImagen() {
        return imagen;
    }
    
}
