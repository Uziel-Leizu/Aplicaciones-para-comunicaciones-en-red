
import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Servidor {
    public static void main(String[] args) throws IOException, Exception {
        System.out.println("Iniciando servidor....");
        ServerSocket ss= new ServerSocket(7000);
        System.out.println("Esperando por Cliente...");
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (;;){
            pool.execute(new Metodos(ss.accept()));
        }
    }
}
