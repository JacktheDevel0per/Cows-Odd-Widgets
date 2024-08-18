package xyz.mrcow.cowsOddWidgets.mixin.feature.stepHeight;


import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import xyz.mrcow.cowsOddWidgets.config.Configs;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @ModifyExpressionValue(method = "getStepHeight", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getAttributeValue(Lnet/minecraft/registry/entry/RegistryEntry;)D"))
    private double wrapped(double original) {
        if (Configs.Settings.STEP_MODE.getBooleanValue() && (!Configs.Settings.STEP_MODE_BLOCKED_BY_SNEAK.getBooleanValue() || this.isSneaking())) {
            return original + 0.624;
        } else {
            return original;
        }
    }


}
