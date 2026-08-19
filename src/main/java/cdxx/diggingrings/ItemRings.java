package cdxx.diggingrings;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class ItemRings {

    public static Item HASTE1RING;
    public static Item HASTE2RING;

    public static void load() {
        ResourceKey<Item> haste1Key = DiggingRings.itemKey("haste1ring");
        HASTE1RING = DiggingRings.register(new CustomItem(new Item.Properties().setId(haste1Key), "item.diggingrings.hasterings.tooltip", "item.diggingrings.haste1ring.tooltip"), haste1Key);

        ResourceKey<Item> haste2Key = DiggingRings.itemKey("haste2ring");
        HASTE2RING = DiggingRings.register(new CustomItem(new Item.Properties().setId(haste2Key), "item.diggingrings.hasterings.tooltip", "item.diggingrings.haste2ring.tooltip"), haste2Key);
    }

    public static class CustomItem extends Item {
        private final String tooltipText1;
        private final String tooltipText2;

        public CustomItem(Properties properties, String tooltipText1, String tooltipText2) {
            super(properties);
            this.tooltipText1 = tooltipText1;
            this.tooltipText2 = tooltipText2;
        }

        @Override
        public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
            textConsumer.accept(Component.translatable(this.tooltipText1).withStyle(ChatFormatting.BLUE));
            textConsumer.accept(Component.translatable(this.tooltipText2).withStyle(ChatFormatting.GOLD));
        }
    }
}
