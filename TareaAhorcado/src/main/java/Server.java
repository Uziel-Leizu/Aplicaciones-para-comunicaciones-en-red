
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static int contadorErrores = 0;

    public static void main(String[] args) {
        final int port = 45000;
        ServerSocket Serv = null;
        Socket socket = null;
        try {
            Serv = new ServerSocket(port);
            System.out.println("Servidor iniciado esperando conexion del cliente...");
            socket = Serv.accept();

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);

            //comienza informacion
            final String[] palabra = {"holamundo", "ornitorrinco", "buscaminas", "cazafantasmas", "sandia", "criptomonedas", "molecula", "teclado", "estudiante"};
            final String FIN = "FIN";
            int numerop = (int) (Math.random() * 9 + 1);

            String palabraAdivinadaHastaElMomento = inicializarpalabra(palabra[numerop]);
            String letra;

            do {
                System.out.println("Llevas " + contadorErrores + " errores");
                bw.write(palabraAdivinadaHastaElMomento);
                bw.newLine();
                bw.flush();

                letra = br.readLine();

                palabraAdivinadaHastaElMomento = actualizarPalabra(palabra[numerop], palabraAdivinadaHastaElMomento, letra.charAt(0));

            } while (!palabraAdivinadaHastaElMomento.equals(palabra) && contadorErrores != 6);
            if (contadorErrores==6) {
                bw.write(FIN);
                bw.newLine();
                bw.flush();
            } else {
                bw.write(palabraAdivinadaHastaElMomento + " Haz ganado");
                bw.newLine();
                bw.flush();
            }
        } catch (Exception e) {
        }

    }

    public static String actualizarPalabra(String palabra, String PalabraHastaElMomento, char letra) {
        String Resultado = "";
        int contadorDeLetras = 0;
        for (int i = 0; i < palabra.length(); i++) {
            if (palabra.charAt(i) == letra || palabra.charAt(i) == PalabraHastaElMomento.charAt(i)) {
                Resultado += palabra.charAt(i);
            } else {
                Resultado += "-";
                contadorDeLetras++;
            }
        }
        if (contadorDeLetras == palabra.length()) {
            contadorErrores++;
        }
        return Resultado;
    }

    public static String inicializarpalabra(String palabra) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabra.length(); i++) {
            sb.append("-");
        }
        return sb.toString();
    }
}
