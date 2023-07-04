package me.nixuge.nomoreweather;

import java.io.File;

import lombok.Getter;
import lombok.Setter;
import me.nixuge.nomoreweather.command.commands.DisableWeatherCmd;
import me.nixuge.nomoreweather.command.commands.EnableWeatherCmd;
import me.nixuge.nomoreweather.config.Config;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = McMod.MOD_ID,
        name = McMod.NAME,
        version = McMod.VERSION,
        clientSideOnly = true
)

@Setter
public class McMod {
    public static final String MOD_ID = "nomoreweather";
    public static final String NAME = "No More Weather";
    public static final String VERSION = "1.0.0";


    @Getter
    @Mod.Instance(value = McMod.MOD_ID)
    private static McMod instance;
    @Getter
    private Config config;
    @Getter
    private DataCache dataCache;

    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        String configDirectory = event.getModConfigurationDirectory().toString();
        final File path = new File(configDirectory + File.separator + McMod.MOD_ID + ".cfg");
        Configuration configuration = new Configuration(path);
        this.config = new Config(configuration);
        this.dataCache = new DataCache();
    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(config);
        ClientCommandHandler.instance.registerCommand(new DisableWeatherCmd(this.config, this.dataCache));
        ClientCommandHandler.instance.registerCommand(new EnableWeatherCmd(this.config, this.dataCache));
    }
}
