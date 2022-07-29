package commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import plugin


class ListTickets : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        val claimedTickets = mutableListOf<String>()
        for (key in plugin.claims.keys) {
            val owner = plugin.claims[key]
            if (owner == sender.uniqueId) claimedTickets.add(key as String)
        }
        sender.sendMessage("Deine Tickets: " + claimedTickets.joinToString(", ") { it })

        return true
    }

}