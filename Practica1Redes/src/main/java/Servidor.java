
import java.net.*;
import java.io.*;

public class Servidor {
    public static void main(String[] args){
        try {
            int port= 8000;
            ServerSocket s = new ServerSocket(port);
            s.setReuseAddress(true);
            System.out.println("Servidor iniciado, esperando por archivos...");
            File f = new File("");
            String ruta = f.getAbsolutePath();
            String carpeta="Archivos";
            String ruta_archivos = ruta+"\\"+carpeta+"\\";
            System.out.println("Ruta:"+ruta_archivos);
            File f2 = new File(ruta_archivos);
            f2.mkdirs();
            f2.setWritable(true);
            for(;;){
                Socket cl = s.accept();
                System.out.println("Cliente conectando desde "+cl.getInetAddress()+":"+cl.getPort());
                DataInputStream dis = new DataInputStream(cl.getInputStream());
                String nombre = dis.readUTF();
                long tam = dis.readLong();
            }
        } catch (Exception e) {
            
        }
    }
}
