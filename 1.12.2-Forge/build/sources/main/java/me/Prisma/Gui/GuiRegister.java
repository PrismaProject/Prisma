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


import me.Prisma.utils.HWIDChecker;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.ui.font.FontLoaders;
import net.ccbluex.liquidbounce.utils.FontManager;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.client.gui.*;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.client.Minecraft;

public class GuiRegister extends GuiScreen {
    int alpha = 0;
    final static Base64.Encoder encoder = Base64.getEncoder();
    final static Base64.Decoder decoder = Base64.getDecoder();
    private boolean i = false;
    private boolean j = false;
    private boolean drag = false;
    public static GuiPasswordField password;
    public static GuiUserField username;
    public static GuiUserField Key;
    public static GuiUserField qqid;
    public static boolean render = false;
    private float currentX;
    private float currentY;
    public static boolean Passed = false;
    public static String process = "[Waiting For Login...]";

    @Override
    public void drawScreen(int var1, int var2, float var3) {
        if (i) {
            if (this.alpha < 255) {
                alpha += 5;
            }
        }
        int h = new ScaledResolution(this.mc).getScaledHeight();
        int w = new ScaledResolution(this.mc).getScaledWidth();
        RenderUtils.drawGradientSideways(0, 0, w, h, new Color(60, 96, 203).getRGB(), new Color(51, 201, 217).getRGB());
        ScaledResolution sr = new ScaledResolution(this.mc);
        float xDiff = ((float) (var1 - h / 2) - this.currentX) / (float) sr.getScaleFactor();
        float yDiff = ((float) (var2 - w / 2) - this.currentY) / (float) sr.getScaleFactor();
        this.currentX += xDiff * 0.3f;
        this.currentY += yDiff * 0.3f;
        final ScaledResolution s1 = new ScaledResolution(this.mc);
        ScaledResolution res = new ScaledResolution(this.mc);
        if (!Mouse.isButtonDown(0)) {
            drag = false;
        } else
            drag = true;
        if (Mouse.isButtonDown(0)
                && var1 > this.width / 2 + 65
                && var1 < this.width / 2 + 105
                && var2 > this.height / 2 - 61
                && var2 < this.height / 2 - 55
        ) {
            //返回
//            FontManager.Chinese16.drawString("登录", this.width / 2 +25, this.height / 2 - 61, new Color(100,177,253).getRGB());
            Minecraft.getMinecraft().displayGuiScreen(new GuiLogin());
        }
        if (!username.getText().isEmpty() && !password.getText().isEmpty() && !password.getText().isEmpty() && !Key.getText().isEmpty() && !qqid.getText().isEmpty() && Mouse.isButtonDown(0) && drag
                && var1 > this.width / 2 + 30
                && var1 < this.width / 2 + 155
                && var2 > this.height / 2 + 47
                && var2 < this.height / 2 + 70
        ) {
            if (Register(username.getText(),md5(convertMD5(md5(password.getText()))),qqid.getText(),  Key.getText())) {
                i = true;
                j = true;
                Minecraft.getMinecraft().displayGuiScreen(new GuiLogin());
            } else {
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
                new Color(255, 255, 255).getRGB());
        RenderUtils.drawGradientSideways(
                this.width / 2 + 30,
                this.height / 2 + 47,
                this.width / 2 + 155,
                this.height / 2 + 70,
                new Color(45, 119, 200).getRGB(),
                new Color(41, 144, 208).getRGB());
//        RenderUtils.drawGradientSideways(
//                this.width / 2 + 30,
//                this.height / 2 + 70 + 5,
//                this.width / 2 + 155,
//                this.height / 2 + 93 + 5,
//                new Color(45,119,200).getRGB(),
//                new Color(41,144,208).getRGB());
        /*
                if(password.getText().isEmpty() && !password.isFocused())
            FontLoaders.xyz16.drawString("Password", this.width / 2 + 35,  this.height / 2 - 30 - 20 + 20+8,new Color(100,100,100).getRGB());
        if(Key.getText().isEmpty() && !Key.isFocused())
            FontLoaders.xyz16.drawString("HWID", this.width / 2 + 35, this.height /2 - 30 - 20 + 20 + 20+8,new Color(100,100,100).getRGB());
        if(qqid.getText().isEmpty() && !qqid.isFocused())
            FontLoaders.xyz16.drawString("QQ", this.width / 2 + 35, this.height /2 - 30 - 20 + 20 + 20 + 20 +8,new Color(100,100,100).getRGB());
        if(username.getText().isEmpty() && !username.isFocused())
            FontLoaders.xyz16.drawString("Username", this.width / 2 + 35, this.height / 2 - 30 - 20+8,new Color(100,100,100).getRGB());
         */

        RenderUtils.drawRect(this.width / 2 + 30,
                this.height / 2 - 30 - 20 + 8 + 6,
                this.width / 2 + 155,
                this.height / 2 - 30 - 20 + 8 + 1 + 6,
                new Color(100, 100, 100, 50).getRGB());

        RenderUtils.drawRect(this.width / 2 + 30,
                this.height / 2 - 30 - 20 + 20 + 8 + 6,
                this.width / 2 + 155,
                this.height / 2 - 30 - 20 + 20 + 8 + 1 + 6,
                new Color(100, 100, 100, 50).getRGB());

        RenderUtils.drawRect(this.width / 2 + 30,
                this.height / 2 - 30 - 20 + 20 + 20 + 8 + 6,
                this.width / 2 + 155,
                this.height / 2 - 30 - 20 + 20 + 20 + 8 + 1 + 6,
                new Color(100, 100, 100, 50).getRGB());

        RenderUtils.drawRect(this.width / 2 + 30,
                this.height / 2 - 30 - 20 + 20 + 20 + 20 + 8 + 6,
                this.width / 2 + 155,
                this.height / 2 - 30 - 20 + 20 + 20 + 20 + 8 + 1 + 6,
                new Color(100, 100, 100, 50).getRGB());


        RenderUtils.drawImage((IResourceLocation) new ResourceLocation("liquidbounce/image.jpg"), this.width / 2 - 150 + 20, this.height / 2 - 100 - 2, 1080 / 10, 1602 / 10);

        FontLoaders.xyz26.drawString("Register", this.width / 2 + 10, this.height / 2 - 75, new Color(0, 0, 0, 0).getRGB());

        FontLoaders.xyz20.drawString("Register", this.width / 2 + 75, this.height / 2 + 55, -1);

//        FontLoaders.xyz20.drawString("Back", this.width / 2 - 170, this.height / 2 -110,new Color(150,150,150).getRGB());
//

//
//        FontLoaders.xyz16.drawString("check your identity.", this.width / 2 +10, this.height / 2 - 53, new Color(100,100,100).getRGB());


        FontLoaders.xyz28.drawString("Welcome", this.width / 2 - 150, this.height / 2 + 40 + 20, new Color(0, 0, 0, 0).getRGB());


        FontLoaders.xyz16.drawString("PrismaSense", this.width / 2 - 150, this.height / 2 + 53 + 20, new Color(100, 100, 100).getRGB());
        username.drawTextBox2();
        Key.drawTextBox2();
        qqid.drawTextBox2();
        FontLoaders.xyz16.drawString("code by PrismaTeams", this.width / 2 - 150, this.height / 2 + 53 + 28, new Color(100, 100, 100).getRGB());
        password.drawTextBox2();
        FontManager.Chinese16.drawString("已有账号？现在", this.width / 2 + 10, this.height / 2 - 59, new Color(100, 100, 100).getRGB());

        FontManager.Chinese16.drawString("登录", this.width / 2 + 66, this.height / 2 - 59, new Color(100, 177, 253).getRGB());
        if (password.getText().isEmpty() && !password.isFocused())
            FontManager.Chinese16.drawString("密码", this.width / 2 + 35, this.height / 2 - 30 - 20 + 20 + 8, new Color(100, 100, 100).getRGB());
        if (Key.getText().isEmpty() && !Key.isFocused())
            FontManager.Chinese16.drawString("密钥", this.width / 2 + 35, this.height / 2 - 30 - 20 + 20 + 20 + 8, new Color(100, 100, 100).getRGB());
        if (qqid.getText().isEmpty() && !qqid.isFocused())
            FontLoaders.xyz16.drawString("QQ", this.width / 2 + 35, this.height / 2 - 30 - 20 + 20 + 20 + 20 + 8, new Color(100, 100, 100).getRGB());
        if (username.getText().isEmpty() && !username.isFocused())
            FontManager.Chinese16.drawString("用户", this.width / 2 + 35, this.height / 2 - 30 - 20 + 8, new Color(100, 100, 100).getRGB());

//        var5.drawStringWithShadow("PassWord", this.width / 2 - 100 +5, this.height / 2 - 3, new Color(200,200,200,200).getRGB());


        //       var4.drawStringWithShadow(Now, this.width / 2 - var4.getStringWidth(Now) / 2, this.height / 2 - 75, color);
        super.drawScreen(var1, var2, var3);
//        var6.drawStringWithShadow("Login", this.width / 2 - 52 - var6.getStringWidth("Login") / 2, this.height / 2 + 30, new Color(255,255,255).getRGB());
//        var6.drawStringWithShadow("Exit", this.width / 2 + 52 - var6.getStringWidth("Exit") / 2, this.height / 2 + 30, new Color(255,255,255).getRGB());
//        var5.drawStringWithShadow(Client.name + " " + Client.version + " | Code by " + Client.dev, 2, this.height - 2 - var5.getStringHeight(Client.name + " " + Client.version + " | Code by " + Client.dev), -1);
        Gui.drawRect(0, 0, RenderUtils.width(), RenderUtils.height(), new Color(0, 0, 0, alpha).getRGB());
        if (alpha >= 255 && i && j) {
            i = false;
            j = false;

        }

    }

    @Override
    public void initGui() {
        FontRenderer var1 = this.mc.fontRenderer;
        int var2 = this.height / 2;
        super.initGui();

        render = true;

        username = new GuiUserField(var1, this.width / 2 + 30, this.height / 2 - 30 - 20, 125, 20);
        password = new GuiPasswordField(var1, this.width / 2 + 30, this.height / 2 - 30 - 20 + 20, 125, 20);
        Key = new GuiUserField(var1, this.width / 2 + 30, this.height / 2 - 30 - 20 + 20 + 20, 125, 20);
        qqid = new GuiUserField(var1, this.width / 2 + 30, this.height / 2 - 30 - 20 + 20 + 20 + 20, 125, 20);
//        Keyboard.enableRepeatEvents(true);
    }

    @Override
    protected void keyTyped(char var1, int var2) {
        try {
            super.keyTyped(var1, var2);
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        if (var1 == 9) {
//            if (!username.isFocused()) {
//                username.setFocused(true);
//            } else {
//                username.setFocused(username.isFocused());
//                password.setFocused(!username.isFocused());
//                Key.setFocused(!username.isFocused());
//                qqid.setFocused(!username.isFocused());
//            }

        }


        if (var1 == 27) {

        }

        this.username.textboxKeyTyped(var1, var2);
        this.password.textboxKeyTyped(var1, var2);
        this.Key.textboxKeyTyped(var1, var2);
        this.qqid.textboxKeyTyped(var1, var2);
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
        this.Key.mouseClicked(var1, var2, var3);
        this.qqid.mouseClicked(var1, var2, var3);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void updateScreen() {
        this.username.updateCursorCounter();
        this.password.updateCursorCounter();
        this.Key.updateCursorCounter();
        this.qqid.updateCursorCounter();
    }

    public static boolean Register(String usernames, String passwords, String QQ, String Key) {
        process = "[Logging in...]";
        try {
            Socket socket = new Socket("124.220.10.237", 19730);
            String UserInfo = "#PrismaBounce" + "#TYPE[REG]" + "#ACCOUNT[" + usernames + "]" + "#PASSWORD[" + passwords + "]" + "#HWID[" + HWIDChecker.getCpuId() + "]" + "#KEY[" + Key + "]" +"#QQ"+"[" + QQ + "]";
            OutputStream out = socket.getOutputStream();
            out.write(encode(UserInfo).getBytes());
            InputStream in = socket.getInputStream();

            byte[] data = new byte[1024];
            int len = in.read(data);
            String SeverInfo = decode(new String(data, 0, len));
            System.out.println(decode(new String(data, 0, len)));
            socket.close();
            if (SeverInfo.contains("successful")) {
                return true;
            }
            else {
                return false;
            }
        } catch (Exception exception) {
            System.err.println("Error");
            return false;
        }
    }
    public static String md5(String text){
        byte[] secretBytes = null;
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

    /**
     * 将加密后的字符串进行解密
     * @param encodedText
     * @return
     */
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




