package me.FenrisFox86.ancientcores.common.capabilities;

import net.minecraft.entity.player.PlayerEntity;

public class ModPlayerReader {

    public static Float getFenrisRpgFloat(PlayerEntity player, String key) {
        Float stateIn = player.getCapability(FenrisStateProvider.CAPABILITY).orElse(new DefaultModState())
                .getFenrisRpgFloat(key);
        return stateIn;
    }

    public static void setModFloat(PlayerEntity player, String key, Float stateIn) {
        player.getCapability(FenrisStateProvider.CAPABILITY).orElse(new DefaultModState())
                .setFenrisRpgFloat(key, stateIn);
    }

    public static void addFenrisState(PlayerEntity player, String key, Float add) {
        setModFloat(player, key, getFenrisRpgFloat(player, key) + add);
    }
}
