package me.wawwior.adipocere.block.entity;

import me.wawwior.adipocere.AdipocereBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CoffinBlockEntity extends BlockEntity {

	public CoffinBlockEntity(BlockPos pos, BlockState state) {
		super(AdipocereBlockEntities.COFFIN, pos, state);
	}
  
}
