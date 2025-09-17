package de.dafuqs.head_in_the_clouds.mixin;

import de.dafuqs.head_in_the_clouds.api.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(World.class)
public abstract class WorldMixin {

    @Inject(method = "hasRain(Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    public void clouds$hasRain(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(!HeadInTheCloudsAPI.allowRainAtPos((World)(Object) this, pos)) {
            cir.setReturnValue(false);
        }
    }

}
