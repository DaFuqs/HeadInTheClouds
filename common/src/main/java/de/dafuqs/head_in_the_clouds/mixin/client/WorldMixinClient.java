package de.dafuqs.head_in_the_clouds.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import de.dafuqs.head_in_the_clouds.HeadInTheClouds;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(World.class)
public abstract class WorldMixinClient {

    @ModifyReturnValue(method = "getRainGradient(F)F", at = @At("RETURN"))
    public float getRainGradientCamera(float original, float tickProgress) {
        if (original > 0) {
            Entity e = MinecraftClient.getInstance().cameraEntity;
            if (e != null)
                return HeadInTheClouds.getRainGradient((World)(Object)this, e.getPos().y, original);
        }
        return original;
    }
}
