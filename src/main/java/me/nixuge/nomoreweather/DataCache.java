package me.nixuge.nomoreweather;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

@Getter
@Setter
public class DataCache {
    private float thunderingStrength;
    private float rainingStrength;

    public void putSavedDataInWorld() {
        World world = Minecraft.getMinecraft().theWorld;
        world.setThunderStrength(thunderingStrength);
        world.setRainStrength(rainingStrength);
    }

    public void putNoRainDataInWorld() {
        World world = Minecraft.getMinecraft().theWorld;
        world.setThunderStrength(0f);
        world.setRainStrength(0f);
    }
}
