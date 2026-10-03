package App;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args){
        try {
            ServerSocket s = new ServerSocket(5555);
            for(;;){
                Socket cl = s.accept();
                (new Manejator(cl)).start();
            }
        } catch (Exception e) {
        }
    }
}
