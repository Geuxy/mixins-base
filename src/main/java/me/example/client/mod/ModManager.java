package me.example.client.mod;

import lombok.Getter;
import lombok.Setter;

import me.example.client.BaseClient;
import me.example.client.event.EventSubscriber;
import me.example.client.event.impl.Render2DEvent;
import me.example.client.gui.HudConfigScreen;
import me.example.client.mod.impl.hud.TestMod;
import me.example.client.util.containers.Storage;
import me.example.client.util.interfaces.IMinecraft;
import me.example.client.mod.value.Value;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter
@SuppressWarnings("unchecked")
public class ModManager extends Storage<Mod> implements IMinecraft {

    /*
     * Somewhat of a mod context, meaning every time
     * a mod gets added to the list and the constructor
     * is called, it gets stored in this value and is used
     * when the values in the mod class start initializing
     * after the mod class.
     *
     * it hurts my head to even explain this, but it works.
     */
    @Setter
    private Mod recentlyAddedMod;

    @Override
    public void onStart() {
        this.addAll(
            TestMod::new/*,
            AnotherTestMod::new,
            YetAnotherTestMod::new*/
        );

        BaseClient.INSTANCE.getEventManager().register(this);
    }

    @EventSubscriber
    public void onRender2D(Render2DEvent event) {
        if(!(mc.currentScreen instanceof HudConfigScreen)) {
            this.stream().filter(m -> m.isHud() && m.isEnabled()).forEach( m -> ((HudMod)m).render());
        }
    }

    /**
     * Gets a module by its name
     *
     * @param name Mod name
     * @return mod with same name
     */
    public <T extends Mod> T getModByName(String name) {
        return (T) findFirst(m -> m.getInfo().name().equalsIgnoreCase(name));
    }

    /**
     * Gets a value by its mod and name
     *
     * @param name Values name
     * @param mod Mod that the value is from
     * @return Value that has the same name and mod
     */
    public <T extends Value<?>> T getValueByMod(String name, Mod mod) {
        return (T) mod.getValues().stream()
            .filter(v -> v.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

    /**
     * Gets a value by its mods class and name
     *
     * @param name Values name
     * @param mod Mods class that the value is from
     * @return Value that has the same name and mod
     */
    public <T extends Value<?>> T getValueByClass(String name, Class<? extends Mod> mod) {
        return (T) getByClass(mod).getValues().stream()
            .filter(v -> v.getName().equals(name))
            .findFirst()
            .orElse(null);
    }

}
