package de.dafuqs.head_in_the_clouds.mixin;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.head_in_the_clouds.*;
import de.dafuqs.head_in_the_clouds.api.HeadInTheCloudsAPI;
import net.minecraft.client.*;
import net.minecraft.core.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public abstract class LevelMixin {
    
    @Inject(method = "precipitationAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/biome/Biome$Precipitation;", at = @At("HEAD"), cancellable = true)
    public void precipitationAt(BlockPos pos, CallbackInfoReturnable<Biome.Precipitation> cir) {
        if (!HeadInTheCloudsAPI.allowRainAtPos((Level)(Object)this, pos)) {
            cir.setReturnValue(Biome.Precipitation.NONE);
        }
    }
}
