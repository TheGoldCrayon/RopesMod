package com.github.thegoldcrayon.tgcropesmod;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TGCRopesMod.MODID)
public class TGCRopesMod
{
    public static final String MODID = "tgcropesmod";

    public static final Logger LOGGER = LogManager.getLogger();

    public TGCRopesMod()
    {
        LOGGER.debug("Hello from the Ropes Mod!");
    }

}
