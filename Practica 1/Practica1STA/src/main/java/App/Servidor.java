package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static String relativePath = System.getProperty("user.dir");
    public static void main(String args[]){
        System.out.println("Ruta relativa: "+relativePath);
        final int port = 25001; //puerto de escuhca del servidor
        try {
            ServerSocket s = new ServerSocket(port);
            s.setReuseAddress(true);
            System.out.println("Servidor iniciado con exito en el puerto: "+s.getLocalPort());
            for(;;){
                Socket cliente = s.accept();
                System.out.println("Cliente conectado: "+cliente);
                //leer socket del cliente
                InputStream is = cliente.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                //Escribir socket del cliente
                OutputStream os = cliente.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                
                File archServ = new File(relativePath+"\\src\\archivosServidor");
                System.out.println("Directorio: "+archServ.getAbsolutePath());
                System.out.println("Archivos dentro del servidor: ");
                File[] listaArchivos = archServ.listFiles();
                
                String archivos = "";
                for(File f : listaArchivos){
                    if(f.isDirectory()){
                        System.out.println("Dir: "+f.getName());
                    }
                }
                
            }
        } catch (Exception e) {
        }
    }
}
