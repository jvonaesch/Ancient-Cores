package me.FenrisFox86.ancientcores.common.capabilities;

import net.minecraft.entity.player.PlayerEntity;

public class FenrisPlayerReader {

    public static Float getFenrisRpgFloat(PlayerEntity player, String key) {
        Float stateIn = player.getCapability(FenrisStateProvider.CAPABILITY).orElse(new DefaultModState())
                .getFenrisRpgFloat(key);
        return stateIn;
    }

    public static void setFenrisRpgFloat(PlayerEntity player, String key, Float stateIn) {
        player.getCapability(FenrisStateProvider.CAPABILITY).orElse(new DefaultModState())
                .setFenrisRpgFloat(key, stateIn);
    }

    public static void addFenrisState(PlayerEntity player, String key, Float add) {
        setFenrisRpgFloat(player, key, getFenrisRpgFloat(player, key) + add);
    }
}
