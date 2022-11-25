package net.ccbluex.liquidbounce.features.module.modules.render

import me.Prisma.Main.WbxMain
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.UpdateEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.value.TextValue

@ModuleInfo(name = "Title", description = "Title", category = ModuleCategory.MISC)
class Title : Module(){
    private var S = 0
    private var HM = 0
    private var M = 0
    private var H = 0

    @EventTarget
    fun onUpdate(event: UpdateEvent){
        HM += 1
        if (HM ==20){
            S = S + 1
            HM = 0
        }
        if (S ==60){
            M = M +1
            S = 0
        }
        if (M==60){
            H = H+1
            M = 0
        }
        org.lwjgl.opengl.Display.setTitle(WbxMain.Name + "-㭤" + WbxMain.version + "丨调查员： " + WbxMain.username + "[" + WbxMain.rank + "]丨鸢飞戾天者，望峰息心 | 开始完成新的任务叭~")
    }
}