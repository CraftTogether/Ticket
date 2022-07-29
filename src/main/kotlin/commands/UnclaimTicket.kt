package commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import plugin
import java.util.*


class UnclaimTicket : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false

        if (args.isEmpty()) {
            return false
        }

        val ticketName = args.first()
        val owner = plugin.claims[ticketName] as String?

        if (owner != null) {
            if (owner == sender.uniqueId.toString()) {
                plugin.claims.remove(ticketName)
                sender.sendMessage("Das ticket \"$ticketName\" gehört jetzt nicht mehr dir")
            } else sender.sendMessage("Das ticket \"$ticketName\" gehört nicht dir!")
        } else sender.sendMessage("Das ticket wurde noch nicht beansprucht \"$ticketName\"")

        return true
    }

}