package me.nixuge.nomoreweather.command.commands;

import me.nixuge.nomoreweather.DataCache;
import me.nixuge.nomoreweather.command.MessageBuilder;
import me.nixuge.nomoreweather.config.Config;
import me.nixuge.nomoreweather.command.AbstractCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class DisableWeatherCmd extends AbstractCommand {
    private final Config config;
    private final DataCache dataCache;

    public DisableWeatherCmd(final Config config, DataCache dataCache) {
        super("weatherdisable");

        this.config = config;
        this.dataCache = dataCache;
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> al = new ArrayList<>();
        al.add("disableweather");
        return al;
    }

    @Override
    public void onCommand(final ICommandSender sender, final String[] args) {
        config.setWeatherEnabled(false);
        
        World world = Minecraft.getMinecraft().theWorld;
        dataCache.setRainingStrength(world.getRainStrength(1));
        dataCache.setThunderingStrength(world.getThunderStrength(1));

        dataCache.putNoRainDataInWorld();
        this.tell(new MessageBuilder("Weather disabled.", EnumChatFormatting.GRAY));
    }
}
