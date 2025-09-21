package de.dafuqs.head_in_the_clouds.mixin.client;

import com.llamalad7.mixinextras.injector.*;
import de.dafuqs.head_in_the_clouds.*;
import net.minecraft.client.*;
import net.minecraft.entity.Entity;
import net.minecraft.world.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;

import javax.swing.text.html.parser.*;
import java.util.*;

@Mixin(World.class)
public abstract class WorldMixinClient {
    
    @ModifyReturnValue(method = "getRainGradient(F)F", at = @At("RETURN"))
    public float clouds$getRainGradientCamera(float original, float tickProgress) {
        if(original > 0) {
            Entity e = MinecraftClient.getInstance().cameraEntity;
            if(e != null) {
                return HeadInTheClouds.getRainGradient((World) (Object) this, e.getPos().y, original);
            }
        }
        return original;
    }

}
