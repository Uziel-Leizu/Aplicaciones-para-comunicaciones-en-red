package App;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.JFileChooser;

public class Servidor {

    public static void main(String[] args) {
        int port = 9000;
        File f = new File("C:\\Users\\pakma\\OneDrive - Instituto Politecnico Nacional\\ESCOM\\6\\Redes\\PracticaTiendaMusica\\src\\main\\java\\ServerArch");
        String path = "C:\\Users\\pakma\\OneDrive - Instituto Politecnico Nacional\\ESCOM\\6\\Redes\\PracticaTiendaMusica\\src\\main\\java\\ServerArch\\";
        String arch[] = f.list();

        for (int i = 0; i < arch.length; i++) {
            long l = f.length();
            f = new File(path + arch[i]);
            System.out.println(arch[i] + " tamaño " +l);
        }
        f = null;
        try {
            DatagramSocket s = new DatagramSocket(port);
            System.out.println("Servidor iniciado en el puerto: " + s.getLocalPort() + " esperando datagrama");
            int ban = 0;
            for (;;) {
                DatagramPacket p = new DatagramPacket(new byte[32000], 32000);
                s.receive(p);
                System.out.println("Recibimos algo del cliente");
                StringBuilder sb = new StringBuilder();
                String datos = new String(p.getData(), 0, p.getLength());
                System.out.println(datos);
                int cont = 0;

                if (ban == 0) {
                    for (int i = 0; i < arch.length; i++) {
                        if (datos.contains(arch[i])) {
                            System.out.println("Ya tiene este articulo");
                        } else {
                            sb.append(arch[i] + "\n");
                            //System.out.println("Cambiamos el contador");
                            cont++;
                        }

                    }

                    byte[] b = sb.toString().getBytes();
                    DatagramPacket p1 = new DatagramPacket(b, b.length, p.getAddress(), p.getPort());
                    s.send(p1);

                    ban = 1;
                } else {
                    if (datos.contains("Cancelar")) {
                        ban = 0;
                    } else {
                        String[] listaCan = datos.split("\n");
                        for (int i = 0; i < listaCan.length; i++) {
                            BufferedInputStream bis;
                            byte[] mybytearray;
                            mybytearray = new byte[32000];
                            Long tam = 0L;
                            try {
                                f = new File(path+listaCan[i]);
                                System.out.println(f.getName());
                                long hola = f.length();
                                System.out.println(hola);
                                //DataInputStream DisAr = new DataInputStream(new FileInputStream(path + listaCan[i]));
                                tam = (long) Math.ceil(((double) f.length()) / 32000);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            bis = new BufferedInputStream(new FileInputStream(path + listaCan[i]));
                            long env = 0;
                            System.out.println("Preparandose para enviar el archivo " + path + listaCan[i] + " de " + tam + " bytes\n\n");
                            byte[] b = (tam+"").getBytes();
                            DatagramPacket p1 = new DatagramPacket(b, b.length, p.getAddress(), p.getPort());
                            s.send(p1);
                            int l;
                            int n;
                            while (env < tam && (n = bis.read(mybytearray))!=1) {
                                p1 = new DatagramPacket(mybytearray, mybytearray.length,p.getAddress(),p.getPort());
                                s.send(p1);
                                env = env+1;
                              
                                System.out.print("\rEnviado el byte "+env);
                                Thread.sleep(5);
                            }
                            bis.close(); 
                        }
                        ban = 0;
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
