package net.orcinus.overweightfarming.common.networking.s2c;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orcinus.overweightfarming.OverweightFarming;
import net.orcinus.overweightfarming.common.registry.OFParticleTypes;

public class S2CFluffPacket {
    public static final Identifier ID = new Identifier(OverweightFarming.MODID, "spawn_fluff_particles");


    public static void send(BlockPos pos, PlayerEntity player) {
        PacketByteBuf buf = new PacketByteBuf(Unpooled.buffer());
        buf.writeBlockPos(pos);
        ServerPlayNetworking.send((ServerPlayerEntity) player, ID, buf);
    }

    public static void handle(MinecraftClient client, ClientPlayNetworkHandler network, PacketByteBuf buf, PacketSender sender) {
        int id = buf.readInt();
        client.execute(() -> {
            ClientWorld world = client.world;
            if (world != null) {
                BlockPos pos = buf.readBlockPos();
                if (pos != null) {
                    world.addParticle(OFParticleTypes.FLUFF, true, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 0f, 0f, 0f);
                }
            }
        });
    }
}
