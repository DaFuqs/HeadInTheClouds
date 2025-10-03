package de.dafuqs.head_in_the_clouds.mixin;

import de.dafuqs.head_in_the_clouds.api.HeadInTheCloudsAPI;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin {
    @Inject(method = "hasRain(Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    public void hasRain(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (!HeadInTheCloudsAPI.allowRainAtPos((World)(Object)this, pos)) {
            cir.setReturnValue(false);
        }
    }
}
