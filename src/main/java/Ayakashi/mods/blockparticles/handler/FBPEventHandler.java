package Ayakashi.mods.blockparticles.handler;

import Ayakashi.mods.blockparticles.FBP;
import Ayakashi.mods.blockparticles.particle.FBPParticleDigging;
import Ayakashi.mods.blockparticles.particle.FBPParticleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FBPEventHandler {
    Minecraft mc = Minecraft.getMinecraft();

    public void onEntityJoinWorldEvent(Entity entity, World world) {
        if (entity == this.mc.thePlayer) {
            FBP.fancyEffectRenderer = new FBPParticleManager(world, this.mc.renderEngine, new FBPParticleDigging.Factory());
            if (FBP.originalEffectRenderer == null || FBP.originalEffectRenderer != this.mc.effectRenderer && FBP.originalEffectRenderer != FBP.fancyEffectRenderer) {
                FBP.originalEffectRenderer = this.mc.effectRenderer;
            }

            if (FBP.enabled) {
                this.mc.effectRenderer = FBP.fancyEffectRenderer;
            }
        }

    }
}
