package pw.ian.spigotjs

import javax.script.ScriptEngineManager

import org.bukkit.plugin.java.JavaPlugin

class SpigotJS extends JavaPlugin {

  override def onEnable() = {
    val engine = new ScriptEngineManager().getEngineByName("nashorn")
  }

  override def onDisable() = {

  }

}
