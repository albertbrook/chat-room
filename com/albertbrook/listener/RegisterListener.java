package com.albertbrook.listener;

import com.albertbrook.controller.RegisterController;
import com.albertbrook.settings.Settings;
import com.albertbrook.view.Client;
import com.albertbrook.view.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterListener implements ActionListener {
    private JFrame client;
    private JTextField user;
    private JPasswordField password;
    private JLabel msg;

    private RegisterController registerController = new RegisterController();

    public RegisterListener(Client client) {
        this.client = client;
        user = client.getUser();
        password = client.getPassword();
        msg = client.getLogoL();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = user.getText();
        String pwd = String.valueOf(password.getPassword());

        msg.setFont(Settings.FONT);
        msg.setForeground(Color.red);

        if (username.isEmpty())
            msg.setText("请输入用户名");
        else if (pwd.isEmpty())
            msg.setText("请输入密码");
        else if (registerController.check(username))
            msg.setText("用户已存在");
        else if (registerController.register(username, pwd) != 1)
            msg.setText("注册失败");
        else {
            client.dispose();
            new Room(user.getText());
        }
    }
}
