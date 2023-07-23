package me.example.client.mod;

import lombok.Getter;

import me.example.client.Base;
import me.example.client.event.EventProtocol;
import me.example.client.event.EventTarget;
import me.example.client.event.impl.Render2DEvent;
import me.example.client.gui.HudConfigScreen;
import me.example.client.mod.impl.hud.TestMod;
import net.minecraft.client.Minecraft;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
public class ModManager {

    private final CopyOnWriteArrayList<Mod> modList = new CopyOnWriteArrayList<>();

    public ModManager() {
        modList.add(new TestMod());

        EventProtocol.register(this);
    }

    @EventTarget
    public void onRender2D(Render2DEvent event) {
        if(!(Minecraft.getMinecraft().currentScreen instanceof HudConfigScreen)) {
            for (Mod mod : Base.INSTANCE.getModManager().getModList()) {

                if (mod.isHud() && mod.isEnabled())
                    ((HudMod) mod).render();
            }
        }
    }

    public Mod getMod(Class<?> modClass) {
        return modList.stream().filter(m -> m.getClass().equals(modClass)).findFirst().orElse(null);
    }

    public Mod getMod(String name) {
        return modList.stream().filter(m -> m.getInfo().name().equals(name)).findFirst().orElse(null);
    }

}
