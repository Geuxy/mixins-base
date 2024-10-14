package me.example.client.mixins.client.entity;

import me.example.client.event.impl.ChatEvent;

import net.minecraft.client.entity.EntityPlayerSP;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

    /**
     * @author Geuxy
     * @reason Hook chat event for command system
     */
    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String message, CallbackInfo ci) {
        ChatEvent chatEvent = new ChatEvent(message);
        chatEvent.call();

        if(chatEvent.isCancelled()) {
           ci.cancel();
        }
    }

}
