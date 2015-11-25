package pw.ian.spigotjs;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import javax.script.*;

import org.bukkit.plugin.java.JavaPlugin;

public class SpigotJS extends JavaPlugin {

  @Override
  public void onEnable() {
    setupInitialScripts();
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    try {
      Bindings bindings = engine.createBindings();
      bindings.put("plugin", this);
      bindings.put("server", getServer());
      engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);
      engine.eval(new InputStreamReader(this.getResource("jvm-npm.js")));
      engine.eval("require('./scripts')");
    } catch (ScriptException ex) {
      getLogger().severe("Could not load scripts!");
      ex.printStackTrace();
    }
  }

  private void setupInitialScripts() {
    File pkg = new File("scripts/package.json");
    if (pkg.exists()) return;

    getLogger().info("scripts/ directory does not exist yet. Creating an initial package...");
    try {
      new File("scripts/").mkdir();
      Files.copy(this.getResource("scripts/package.json"), new File("scripts/package.json").toPath());
      Files.copy(this.getResource("scripts/index.js"), new File("scripts/index.js").toPath());
    } catch (IOException ex) {
      getLogger().severe("Could not copy initial scripts.");
      ex.printStackTrace();
    }
    getLogger().info("Created.");
  }

}
