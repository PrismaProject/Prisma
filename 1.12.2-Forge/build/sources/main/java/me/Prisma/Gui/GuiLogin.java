package me.Prisma.Gui;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


import me.Prisma.utils.GamingTime;
import me.Prisma.utils.HWIDChecker;
import me.Prisma.utils.Main.VersionCrack;
import me.Prisma.utils.Main.WebUtils;
import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.ui.font.FontLoaders;
import net.ccbluex.liquidbounce.utils.FontManager;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;

import javax.swing.*;


public class GuiLogin  extends GuiScreen  {
    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();
    int alpha = 0;
    private boolean i = false;
    private boolean j = false;
    public static GuiPasswordField password;
    public static GuiUserField username;
    public static  boolean render = false;
    private float currentX;
    private float currentY;
    public boolean drag = false;
    public static boolean Passed = false;
    public static boolean UnDisCheck = false;
    public static String process = "[Waiting For Login...]";
    public static String CheckMode = "-GiteeCodeLogin";
    @Override
    public void drawScreen(int var1, int var2, float var3) {
        if(i) {
            if(this.alpha < 255) {
                alpha += 5;
            }
        }
        int h = new ScaledResolution(this.mc).getScaledHeight();
        int w = new ScaledResolution(this.mc).getScaledWidth();
        RenderUtils.drawGradientSideways(0, 0, w, h, new Color(60,96,203).getRGB(), new Color(51,201,217).getRGB());
        ScaledResolution sr = new ScaledResolution(this.mc);
        float xDiff = ((float)(var1 - h / 2) - this.currentX) / (float)sr.getScaleFactor();
        float yDiff = ((float)(var2 - w / 2) - this.currentY) / (float)sr.getScaleFactor();
        this.currentX += xDiff * 0.3f;
        this.currentY += yDiff * 0.3f;
        if (!Mouse.isButtonDown(0)) {
            drag = false;
        }else
            drag = true;
        if (Mouse.isButtonDown(0)
                && var1 > this.width / 2 + 120
                && var1 < this.width / 2 + 155
                && var2 > this.height / 2 + 75
                && var2 < this.height / 2 + 82
        ){
            Minecraft.getMinecraft().displayGuiScreen(new GuiRegister());
        }
        if (!username.getText().isEmpty() && !password.getText().isEmpty() && Mouse.isButtonDown(0) && drag
                && var1 > this.width / 2 + 30
                && var1 < this.width / 2 + 155
                && var2 > this.height / 2 + 47
                && var2 < this.height / 2 + 70
        ) {
            LiquidBounce.INSTANCE.setUsername(username.getText());
            LiquidBounce.INSTANCE.setPassword(password.getText());
            String passwords = password.getText();
            if (Login(username.getText(),md5(convertMD5(md5(passwords))))){
                i = true;
                j = true;
                Minecraft.getMinecraft().displayGuiScreen(new GuiWait());
                LiquidBounce.User = username.getText();
            }else {
                i = true;
                j = true;
                i = true;
                j = false;
                Runtime.getRuntime().exit(0);
                Runtime.getRuntime().exit(0);
                Runtime.getRuntime().exit(0);
                Runtime.getRuntime().exit(0);
                Runtime.getRuntime().exit(0);
                Runtime.getRuntime().exit(0);
            }
        }


        RenderUtils.drawFastRoundedRect(this.width / 2 - 180,
                this.height / 2 - 115,
                this.width / 2 + 180,
                this.height / 2 + 115,
                3,
                new Color(255,255,255).getRGB());
        RenderUtils.drawGradientSideways(
                this.width / 2 + 30,
                this.height / 2 + 47,
                this.width / 2 + 155,
                this.height / 2 + 70,
                new Color(45,119,200).getRGB(),
                new Color(41,144,208).getRGB());


        RenderUtils.drawRect(this.width / 2 + 30,
                this.height / 2  - 9,
                this.width / 2+ 155,
                this.height / 2 - 8,
                new Color(100,100,100,50).getRGB());

        RenderUtils.drawRect(this.width / 2 + 30,
                this.height / 2  + 30,
                this.width / 2+ 155,
                this.height / 2 + 31,
                new Color(100,100,100,50).getRGB());




        RenderUtils.drawImage((IResourceLocation) new ResourceLocation("liquidbounce/image.jpg"),this.width / 2 - 150 +20, this.height / 2 - 100 - 2, 1080/10, 1602/10);

        FontLoaders.xyz26.drawString("Login", this.width / 2 +10, this.height / 2 - 75, new Color(0,0,0,0).getRGB());

        FontLoaders.xyz20.drawString("Login", this.width / 2 +80, this.height / 2 + 55,-1);

        FontManager.Chinese16.drawString("没有账号？", this.width / 2 +85, this.height / 2 + 53+ 25,new Color(200,200,200).getRGB());

        FontManager.Chinese16.drawString("立即注册", this.width / 2 + 125, this.height / 2 + 53+ 25,new Color(100,177,253).getRGB());

        FontLoaders.xyz16.drawString("Log in to your account so that we can", this.width / 2 +10, this.height / 2 - 61, new Color(100,100,100).getRGB());

        FontLoaders.xyz16.drawString("check your identity.", this.width / 2 +10, this.height / 2 - 53, new Color(100,100,100).getRGB());


        FontLoaders.xyz28.drawString("Welcome", this.width / 2 - 150, this.height / 2 + 40 + 20, new Color(0,0,0,0).getRGB());


        FontLoaders.xyz16.drawString("PrismaSense", this.width / 2 - 150, this.height / 2 + 53+ 20,  new Color(100,100,100).getRGB());
        username.drawTextBox2();
        FontLoaders.xyz16.drawString("code by Prisma Teams", this.width / 2 - 150, this.height / 2 + 53 + 28,  new Color(100,100,100).getRGB());
        password.drawTextBox2();
        if(password.getText().isEmpty() && !password.isFocused())
            FontManager.Chinese16.drawString("密码", this.width / 2 + 35, this.height / 2 + 20,new Color(100,100,100).getRGB());
        if(username.getText().isEmpty() && !username.isFocused())
            FontManager.Chinese16.drawString("账号", this.width / 2 + 35, this.height / 2 - 19,new Color(100,100,100).getRGB());




        super.drawScreen(var1, var2, var3);
        Gui.drawRect(0, 0, RenderUtils.width(), RenderUtils.height(), new Color(0,0,0,alpha).getRGB());
        if(alpha >= 255 && i && j) {
            i = false;
            j = false;

        }

    }

    @Override
    public void initGui() {
        FontRenderer var1 = this.mc.fontRenderer;
        super.initGui();
        render = true;
        username = new GuiUserField(var1, this.width / 2 + 30, this.height / 2 - 30, 125, 20);
        password = new GuiPasswordField(var1, this.width / 2 + 30, this.height / 2 + 10, 125, 20);
    }

    @Override
    protected void keyTyped(char var1, int var2) {

        if (var1 == 9) {
            if (!username.isFocused()) {
                username.setFocused(true);
            } else {
                username.setFocused(username.isFocused());
                password.setFocused(!username.isFocused());
            }
        }


        if (var1 == 27) {

        }

        this.username.textboxKeyTyped(var1, var2);
        this.password.textboxKeyTyped(var1, var2);
    }

    @Override
    protected void mouseClicked(int var1, int var2, int var3) {
        try {
            super.mouseClicked(var1, var2, var3);
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        this.username.mouseClicked(var1, var2, var3);
        this.password.mouseClicked(var1, var2, var3);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        this.username.updateCursorCounter();
        this.password.updateCursorCounter();
    }
    public static boolean Login(String usernames, String passwords){
        process = "[Logging in...]";
        try {
            UnDisCheck = true;
            if(CheckMode!= VersionCrack.StartCode) {
                Socket socket = new Socket("124.220.10.237", 19730);
                String UserInfo =
                        "#PrismaBounce" +
                                "#TYPE[LOGIN]" +
                                "#ACCOUNT[" + usernames + "]" +
                                "#PASSWORD[" + passwords + "]" +
                                "#HWID[" + HWIDChecker.getCpuId() + "]" +
                                "#TIME" + "[" + HWIDChecker.getSubString(HWIDChecker.get("http://vv6.video.qq.com/checktime"), "<root><s>o</s><t>", "</t><ip>") + "]";
                OutputStream out = socket.getOutputStream();
                out.write(encode(UserInfo).getBytes());
                InputStream in = socket.getInputStream();
                byte[] data = new byte[1024];
                int len = in.read(data);
                String SeverInfo = decode(new String(data, 0, len));
                System.out.println(decode(new String(data, 0, len)));

                if (SeverInfo.contains("successful")) {
                    Display.setTitle(LiquidBounce.CLIENT_NAME + "鸢飞戾天者，望峰息心"  + " | 时间已流失"+ GamingTime.timeText + "Code By" + LiquidBounce.CLIENT_CREATOR +"| Thank you for use"+ LiquidBounce.User);
                    VersionCrack.sendWindowsMessageLogin();
                    return true;
                } else {
                    process = "[Failed.]";
                    VersionCrack.sendWindowsMessageLogin();
                    return true;
                }
            }else{
                if (WebUtils.get("https://gitee.com/NatsuCN/prisma-sense/raw/master/PrismaSenseVersionAndCode.txt")
                        .contains("Pass")) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "抱歉,第二验证通道已关闭", "Prisma-Protection", 1);
                    process = "[Failed.]";
                    return true;
                }
            }
        } catch (Exception exception) {
            System.err.println("Error");
            return true;
        }
    }

    public static String md5(String text){
        byte[] secretBytes;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(
                    text.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("md5 errror");
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
    public static String convertMD5(String inStr){
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
    public static String encode(String text) {
        byte[] textByte = new byte[0];
        try {
            textByte = text.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String encodedText = encoder.encodeToString(textByte);
        return encodedText;
    }

    public static String decode(String encodedText) {
        String text = null;
        try {
            text = new String(decoder.decode(encodedText), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return text;
    }
}




