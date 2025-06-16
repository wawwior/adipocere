package me.wawwior.adipocere.block_entities;

import me.wawwior.adipocere.AdipocereBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class TombstoneBlockEntity extends BlockEntity {

	public TombstoneBlockEntity(BlockPos pos, BlockState state) {
		super(AdipocereBlockEntities.TOMBSTONE, pos, state);
	}
  
}
