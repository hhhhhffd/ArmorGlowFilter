package com.armorglow.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(method = "shouldEntityAppearGlowing", at = @At("RETURN"), cancellable = true)
    private void armorglow$hidePlayerOutline(Entity entity, CallbackInfoReturnable<Boolean> result) {
        if (!result.getReturnValueZ() || !(entity instanceof Player player) || !entity.isCurrentlyGlowing()) return;
        if (!player.getItemBySlot(EquipmentSlot.HEAD).isEmpty()
                || !player.getItemBySlot(EquipmentSlot.CHEST).isEmpty()
                || !player.getItemBySlot(EquipmentSlot.LEGS).isEmpty()
                || !player.getItemBySlot(EquipmentSlot.FEET).isEmpty()) {
            result.setReturnValue(false);
        }
    }
}
