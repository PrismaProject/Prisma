package me.Prisma.HWID;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
    public static JTextField username = new JTextField();
    public static JPasswordField password = new JPasswordField();
    public static JButton Login = new JButton("登录");
    public void Login() {
        setBounds(40, 40, 250, 250);
        Container A = getContentPane();
        A.setLayout(new GridLayout(4, 2, 40, 40));
        A.add(new JLabel("用户名"));
        A.add(username);
        A.add(new JLabel("密码"));
        A.add(password);
        A.add(Login);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        Login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = username.getText();
                String pass = password.getText();
                try {
                    if (WebUtils.get("https://gitee.com/funknightmai/fkhk-hwid/blob/master/YisakaHWID.json")
                            .contains("[" + name + "]" + HWIDUtils.getHWID() + ":" + pass)) {
                        JOptionPane.showMessageDialog(null, "登陆成功");
                    } else {
                        JOptionPane.showMessageDialog(null, "登录失败");
                        System.exit(0);
                    }
                } catch (NoSuchAlgorithmException | IOException e1) {
                    e1.printStackTrace();
                }
            }
                             }
        );
    }
}