
package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.ListValue

import net.minecraft.util.ResourceLocation

@ModuleInfo(name = "Cape", description = "LiquidBounce+ capes.", category = ModuleCategory.RENDER)
class Cape : Module() {

    val styleValue = ListValue("Style", arrayOf("Dark", "Astolfo", "Sunny", "Target", "Wyy", "PowerX", "Azrael", "Flux", "LiquidBounce", "Light", "Novoline", "Special1", "Special2"), "Dark")

    fun getCapeLocation(value: String): ResourceLocation {
        return try {
            CapeStyle.valueOf(value.toUpperCase()).location
        } catch (e: IllegalArgumentException) {
            CapeStyle.DARK.location
        }
    }

    enum class CapeStyle(val location: ResourceLocation) {
        DARK(ResourceLocation("Prisma/capes/dark.png")),
        ASTOLFO(ResourceLocation("Prisma/capes/astolfo.png")),
        LIGHT(ResourceLocation("Prisma/capes/light.png")),
        SUNNY(ResourceLocation("Prisma/capes/Sunny.png")),
        WYY(ResourceLocation("Prisma/capes/Wyy.png")),
        POWERX(ResourceLocation("Prisma/capes/PowerX.png")),
        AZRAEL(ResourceLocation("Prisma/capes/azrael.png")),
        TARGET(ResourceLocation("Prisma/capes/Target.png")),
        FLUX(ResourceLocation("Prisma/capes/Flux.png")),
        LIQUIDBOUNCE(ResourceLocation("Prisma/capes/LiquidBounce.png")),
        NOVOLINE(ResourceLocation("Prisma/capes/Novoline.png")),
        SPECIAL1(ResourceLocation("Prisma/capes/special1.png")),
        SPECIAL2(ResourceLocation("Prisma/capes/special2.png"))
    }

    override val tag: String
        get() = styleValue.get()

}