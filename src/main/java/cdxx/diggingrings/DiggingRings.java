package cdxx.diggingrings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiggingRings implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("DiggingRing");

    @Override
    public void onInitialize() {
        LOGGER.info("Hasty!");
        ItemRings.load();

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (Player player : server.getPlayerList().getPlayers()) {
                if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ItemRings.HASTE1RING) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 0, true, false));
                } else if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ItemRings.HASTE2RING) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 20, 1, true, false));
                }
            }
        });
    }
}