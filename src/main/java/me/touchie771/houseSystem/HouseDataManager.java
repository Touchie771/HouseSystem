package me.touchie771.houseSystem;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class HouseDataManager {

    private final FileConfiguration housesFile;

    public HouseDataManager(HouseSystem plugin) {
        housesFile = YamlConfiguration.loadConfiguration(
                new File(plugin.getDataFolder(), "houses.yml")
        );
    }

    public void addHouse(Player player, Location location) {
        housesFile.set(player.getUniqueId().toString(), String.format("%.2f, %.2f, %.2f",
                location.getX(), location.getY(), location.getZ()));
    }

    public void removeHouse(Player player) {
        if (housesFile.get(player.getUniqueId().toString()) != null) {
            housesFile.set(player.getUniqueId().toString(), null);
        }
    }

    public Location getHouse(Player player) {
        String locationString = housesFile.getString(player.getUniqueId().toString());
        if (locationString == null) {
            return null;
        }
        String[] locationParts = locationString.split(", ");
        return new Location(player.getWorld(),
                Double.parseDouble(locationParts[0]),
                Double.parseDouble(locationParts[1]),
                Double.parseDouble(locationParts[2]));
    }
}