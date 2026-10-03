package App;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Servidor {

    public static String relativePath = System.getProperty("user.dir");
    static String  path = relativePath + "\\src\\archivosServidor\\";
    private static Cancion[] misCanciones;
    private static int port = 9000;
    public static GenCan canciones;
    public static File f;

    public static void main(String[] args) {
        misCanciones = new Cancion[6];
        canciones = new GenCan(misCanciones);
        misCanciones = canciones.generarCanciones();
        try {
            DatagramSocket serv = new DatagramSocket(port);
            System.out.println("Servidor escuchando en el puerto: " + serv.getLocalPort() + " esperando datagrama");
            for (;;) {
                DatagramPacket pE = new DatagramPacket(new byte[32000], 32000);
                serv.receive(pE);
                String datos = new String(pE.getData(), 0, pE.getLength());
                if (datos.contains("catalogo")) { //Cargar catalogo
                    System.out.println("Enviando catalogo al cliente");
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);

                    //Escritura de objeto para datagrama
                    for (int i = 0; i < 6; i++) {
                        oos.writeObject(misCanciones[i]);
                        oos.flush();
                    } //for
                    //Envio de datagrama
                    byte[] b2 = baos.toByteArray();
                    DatagramPacket dS = new DatagramPacket(b2, b2.length, pE.getAddress(), pE.getPort());
                    serv.send(dS);
                } else {
                    String[] listaCan = datos.split("\n");
                    for (int i = 0; i < listaCan.length; i++) {
                        BufferedInputStream bis;
                        byte[] mybytearray;
                        mybytearray = new byte[32000];
                        Long tam = 0L;
                        try {
                            f = new File(path + listaCan[i]+".mp3");
                            System.out.println(f.getName());
                            long hola = f.length();
                            System.out.println(hola);
                            //DataInputStream DisAr = new DataInputStream(new FileInputStream(path + listaCan[i]));
                            tam = (long) Math.ceil(((double) f.length()) / 32000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        bis = new BufferedInputStream(new FileInputStream(path + listaCan[i]+".mp3"));
                        long env = 0;
                        System.out.println("Preparandose para enviar el archivo " + path + listaCan[i] + " de " + tam + " bytes\n\n");
                        byte[] b = (tam + "").getBytes();
                        DatagramPacket p1 = new DatagramPacket(b, b.length, pE.getAddress(), pE.getPort());
                        serv.send(p1);
                        int l;
                        int n;
                        while (env < tam && (n = bis.read(mybytearray)) != 1) {
                            p1 = new DatagramPacket(mybytearray, mybytearray.length, pE.getAddress(), pE.getPort());
                            serv.send(p1);
                            env = env + 1;

                            System.out.print("\rEnviado el byte " + env);
                            Thread.sleep(5);
                        }
                        bis.close();
                    }
                }

            }//forever
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
