package com.standingpigs.client;

import net.minecraft.client.model.PigModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.animal.Pig;

/**
 * Vanilla pig model, re-posed so the pig stands upright on its two hind legs.
 * The front legs become arms that swing while walking and sway while idle.
 */
public class StandingPigModel extends PigModel<Pig> {

    public StandingPigModel(ModelPart root) {
        super(root);
    }

    @Override
    public void setupAnim(Pig pig, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // vanilla sets head look direction and leg swing; we then override the pose
        super.setupAnim(pig, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        // Body upright (belly faces forward), head on top
        this.body.setPos(0.0F, 12.0F, 3.0F);
        this.body.xRot = 0.0F;
        this.body.yRot = 0.0F;
        this.body.zRot = 0.0F;
        this.head.setPos(0.0F, 0.0F, 2.0F);

        // Legs and arms
        this.rightHindLeg.setPos(-3.0F, 18.0F, 0.0F);
        this.leftHindLeg.setPos(3.0F, 18.0F, 0.0F);
        this.rightFrontLeg.setPos(-7.0F, 4.0F, 0.0F);
        this.leftFrontLeg.setPos(7.0F, 4.0F, 0.0F);

        float swing = limbSwing * 0.6662F;
        float amount = Math.min(limbSwingAmount, 1.0F);

        this.rightHindLeg.xRot = Mth.cos(swing) * 1.2F * amount;
        this.leftHindLeg.xRot = Mth.cos(swing + (float) Math.PI) * 1.2F * amount;
        this.rightHindLeg.yRot = 0.0F;
        this.leftHindLeg.yRot = 0.0F;
        this.rightHindLeg.zRot = 0.0F;
        this.leftHindLeg.zRot = 0.0F;

        // arms swing opposite to the leg on the same side
        float idleSway = Mth.sin(ageInTicks * 0.067F) * 0.04F;
        float idleSide = Mth.cos(ageInTicks * 0.09F) * 0.04F + 0.08F;

        this.rightFrontLeg.xRot = Mth.cos(swing + (float) Math.PI) * 1.0F * amount + idleSway;
        this.leftFrontLeg.xRot = Mth.cos(swing) * 1.0F * amount - idleSway;
        this.rightFrontLeg.yRot = 0.0F;
        this.leftFrontLeg.yRot = 0.0F;
        this.rightFrontLeg.zRot = idleSide;
        this.leftFrontLeg.zRot = -idleSide;
    }
}
