package me.touchie771.houseSystem;

import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;

@Command(name = "house")
public class HouseCommand {

    private final HouseDataManager houseDataManager;

    public HouseCommand(HouseSystem plugin) {
        houseDataManager = new HouseDataManager(plugin);
    }

    @Execute(name = "add")
    public void addHouse(@Arg Player player) {

    }
}