package me.Prisma.utils.Main;

 import me.Prisma.utils.Main.SystemUtils;
 import net.ccbluex.liquidbounce.LiquidBounce;
 import javax.swing.*;
 import java.awt.*;
 import java.io.IOException;
 import java.security.NoSuchAlgorithmException;

 import net.ccbluex.liquidbounce.utils.login.VarUtils;
 import org.lwjgl.opengl.Display;
 import scala.tools.nsc.doc.base.comment.Code;

public class VersionCrack {
    public static String Name = "PrismaSense";
    public static String Dev = "Prisma Teams";
    public static boolean CheckVersionTrue = false;
    public static boolean GuiLoginTrue = false;
    public static boolean crackwindow = false;
    public static boolean CodeLoginFinished = true;
    public static String userCode;
    public static String StartCode;
    public static String number;
    public static boolean Loading;
    public static void Liquid() {
        Display.setTitle("Prisma-Sense | " + LiquidBounce.CrackedVersion + " | 请您登录的说..... | 谁在意故事的结尾 满江灯影皆碎 追惜东去流水   ---《风灵玉秀》");
    }
    public static void StartCode() {
        String AT = JOptionPane.showInputDialog("请输入前置启动项");
        final int R = 0;
        StartCode = AT;
    }
    public static void Cracked() throws IOException {
        if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/PrismaSenseVersionAndCode.txt").contains(LiquidBounce.CrackedVersion)) {
            CheckVersionTrue = true;
            VarUtils.UnChangeClass = true;
        } else {
            JOptionPane.showMessageDialog(null, "版本不匹配，检查版本以及网络连接状态后重试");
            System.exit(0);
        }
    }
    public static void Cracked2() throws IOException {
        if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/PrismaSenseVersionAndCode.txt").contains(LiquidBounce.CrackedVersion)) {
            VersionCrack.CheckVersionTrue = true;
            VarUtils.UnChangeClass = true;
        } else {
            JOptionPane.showMessageDialog(null, "启动失败，请检查网络连接状态后重试");
            System.exit(0);
        }
    }

    public static void CodeLogin() throws AWTException, IOException {
        //  PrismaSenseMain.Cracked();
        SystemUtils.displayTray("Prisma-CheckCode", "CodeLogin", TrayIcon.MessageType.WARNING);
        String AT = JOptionPane.showInputDialog(null, "请输入棱镜通行证", "请查看群公告获取验证码");
        final int R = 0;
        userCode = AT;

        try {
            if (userCode == null) {
                JOptionPane.showMessageDialog(null, "棱镜通行证不可为空的说!", "Prisma-CheckCode", 0);
                System.exit(0);
            } else {
                if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/PrismaSenseVersionAndCode.txt")
                        .contains(userCode)) {
                    JOptionPane.showMessageDialog(null, "令牌验证成功", "Checker", 1);
                    SystemUtils.displayTray("令牌验证成功", "正在启动", TrayIcon.MessageType.WARNING);
                    CodeLoginFinished = true;
                } else {
                    JOptionPane.showMessageDialog(null, "令牌验证失败", "Checker", 0);
                    JOptionPane.showInputDialog(null, "令牌验证码错误", "请查看群公告获取验证码");
                    SystemUtils.displayTray("您还未获取令牌验证码,请获取后重试！", "您还未获取令牌验证码,请获取后重试！", TrayIcon.MessageType.WARNING);
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "连接服务器失败,无效的登录会话");
            SystemUtils.displayTray("连接服务器失败,无效的登录会话", "连接服务器失败,无效的登录会话", TrayIcon.MessageType.WARNING);
            System.exit(0);
        }
    }
    public static void LoginCheck(){
        VarUtils.UnChangeClass = true;
        //逗比信使笑死我了
    }

    public static void sendWindowsMessageLogin() throws AWTException, IOException {
        String AT = JOptionPane.showInputDialog("Please enter ur QQ number");
        final int R = 0;
        number = AT;
        final int R2 = 0;
        Loading = true;
        try {
            if (number == null) {
                JOptionPane.showMessageDialog(null, "QQ号不能为空!", "Logoget", 0);
                System.exit(0);
            }
        } finally {

        }
    }

    public static void TimeDisplay() {

    }
}
