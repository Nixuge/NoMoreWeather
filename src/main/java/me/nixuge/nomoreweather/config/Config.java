package me.nixuge.nomoreweather.config;

import lombok.Getter;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

@Getter
public class Config {
    private final Configuration configuration;
    
    @Getter
    private Property weatherEnabled;

    public boolean isWeatherEnabled() {
        return weatherEnabled.getBoolean();
    }
    public void setWeatherEnabled(boolean weatherEnabled) {
        this.weatherEnabled.set(weatherEnabled);
        this.configuration.save();
    }

    public Config(final Configuration configuration) {
        this.configuration = configuration;
        this.loadConfiguration();
        this.configuration.save();
    }

    private void loadConfiguration() {
        this.weatherEnabled = this.configuration.get(
                "General",
                "Weather enabled",
                true,
                ""
        );
    }
}