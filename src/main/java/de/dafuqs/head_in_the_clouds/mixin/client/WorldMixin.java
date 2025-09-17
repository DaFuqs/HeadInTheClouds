package de.dafuqs.head_in_the_clouds.mixin.client;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.head_in_the_clouds.*;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(World.class)
public abstract class WorldMixin {

    @ModifyReturnValue(method = "getRainGradient(F)F", at = @At("RETURN"))
    public float clouds$getRainGradient(float original, float tickProgress) {
        if(original > 0) {
            return HeadInTheClouds.getRainGradient((World) (Object) this, original);
        }
        return original;
    }

}
