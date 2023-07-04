package me.nixuge.nomoreweather.mixins.world;

import me.nixuge.nomoreweather.DataCache;
import me.nixuge.nomoreweather.McMod;
import me.nixuge.nomoreweather.config.Config;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public class WorldMixin {
    private final McMod instance = McMod.getInstance();
    private final Config conf = instance.getConfig();
    private final DataCache cache = instance.getDataCache();

    @Inject(method = "setThunderStrength", at = @At("HEAD"), cancellable = true)
    public void setThunderStrength(float strength, CallbackInfo ci) {
        if (!conf.isWeatherEnabled()) {
            cache.setThunderingStrength(strength);
            ci.cancel();
        }
    }

    @Inject(method = "setRainStrength", at = @At("HEAD"), cancellable = true)
    public void setRainStrength(float strength, CallbackInfo ci) {
        if (!conf.isWeatherEnabled() && strength > 0) {
            cache.setRainingStrength(strength);
            ci.cancel();
        }
    }
}
