package App;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Cliente {
    public static void main(String[] args){
        try {
            DatagramSocket cl = new DatagramSocket();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe un nombre y presiona <Enter>");
            String nombre = br.readLine();
            byte[] b = nombre.getBytes();
            InetAddress dst = InetAddress.getByName("127.0.0.1");
            int pto = 5555;
            DatagramPacket p = new DatagramPacket(b,b.length,dst,pto);
            cl.send(p);
            System.out.println("Mensaje envidao al servidor, obteniendo respuesta...");
            DatagramPacket p1 = new DatagramPacket(new byte[65535], 65535);
            cl.receive(p1);
            String respuesta = new String(p1.getData(),0,p1.getLength());
            System.out.println(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
