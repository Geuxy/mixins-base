package me.example.client.gui.modlist.component.sub;

import lombok.Getter;
import lombok.Setter;

import me.example.client.gui.modlist.component.Component;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Getter @Setter
public abstract class SubComponent<T> extends Component {

    protected final T value;

    public SubComponent(T value, float x, float y, float width, float height) {
        super(null, x, y, width, height);
        this.value = value;
    }

}
