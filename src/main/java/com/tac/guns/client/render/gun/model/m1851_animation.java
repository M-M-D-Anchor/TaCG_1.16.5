package com.tac.guns.client.render.gun.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.tac.guns.client.SpecialModels;
import com.tac.guns.client.render.gun.IOverrideModel;
import com.tac.guns.client.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;

/*
 * Because the revolver has a rotating chamber, we need to render it in a
 * different way than normal items. In this case we are overriding the model.
 */

/**
 * Author: Timeless Development, and associates.
 */
public class m1851_animation implements IOverrideModel {

    //The render method, similar to what is in DartEntity. We can render the item
    @Override
    public void render(float v, ItemTransforms.TransformType transformType, ItemStack stack, ItemStack parent, LivingEntity entity, PoseStack matrices, MultiBufferSource renderBuffer, int light, int overlay) {
        //The render method, similar to what is in DartEntity. We can render the item
        
        RenderUtil.renderModel(SpecialModels.M1851.getModel(), stack, matrices, renderBuffer, light, overlay);
        RenderUtil.renderModel(SpecialModels.M1851_HAMMER.getModel(), stack, matrices, renderBuffer, light, overlay);
        //Make sure the player has it
//        if(entity.equals(Minecraft.getInstance().player)) {

        //Always push
        matrices.pushPose();

        //We're getting the cooldown tracker for the item - items like the sword, ender pearl, and chorus fruit all have this too.
        ItemCooldowns tracker = Minecraft.getInstance().player.getCooldowns();
        float cooldown = tracker.getCooldownPercent(stack.getItem(), Minecraft.getInstance().getFrameTime());
        cooldown = (float) easeInOutBack(cooldown);

        //Here we're moving the model into position
        matrices.translate(0, -5.0 * 0.0625, 0);

        //We rotate the chamber part of the model according to the cooldown variable above, which is manipulated by the method provided below.
        matrices.mulPose(Vector3f.ZN.rotationDegrees(20F * cooldown));
        //Then move it
        matrices.translate(0, 5.0 * 0.0625, 0.0);

        //Render the chamber part of the gun
        RenderUtil.renderModel(SpecialModels.M1851_CYLINDER.getModel(), stack, matrices, renderBuffer, light, overlay);

        /*matrices.rotate(Vector3f.ZP.rotationDegrees(15F * cooldown));

        //matrices.pushPose();

        if (cooldown != 0) {
            matrices.translate(0, -0.82, 0);
            matrices.rotate(Vector3f.XN.rotationDegrees(90F));
            //matrices.translate(0, 0.82, 0);
        }

        matrices.rotate(Vector3f.XP.rotationDegrees(90F));
        RenderUtil.renderModel(SpecialModels.M1851_HAMMER.getModel(), stack, matrices, renderBuffer, light, overlay);

        */

        //Always pop
        matrices.popPose();
    }
    private double easeInOutBack(double x) {
        double c1 = 1.70158;
        double c2 = c1 * 1.525;
        return (x < 0.5 ? (Math.pow(2 * x, 2) * ((c2 + 1) * 2 * x - c2)) / 2 : (Math.pow(2 * x - 2, 2) * ((c2 + 1) * (x * 2 - 2) + c2) + 2) / 2);
    }
     
    //TODO comments
}