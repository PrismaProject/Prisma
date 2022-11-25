package me.Prisma.Gui;


import net.ccbluex.liquidbounce.ui.font.FontLoaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;

public class GuiWait extends GuiScreen {
    private static long startTime;
    private GuiScreen screen;
    int alpha;
    long waitTimeeee;
    long waitTimeeee2;
    public GuiWait(GuiScreen Screen,long waitTime,long waitTime2) {
        this.alpha = 1;
        this.screen = Screen;
        this.waitTimeeee = waitTime;
        this.waitTimeeee2 = waitTime2;
    }

    public GuiWait() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution res = new ScaledResolution(this.mc);
        Gui.drawRect(0,0,res.getScaledWidth(),res.getScaledHeight(),new Color(0,0,0,255).getRGB());
        if (startTime == 0L) {
            startTime = System.currentTimeMillis();
        }
        FontLoaders.GoogleSans36.drawString("Please Wait...",(float) res.getScaledWidth_double() / 2 - 50,(float) res.getScaledHeight_double() / 2,new Color(244,255,244,this.alpha).getRGB());
        if (startTime + waitTimeeee + waitTimeeee2 <= System.currentTimeMillis()) { //2000
            if (this.alpha != 255) {
                ++this.alpha;
            }
            if (this.alpha != 255) {
                ++this.alpha;
            }
            if (this.alpha != 255) {
                ++this.alpha;
            }
        }
        if (this.alpha >= 255 && startTime + waitTimeeee2 + waitTimeeee <= System.currentTimeMillis()) {//3000
            Minecraft.getMinecraft().displayGuiScreen(new GuiWelcome());
        }
    }
    static {
        startTime = 0L;
    }
}
