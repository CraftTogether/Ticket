package commands

import net.kyori.adventure.text.format.TextColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import plugin

class RenameEvent : Listener {

    @EventHandler
    fun onInventoryCLickEvent(event: InventoryClickEvent) {
        if (event.inventory.type != InventoryType.ANVIL) return
        if (event.inventory.getItem(0)?.type != Material.PAPER) return
        val item = event.currentItem ?: return

        val player = event.whoClicked
        val itemName = item.itemMeta.displayName()?.asString() ?: "unbekannt"
        val owner = plugin.claims[itemName] as String?

        if (owner != player.uniqueId.toString()) {
            event.isCancelled = true
            player.closeInventory()

            if (owner == null) {
                player.sendMessage("Das Ticket \"$itemName\" gehört noch nicht dir! Aber du kannst es beanspruchen".asComponent().color(TextColor.color(0xe53d57)))
            } else {
                player.sendMessage("Das Ticket \"$itemName\" gehört nicht dir!".asComponent().color(TextColor.color(0xe53d57)))
            }
        }

    }

}