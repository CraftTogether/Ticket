import commands.ClaimTicket
import commands.ListTickets
import commands.RenameEvent
import commands.UnclaimTicket
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import java.io.FileWriter


lateinit var plugin: Plugin

class Plugin : JavaPlugin() {

    private val claimsFile = File(dataFolder.path + File.separator + "claim.json")
    lateinit var claims: JSONObject

    override fun onEnable() {
        Bukkit.getPluginManager().registerEvents(RenameEvent(), this)
        getCommand("claimticket")?.setExecutor(ClaimTicket()) ?: throw Exception("Couldn't find the command")
        getCommand("unclaimticket")?.setExecutor(UnclaimTicket()) ?: throw Exception("Couldn't find the command")
        getCommand("listtickets")?.setExecutor(ListTickets()) ?: throw Exception("Couldn't find the command")

        prepareClaimList()

        Bukkit.getConsoleSender().sendMessage("Ticket plugin loaded!")
        plugin = this
    }

    override fun onDisable() {
        val writer = FileWriter(claimsFile)
        writer.write(claims.toJSONString())
        writer.close()
        Bukkit.getConsoleSender().sendMessage("Unloaded Ticket plugin!")
    }

    private fun prepareClaimList() {
        val file = if (claimsFile.exists()) claimsFile else createClaimList()
        val content = file.readText()
        val json = JSONParser().parse(content) as JSONObject
        claims = json
    }

    private fun createClaimList(): File {
        claimsFile.parentFile.mkdirs()
        claimsFile.createNewFile()
        val writer = FileWriter(claimsFile)
        writer.write("{}")
        writer.close()
        return claimsFile
    }

}