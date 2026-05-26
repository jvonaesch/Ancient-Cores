package me.FenrisFox86.ancientcores.common.items.core;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;

public enum CoreType {
    MAGMA("magma",
            Attributes.MAX_HEALTH, 4,
            Attributes.ATTACK_DAMAGE, 4,
            Attributes.KNOCKBACK_RESISTANCE, 4,
            null, 0 // Instead give magma walker
    ),
    DYNAMO("dynamo",
            Attributes.ATTACK_SPEED, 4,
            Attributes.ATTACK_KNOCKBACK, 4,
            Attributes.MOVEMENT_SPEED, 4,
            Attributes.JUMP_STRENGTH, 4
    ),;


    public final String core_name;
    public final AttributeModifier headModifier;
    public final AttributeModifier bodyModifier;
    public final AttributeModifier legsModifier;
    public final AttributeModifier feetModifier;
    public final Attribute headAttribute;
    public final Attribute bodyAttribute;
    public final Attribute legsAttribute;
    public final Attribute feetAttribute;

    private CoreType(
            String core_name,
            Attribute headAttribute, int headValue,
            Attribute bodyAttribute, int bodyValue,
            Attribute legsAttribute, int legsValue,
            Attribute feetAttribute, int feetValue) {
        this.core_name = core_name;
        this.headAttribute = headAttribute;
        this.bodyAttribute = bodyAttribute;
        this.legsAttribute = legsAttribute;
        this.feetAttribute = feetAttribute;
        this.headModifier = coreModifier("head", headValue);
        this.bodyModifier = coreModifier("body", bodyValue);
        this.legsModifier = coreModifier("legs", legsValue);
        this.feetModifier = coreModifier("feet", feetValue);
    }

    private AttributeModifier coreModifier (String name, double value) {
        return new AttributeModifier(
                core_name + ":" + name + "_modifier",
                value,
                AttributeModifier.Operation.ADDITION);
    }
}
