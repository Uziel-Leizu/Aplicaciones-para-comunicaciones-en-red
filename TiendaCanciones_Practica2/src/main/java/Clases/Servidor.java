/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author familia
 */
public class Servidor {

    public static FrmCliente fc = new FrmCliente();

    public static void main(String[] args) {

        DatagramSocket s;

        try {
            s = new DatagramSocket(4040);

            for (;;) {
                waitConnection(s);
                recibirCancion(s);
               
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void waitConnection(DatagramSocket s) throws SocketException, IOException {

        String msj = "";
        System.out.println("Servidor iniciado... esperando conexion..");
        byte[] b = new byte[65535];
        DatagramPacket p = new DatagramPacket(b, b.length);
        s.receive(p);
        msj = new String(p.getData(), 0, p.getLength());
        System.out.println("Se ha recibido conexion desde " + p.getAddress() + ":" + p.getPort() + "msj:" + msj);
    }

    public static void recibirCancion(DatagramSocket socket) {
        try {
            DatagramPacket p = new DatagramPacket(new byte[65535], 65535);
            socket.receive(p);
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));
            int v1 = dis.readInt();
            System.out.println("v1"+v1);
            File rt = new File("");
            String ruta = rt.getAbsolutePath() + "\\Cliente\\";
            
            byte[] receiveFileName = new byte[1024];
            System.out.println("fc.nc = " + v1);
            for (int a = 0; a < v1; a++) {
                DatagramPacket receiveFileNamePacket = new DatagramPacket(receiveFileName, receiveFileName.length);
                socket.receive(receiveFileNamePacket);
                System.out.println("socket recibido");
                byte[] data = receiveFileNamePacket.getData();
                String fileName = new String(data, 0, receiveFileNamePacket.getLength()); // Converting the name to string

                File f = new File(ruta + fileName);
                FileOutputStream outToFile = new FileOutputStream(f);
                receiveFile(outToFile, socket);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void receiveFile(FileOutputStream outToFile, DatagramSocket socket) throws IOException {
        System.out.println("Receiving file");
        boolean flag; // Bandera para ultimo datagrama
        int sequenceNumber = 0;  //Numero de secuencia datagrama
        int foundLast = 0; //Bandera de ultimo datagrama encontrado

        while (true) {
            byte[] message = new byte[1024];
            byte[] fileByteArray = new byte[1021];

            DatagramPacket receivedPacket = new DatagramPacket(message, message.length);
            socket.receive(receivedPacket);
            message = receivedPacket.getData(); 

            InetAddress address = receivedPacket.getAddress();
            int port = receivedPacket.getPort();

            sequenceNumber = ((message[0] & 0xff) << 8) + (message[1] & 0xff);
            flag = (message[2] & 0xff) == 1;

            if (sequenceNumber == (foundLast + 1)) {

                foundLast = sequenceNumber;
               
                System.arraycopy(message, 3, fileByteArray, 0, 1021);

                outToFile.write(fileByteArray);
                System.out.println("Received: Sequence number:" + foundLast);

                
                sendAck(foundLast, socket, address, port);
            } else {
                System.out.println("Expected sequence number: " + (foundLast + 1) + " but received " + sequenceNumber + ". DISCARDING");
               
                //Reenvio de datagrama
                sendAck(foundLast, socket, address, port);
            }
            
            //Verificar si es el ultimo datagrama
            if (flag) {
                outToFile.close();
                break;
            }
        }
    }

    private static void sendAck(int foundLast, DatagramSocket socket, InetAddress address, int port) throws IOException {
      
        byte[] ackPacket = new byte[2];
        ackPacket[0] = (byte) (foundLast >> 8);
        ackPacket[1] = (byte) (foundLast);
        
        //Envio del datagrama
        DatagramPacket acknowledgement = new DatagramPacket(ackPacket, ackPacket.length, address, port);
        socket.send(acknowledgement);
        System.out.println("Paquete enviado, secuencia numero: = " + foundLast);
    }

}
