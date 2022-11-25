/*
 * Decompiled with CFR 0_132.
 */
package net.ccbluex.liquidbounce.ui.font;

import java.awt.Font;
import java.io.InputStream;


import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import scala.tools.nsc.doc.model.Public;

public abstract class FontLoaders {


    public static CFontRenderer kiona34 = new CFontRenderer(FontLoaders.getKiona(22), true, true);
     static CFontRenderer kiona22 = new CFontRenderer(FontLoaders.getKiona(22), true, true);
    public static CFontRenderer kiona26 = new CFontRenderer(FontLoaders.getKiona(26), true, true);
    public static CFontRenderer kiona48 = new CFontRenderer(FontLoaders.getKiona(48), true, true);
    public static CFontRenderer GoogleSans24 = new CFontRenderer(FontLoaders.getGoogleSans(24), true, true);
    public static CFontRenderer GoogleSans36 = new CFontRenderer(FontLoaders.getGoogleSans(36), true, true);
    public static CFontRenderer GoogleSans55 = new CFontRenderer(FontLoaders.getGoogleSans(55), true, true);
    public static CFontRenderer GoogleSans18 = new CFontRenderer(FontLoaders.getGoogleSans(18), true, true);
    public static CFontRenderer GoogleSans12 = new CFontRenderer(FontLoaders.getGoogleSans(12), true, true);
    public static CFontRenderer GoogleSans14 = new CFontRenderer(FontLoaders.getGoogleSans(14), true, true);
    public static CFontRenderer xyz16 = new CFontRenderer(FontLoaders.getxyz(16), true, true);
    public static CFontRenderer xyz18 = new CFontRenderer(FontLoaders.getxyz(18), true, true);
    public static CFontRenderer calibrilite65 = new CFontRenderer(FontLoaders.getcalibrilite(65), true, true);
    public static CFontRenderer xyz20 = new CFontRenderer(FontLoaders.getxyz(20), true, true);
    public static CFontRenderer xyz28 = new CFontRenderer(FontLoaders.getxyz(28), true, true);
    public static CFontRenderer xyz26 = new CFontRenderer(FontLoaders.getxyz(26), true, true);
    public static CFontRenderer NovICON44 = new CFontRenderer(FontLoaders.getNovICON(44), true, true);
    public static CFontRenderer NovICON10 = new CFontRenderer(FontLoaders.getNovICON(10), true, true);
    private static Font getNovICON(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/fonts/NovICON.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }

    private static Font getGoogleSans(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/fonts/GoogleSans.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getxyz(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/fonts/Arial.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getKiona(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager()
                    .getResource(new ResourceLocation("liquidbounce/fonts/Kiona.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    private static Font getcalibrilite(int size) {
        Font font;
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("liquidbounce/fonts/calibrilites.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, size);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
}