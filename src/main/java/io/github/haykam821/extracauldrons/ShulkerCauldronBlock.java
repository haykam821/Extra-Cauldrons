package io.github.haykam821.extracauldrons;

import java.util.List;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.CauldronBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.BlockView;

public class ShulkerCauldronBlock extends CauldronBlock {
	public ShulkerCauldronBlock(Settings settings) {
		super(settings);
	}

	@Environment(EnvType.CLIENT)
	public void buildTooltip(ItemStack itemStack, BlockView view, List<Text> list, TooltipContext context) {
		super.buildTooltip(itemStack, view, list, context);

		CompoundTag tag = itemStack.getTag();
		if (tag != null && tag.contains("BlockStateTag")) {
			CompoundTag stateTag = tag.getCompound("BlockStateTag");
			if (stateTag != null && stateTag.contains("level")) {
				list.add(new TranslatableText("cauldron.contents", new Object[]{
						stateTag.getString("level")
				}));
			}
		}
	}
}