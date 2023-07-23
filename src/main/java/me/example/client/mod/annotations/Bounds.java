package me.example.client.mod.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Bounds {

    float posX();
    float posY();
    float width();
    float height();
}
