package commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import plugin
import java.util.*


class ClaimTicket : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (args.isEmpty()) {
            //sender.sendMessage("Gebe den Namen eines Tickets ein!")
            return false
        }

        val ticketName = args.first()
        val owner = plugin.claims[ticketName] as String?

        if (owner != null) {
            if (owner == sender.uniqueId.toString()) sender.sendMessage("Dir gehört bereits das Ticket \"$ticketName\"") else {
                val ownerName = Bukkit.getPlayer(UUID.fromString(owner))?.name ?: "unbekannt"
                sender.sendMessage("Das ticket \"$ticketName\" gehört bereits $ownerName")
            }
        } else {
            plugin.claims[ticketName] = sender.uniqueId.toString()
            sender.sendMessage("Dir gehört jetzt das ticket \"$ticketName\"")
        }

        return true
    }

}