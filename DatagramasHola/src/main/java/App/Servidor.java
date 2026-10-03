package App;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {
    public static void main(String[] args){
        try {
            DatagramSocket s = new DatagramSocket(5555);
            System.out.println("Servidor iniciado en el puerto: "+s.getLocalPort()+" esperando datagrama");
            for(;;){
                DatagramPacket p = new DatagramPacket(new byte[65535], 65535);
                s.receive(p);
                System.out.println("Datagrama recibido desde -> "+p.getAddress()+":"+p.getPort());
                String datos = new String(p.getData(),0,p.getLength());
                System.out.println("Con el mensaje: "+datos);
                String saludo = "Hola "+datos;
                byte[]b= saludo.getBytes();
                DatagramPacket p1 = new DatagramPacket(b, b.length,p.getAddress(),p.getPort());
                s.send(p1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
