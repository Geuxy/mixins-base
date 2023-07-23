package me.example.client.mod.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Basic mixin client base.
 * @author Geuxy
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ModInfo {

    String name();
    String description() default "Not described.";
}
