package Ayakashi.mods.blockparticles.particle;

import Ayakashi.mods.ReflectionHelper;
import Ayakashi.mods.blockparticles.FBP;
import com.google.common.base.Throwables;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.DestroyBlockProgress;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.*;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.Map;

public class FBPParticleManager extends EffectRenderer {
    private static MethodHandle getBlockDamage;
    private static MethodHandle getParticleScale;
    private static MethodHandle getParticleTexture;
    private static MethodHandle getParticleTypes;
    private static MethodHandle getSourceState;

    private static MethodHandle X, Y, Z;

    private static IBlockState blockState;

    Minecraft mc;

    public FBPParticleManager(World worldIn, TextureManager rendererIn, IParticleFactory particleFactory) {
        super(worldIn, rendererIn);

        mc = Minecraft.getMinecraft();

        TextureAtlasSprite white = mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture((Blocks.snow.getDefaultState()));

        MethodHandles.Lookup lookup = MethodHandles.publicLookup();

        try {
            getParticleTypes = lookup.unreflectGetter(ReflectionHelper.findField(EffectRenderer.class, "particleTypes", "particleTypes"));
            X = lookup.unreflectGetter(ReflectionHelper.findField(Entity.class, "posX", "posX"));
            Y = lookup.unreflectGetter(ReflectionHelper.findField(Entity.class, "posY", "posY"));
            Z = lookup.unreflectGetter(ReflectionHelper.findField(Entity.class, "posZ", "posZ"));
            lookup.unreflectGetter(ReflectionHelper.findField(Entity.class, "motionX", "motionX"));
            lookup.unreflectGetter(ReflectionHelper.findField(Entity.class, "motionY", "motionY"));
            lookup.unreflectGetter(ReflectionHelper.findField(Entity.class, "motionZ", "motionZ"));
            getParticleScale = lookup.unreflectGetter(ReflectionHelper.findField(EntityFX.class, "particleScale", "particleScale"));
            getParticleTexture = lookup.unreflectGetter(ReflectionHelper.findField(EntityFX.class, "particleIcon", "particleIcon"));
            lookup.unreflectGetter(ReflectionHelper.findField(EntityFX.class, "particleMaxAge", "particleMaxAge"));
            getSourceState = lookup.unreflectGetter(ReflectionHelper.findField(EntityDiggingFX.class, "sourceState"));
            getBlockDamage = lookup.unreflectGetter(ReflectionHelper.findField(RenderGlobal.class, "damagedBlocks", "damagedBlocks"));
        } catch (Throwable e) {
            throw Throwables.propagate(e);
        }
    }

    @Override
    public void addEffect(EntityFX efx) {
        EntityFX toAdd = efx;
        if (FBP.fancyRain && toAdd instanceof EntityRainFX) {
            efx.setAlphaF(0);
        } else if (toAdd instanceof EntityDiggingFX && !(toAdd instanceof FBPParticleDigging)) {
            try {
                blockState = (IBlockState) getSourceState.invokeExact((EntityDiggingFX) efx);

                if (blockState != null && !(FBP.frozen && !FBP.spawnWhileFrozen)
                        && (FBP.spawnRedstoneBlockParticles || blockState.getBlock() != Blocks.redstone_block)) {
                    efx.setDead();

                    if (!(blockState.getBlock() instanceof BlockLiquid)
                            && !FBP.INSTANCE.isBlacklisted(blockState.getBlock())) {
                        toAdd = new FBPParticleDigging(worldObj, (double) X.invokeExact((Entity) efx),
                                (double) Y.invokeExact((Entity) efx), (double) Z.invokeExact((Entity) efx), 0, 0, 0,
                                (float) getParticleScale.invokeExact((EntityFX) efx),
                                toAdd.getRedColorF(), toAdd.getGreenColorF(),
                                toAdd.getBlueColorF(), blockState, null,
                                (TextureAtlasSprite) getParticleTexture.invokeExact((EntityFX) efx));
                    } else
                        return;
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        } else if (toAdd instanceof FBPParticleDigging) {
            try {
                blockState = (IBlockState) getSourceState.invokeExact((EntityDiggingFX) efx);

                if (blockState != null && !(FBP.frozen && !FBP.spawnWhileFrozen)
                        && (FBP.spawnRedstoneBlockParticles || blockState.getBlock() != Blocks.redstone_block)) {

                    if (blockState.getBlock() instanceof BlockLiquid
                            || FBP.INSTANCE.isBlacklisted(blockState.getBlock())) {
                        efx.setDead();
                        return;
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }

        super.addEffect(toAdd);
    }

    @Nullable
    public EntityFX spawnEffectParticle(int particleId, double xCoord, double yCoord, double zCoord, double xSpeed,
                                        double ySpeed, double zSpeed, int... parameters) {
        IParticleFactory iparticlefactory = null;

        try {
            iparticlefactory = ((Map<Integer, IParticleFactory>) getParticleTypes.invokeExact((EffectRenderer) this))
                    .get(particleId);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (iparticlefactory != null) {
            EntityFX particle = iparticlefactory.getEntityFX(particleId, mc.theWorld, xCoord, yCoord, zCoord, xSpeed,
                    ySpeed, zSpeed, parameters), toSpawn = particle;

            if (particle instanceof EntityDiggingFX && !(particle instanceof FBPParticleDigging)) {
                blockState = Block.getStateById(parameters[0]);

                if (blockState != null && !(FBP.frozen && !FBP.spawnWhileFrozen)
                        && (FBP.spawnRedstoneBlockParticles || blockState.getBlock() != Blocks.redstone_block)) {
                    if (!(blockState.getBlock() instanceof BlockLiquid)
                            && !FBP.INSTANCE.isBlacklisted(blockState.getBlock())) {
                        toSpawn = new FBPParticleDigging(mc.theWorld, xCoord, yCoord + 0.10000000149011612D, zCoord,
                                xSpeed, ySpeed, zSpeed, -1, 1, 1, 1, blockState, null, null).func_174845_l()
                                .multipleParticleScaleBy(0.6F);
                    } else
                        toSpawn = particle;
                }
            }

            this.addEffect(toSpawn);

            return toSpawn;
        }
        return null;
    }

    @Override
    public void addBlockDestroyEffects(BlockPos pos, IBlockState state) {
        Block b = state.getBlock();
        state = state.getBlock().getActualState(state, worldObj, pos);
        b = state.getBlock();
        int i = 4;

        TextureAtlasSprite texture = mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(state);

        for (int j = 0; j < FBP.particlesPerAxis; ++j) {
            for (int k = 0; k < FBP.particlesPerAxis; ++k) {
                for (int l = 0; l < FBP.particlesPerAxis; ++l) {
                    double d0 = pos.getX() + ((j + 0.5D) / FBP.particlesPerAxis);
                    double d1 = pos.getY() + ((k + 0.5D) / FBP.particlesPerAxis);
                    double d2 = pos.getZ() + ((l + 0.5D) / FBP.particlesPerAxis);

                    if (!(b instanceof BlockLiquid) && !(FBP.frozen && !FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || b != Blocks.redstone_block) && !FBP.INSTANCE.isBlacklisted(b)) {
                        float scale = (float) FBP.random.nextDouble(0.75, 1);

                        FBPParticleDigging toSpawn = new FBPParticleDigging(worldObj, d0, d1, d2,
                                d0 - pos.getX() - 0.5D, -0.001, d2 - pos.getZ() - 0.5D, scale, 1, 1, 1, state, null,
                                texture).func_174846_a(pos);

                        addEffect(toSpawn);
                    }
                }
            }
        }
    }

    @Override
    public void addBlockHitEffects(BlockPos pos, EnumFacing side) {
        IBlockState iblockstate = worldObj.getBlockState(pos);

        if (iblockstate.getBlock().getRenderType() != -1) {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            float f = 0.1F;

            AxisAlignedBB axisalignedbb = iblockstate.getBlock().getSelectedBoundingBox(worldObj, pos);

            double d0 = 0, d1 = 0, d2 = 0;

            MovingObjectPosition obj = Minecraft.getMinecraft().objectMouseOver;

            if (obj == null || obj.hitVec == null)
                obj = new MovingObjectPosition(null, new Vec3(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5));

            if (FBP.smartBreaking && !(iblockstate.getBlock() instanceof BlockLiquid) && !(FBP.frozen && !FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || iblockstate.getBlock() != Blocks.redstone_block)) {
                d0 = obj.hitVec.xCoord
                        + FBP.random.nextDouble(-0.21, 0.21) * Math.abs(axisalignedbb.maxX - axisalignedbb.minX);
                d1 = obj.hitVec.yCoord
                        + FBP.random.nextDouble(-0.21, 0.21) * Math.abs(axisalignedbb.maxY - axisalignedbb.minY);
                d2 = obj.hitVec.zCoord
                        + FBP.random.nextDouble(-0.21, 0.21) * Math.abs(axisalignedbb.maxZ - axisalignedbb.minZ);
            } else {
                d0 = i + worldObj.rand.nextDouble() * (axisalignedbb.maxX - axisalignedbb.minX - 0.20000000298023224D)
                        + 0.10000000149011612D + axisalignedbb.minX;
                d1 = j + worldObj.rand.nextDouble() * (axisalignedbb.maxY - axisalignedbb.minY - 0.20000000298023224D)
                        + 0.10000000149011612D + axisalignedbb.minY;
                d2 = k + worldObj.rand.nextDouble() * (axisalignedbb.maxZ - axisalignedbb.minZ - 0.20000000298023224D)
                        + 0.10000000149011612D + axisalignedbb.minZ;
            }

            switch (side) {
                case DOWN:
                    d1 = j + iblockstate.getBlock().getBlockBoundsMinY() - f;
                    break;
                case EAST:
                    d0 = i + iblockstate.getBlock().getBlockBoundsMaxX() + f;
                    break;
                case NORTH:
                    d2 = k + iblockstate.getBlock().getBlockBoundsMinZ() - f;
                    break;
                case SOUTH:
                    d2 = k + iblockstate.getBlock().getBlockBoundsMaxZ() + f;
                    break;
                case UP:
                    d1 = j + iblockstate.getBlock().getBlockBoundsMaxY() + f;
                    break;
                case WEST:
                    d0 = i + iblockstate.getBlock().getBlockBoundsMinX() - f;
                    break;
                default:
                    break;
            }

            if (!(iblockstate.getBlock() instanceof BlockLiquid) && !(FBP.frozen && !FBP.spawnWhileFrozen) && (FBP.spawnRedstoneBlockParticles || iblockstate.getBlock() != Blocks.redstone_block)) {

                int damage = 0;

                try {
                    DestroyBlockProgress progress;
                    Map mp = (Map<Integer, DestroyBlockProgress>) getBlockDamage
                            .invokeExact(Minecraft.getMinecraft().renderGlobal);

                    if (!mp.isEmpty()) {

                        for (Object o : mp.values()) {
                            progress = (DestroyBlockProgress) o;

                            if (progress.getPosition().equals(pos)) {
                                damage = progress.getPartialBlockDamage();
                                break;
                            }
                        }
                    }
                } catch (Throwable ignored) {

                }

                EntityFX toSpawn;

                if (!FBP.INSTANCE.isBlacklisted(iblockstate.getBlock())) {
                    toSpawn = new FBPParticleDigging(worldObj, d0, d1, d2, 0.0D, 0.0D, 0.0D, -2, 1, 1, 1, iblockstate,
                            side, null).func_174846_a(pos);

                    if (FBP.smartBreaking) {
                        toSpawn = ((FBPParticleDigging) toSpawn).MultiplyVelocity(side == EnumFacing.UP ? 0.7F : 0.15F);
                        toSpawn = toSpawn.multipleParticleScaleBy(0.325F + (damage / 10f) * 0.5F);
                    } else {
                        toSpawn = ((FBPParticleDigging) toSpawn).MultiplyVelocity(0.2F);
                        toSpawn = toSpawn.multipleParticleScaleBy(0.6F);
                    }

                    addEffect(toSpawn);
                }
            }
        }
    }
}
