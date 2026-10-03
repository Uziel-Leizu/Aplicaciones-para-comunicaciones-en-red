
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {
        final String IP = "127.0.0.1";
        final int port = 45000;

        try {
            Socket socket = new Socket(IP, port);
            System.out.println("Esperando a que el servidor conteste");

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            final String FIN = "FIN";
            String palabraAdivinadaHastaElMomento = "";
            Scanner scan = new Scanner(System.in);

            while (!palabraAdivinadaHastaElMomento.equals(FIN)){
                //System.out.println("Esperando respuesta del servidor ");
                palabraAdivinadaHastaElMomento = br.readLine();
                
                if(!palabraAdivinadaHastaElMomento.equals(FIN)){
                    System.out.println("Has adivinado: ");
                    System.out.println(palabraAdivinadaHastaElMomento);
                    System.out.println("Indique una nueva letra para adivinar la palabra: ");
                    String letra = scan.nextLine();
                    
                    bw.write(letra);
                    bw.newLine();
                    bw.flush();
                }else{
                    palabraAdivinadaHastaElMomento = br.readLine();
                    System.out.println("Has adivinado: ");
                    System.out.println(palabraAdivinadaHastaElMomento);
                }
            } 
            
        } catch (Exception e) {
        }
    }
}
