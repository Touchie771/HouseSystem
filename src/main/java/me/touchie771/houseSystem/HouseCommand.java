package me.touchie771.houseSystem;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.optional.OptionalArg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

@Command(name = "house")
@Permission("housesystem.main")
public class HouseCommand {

    private final HouseDataManager houseDataManager;

    public HouseCommand(HouseSystem plugin) {
        houseDataManager = new HouseDataManager(plugin);
    }

    @Execute
    public void tpToHouse(@Context Player sender) {
        Location location = houseDataManager.getHouse(sender);
        if (location == null) {
            sender.sendMessage(Component.text("You don't have a house", NamedTextColor.RED, TextDecoration.BOLD));
            return;
        }
        sender.teleport(location);
        sender.sendMessage(Component.text("You have been teleported to your house", NamedTextColor.GREEN, TextDecoration.BOLD));
    }

    @Execute(name = "add")
    @Permission("housesystem.add")
    public void addHouse(@Context Player sender
            , @Arg Player player, @OptionalArg Double x, @OptionalArg Double y, @OptionalArg Double z, @OptionalArg World world) {
        if (x == null) {
            houseDataManager.addHouse(player, sender.getLocation());
            sender.sendMessage(Component.text("House set for " + player.getName(), NamedTextColor.GREEN, TextDecoration.BOLD));
            player.sendMessage(Component.text("Your house has been set, do /house details for more info", NamedTextColor.GREEN,
                    TextDecoration.BOLD));
            return;
        }
        if (y != null && z != null && world != null) {
            houseDataManager.addHouse(player, new Location(world, x, y, z));
            sender.sendMessage(Component.text("House set for " + player.getName(), NamedTextColor.GREEN, TextDecoration.BOLD));
            player.sendMessage(Component.text("Your house has been set, do /house details for more info", NamedTextColor.GREEN,
                    TextDecoration.BOLD));
        }
    }

    @Execute(name = "remove")
    @Permission("housesystem.remove")
    public void removeHouse(@Arg Player player) {
        houseDataManager.removeHouse(player);
        player.sendMessage(Component.text("Your house has been removed", NamedTextColor.GREEN, TextDecoration.BOLD));
    }

    @Execute(name = "list")
    @Permission("housesystem.list")
    public void listHouses(@Context Player sender) {
        var houses = houseDataManager.getHouses();
        if (houses.isEmpty()) {
            sender.sendMessage(Component.text("No houses set.", NamedTextColor.YELLOW, TextDecoration.ITALIC));
            return;
        }
        sender.sendMessage(Component.text("Houses:", NamedTextColor.GOLD, TextDecoration.BOLD));
        for (var entry : houses.entrySet()) {
            Player player = entry.getKey();
            Location loc = entry.getValue();
            sender.sendMessage(Component.text(
                "- " + player.getName() + ": " +
                    String.format("(%.2f, %.2f, %.2f) in %s",
                        loc.getX(), loc.getY(), loc.getZ(),
                        loc.getWorld() != null ? loc.getWorld().getName() : "unknown"
                    ),
                NamedTextColor.WHITE
            ));
        }
    }

    @Execute(name = "details")
    @Permission("housesystem.details")
    public void detailsHouse(@Context Player player) {
        Location location = houseDataManager.getHouse(player);
        if (location == null) {
            player.sendMessage(Component.text("You don't have a house", NamedTextColor.RED, TextDecoration.BOLD));
            return;
        }
        player.sendMessage(Component.text("Your house is at " + location, NamedTextColor.GREEN, TextDecoration.BOLD));
    }
}