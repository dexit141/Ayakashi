package net.minecraft.client.renderer.entity;

import Ayakashi.mods.ItemPhysics.ItemPhysics;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import java.util.Random;

public class RenderEntityItem extends Render<EntityItem> {
    private final Random field_177079_e = new Random();

    public RenderEntityItem(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize = 0.15F;
        this.shadowOpaque = 0.75F;
    }

    private int func_177077_a(EntityItem itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
        ItemStack itemstack = itemIn.getEntityItem();
        Item item = itemstack.getItem();
        if (item == null) {
            return 0;
        } else {
            boolean flag = p_177077_9_.isGui3d();
            int i = this.func_177078_a(itemstack);
            float f = 0.25F;
            float f1 = MathHelper.sin(((float) itemIn.getAge() + p_177077_8_) / 10.0F + itemIn.hoverStart) * 0.1F + 0.1F;
            float f2 = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
            GlStateManager.translate((float) p_177077_2_, (float) p_177077_4_ + f1 + 0.25F * f2, (float) p_177077_6_);
            float f6;
            if (flag || this.renderManager.options != null) {
                f6 = (((float) itemIn.getAge() + p_177077_8_) / 20.0F + itemIn.hoverStart) * 57.295776F;
                GlStateManager.rotate(f6, 0.0F, 1.0F, 0.0F);
            }

            if (!flag) {
                f6 = -0.0F * (float) (i - 1) * 0.5F;
                float f4 = -0.0F * (float) (i - 1) * 0.5F;
                float f5 = -0.046875F * (float) (i - 1) * 0.5F;
                GlStateManager.translate(f6, f4, f5);
            }

            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            return i;
        }
    }

    private int func_177078_a(ItemStack stack) {
        int i = 1;
        if (stack.stackSize > 48) {
            i = 5;
        } else if (stack.stackSize > 32) {
            i = 4;
        } else if (stack.stackSize > 16) {
            i = 3;
        } else if (stack.stackSize > 1) {
            i = 2;
        }

        return i;
    }

    public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
        ItemPhysics.doRender(entity, x, y, z, partialTicks);
    }

    protected ResourceLocation getEntityTexture(EntityItem entity) {
        return TextureMap.locationBlocksTexture;
    }
}
