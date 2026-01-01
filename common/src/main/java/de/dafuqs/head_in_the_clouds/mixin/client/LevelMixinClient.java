package de.dafuqs.head_in_the_clouds.mixin.client;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.head_in_the_clouds.*;
import net.minecraft.client.*;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Level.class)
public abstract class LevelMixinClient {
    
    @ModifyReturnValue(method = "getRainLevel", at = @At("RETURN"))
    public float headInTheClouds$getRainLevel(float original) {
        Entity e = Minecraft.getInstance().getCameraEntity();
        if (e != null) {
            return HeadInTheClouds.getRainGradient((Level) (Object) this, e.blockPosition(), original);
        }
        return original;
    }
    
}
