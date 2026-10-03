package App;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Enumeration;

public class Servidor {
    
    public static void main(String[] args){
        int pto=9930;
        try {
            ServerSocket s = new ServerSocket(pto);
            for(;;){
                System.out.println("Esperando conexión...");
                Socket cl=s.accept();
                System.out.println("Ciente conectado..\n");
                Manejador m = new Manejador(cl);
                m.start();
            }
        } catch (Exception e) {
        }
    }
}

class Manejador extends Thread{
    Socket cl;
    DataInputStream in;
    public Manejador(Socket cl){
        this.cl=cl;
    }
    @Override
    public void run(){
        
        try {
            int ptoMc = 9932;
            int pto_dst=9931;
            
            MulticastSocket Ms = new MulticastSocket(ptoMc);
            Ms.setReuseAddress(true);
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            NetworkInterface ni = NetworkInterface.getByIndex(1);
            
            InetAddress gpo = InetAddress.getByName("230.1.1.1");
            SocketAddress dir;
            try {
                dir = new InetSocketAddress(gpo,ptoMc);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            Ms.joinGroup(dir, ni);
            String mensaje;
            
            BufferedReader br = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            for(;;){
                mensaje = br.readLine();
                byte[] b = mensaje.getBytes();
                
                System.out.println("Mensaje recibido: "+mensaje);
                if(mensaje.indexOf("SALIR")>=0){
                    System.out.println("El cliente se ha desconectado");
                    cl.close();
                    Ms.close();
                    break;
                }
                DatagramPacket p = new DatagramPacket(b, b.length,gpo,pto_dst);
                Ms.send(p);
                System.out.println("Enviando mensaje: "+mensaje);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
