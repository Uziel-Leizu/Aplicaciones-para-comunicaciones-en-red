
import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 *
 * @author axele
 */
public class CEnvia {

    public static void main(String[] args) {
        try {
            int op;
            Scanner scan = new Scanner(System.in);
            int pto = 8000;
            String dir = "127.0.0.1";
            Socket cl = new Socket(dir, pto);
            for (;;) {
                System.out.println("Conexion con servidor establecida..");
                System.out.println("---------------Bienvenido--------------");
                System.out.println("------------------Menú-----------------");
                System.out.println("1.Subir archivo");
                System.out.println("2.Descargar archivo");
                System.out.println("3.Listar contenido");
                System.out.print("R= ");
                op = scan.nextInt();
                if (op == 1) {
                    JFileChooser jf = new JFileChooser();
                    //jf.setMultiSelectionEnabled(true);
                    int r = jf.showOpenDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        File f = jf.getSelectedFile();
                        String nombre = f.getName();
                        String path = f.getAbsolutePath();
                        long tam = f.length();
                        System.out.println("Preparandose para enviar archivo " + path + " de " + tam + " bytes\n\n");
                        DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                        DataInputStream dis = new DataInputStream(new FileInputStream(path));
                        dos.writeUTF(nombre);
                        dos.flush();
                        dos.writeLong(tam);
                        dos.flush();
                        long enviados = 0;
                        int l = 0, porcentaje = 0;
                        while (enviados < tam) {
                            byte[] b = new byte[1500];
                            l = dis.read(b);
                            System.out.println("enviados: " + l);
                            dos.write(b, 0, l);
                            dos.flush();
                            enviados = enviados + l;
                            porcentaje = (int) ((enviados * 100) / tam);
                            System.out.print("\rEnviado el " + porcentaje + " % del archivo");
                        }//while
                        scan.nextLine();//limpiar la entrada del buffer
                        System.out.println("\nArchivo enviado.. Presione <ENTER> para continuar...");
                        scan.nextLine();
                        dis.close();
                        dos.close();
                        cl.close();
                    }//if  
                } else if (op == 2) {
                    
                }else if (op == 3){
                    
                }else{
                    scan.nextLine();
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("Opcion ingresada no valida Presione <ENTER> para continuar...");
                    scan.nextLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }//catch
    }//main
}
