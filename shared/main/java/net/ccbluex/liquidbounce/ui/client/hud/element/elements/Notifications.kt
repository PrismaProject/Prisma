package net.ccbluex.liquidbounce.ui.client.hud.element.elements

import me.Prisma.utils.EaseUtils
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.api.minecraft.client.gui.IFontRenderer
import net.ccbluex.liquidbounce.api.minecraft.util.IResourceLocation
import net.ccbluex.liquidbounce.ui.client.hud.element.Border
import net.ccbluex.liquidbounce.ui.client.hud.element.Element
import net.ccbluex.liquidbounce.ui.client.hud.element.ElementInfo
import net.ccbluex.liquidbounce.ui.client.hud.element.Side
import net.ccbluex.liquidbounce.ui.font.Fonts
import net.ccbluex.liquidbounce.utils.render.RenderUtils
import net.ccbluex.liquidbounce.value.FontValue
import net.ccbluex.liquidbounce.value.IntegerValue
import net.minecraft.util.ResourceLocation
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.*
import java.awt.Color
import kotlin.math.max

/**
 * CustomHUD Notification element
 */
@ElementInfo(name = "Notifications")
class Notifications(
    x: Double = 0.0,
    y: Double = 0.0,
    scale: Float = 1F,
    side: Side = Side(Side.Horizontal.RIGHT, Side.Vertical.DOWN)
) : Element(x, y, scale, side) {

    private val backGroundAlphaValue = IntegerValue("BackGroundAlpha", 170, 0, 255)
    private val fontValue = FontValue("Font", Fonts.font35)

    /**
     * Example notification for CustomHUD designer
     */
    private val exampleNotification = Notification(
        "This is an example notification.",
        NotifyType.INFO
    )
    /**
     * Draw element
     */
    override fun drawElement(): Border? {
        // bypass java.util.ConcurrentModificationException
        LiquidBounce.hud.notifications.map { it }.forEachIndexed { index, notify ->
            glPushMatrix()

            if (notify.drawNotification(index, fontValue.get(), backGroundAlphaValue.get(), 0f, this.renderX.toFloat(), this.renderY.toFloat(), scale)) {
                LiquidBounce.hud.notifications.remove(notify)
            }

            glPopMatrix()
        }

        if (classProvider.isGuiHudDesigner(mc.currentScreen)) {
            if (!LiquidBounce.hud.notifications.contains(exampleNotification)) {
                LiquidBounce.hud.addNotification(exampleNotification)
            }

            exampleNotification.fadeState = FadeState.STAY
            exampleNotification.displayTime = System.currentTimeMillis()
//            exampleNotification.x = exampleNotification.textLength + 8F

            return Border(-exampleNotification.width.toFloat(), -exampleNotification.height.toFloat(), 0F, 0F)
        }

        return null
    }
}

class Notification(
    val content: String,
    val type: NotifyType,
    val time: Int = 1500,
    val animeTime: Int = 500
) {
    var width = 105
    val height = 18
    val imageWidth = 16

    var fadeState = FadeState.IN
    var nowY = -height
    var displayTime = System.currentTimeMillis()
    var animeXTime = System.currentTimeMillis()
    var animeYTime = System.currentTimeMillis()

    /**
     * Draw notification
     */
    fun drawNotification(index: Int, font: IFontRenderer, alpha: Int, blurRadius: Float, x: Float, y: Float, scale: Float): Boolean {


        this.width = 100.coerceAtLeast((font.getStringWidth(this.content))+10 + 15)
        val realY = -(index+0) * height
        val nowTime = System.currentTimeMillis()
        var transY = nowY.toDouble()

        // Y-Axis Animation
        if (nowY != realY) {
            var pct = (nowTime - animeYTime) / animeTime.toDouble()
            if (pct> 1) {
                nowY = realY
                pct = 1.0
            } else {
                pct = EaseUtils.easeOutExpo(pct)
            }
            transY += (realY - nowY) * pct
        } else {
            animeYTime = nowTime
        }

        // X-Axis Animation
        var pct = (nowTime - animeXTime) / animeTime.toDouble()

        when (fadeState) {
            FadeState.IN -> {
                if (pct> 1) {
                    fadeState = FadeState.STAY
                    animeXTime = nowTime
                    pct = 1.0
                }
                pct = EaseUtils.easeOutExpo(pct)
            }

            FadeState.STAY -> {
                pct = 1.0
                if ((nowTime - animeXTime)> time) {
                    fadeState = FadeState.OUT
                    animeXTime = nowTime
                }
            }

            FadeState.OUT -> {
                if (pct> 1) {
                    fadeState = FadeState.END
                    animeXTime = nowTime
                    pct = 1.0
                }
                pct = 1 - EaseUtils.easeInExpo(pct)
            }

            FadeState.END -> {
                return true
            }
        }
        val color = Color.WHITE.rgb
        val fontHeight = Fonts.font40.fontHeight
        val transX = width - (width * pct) - width
        GL11.glTranslated(transX, transY, 0.0)

        //Draw Noti
        //BlurUtils.draw(0f,-1f,width.toFloat(),height.toFloat(),1f)
        glEnable(0x809D)
        glDisable(0x809D);

        RenderUtils.drawShadowWithCustomAlpha(0F, 0F, width.toFloat(), height.toFloat(), 200F)
        RenderUtils.drawRect(0F, height.toFloat(), max(width - width * ((nowTime - displayTime) / (animeTime * 3F + time)), 0F), height.toFloat() + 1f, Color(255,255,255, 0))
        //GL11.glTranslatef(-width.toFloat(), -height.toFloat(), 0F)
        //GL11.glTranslatef(width.toFloat(), height.toFloat(), 0F)
        Fonts.bold35.getGameFontRenderer().drawStringWithShadow2("Toggle", 6.0F, 0.5F,Color(183,183,183,200).rgb)
        Fonts.bold35.getGameFontRenderer().drawStringWithShadow2(content, 6.0F, 9.5F,Color(255,255,255,255).rgb)
        return false
    }
}


enum class NotifyType(var resourcepack: ResourceLocation) {
    SUCCESS(ResourceLocation("Prisma/notification/success.png")),
    ERROR(ResourceLocation("Prisma/notification/ERROR.png")),
    WARNING(ResourceLocation("Prisma/notification/WARNING.png")),
    INFO(ResourceLocation("Prisma/notification/INFO.png"));
}
enum class DrawNewColor(var renderColor: Color) {
    SUCCESS(Color(43,150,51,255)),
    ERROR(Color(173,31,31,255)),
    WARNING(Color(147,147,41,255)),
    INFO(Color(70,125,152,255));
}
enum class FadeState { IN, STAY, OUT, END }
