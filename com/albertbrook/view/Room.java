package com.albertbrook.view;

import com.albertbrook.controller.ChatController;
import com.albertbrook.controller.WordController;
import com.albertbrook.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Room extends JFrame {
    private JTextArea textArea = new JTextArea();
    private JTextField textField = new JTextField();

    private Socket socket;

    private String username;

    private ChatController chatController = new ChatController();
    private WordController wordController = new WordController();

    public Room(String username) {
        this.username = username;

        setTitle(username + "聊天窗口");
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);

        textArea.setFont(Settings.FONT);
        textArea.setEditable(false);
        textField.setFont(Settings.FONT);
        textField.requestFocus();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        textField.addActionListener(e -> {
            String str = textField.getText();
            if (str.trim().isEmpty())
                return;
            if (!chatController.say(username)) {
                textArea.append("5秒内请勿发言超过3次\n");
                return;
            }
            if (wordController.matchCount(str)) {
                textArea.append("不好的言论\n");
                return;
            }
            textField.setText("");
            textArea.append(str + "\n");

            send(str);
        });

        try {
            socket = new Socket("localhost", 54230);
            textArea.append(socket.getLocalSocketAddress() + " in\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Receive()).start();
    }

    private void send(String str) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(username + ": " + str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Receive implements Runnable {
        @Override
        public void run() {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                while (true)
                    textArea.append(dataInputStream.readUTF() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
