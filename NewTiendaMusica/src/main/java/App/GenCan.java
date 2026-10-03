package App;

public class GenCan {
        private Cancion[] misCanciones;
    
    private int[] id = {1,2,3,4,5,6};
    
    private String[] nombre = {"Alone Again","Bad Habits","Happier","Im Still Standing","Set fire to the rain","Somebody That I Used To Know"};
    
    private double[] precio = {60.50, 50.0, 90.0, 27.90, 27.95, 52.50};
    
    private String[] año = {"1971","2020","2017","1983","2011","2011"};
    
    private String[] imagen = {"Alone.jpg","badhabits.jpg","Happier.jpg","still.jpg","adele.jpg","somebody.jpg"};
    
    private String[] artista={"Gilbert O Sullivan","Ed Sheeran","Ed Sheeran","Elton John","Adele","Gotye"};
    

    public GenCan(Cancion[] misCanciones) {
        this.misCanciones = misCanciones;
    }
    
    public Cancion[] generarCanciones(){
        
         for(int i = 0; i < misCanciones.length; i++) 
           misCanciones[i] = new Cancion(id[i], nombre[i], precio[i], año[i], artista[i], imagen[i]);
        return misCanciones;
    }
    
}
