/*
 Copyright Alan Wood 2021
 None of this code to be reused without my written permission
 Intellectual Rights owned by Alan Wood
 */

package net.ccbluex.liquidbounce.features.module.modules.render

import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.value.BoolValue
import net.minecraft.entity.EntityLivingBase
import net.ccbluex.liquidbounce.event.AttackEvent
import net.ccbluex.liquidbounce.event.MotionEvent
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.minecraft.util.EnumParticleTypes
import net.minecraft.init.Blocks
import net.ccbluex.liquidbounce.value.ListValue
import net.minecraft.block.Block
import net.minecraft.entity.effect.EntityLightningBolt
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity

@ModuleInfo(name = "AttackEffects", description = "Rise.", category = ModuleCategory.RENDER)
class AttackEffects : Module() {
    val amount = IntegerValue("Amount", 5, 1, 10)
    private val sound = BoolValue("Sound", true)
    private val lightingSoundValue = BoolValue("LightingSound", true)

    private var target: EntityLivingBase? = null

    @EventTarget
    fun onAttack(event: AttackEvent) {
        if (event.targetEntity is EntityLivingBase) target = event.targetEntity
    }

    @EventTarget
    fun onMotion(event: MotionEvent) {
        if (event.isPre()) {
            if (target != null && target!!.hurtTime >= 9 && mc.thePlayer!!.getDistance(
                    target!!.posX,
                    target!!.posY,
                    target!!.posZ
                ) < 10
            ) {
                if (mc.thePlayer!!.ticksExisted > 3) {
                    when (mode.get().toLowerCase()) {
                        "blood" -> {
                            var i = 0
                            while (i < amount.value) {
                                mc2.world.spawnParticle(
                                    EnumParticleTypes.BLOCK_CRACK,
                                    target!!.posX,
                                    target!!.posY + target!!.height - 0.75,
                                    target!!.posZ,
                                    0.0,
                                    0.0,
                                    0.0,
                                    Block.getStateId(Blocks.REDSTONE_BLOCK.defaultState)
                                )
                                i++
                            }


                        }
                        "criticals" -> {
                            var i = 0
                            while (i < amount.value) {
                                mc2.effectRenderer.emitParticleAtEntity(target, EnumParticleTypes.CRIT)
                                i++
                            }
                        }
                        "magic" -> {
                            var i = 0
                            while (i < amount.value) {
                                mc2.effectRenderer.emitParticleAtEntity(target, EnumParticleTypes.CRIT_MAGIC)
                                i++
                            }
                        }
                        "lighting" -> {
                            mc.netHandler2.handleSpawnGlobalEntity(SPacketSpawnGlobalEntity(EntityLightningBolt(
                                mc2.world,
                                target!!.posX,
                                target!!.posY,
                                target!!.posZ,
                                target!!.preventEntitySpawning
                            )))
                            if(lightingSoundValue.get()) {
                                mc.soundHandler.playSound("random.explode", 0.5f )
                            }
                        }
                        "smoke" -> mc2.effectRenderer.emitParticleAtEntity(target, EnumParticleTypes.FLAME)
                        "water" -> mc2.effectRenderer.emitParticleAtEntity(target, EnumParticleTypes.WATER_DROP)
                        "heart" -> mc2.effectRenderer.emitParticleAtEntity(target, EnumParticleTypes.HEART)
                        "fire" -> mc2.effectRenderer.emitParticleAtEntity(target, EnumParticleTypes.LAVA)
                    }
                }
                target = null
            }
        }
    }




    companion object {
        val mode = ListValue(
            "Mode", arrayOf(
                "Blood",
                "Criticals",
                "Magic",
                "Lighting",
                "Fire",
                "Heart",
                "Water",
                "Smoke"
            ), "Blood"
        )
    }
}