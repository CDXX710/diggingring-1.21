package cdxx.diggingrings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
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

    public static Item register(Item item, String id) {
        ResourceLocation itemID = ResourceLocation.fromNamespaceAndPath("diggingrings", id);
        return Registry.register(BuiltInRegistries.ITEM, itemID, item);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Hasty!");
        ItemRings.load();
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.add(ItemRings.HASTE1RING));
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register((itemGroup) -> itemGroup.add(ItemRings.HASTE2RING));

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // MobEffects.DIG_SPEED is the Haste effect under official mappings.
            for (Player player : server.getPlayerList().getPlayers()) {
                if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ItemRings.HASTE1RING) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 0, true, false));
                } else if (player.getItemBySlot(EquipmentSlot.OFFHAND).getItem() == ItemRings.HASTE2RING) {
                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 10, 1, true, false));
                }
            }
        });
    }
}
