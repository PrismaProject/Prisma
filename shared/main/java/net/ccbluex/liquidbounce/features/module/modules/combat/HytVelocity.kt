package net.ccbluex.liquidbounce.features.module.modules.combat

import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.minecraft.network.play.server.SPacketPlayerPosLook

@ModuleInfo(name = "HytVelocity",description = "By K0itoyuu",category = ModuleCategory.COMBAT)
class HytVelocity:Module() {
    private var canCancel = false
    @EventTarget
    fun onPacket(event: PacketEvent) {
        if (classProvider.isSPacketEntityVelocity(event.packet)) {
            event.cancelEvent()
            canCancel = true
        }
        //A
        if (event.packet is SPacketPlayerPosLook) {
            val packet = event.packet
            event.cancelEvent()
            mc.netHandler.addToSendQueue(
                classProvider.createCPacketPlayerPosLook(packet.x,packet.y,packet.z,packet.yaw,packet.pitch,mc.thePlayer!!.onGround)
            )
            canCancel = false
        }
        //A不管用再用这个试试
        //B
        /*if (classProvider.isSPacketPlayerPosLook(event.packet) && canCancel) {
            val packet = event.packet.asSPacketPosLook()
            event.cancelEvent()
            mc.netHandler.addToSendQueue(classProvider.createCPacketPlayerLook(packet.yaw,packet.pitch,mc.thePlayer!!.onGround))
            canCancel = false
        }*/
    }
}