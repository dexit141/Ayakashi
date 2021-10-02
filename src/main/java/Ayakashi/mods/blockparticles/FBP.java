package Ayakashi.mods.blockparticles;

import Ayakashi.mods.ReflectionHelper;
import Ayakashi.mods.blockparticles.handler.FBPEventHandler;
import Ayakashi.mods.blockparticles.particle.FBPParticleManager;
import com.google.common.base.Throwables;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;

import java.io.File;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SplittableRandom;

public class FBP {
    public static final ResourceLocation LOCATION_PARTICLE_TEXTURE;
    public static final Vec3[] CUBE;
    public static final Vec3[] CUBE_NORMALS;
    public static FBP INSTANCE;
    public static File config;
    public static int minAge;
    public static int maxAge;
    public static int particlesPerAxis;
    public static double scaleMult;
    public static double gravityMult;
    public static double rotationMult;
    public static boolean enabled;
    public static boolean infiniteDuration;
    public static boolean randomRotation;
    public static boolean cartoonMode;
    public static boolean spawnWhileFrozen;
    public static boolean spawnRedstoneBlockParticles;
    public static boolean randomizedScale;
    public static boolean randomFadingSpeed;
    public static boolean entityCollision;
    public static boolean bounceOffWalls;
    public static boolean lowTraction;
    public static boolean smartBreaking;
    public static boolean fancyRain;
    public static boolean fancyFlame;
    public static boolean fancySmoke;
    public static boolean waterPhysics;
    public static boolean restOnFloor;
    public static boolean frozen;
    public static SplittableRandom random;
    public static VertexFormat POSITION_TEX_COLOR_LMAP_NORMAL;
    public static MethodHandle setSourcePos;
    public static FBPParticleManager fancyEffectRenderer;
    public static EffectRenderer originalEffectRenderer;

    static {
        LOCATION_PARTICLE_TEXTURE = new ResourceLocation("textures/particle/particles.png");
        CUBE = new Vec3[]{new Vec3(1.0, 1.0, -1.0), new Vec3(1.0, 1.0, 1.0), new Vec3(-1.0, 1.0, 1.0), new Vec3(-1.0, 1.0, -1.0), new Vec3(-1.0, -1.0, -1.0), new Vec3(-1.0, -1.0, 1.0), new Vec3(1.0, -1.0, 1.0), new Vec3(1.0, -1.0, -1.0), new Vec3(-1.0, -1.0, 1.0), new Vec3(-1.0, 1.0, 1.0), new Vec3(1.0, 1.0, 1.0), new Vec3(1.0, -1.0, 1.0), new Vec3(1.0, -1.0, -1.0), new Vec3(1.0, 1.0, -1.0), new Vec3(-1.0, 1.0, -1.0), new Vec3(-1.0, -1.0, -1.0), new Vec3(-1.0, -1.0, -1.0), new Vec3(-1.0, 1.0, -1.0), new Vec3(-1.0, 1.0, 1.0), new Vec3(-1.0, -1.0, 1.0), new Vec3(1.0, -1.0, 1.0), new Vec3(1.0, 1.0, 1.0), new Vec3(1.0, 1.0, -1.0), new Vec3(1.0, -1.0, -1.0)};
        CUBE_NORMALS = new Vec3[]{new Vec3(0.0, 1.0, 0.0), new Vec3(0.0, -1.0, 0.0), new Vec3(0.0, 0.0, 1.0), new Vec3(0.0, 0.0, -1.0), new Vec3(-1.0, 0.0, 0.0), new Vec3(1.0, 0.0, 0.0)};
        FBP.config = null;
        FBP.enabled = true;
        FBP.infiniteDuration = false;
        FBP.random = new SplittableRandom();
    }

    public List<String> blockParticleBlacklist;
    public List<Material> floatingMaterials;
    public FBPEventHandler eventHandler;

    public FBP() {
        this.eventHandler = new FBPEventHandler();
        FBP.INSTANCE = this;
        (FBP.POSITION_TEX_COLOR_LMAP_NORMAL = new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F);
        FBP.POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.TEX_2F);
        FBP.POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.COLOR_4UB);
        FBP.POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.TEX_2S);
        FBP.POSITION_TEX_COLOR_LMAP_NORMAL.addElement(DefaultVertexFormats.NORMAL_3B);
        this.blockParticleBlacklist = Collections.synchronizedList(new ArrayList<>());
        this.floatingMaterials = Collections.synchronizedList(new ArrayList<>());
    }

    public static boolean isEnabled() {
        if (!FBP.enabled) {
            FBP.frozen = false;
        }
        return FBP.enabled;
    }

    public void onStart() {
        final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        try {
            FBP.setSourcePos = lookup.unreflectSetter(ReflectionHelper.findField(EntityDiggingFX.class, "sourcePos", "sourcePos"));
        } catch (Exception e) {
            throw Throwables.propagate(e);
        }
        this.syncWithModule();
    }

    public boolean isBlacklisted(final Block b) {
        return b == null || this.blockParticleBlacklist.contains(b.getLocalizedName());
    }

    public boolean doesMaterialFloat(final Material mat) {
        return this.floatingMaterials.contains(mat);
    }

    public void syncWithModule() {
        FBP.minAge = 10;
        FBP.maxAge = 50;
        FBP.infiniteDuration = false;
        FBP.particlesPerAxis = 3;
        FBP.scaleMult = 0.7;
        FBP.gravityMult = 1.0;
        FBP.rotationMult = 1.0;
        FBP.randomRotation = true;
        FBP.cartoonMode = false;
        FBP.randomizedScale = true;
        FBP.randomFadingSpeed = true;
        FBP.spawnRedstoneBlockParticles = true;
        FBP.entityCollision = true;
        FBP.bounceOffWalls = true;
        FBP.lowTraction = false;
        FBP.smartBreaking = true;
        FBP.fancyFlame = true;
        FBP.fancySmoke = true;
        FBP.waterPhysics = true;
        FBP.restOnFloor = true;
    }
}
