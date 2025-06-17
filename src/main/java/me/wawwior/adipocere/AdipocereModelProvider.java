package me.wawwior.adipocere;

import me.wawwior.adipocere.block.CoffinBlock;
import me.wawwior.adipocere.block.enums.CoffinPart;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class AdipocereModelProvider extends FabricModelProvider {

    public static final Model COFFIN_BASE = block("coffin_base", TextureKey.TOP, TextureKey.BOTTOM, TextureKey.END, TextureKey.SIDE);

    public AdipocereModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        registerCoffin(
                blockStateModelGenerator
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private static Model block(String parent, TextureKey... requiredTextureKeys) {
        return new Model(Optional.ofNullable(Identifier.of(Adipocere.MOD_ID, "block/" + parent)), Optional.empty(), requiredTextureKeys);
    }

    private static TextureMap coffinHeadMap() {
        return new TextureMap()
                .put(TextureKey.TOP, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_head_top"))
                .put(TextureKey.BOTTOM, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_head_bottom"))
                .put(TextureKey.END, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_head_end"))
                .put(TextureKey.SIDE, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_head_side"));
    }

    private static TextureMap coffinFootMap() {
        return new TextureMap()
                .put(TextureKey.TOP, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_foot_top"))
                .put(TextureKey.BOTTOM, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_foot_bottom"))
                .put(TextureKey.END, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_foot_end"))
                .put(TextureKey.SIDE, ModelIds.getBlockSubModelId(AdipocereBlocks.COFFIN, "_foot_side"));
    }

    private static BlockStateSupplier createCoffinStates(Identifier headModel, Identifier footModel) {
        VariantSetting<VariantSettings.Rotation> yRot = VariantSettings.Y;
        return VariantsBlockStateSupplier.create(AdipocereBlocks.COFFIN).coordinate(BlockStateVariantMap.create(CoffinBlock.FACING, CoffinBlock.PART)
                .register(Direction.NORTH, CoffinPart.HEAD, BlockStateVariant.create().put(VariantSettings.MODEL, headModel))
                .register(Direction.EAST, CoffinPart.HEAD, BlockStateVariant.create().put(VariantSettings.MODEL, headModel).put(yRot, VariantSettings.Rotation.R90))
                .register(Direction.SOUTH, CoffinPart.HEAD, BlockStateVariant.create().put(VariantSettings.MODEL, headModel).put(yRot, VariantSettings.Rotation.R180))
                .register(Direction.WEST, CoffinPart.HEAD, BlockStateVariant.create().put(VariantSettings.MODEL, headModel).put(yRot, VariantSettings.Rotation.R270))
                .register(Direction.NORTH, CoffinPart.FOOT, BlockStateVariant.create().put(VariantSettings.MODEL, footModel).put(yRot, VariantSettings.Rotation.R180))
                .register(Direction.EAST, CoffinPart.FOOT, BlockStateVariant.create().put(VariantSettings.MODEL, footModel).put(yRot, VariantSettings.Rotation.R270))
                .register(Direction.SOUTH, CoffinPart.FOOT, BlockStateVariant.create().put(VariantSettings.MODEL, footModel))
                .register(Direction.WEST, CoffinPart.FOOT, BlockStateVariant.create().put(VariantSettings.MODEL, footModel).put(yRot, VariantSettings.Rotation.R90))
        );
    }

    private static void registerCoffin(BlockStateModelGenerator generator) {
        Identifier coffinHeadModel = COFFIN_BASE.upload(AdipocereBlocks.COFFIN, "_head", coffinHeadMap(), generator.modelCollector);
        Identifier coffinFootModel = COFFIN_BASE.upload(AdipocereBlocks.COFFIN, "_foot", coffinFootMap(), generator.modelCollector);
        generator.blockStateCollector.accept(createCoffinStates(coffinHeadModel, coffinFootModel));
    }

    @Override
    public String getName() {
        return "Adipocere Model Provider";
    }
}
