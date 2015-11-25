package pw.ian.spigotjs

import java.io.InputStreamReader
import javax.script.ScriptEngineManager

import org.bukkit.plugin.java.JavaPlugin

class SpigotJS extends JavaPlugin {

  override def onEnable() = {
    val engine = new ScriptEngineManager().getEngineByName("nashorn")
    engine.eval(new InputStreamReader(this.getResource("jvm-npm.js")))
    engine.eval("require('./scripts')")
  }

}
