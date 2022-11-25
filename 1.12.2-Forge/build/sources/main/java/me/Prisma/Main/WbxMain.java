package me.Prisma.Main;



import me.Prisma.HWID.HWIDUtils;
import me.Prisma.HWID.WebUtils;
import me.Prisma.utils.HWIDChecker;
import me.Prisma.utils.Main.SystemUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class WbxMain {
    public static String Name = "Prisma";

    public static void Main() {
        Display.setTitle("Prisma-㭤 | 鸢飞戾天者，望峰息心 | Version 221113");
    }

    public static void Liquid() {
        Display.setTitle(Name + "-㭤" + version + "丨调查员： " + WbxMain.username + "[" + WbxMain.rank + "]丨鸢飞戾天者，望峰息心 | 开始完成新的任务叭~");
    }

    public static String Cracked = "Prisma";
    public static String version = "221113";
    public static String username;
    public static String password;
    public static String rank;
    public static boolean isStarting;

    public static void sendWindowsMessageLogin() throws AWTException, IOException {
      //  FakeSenseMain.Cracked();
        SystemUtils.displayTray("请您在下方填写您的序列号", "Login", TrayIcon.MessageType.WARNING);
        String AT = JOptionPane.showInputDialog("请在下方填写您的序列号(用户名)");
        final int R = 0;
        WbxMain.username = AT;
        AT = JOptionPane.showInputDialog("请在下方填写您的证件号(密码)");
        final int R2 = 0;
        WbxMain.password = AT;
        WbxMain.isStarting = true;

        try {
            if (username == null) {
                JOptionPane.showMessageDialog(null, "您的序列号不能为空!", "验证", 0);
                System.exit(0);
            } else if (password == null) {
                JOptionPane.showMessageDialog(null, "您的证件号不能为空!", "验证", 0);
                System.exit(0);
            } else {
                if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/Data").contains("[" + username + "]")) {
                    if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/Data").contains("[" + username + "]" + HWIDUtils.getHWID() + ":" + password + "[1]")) {
                        rank = "User";
                        JOptionPane.showMessageDialog(null, "身份证明已验证完毕", "身份证明核验处", 1);
                        SystemUtils.displayTray("身份证明验证完毕", "正在前往任务地", TrayIcon.MessageType.WARNING);
                    } else if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/Data").contains("[" + username + "]" + HWIDUtils.getHWID() + ":" + password + "[2]")) {
                        rank = "Beta";
                        JOptionPane.showMessageDialog(null, "身份证明已验证完毕", "身份证明核验处", 1);
                        SystemUtils.displayTray("身份证明验证完毕", "正在前往任务地", TrayIcon.MessageType.WARNING);
                    } else if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/Data").contains("[" + username + "]" + HWIDUtils.getHWID() + ":" + password + "[3]")) {
                        rank = "Cat";
                        JOptionPane.showMessageDialog(null, "登录许可签发完成", "身份证明核验处", 1);
                        SystemUtils.displayTray("身份证明验证完毕", "正在前往任务地", TrayIcon.MessageType.WARNING);
                    } else if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/Data").contains("[" + username + "]" + HWIDUtils.getHWID() + ":" + password + "[4]")) {
                        rank = "Dev";
                        JOptionPane.showMessageDialog(null, "登录许可签发完成", "身份证明核验处", 1);
                        SystemUtils.displayTray("身份证明验证完毕", "正在前往任务地", TrayIcon.MessageType.WARNING);
                    } else {
                        JOptionPane.showMessageDialog(null, "正在前往任务地", "身份证明核验处", 0);
                        JOptionPane.showInputDialog(null, "您还没获取棱镜下发的身份核验证明,请获取后重试！", HWIDUtils.getHWID());
                        SystemUtils.displayTray("阁下没能成功取得身份证明文件TwT", "阁下没能成功取得身份证明文件TwT", TrayIcon.MessageType.WARNING);
                        System.exit(0);
                    }
                }
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            JOptionPane.showInputDialog("阁下没能成功取得身份证明文件TwT", LiquidBounce.HWID);
            SystemUtils.displayTray("阁下没能成功取得身份证明文件TwT", "阁下没能成功取得身份证明文件TwT", TrayIcon.MessageType.WARNING);
            System.exit(0);
        }
    }
}