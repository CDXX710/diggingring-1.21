package cdxx.diggingrings;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ItemRings {

    public static Item HASTE1RING;
    public static Item HASTE2RING;

    public static void load() {
        HASTE1RING = DiggingRings.register(new CustomItem(new Item.Properties(), "item.diggingrings.hasterings.tooltip", "item.diggingrings.haste1ring.tooltip"), "haste1ring");
        HASTE2RING = DiggingRings.register(new CustomItem(new Item.Properties(), "item.diggingrings.hasterings.tooltip", "item.diggingrings.haste2ring.tooltip"), "haste2ring");
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
        public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type) {
            tooltip.add(Component.translatable(this.tooltipText1).withStyle(ChatFormatting.BLUE));
            tooltip.add(Component.translatable(this.tooltipText2).withStyle(ChatFormatting.GOLD));
        }
    }
}
