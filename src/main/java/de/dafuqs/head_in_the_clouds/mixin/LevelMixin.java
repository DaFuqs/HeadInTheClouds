package de.dafuqs.head_in_the_clouds.mixin;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.head_in_the_clouds.*;
import de.dafuqs.head_in_the_clouds.api.*;
import net.minecraft.client.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(Level.class)
public abstract class LevelMixin {
    
    @Inject(method = "precipitationAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/biome/Biome$Precipitation;", at = @At("HEAD"), cancellable = true)
    public void precipitationAt(BlockPos pos, CallbackInfoReturnable<Biome.Precipitation> cir) {
        if (!HeadInTheCloudsAPI.allowRainAtPos((Level)(Object)this, pos)) {
            cir.setReturnValue(Biome.Precipitation.NONE);
        }
    }
    
    @Shadow
    public abstract boolean isClientSide();
    
    @ModifyReturnValue(method = "getRainLevel", at = @At("RETURN"))
    public float headInTheClouds$getRainLevel(float original) {
        if(this.isClientSide()) {
            Entity e = Minecraft.getInstance().getCameraEntity();
            if (e != null) {
                return HeadInTheClouds.getRainGradient((Level) (Object) this, e.position(), original);
            }
        }
        return original;
    }
    
}
