package net.pekkatrol.hg2t.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Attr;

public class EnergizedGolemEntity extends Monster {

    private enum AnimationType {
        IDLE,
        WALK,
        ATTACK,
        DEATH
    }

    private AnimationType currentAnimation = null;

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState attackAnimationState = new AnimationState();
    public final AnimationState deathAnimationState = new AnimationState();

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 300)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 10)
                .add(Attributes.FOLLOW_RANGE, 20D)
                .add(Attributes.ATTACK_DAMAGE, 12.0D);
    }

    public EnergizedGolemEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));

        this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        this.swing(InteractionHand.MAIN_HAND);
        return super.doHurtTarget(entity);
    }

    @Override
    protected void tickDeath() {
        this.deathTime++;

        if (this.deathTime >= 40) {
            this.remove(RemovalReason.KILLED);
        }
    }

    private void setupAnimationStates() {

        AnimationType newAnimation;

        if (this.isDeadOrDying()) {
            newAnimation = AnimationType.DEATH;
        }
        else if (this.swinging) {
            newAnimation = AnimationType.ATTACK;
        }
        else if (this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D) {
            newAnimation = AnimationType.WALK;
        }
        else {
            newAnimation = AnimationType.IDLE;
        }

        if (newAnimation != currentAnimation) {
            stopAllAnimations();
            currentAnimation = newAnimation;

            switch (currentAnimation) {
                case IDLE -> idleAnimationState.start(this.tickCount);
                case WALK -> walkAnimationState.start(this.tickCount);
                case ATTACK -> attackAnimationState.start(this.tickCount);
                case DEATH -> deathAnimationState.start(this.tickCount);
            }
        }
    }

    private void stopAllAnimations() {
        idleAnimationState.stop();
        walkAnimationState.stop();
        attackAnimationState.stop();
        deathAnimationState.stop();
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }
}
