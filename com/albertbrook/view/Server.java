package com.albertbrook.view;

import com.albertbrook.controller.ChatController;
import com.albertbrook.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends JFrame {
    private JTextArea textArea = new JTextArea(10, 20);

    private ArrayList<Send> arrayList = new ArrayList<>();

    private ChatController chatController = new ChatController();

    private Server() {
        setTitle("服务器端窗口");
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        textArea.setEditable(false);
        textArea.setFont(Settings.FONT);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(54230);
            textArea.append(serverSocket.getLocalSocketAddress() + "\n");
            while (true) {
                Socket socket = serverSocket.accept();
                arrayList.add(new Send(socket));
                textArea.append(socket.getRemoteSocketAddress() + " in\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Send implements Runnable {
        private Socket socket;

        private Send(Socket socket) {
            this.socket = socket;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                while (true) {
                    String str = dataInputStream.readUTF();
                    String username = str.split(": ")[0];
                    String chat = str.split(": ")[1];
                    str = socket.getRemoteSocketAddress() + " " + str;
                    chatController.save(username, chat);
                    textArea.append(str + "\n");
                    for (Send send : arrayList) {
                        if (send != this) {
                            send.send(str);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void send(String str) {
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
