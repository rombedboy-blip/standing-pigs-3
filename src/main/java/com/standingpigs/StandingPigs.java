package com.standingpigs;

import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(StandingPigs.MOD_ID)
public class StandingPigs {
    public static final String MOD_ID = "standingpigs";

    public StandingPigs() {
        // Visual-only mod: works on the client even if the server does not have it.
        ModLoadingContext.get().registerExtensionPoint(
                IExtensionPoint.DisplayTest.class,
                () -> new IExtensionPoint.DisplayTest(
                        () -> IExtensionPoint.DisplayTest.IGNORESERVERONLY,
                        (remote, isServer) -> true));
    }
}
