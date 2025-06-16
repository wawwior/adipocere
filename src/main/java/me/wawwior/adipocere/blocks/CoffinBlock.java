package me.wawwior.adipocere.blocks;

import me.wawwior.adipocere.block_entities.CoffinBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CoffinBlock extends Block implements BlockEntityProvider {

	public CoffinBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
	  return new CoffinBlockEntity(pos, state);
	}

  
}
