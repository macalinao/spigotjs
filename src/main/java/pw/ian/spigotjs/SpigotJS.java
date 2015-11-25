package pw.ian.spigotjs;

import java.io.InputStreamReader;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotJS extends JavaPlugin {

  @Override
  public void onEnable() {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    try {
      engine.eval(new InputStreamReader(this.getResource("jvm-npm.js")));
      engine.eval("require('./scripts')");
    } catch (ScriptException ex) {
      getLogger().severe("Could not load scripts!");
      ex.printStackTrace();
    }
  }

}
