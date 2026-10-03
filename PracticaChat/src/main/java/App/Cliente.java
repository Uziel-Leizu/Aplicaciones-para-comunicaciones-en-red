package App;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Enumeration;
import java.util.Scanner;

public class Cliente extends Thread {

    final int port = 9930;
    final String servidor = "localhost";
    Socket s;
    InputStream is;
    InputStreamReader isr;
    BufferedReader br;
    OutputStream os;
    OutputStreamWriter osw;
    BufferedWriter bw;
    String nombre;

    @Override
    public void run() {
        IniciarCliente();
    }

    public void IniciarCliente() {
        try {
            nombre = ClienteInterfaz.getNombre();
            String msg = "Hola";
            int ptoMc = 9931;
            s = new Socket(servidor, port);
            System.out.println("Hola, cliente iniciado");

            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            NetworkInterface ni = NetworkInterface.getByIndex(1);

            os = s.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            MulticastSocket Mcl = new MulticastSocket(ptoMc);
            Mcl.setReuseAddress(true);
            InetAddress gpo = InetAddress.getByName("230.1.1.1");
            SocketAddress dir;
            try {
                dir = new InetSocketAddress(gpo, ptoMc);
            } catch (Exception e) {
                System.err.println("Sintaxis: java UDPEchoClient host [port]");
                return;
            }
            Mcl.joinGroup(dir, ni);
            for (;;) {
                System.out.println("Entramos al forever");
                DatagramPacket p = new DatagramPacket(new byte[65535], 65535);
                Mcl.receive(p);
                String datos = new String(p.getData(), 0, p.getLength());
                if (datos.contains("/p")) {
                    if(datos.contains("/p "+nombre)){
                        ClienteInterfaz.setText("Mensaje privado: " + datos);
                    }
                } else {
                    System.out.println("Datagrama multicast: " + new String(p.getData(), 0, p.getLength()));
                    ClienteInterfaz.setText(new String(p.getData(), 0, p.getLength()));
                }

            }
        } catch (Exception e) {
        }
    }

    public void CerrarCliente() {
        try {
            s.close();
        } catch (Exception e) {
            //System.out.println("El servidor nunca se conecto a un cliente");
        }
        try {
            System.out.println("Cliente Apagado");
            this.stop();
        } catch (Exception e) {
            //System.out.println("Entramos a la ecepcion: "+e);
        }
    }

    public String Enviar(String Mensaje) {
        try {
            if (s.isConnected()) {
                String msg = Mensaje;
                bw.write(msg);
                bw.newLine();
                bw.flush();
                return "Estoy conectado";
            } else {
                return "No estoy conectado";
            }
        } catch (Exception e) {
            return "No estoy conectado";
        }
    }
}
