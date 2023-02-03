package de.dafuqs.head_in_the_clouds.mixin.client;

import de.dafuqs.head_in_the_clouds.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

@Mixin(World.class)
public abstract class WorldMixin {

    @Shadow @Final public boolean isClient;

    @Inject(method = "getRainGradient(F)F", at = @At("RETURN"), cancellable = true)
    public void clouds$getRainGradient(float delta, CallbackInfoReturnable<Float> cir) {
        if(isClient) {
            cir.setReturnValue(HeadInTheClouds.getRainGradient((World) (Object) this, cir.getReturnValue()));
        }
    }

}
