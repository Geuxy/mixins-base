package me.example.client.event;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class Event {

    public Event onFire() {
        List<EventData> dataList = EventProtocol.getData(this.getClass());

        if(dataList != null) {
            dataList.forEach(d -> {
                try {
                    d.getTarget().invoke(d.getSource(), this);

                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        }
        return this;
    }

}
