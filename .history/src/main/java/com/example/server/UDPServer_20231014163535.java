package com.example.server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    

    public UDPServer() {
        initServer();
    }

    private void initServer() {
        int port = 5000; // Specify the port on which the server will listen

        try {
            DatagramSocket socket = new DatagramSocket(port);

            System.out.println("UDP Server is running on port " + port);

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Received from client: " + receivedMessage);

                // Send a response back to the client (if needed)
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                String responseMessage = "Hello, client!, You visited me from IP: " + clientAddress + ", Port: " + clientPort;
                byte[] sendData = responseMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
