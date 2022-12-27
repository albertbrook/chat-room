package com.albertbrook.view;

import com.albertbrook.listener.LoginListener;
import com.albertbrook.listener.RegisterListener;
import com.albertbrook.settings.Settings;

import javax.swing.*;
import java.awt.*;

public class Client extends JFrame {
    private Container container;
    private JPanel logo;
    private JLabel logoL;
    private JLabel userL;
    private JTextField user;
    private JLabel passwordL;
    private JPasswordField password;
    private JButton login;
    private JButton register;

    private Client() {
        setTitle("聊天室");
        setSize(500, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        initElement();
        addElement();
        setElement();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JLabel getLogoL() {
        return logoL;
    }

    public JTextField getUser() {
        return user;
    }

    public JPasswordField getPassword() {
        return password;
    }

    private void initElement() {
        container = getContentPane();
        logo = new JPanel(new BorderLayout());
        logoL = new JLabel("聊天室", JLabel.CENTER);
        userL = new JLabel("用户名");
        user = new JTextField(300);
        passwordL = new JLabel("密码");
        password = new JPasswordField(300);
        login = new JButton("登陆");
        register = new JButton("注册");
    }

    private void addElement() {
        logo.add(logoL, BorderLayout.CENTER);

        container.add(logo);
        container.add(userL);
        container.add(user);
        container.add(passwordL);
        container.add(password);
        container.add(login);
        container.add(register);
    }

    private void setElement() {
        container.setBackground(Color.WHITE);

        int x = 105;
        int y = 150;

        logo.setBounds(0, 0, 500, 100);
        logo.setBackground(new Color(0, 128, 128));

        logoL.setFont(new Font("华文行楷", Font.BOLD, 30));

        userL.setFont(Settings.FONT);
        userL.setBounds(x, y, 60, 30);

        user.setFont(Settings.FONT);
        user.setBounds(x + 90, y, 200, 30);

        passwordL.setFont(Settings.FONT);
        passwordL.setBounds(x + 10, y + 60, 40, 30);

        password.setFont(Settings.FONT);
        password.setBounds(x + 90, y + 60, 200, 30);

        login.setFont(Settings.FONT);
        login.setBounds(x + 30, y + 120, 100, 40);
        login.addActionListener(new LoginListener(this));

        register.setFont(Settings.FONT);
        register.setBounds(x + 160, y + 120, 100, 40);
        register.addActionListener(new RegisterListener(this));
    }

    public static void main(String[] args) {
        new Client();
    }
}
