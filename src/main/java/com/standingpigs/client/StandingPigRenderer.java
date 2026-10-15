package com.standingpigs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.SaddleLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Pig;

public class StandingPigRenderer extends MobRenderer<Pig, StandingPigModel> {
    private static final ResourceLocation PIG_TEXTURE = new ResourceLocation("textures/entity/pig/pig.png");
    private static final ResourceLocation SADDLE_TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");

    public StandingPigRenderer(EntityRendererProvider.Context context) {
        super(context, new StandingPigModel(context.bakeLayer(ModelLayers.PIG)), 0.6F);
        this.addLayer(new SaddleLayer<>(this, new StandingPigModel(context.bakeLayer(ModelLayers.PIG_SADDLE)), SADDLE_TEXTURE));
    }

    @Override
    public ResourceLocation getTextureLocation(Pig pig) {
        return PIG_TEXTURE;
    }

    @Override
    protected void setupRotations(Pig pig, PoseStack poseStack, float bob, float yBodyRot, float partialTick) {
        super.setupRotations(pig, poseStack, bob, yBodyRot, partialTick);
        if (!pig.isDeadOrDying()) {
            // little waddle while walking
            float speed = Math.min(pig.walkAnimation.speed(partialTick), 1.0F);
            float pos = pig.walkAnimation.position(partialTick);
            poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.sin(pos * 0.6662F) * 5.0F * speed));
        }
    }

    @Override
    protected void scale(Pig pig, PoseStack poseStack, float partialTick) {
        // upright pig is tall, shrink it a bit so it roughly fits the hitbox
        poseStack.scale(0.75F, 0.75F, 0.75F);
    }
}
