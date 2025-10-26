package me.touchie771.houseSystem;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HouseDataManager {

    private final HouseSystem plugin;
    private final File file;
    private final FileConfiguration housesFile;

    public HouseDataManager(HouseSystem plugin) {
        this.plugin = plugin;
        if (!plugin.getDataFolder().exists()) {
            // Ensure plugin data folder exists
            if (!plugin.getDataFolder().mkdirs()) {
                plugin.getLogger().severe("Could not create data folder!");
            }
        }
        this.file = new File(plugin.getDataFolder(), "houses.yml");
        this.housesFile = YamlConfiguration.loadConfiguration(file);
    }

    public void addHouse(Player player, Location location) {
        housesFile.set(player.getUniqueId().toString(), String.format("%.2f, %.2f, %.2f",
                location.getX(), location.getY(), location.getZ()));
        save();
    }

    public void removeHouse(Player player) {
        if (housesFile.get(player.getUniqueId().toString()) != null) {
            housesFile.set(player.getUniqueId().toString(), null);
            save();
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

    public HashMap<Player, Location> getHouses() {
        HashMap<Player, Location> houses = new HashMap<>();
        for (String key : housesFile.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(key);
                Player player = Bukkit.getPlayer(uuid);
                if (player == null) {
                    continue;
                }
                Location location = getHouse(player);
                if (location != null) {
                    houses.put(player, location);
                }
            } catch (IllegalArgumentException ignored) {
            }
        }
        return houses;
    }

    private void save() {
        try {
            housesFile.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Failed to save houses.yml: " + e.getMessage());
        }
    }
}