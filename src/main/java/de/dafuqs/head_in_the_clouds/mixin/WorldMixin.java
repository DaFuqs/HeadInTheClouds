package de.dafuqs.head_in_the_clouds.mixin;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.head_in_the_clouds.*;
import de.dafuqs.head_in_the_clouds.api.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(World.class)
public abstract class WorldMixin {
    
    @ModifyReturnValue(method = "getRainGradient(F)F", at = @At("RETURN"))
    public float clouds$getRainGradient(float original, float tickProgress) {
        if(original > 0) {
            return HeadInTheClouds.getRainGradient((World) (Object) this, original);
        }
        return original;
    }
    
    @Inject(method = "hasRain(Lnet/minecraft/util/math/BlockPos;)Z", at = @At("HEAD"), cancellable = true)
    public void clouds$hasRain(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if(!HeadInTheCloudsAPI.allowRainAtPos((World)(Object) this, pos)) {
            cir.setReturnValue(false);
        }
    }

}
