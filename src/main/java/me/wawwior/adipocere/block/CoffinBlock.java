package me.wawwior.adipocere.block;

import me.wawwior.adipocere.block.entity.CoffinBlockEntity;
import me.wawwior.adipocere.block.enums.CoffinPart;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

public class CoffinBlock extends HorizontalFacingBlock implements BlockEntityProvider {

	public static final EnumProperty<CoffinPart> PART = EnumProperty.of("part", CoffinPart.class);
	public static final VoxelShape EAST_SHAPE = Block.createCuboidShape(0.0, 0.0, 1.0, 15.0, 11.0, 15.0);
	public static final VoxelShape WEST_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 16.0, 11.0, 15.0);
	public static final VoxelShape NORTH_SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 11.0, 16.0);
	public static final VoxelShape SOUTH_SHAPE = Block.createCuboidShape(1.0, 0.0, 0.0, 15.0, 11.0, 15.0);

	public CoffinBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.getStateManager().getDefaultState().with(PART, CoffinPart.FOOT));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(FACING, PART);
	}

	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new CoffinBlockEntity(pos, state);
	}

	@Nullable
	public static Direction getDirection(BlockView world, BlockPos pos) {
		BlockState blockState = world.getBlockState(pos);
		return blockState.getBlock() instanceof CoffinBlock ? blockState.get(FACING) : null;
	}

	private static Direction getDirectionTowardsOtherPart(CoffinPart part, Direction direction) {
		return part == CoffinPart.FOOT ? direction : direction.getOpposite();
	}

	public static Direction getDirectionTowardsOtherPart(BlockState state) {
        return getDirectionTowardsOtherPart(state.get(PART), state.get(FACING));
	}

	public static DoubleBlockProperties.Type getCoffinPart(BlockState state) {
		CoffinPart coffinPart = state.get(PART);
		return coffinPart == CoffinPart.HEAD ? DoubleBlockProperties.Type.FIRST : DoubleBlockProperties.Type.SECOND;
	}

	@SuppressWarnings("deprecation")
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (direction == getDirectionTowardsOtherPart(state)) {
			return neighborState.isOf(this) && neighborState.get(PART) != state.get(PART) ? state : Blocks.AIR.getDefaultState();
		} else {
			return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
		}
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient && player.isCreative()) {
			CoffinPart coffinPart = state.get(PART);
			if (coffinPart == CoffinPart.FOOT) {
				BlockPos blockPos = pos.offset(getDirectionTowardsOtherPart(coffinPart, state.get(FACING)));
				BlockState blockState = world.getBlockState(blockPos);
				if (blockState.isOf(this) && blockState.get(PART) == CoffinPart.HEAD) {
					world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
					world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(blockState));
				}
			}
		}
		super.onBreak(world, pos, state, player);
	}

	@Nullable
	@Override
	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getHorizontalPlayerFacing();
		BlockPos blockPos = ctx.getBlockPos();
		BlockPos blockPos2 = blockPos.offset(direction);
		World world = ctx.getWorld();
		return world.getBlockState(blockPos2).canReplace(ctx) && world.getWorldBorder().contains(blockPos2) ? this.getDefaultState().with(FACING, direction) : null;
	}

	@Override
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		if (!world.isClient) {
			BlockPos blockPos = pos.offset(state.get(FACING));
			world.setBlockState(blockPos, state.with(PART, CoffinPart.HEAD), Block.NOTIFY_ALL);
			world.updateNeighbors(pos, Blocks.AIR);
			state.updateNeighbors(world, pos, Block.NOTIFY_ALL);
		}
	}

	@SuppressWarnings("deprecation")
    @Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		Direction direction = getDirectionTowardsOtherPart(state).getOpposite();
		return switch (direction) {
			case NORTH -> NORTH_SHAPE;
			case SOUTH -> SOUTH_SHAPE;
			case WEST -> WEST_SHAPE;
			default -> EAST_SHAPE;
		};
	}

}
