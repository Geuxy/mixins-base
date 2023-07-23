package me.example.client.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
public class EventProtocol {

    private static final Map<Class<?>, ArrayList<EventData>> REGISTRY = new HashMap<>();

    public static ArrayList<EventData> getData(Class<?> clazz) {
        return REGISTRY.get(clazz);
    }

    public static void register(Object object) {
        for(Method method : object.getClass().getMethods()) {
            if (!(method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventTarget.class))) {
                Class<?> clazz = method.getParameterTypes()[0];
                EventData data = new EventData(object, method, method.getAnnotation(EventTarget.class).priority());

                if (!data.getTarget().isAccessible())
                    data.getTarget().setAccessible(true);

                if (REGISTRY.containsKey(clazz)) {
                    if (!REGISTRY.get(clazz).contains(data))
                        REGISTRY.get(clazz).add(data);
                } else {
                    ArrayList<EventData> dataList = new ArrayList<>();
                    dataList.add(data);

                    REGISTRY.put(clazz, dataList);
                }
            }
        }
    }

    public static void unregister(Object object) {
        for(ArrayList<EventData> data : REGISTRY.values()) {
            for(int i = data.size() -1; i >= 0; i--) {
                if(data.get(i).getSource().equals(object))
                    data.remove(i);
            }
        }
        EventProtocol.REGISTRY.entrySet().removeIf(classArrayListEntry -> classArrayListEntry.getValue().isEmpty());
    }

}
