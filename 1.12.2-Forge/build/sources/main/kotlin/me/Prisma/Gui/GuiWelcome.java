package me.Prisma.Gui;

import java.awt.Color;
import java.io.IOException;


import net.ccbluex.liquidbounce.LiquidBounce;
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation;
import net.ccbluex.liquidbounce.injection.backend.GuiScreenImplKt;
import net.ccbluex.liquidbounce.ui.client.GuiMainMenu;
import net.ccbluex.liquidbounce.ui.font.CFontRenderer;
import net.ccbluex.liquidbounce.ui.font.FontLoaders;
import net.ccbluex.liquidbounce.utils.render.ParticleUtils;
import net.ccbluex.liquidbounce.utils.render.RenderUtils;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;


import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class GuiWelcome
        extends GuiScreen {
    private static long startTime;
    int alpha = 255;
    private float currentX;
    private float currentY;
    double Anitext = 20.0;
    private boolean i = false;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (startTime == 0L) {
            startTime = System.currentTimeMillis();
        }

        int h = new ScaledResolution(this.mc).getScaledHeight();
        int w = new ScaledResolution(this.mc).getScaledWidth();
        ScaledResolution sr = new ScaledResolution(this.mc);
        float xDiff = ((float)(mouseX - h / 2) - this.currentX) / (float)sr.getScaleFactor();
        float yDiff = ((float)(mouseY - w / 2) - this.currentY) / (float)sr.getScaleFactor();
        this.currentX += xDiff * 0.3f;
        this.currentY += yDiff * 0.3f;
        CFontRenderer fontwel2 = FontLoaders.GoogleSans24;
        CFontRenderer fontwel = FontLoaders.GoogleSans36;

        GlStateManager.translate(this.currentX / 100.0f, this.currentY / 100.0f, 0.0f);
        RenderUtils.drawImage((IResourceLocation) new ResourceLocation("liquidbounce/welcome.png"), -1, -1, sr.getScaledWidth() + 1, sr.getScaledHeight() + 1);

        final ScaledResolution scaledResolution = new ScaledResolution(mc);
        final int width = scaledResolution.getScaledWidth();
        final int height = scaledResolution.getScaledHeight();
        ParticleUtils.drawParticles(Mouse.getX() * width / mc.displayWidth, height - Mouse.getY() * height / mc.displayHeight - 1);

        GlStateManager.translate(this.currentX / 15.0f, this.currentY / 15.0f, 0.0f);
        GlStateManager.translate(-this.currentX / 100.0f, -this.currentY / 100.0f, 0.0f);
        FontLoaders.kiona34.drawCenteredString("Welcome to " + "PrismaSense"+" "+ LiquidBounce.User, (float)sr.getScaledWidth() / 2.0f, (float)sr.getScaledHeight() / 2.0f - 3.0f - (float)this.Anitext, new Color(255, 255, 255).getRGB());
        FontLoaders.kiona34.drawCenteredString("Click to continue", (float)sr.getScaledWidth() / 2.0f, (float)sr.getScaledHeight() / 2.0f + (float)fontwel.getStringHeight("Click to continue") + 15 - (float)this.Anitext, new Color(255, 255, 255).getRGB());

        GlStateManager.translate(-this.currentX / 15.0f, -this.currentY / 15.0f, 0.0f);
        Gui.drawRect(0, 0, RenderUtils.width(), RenderUtils.height(), new Color(0,0,0,alpha).getRGB());
        if(i) {
            if(this.alpha < 255) {
                alpha += 10;
            }
            if (this.alpha >= 255) {
                GuiScreenImplKt.unwrap(LiquidBounce.wrapper.getClassProvider().wrapGuiScreen(new GuiMainMenu()));
            }
        }else {
            if (this.alpha > 0 && startTime + 2000L <= System.currentTimeMillis()) {
                this.alpha -= 5;
            }
            if (this.alpha <= 0 && startTime + 10000L <= System.currentTimeMillis()) {
               // this.mc.displayGuiScreen(new end.Prisma.Gui.GuiMainMenu());
                i = true;
            }
        }
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
     GuiLogin guiLogin = new GuiLogin();
        if (guiLogin.drag){
            i = true;
        }
    }
    @Override
    public void initGui() {
    }
    static {
        startTime = 0L;
    }

}

