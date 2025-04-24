package net.orcinus.overweightfarming.blocks;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.vehicle.VehicleEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.SpecialPlantable;
import org.jetbrains.annotations.Nullable;

public class CropFullBlock extends BushBlock implements BonemealableBlock, SpecialPlantable {
    public final Block stemBlock;
    private static final VoxelShape SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    public static final MapCodec<CropFullBlock> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("block").forGetter(CropFullBlock::getStemBlock),
                    propertiesCodec()
            ).apply(instance, CropFullBlock::new)
    );

    public CropFullBlock(Block stemBlock, Properties properties) {
        super(properties);
        this.stemBlock = stemBlock;
        this.registerDefaultState(this.stateDefinition.any());
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext context) {
        boolean isPlayer = context instanceof EntityCollisionContext entityCollisionContext && (entityCollisionContext.getEntity() instanceof LivingEntity || entityCollisionContext.getEntity() instanceof VehicleEntity || entityCollisionContext.getEntity() instanceof ItemEntity || entityCollisionContext.getEntity() instanceof ExperienceOrb);
        return isPlayer ? Shapes.block() : Shapes.empty();
    }

    public Block getStemBlock() {
        return this.stemBlock;
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader world, BlockPos blockPos, BlockState state) {
        return !world.getBlockState(blockPos.above()).is(this.stemBlock);
    }

    @Override
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos blockPos, BlockState state) {
        return true;
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter world, BlockPos blockPos) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos blockPos, BlockState state) {
        BlockPos above = blockPos.above();
        BlockPos below = blockPos.below();
        if (this.stemBlock != null && world.getBlockState(above).isAir()) {
            world.setBlock(above, stemBlock.defaultBlockState(), 2);
        }
        if (world.isStateAtPosition(below, BlockBehaviour.BlockStateBase::isAir) && this.shouldGrowRoots()) {
            world.setBlock(below, Blocks.HANGING_ROOTS.defaultBlockState(), 2);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState p_51034_, LevelAccessor world, BlockPos blockPos, BlockPos p_51037_) {
        return state;
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    public void randomTick(BlockState state, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
    }

    public boolean shouldGrowRoots() {
        return true;
    }

    @Override
    public boolean canPlacePlantAtPosition(ItemStack itemStack, LevelReader level, BlockPos pos, @Nullable Direction direction) {
        return false;
    }

    @Override
    public void spawnPlantAtPosition(ItemStack itemStack, LevelAccessor level, BlockPos pos, @Nullable Direction direction) {
    }

}
