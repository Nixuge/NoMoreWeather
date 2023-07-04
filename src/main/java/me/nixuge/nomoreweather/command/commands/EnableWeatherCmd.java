package me.nixuge.nomoreweather.command.commands;


import me.nixuge.nomoreweather.command.MessageBuilder;
import me.nixuge.nomoreweather.config.Config;
import me.nixuge.nomoreweather.DataCache;
import me.nixuge.nomoreweather.command.AbstractCommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class EnableWeatherCmd extends AbstractCommand {
    private final Config config;
    private final DataCache dataCache;

    public EnableWeatherCmd(final Config config, DataCache dataCache) {
        super("weatherenable");

        this.config = config;
        this.dataCache = dataCache;
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> al = new ArrayList<>();
        al.add("enableweather");
        return al;
    }

    @Override
    public void onCommand(final ICommandSender sender, final String[] args) {
        config.setWeatherEnabled(true);
        dataCache.putSavedDataInWorld();
        this.tell(new MessageBuilder("Weather enabled.", EnumChatFormatting.GRAY));
    }
}
