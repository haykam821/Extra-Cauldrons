package io.github.haykam821.extracauldrons;

import net.minecraft.block.BlockState;
import net.minecraft.block.CauldronBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LavaCauldronBlock extends CauldronBlock {
	public LavaCauldronBlock(Settings settings) {
		super(settings);
	}

	public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
		ItemStack itemStack = playerEntity.getStackInHand(hand);
		if (itemStack.isEmpty()) {
			return ActionResult.PASS;
		} else {
			int level = (Integer)blockState.get(LEVEL);
			Item item = itemStack.getItem();

			if (item == Items.LAVA_BUCKET) {
				if (level < 3 && !world.isClient) {
					if (!playerEntity.abilities.creativeMode) {
						playerEntity.setStackInHand(hand, new ItemStack(Items.BUCKET));
					}

					playerEntity.incrementStat(Stats.FILL_CAULDRON);
					this.setLevel(world, blockPos, blockState, 3);
					world.playSound((PlayerEntity) null, blockPos, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				return ActionResult.SUCCESS;
			} else if (item == Items.BUCKET) {
				if (level == 3 && !world.isClient) {
					if (!playerEntity.abilities.creativeMode) {
						itemStack.decrement(1);
						if (itemStack.isEmpty()) {
							playerEntity.setStackInHand(hand, new ItemStack(Items.LAVA_BUCKET));
						} else if (!playerEntity.inventory.insertStack(new ItemStack(Items.LAVA_BUCKET))) {
							playerEntity.dropItem(new ItemStack(Items.LAVA_BUCKET), false);
						}
					}

					playerEntity.incrementStat(Stats.USE_CAULDRON);
					this.setLevel(world, blockPos, blockState, 0);
					world.playSound((PlayerEntity)null, blockPos, SoundEvents.ITEM_BUCKET_FILL_LAVA, SoundCategory.BLOCKS, 1.0F, 1.0F);
				}

				return ActionResult.SUCCESS;
			}
		}

		return ActionResult.PASS;
	}

	public void onEntityCollision(BlockState blockState, World world, BlockPos blockPos, Entity entity) {
		int level = blockState.get(LEVEL);
		float liquidY = ((level * 3) + 6.0F) / 16.0F + blockPos.getY();

		if (!world.isClient && level > 0 && entity.getY() <= (double) liquidY) {
			if (!entity.isFireImmune()) {
				entity.damage(DamageSource.LAVA, 4.0F);
				entity.setOnFireFor(15);
			}
		}
	}
}