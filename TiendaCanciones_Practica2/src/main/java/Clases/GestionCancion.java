/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author familia
 */
public class GestionCancion {
    
    private Cancion[] misCanciones;
    
    private int[] id = {1,2,3,4,5,6,7,8,9,10,11,12,13};
    
    private String[] nombre = {"Me porto Bonito", "Efecto", "Un coco", "Lovely", "TV", 
                                "Aeropuerto", "La Mitad", "Alaska", "141", "Ojos Cerrados",
                                "El color de tus ojos", "Calidad", "Ya Superame"};
    
    private double[] precio = {60.50, 50.0, 90.0, 27.90, 27.95,
                                52.50, 70.0, 20.79, 15.58, 40.0, 
                                42.0, 7.50, 100.0
                               };
    
    private String[] año = { "2022","2022","2022","2018","2022","2022","2022","2022","2022",
                              "2022","2017","2022","2019"
                               
    };
    
    private String[] imagen = { "bad.jpg","bad.jpg","bad.jpg","billie.jpg","billie.jpg",
                             "camilo.png","camilo.png",
                             "camilo.png","141.jpg","ms.jpg","ms.jpg","firme.jpg","firme.jpg"
        
    };
    
    private String[] artista={"Bad Bunny","Bad Bunny","Bad Bunny","Billie Elish","Billie Elish","Camilo","Camilo"
            ,"Camilo","Banda MS","Banda Ms","Banda Ms","Grupo Firme","Grupo Firme"
    };
    
    private String[] duracion={"2:58","3:33","3:16","3:20","4:41","2:57","2:57","4:01","2:55","3:00","3:22","2:50","3:09"
        };
    

    public GestionCancion(Cancion[] misCanciones) {
        this.misCanciones = misCanciones;
    }
    
    public Cancion[] generarCanciones(){
        
         for(int i = 0; i < misCanciones.length; i++) 
           misCanciones[i] = new Cancion(id[i], nombre[i], precio[i], año[i], duracion[i], artista[i], imagen[i]);
        
        
        return misCanciones;
    }
    
    
    
    
}
