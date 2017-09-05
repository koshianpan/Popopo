package online.popopo.common.command;

import online.popopo.common.message.Formatter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class Factory {
    private final Formatter formatter;
    private final Completer completer;

    public  Factory(Formatter f) {
        this.formatter = f;
        this.completer = new DefaultCompleter();
    }

    public Wrapper create(Definition d) {
        return new Wrapper(d, completer, formatter);
    }

    private class DefaultCompleter implements Completer {
        @Override
        public List<String> candidateOf(String key) {
            if (key.equals("world")) {
                return Bukkit
                        .getWorlds().stream()
                        .map(World::getName)
                        .collect(Collectors.toList());
            }

            if (key.equals("player")) {
                return Bukkit
                        .getOnlinePlayers().stream()
                        .map(Player::getName)
                        .collect(Collectors.toList());
            }

            if (key.equals("environment")) {
                return Arrays
                        .stream(World.Environment.values())
                        .map(v -> v.name().toLowerCase())
                        .collect(Collectors.toList());
            }

            if (key.equals("material")) {
                return Arrays
                        .stream(Material.values())
                        .map(v -> v.name().toLowerCase())
                        .collect(Collectors.toList());
            }

            return new ArrayList<>();
        }
    }
}
