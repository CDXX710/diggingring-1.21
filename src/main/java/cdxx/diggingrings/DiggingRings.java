package cdxx.diggingrings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiggingRings implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("DiggingRing");

    public static ResourceKey<Item> itemKey(String id) {
        return ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("diggingrings", id));
    }

    public static Item register(Item item, ResourceKey<Item> key) {
        return Registry.register(BuiltInRegistries.ITEM, key, item);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Hasty!");
        ItemRings.load();
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((content) -> content.accept(ItemRings.HASTE1RING));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((content) -> content.accept(ItemRings.HASTE2RING));

        // MobEffects.DIG_SPEED is the Haste effect under official mappings.
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (Player player : server.getPlayerList().getPlayers()) {
                if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ItemRings.HASTE1RING) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 0, true, false, false));
                } else if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ItemRings.HASTE2RING) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 1, true, false, false));
                }
            }
        });
    }
}
