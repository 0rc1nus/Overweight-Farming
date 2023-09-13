package net.orcinus.overweightfarming.common.networking.c2s;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.util.OverweightGrowthManager;
import org.jetbrains.annotations.Nullable;

public class C2SFluffPacket {
    public static final Identifier ID = new Identifier(OverweightFarming.MODID, "fluff_plant");

    public static void send(BlockPos pos) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBlockPos(pos);

        ClientPlayNetworking.send(ID, buf);
    }

    public static void handle(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        BlockPos pos = buf.readBlockPos();
        server.execute(() -> {
            ServerWorld serverWorld = server.getOverworld();
            OverweightGrowthManager manager = new OverweightGrowthManager(serverWorld.getRandom());

            BlockState blockState = serverWorld.getBlockState(pos);
            BlockState blockState2 = serverWorld.getBlockState(pos.down());

            if(blockState != null && blockState2 != null && FabricLoader.getInstance().isModLoaded("immersive_weathering")){
                @Nullable Block weeds = manager.getCompatBlock("immersive_weathering", "weeds");
                if(weeds != null) {
                    if(blockState.isIn(BlockTags.CROPS)){
                        serverWorld.setBlockState(pos, weeds.getDefaultState());
                    }else if(blockState2.isIn(BlockTags.CROPS)){
                        serverWorld.setBlockState(pos.down(), weeds.getDefaultState());
                    }
                }
            }
        });
    }
}
