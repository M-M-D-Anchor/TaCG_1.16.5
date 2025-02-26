package com.tac.guns.client.settings;

import com.tac.guns.Config;
import com.tac.guns.client.handler.CrosshairHandler;
import com.tac.guns.client.render.crosshair.Crosshair;
import net.minecraft.client.AbstractOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.client.settings.SliderPercentageOption;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TranslationTextComponent;

import java.text.DecimalFormat;

/**
 * Author: Forked from MrCrayfish, continued by Timeless devs
 */
public class GunOptions
{
    private static final DecimalFormat FORMAT = new DecimalFormat("0.0#");

    public static final SliderPercentageOption ADS_SENSITIVITY = new GunSliderPercentageOption("tac.options.adsSensitivity", 0.0, 2.0, 0.01F, gameSettings -> {
        return Config.CLIENT.controls.aimDownSightSensitivity.get();
    }, (gameSettings, value) -> {
        Config.CLIENT.controls.aimDownSightSensitivity.set(MathHelper.clamp(value, 0.0, 2.0));
        Config.saveClientConfig();
    }, (gameSettings, option) -> {
        double adsSensitivity = Config.CLIENT.controls.aimDownSightSensitivity.get();
        return new TranslationTextComponent("tac.options.adsSensitivity.format", FORMAT.format(adsSensitivity));
    });

   /* public static final BooleanOption BURST_MECH = new BooleanOption("tac.options.burstPress", (settings) -> {
        return Config.CLIENT.controls.burstPress.get();
    }, (settings, value) -> {
        Config.CLIENT.controls.burstPress.set(value);
        Config.saveClientConfig();
    });
    // this.minecraft.displayGuiScreen
*/

    public static final AbstractOption CROSSHAIR = new GunListOption<>("tac.options.crosshair", () -> {
        return CrosshairHandler.get().getRegisteredCrosshairs();
    }, () -> {
        return ResourceLocation.tryCreate(Config.CLIENT.display.crosshair.get());
    }, (value) -> {
        Config.CLIENT.display.crosshair.set(value.toString());
        Config.saveClientConfig();
        CrosshairHandler.get().setCrosshair(value);
    }, (value) -> {
        ResourceLocation id = value.getLocation();
        return new TranslationTextComponent(id.getNamespace() + ".crosshair." + id.getPath());
    }).setRenderer((button, matrixStack, partialTicks) -> {
        matrixStack.push();
        matrixStack.translate(button.x, button.y, 0);
        matrixStack.translate(button.getWidth() + 2, 2, 0);
        Crosshair crosshair = CrosshairHandler.get().getCurrentCrosshair();
        if(crosshair != null)
        {
            if(crosshair.isDefault())
            {
                Minecraft mc = Minecraft.getInstance();
                mc.getTextureManager().bindTexture(AbstractGui.GUI_ICONS_LOCATION);
                AbstractGui.blit(matrixStack, (16 - 15) / 2, (16 - 15) / 2, 0, 0, 0, 15, 15, 256, 256);
            }
            else
            {
                crosshair.render(Minecraft.getInstance(), matrixStack, 16, 16, partialTicks);
            }
        }
        matrixStack.pop();
    });
    public static final BooleanOption HOLD_TO_AIM = new BooleanOption("tac.options.holdToAim", (settings) -> {
        return Config.CLIENT.controls.holdToAim.get();
    }, (settings, value) -> {
        Config.CLIENT.controls.holdToAim.set(value);
        Config.saveClientConfig();
    });

    public static final BooleanOption ALLOW_LEVER = new BooleanOption("tac.options.allowLever", (settings) -> {
        return Config.CLIENT.rightClickUse.allowLever.get();
    }, (settings, value) -> {
        Config.CLIENT.rightClickUse.allowLever.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption ALLOW_BUTTON = new BooleanOption("tac.options.allowButton", (settings) -> {
        return Config.CLIENT.rightClickUse.allowButton.get();
    }, (settings, value) -> {
        Config.CLIENT.rightClickUse.allowButton.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption ALLOW_DOORS = new BooleanOption("tac.options.allowDoors", (settings) -> {
        return Config.CLIENT.rightClickUse.allowDoors.get();
    }, (settings, value) -> {
        Config.CLIENT.rightClickUse.allowDoors.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption ALLOW_TRAP_DOORS = new BooleanOption("tac.options.allowTrapDoors", (settings) -> {
        return Config.CLIENT.rightClickUse.allowTrapDoors.get();
    }, (settings, value) -> {
        Config.CLIENT.rightClickUse.allowTrapDoors.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption ALLOW_CRAFTING_TABLE = new BooleanOption("tac.options.allowCraftingTable", (settings) -> {
        return Config.CLIENT.rightClickUse.allowCraftingTable.get();
    }, (settings, value) -> {
        Config.CLIENT.rightClickUse.allowCraftingTable.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption ALLOW_CHESTS = new BooleanOption("tac.options.allowChests", (settings) -> {
        return Config.CLIENT.rightClickUse.allowChests.get();
    }, (settings, value) -> {
        Config.CLIENT.rightClickUse.allowChests.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption ALLOW_FENCE_GATES = new BooleanOption("tac.options.allowFenceGates", (settings) -> {
        return Config.CLIENT.rightClickUse.allowFenceGates.get();
    }, (settings, value) -> {
        Config.CLIENT.rightClickUse.allowFenceGates.set(value);
        Config.saveClientConfig();
    });

    public static final BooleanOption DOUBLE_RENDER_EXIST = new BooleanOption("tac.options.doubleRender", (settings) -> {
        return Config.CLIENT.display.scopeDoubleRender.get();
    }, (settings, value) -> {
        Config.CLIENT.display.scopeDoubleRender.set(false);
        Config.saveClientConfig();
    });
    public static final BooleanOption BANNED_DROP = new BooleanOption("tac.options.bannedDrop", (settings) -> {
        return Config.COMMON.gameplay.bannedDrop.get();
    }, (settings, value) -> {
        Config.COMMON.gameplay.bannedDrop.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption SHOW_FPS_TRAILS_EXIST = new BooleanOption("tac.options.showFirstPersonBulletTrails", (settings) -> {
        return Config.CLIENT.display.showFirstPersonBulletTrails.get();
    }, (settings, value) -> {
        Config.CLIENT.display.showFirstPersonBulletTrails.set(value);
        Config.saveClientConfig();
    });
    public static final BooleanOption REDDOT_SQUISH_EXIST = new BooleanOption("tac.options.reddotSquish", (settings) -> {
        return Config.CLIENT.display.redDotSquishUpdate.get();
    }, (settings, value) -> {
        Config.CLIENT.display.redDotSquishUpdate.set(value);
        Config.saveClientConfig();
    });

    public static final BooleanOption FIREMODE_EXIST = new BooleanOption("tac.options.firemodeExist", (settings) -> {
        return Config.CLIENT.weaponGUI.weaponTypeIcon.showWeaponIcon.get();
    }, (settings, value) -> {
        Config.CLIENT.weaponGUI.weaponTypeIcon.showWeaponIcon.set(value);
        Config.saveClientConfig();
    });
    //Firemode positioning

    public static final SliderPercentageOption X_MAIN_POS = new GunSliderPercentageOption("tac.options.xMainPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.mainX.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.mainX.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.mainX.get();
                return new TranslationTextComponent("tac.options.xMainPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption Y_MAIN_POS = new GunSliderPercentageOption("tac.options.yMainPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.mainY.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.mainY.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.mainY.get();
                return new TranslationTextComponent("tac.options.yMainPos.format", FORMAT.format(adsSensitivity));
            });

    public static final SliderPercentageOption X_FIREMODE_POS = new GunSliderPercentageOption("tac.options.xFiremodePos", -500, 500, 0.001F,
    gameSettings ->
    {
        return Config.CLIENT.weaponGUI.weaponFireMode.x.get();
        //return Config.CLIENT.controls.aimDownSightSensitivity.get();
    },
    (gameSettings, value) ->
    {
        Config.CLIENT.weaponGUI.weaponFireMode.x.set(MathHelper.clamp(value, -500, 500));
        Config.saveClientConfig();
    },
    (gameSettings, option) -> {
        double adsSensitivity = Config.CLIENT.weaponGUI.weaponFireMode.x.get();
        return new TranslationTextComponent("tac.options.xFiremodePos.format", FORMAT.format(adsSensitivity));
    });
    public static final SliderPercentageOption Y_FIREMODE_POS = new GunSliderPercentageOption("tac.options.yFiremodePos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponFireMode.y.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponFireMode.y.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponFireMode.y.get();
                return new TranslationTextComponent("tac.options.yFiremodePos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption SIZE_FIREMODE_POS = new GunSliderPercentageOption("tac.options.sizeFiremodePos", 0.1, 4, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponFireMode.weaponFireModeSize.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponFireMode.weaponFireModeSize.set(MathHelper.clamp(value, 0.1, 4));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponFireMode.weaponFireModeSize.get();
                return new TranslationTextComponent("tac.options.sizeFiremodePos.format", FORMAT.format(adsSensitivity));
            });

    //AmmoCounter positioning
    public static final BooleanOption AMMOCOUNTER_EXIST = new BooleanOption("tac.options.ammoCounterExist", (settings) -> {
        return Config.CLIENT.weaponGUI.weaponAmmoCounter.showWeaponAmmoCounter.get();
    }, (settings, value) -> {
        Config.CLIENT.weaponGUI.weaponAmmoCounter.showWeaponAmmoCounter.set(value);
        Config.saveClientConfig();
    });
    public static final SliderPercentageOption X_AMMOCOUNTER_POS = new GunSliderPercentageOption("tac.options.xAmmoCounterPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponAmmoCounter.x.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponAmmoCounter.x.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponAmmoCounter.x.get();
                return new TranslationTextComponent("tac.options.xAmmoCounterPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption Y_AMMOCOUNTER_POS = new GunSliderPercentageOption("tac.options.yAmmoCounterPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponAmmoCounter.y.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponAmmoCounter.y.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponAmmoCounter.y.get();
                return new TranslationTextComponent("tac.options.yAmmoCounterPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption SIZE_AMMOCOUNTER_POS = new GunSliderPercentageOption("tac.options.sizeAmmoCounterPos", 0.1, 4, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponAmmoCounter.weaponAmmoCounterSize.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponAmmoCounter.weaponAmmoCounterSize.set(MathHelper.clamp(value, 0.1, 4));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponAmmoCounter.weaponAmmoCounterSize.get();
                return new TranslationTextComponent("tac.options.sizeAmmoCounterPos.format", FORMAT.format(adsSensitivity));
            });

    //WeaponIcon positioning
    public static final BooleanOption WeaponIcon_EXIST = new BooleanOption("tac.options.iconExist", (settings) -> {
        return Config.CLIENT.weaponGUI.weaponTypeIcon.showWeaponIcon.get();
    }, (settings, value) -> {
        Config.CLIENT.weaponGUI.weaponTypeIcon.showWeaponIcon.set(value);
        Config.saveClientConfig();
    });
    public static final SliderPercentageOption X_Icon_POS = new GunSliderPercentageOption("tac.options.xIconPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponTypeIcon.x.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponTypeIcon.x.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponTypeIcon.x.get();
                return new TranslationTextComponent("tac.options.xIconPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption Y_Icon_POS = new GunSliderPercentageOption("tac.options.yIconPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponTypeIcon.y.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponTypeIcon.y.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponTypeIcon.y.get();
                return new TranslationTextComponent("tac.options.yIconPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption SIZE_Icon_POS = new GunSliderPercentageOption("tac.options.sizeIconPos", 0.1, 4, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponTypeIcon.weaponIconSize.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponTypeIcon.weaponIconSize.set(MathHelper.clamp(value, 0.1, 4));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponTypeIcon.weaponIconSize.get();
                return new TranslationTextComponent("tac.options.sizeIconPos.format", FORMAT.format(adsSensitivity));
            });

    //WeaponIcon positioning
    public static final BooleanOption ReloadBar_EXIST = new BooleanOption("tac.options.reloadBarExist", (settings) -> {
        return Config.CLIENT.weaponGUI.weaponReloadTimer.showWeaponReloadTimer.get();
    }, (settings, value) -> {
        Config.CLIENT.weaponGUI.weaponReloadTimer.showWeaponReloadTimer.set(value);
        Config.saveClientConfig();
    });
    public static final SliderPercentageOption X_ReloadBar_POS = new GunSliderPercentageOption("tac.options.xReloadBarPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponReloadTimer.x.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponReloadTimer.x.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponReloadTimer.x.get();
                return new TranslationTextComponent("tac.options.xReloadBarPos.format", FORMAT.format(adsSensitivity));
            });

    public static final BooleanOption OVERHEAT_EXIST = new BooleanOption("tac.options.overheatExist", (settings) -> {
        return Config.CLIENT.weaponGUI.weaponOverheatBar.showWeaponOverheatBar.get();
    }, (settings, value) -> {
        Config.CLIENT.weaponGUI.weaponOverheatBar.showWeaponOverheatBar.set(value);
        Config.saveClientConfig();
    });

    public static final SliderPercentageOption OVERHEAT_ALPHA = new GunSliderPercentageOption("tac.options.overheatAlpha", 0f, 1f, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponOverheatBar.weaponOverheatBarAlpha.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponOverheatBar.weaponOverheatBarAlpha.set(MathHelper.clamp(value, 0f, 1f));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double alpha = Config.CLIENT.weaponGUI.weaponOverheatBar.weaponOverheatBarAlpha.get();
                return new TranslationTextComponent("tac.options.overheatAlpha.format", FORMAT.format(alpha));
            });

    public static final SliderPercentageOption Fire_Volume = new GunSliderPercentageOption("tac.options.weaponsVolume", 0f, 1f, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.sounds.weaponsVolume.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.sounds.weaponsVolume.set(MathHelper.clamp(value, 0f, 1f));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.sounds.weaponsVolume.get();
                return new TranslationTextComponent("tac.options.weaponsVolume.format", FORMAT.format(adsSensitivity));
            });

    public static final SliderPercentageOption BARREL_VOLUME = new GunSliderPercentageOption("tac.options.barrelVolume", 0f, 1f, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.sounds.barrelVolume.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.sounds.barrelVolume.set(MathHelper.clamp(value, 0f, 1f));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.sounds.barrelVolume.get();
                return new TranslationTextComponent("tac.options.barrelVolume.format", FORMAT.format(adsSensitivity));
            });

    public static final SliderPercentageOption BARREL_LOW_VOLUME = new GunSliderPercentageOption("tac.options.barrelLowVolume", 0f, 1f, 0.01F,
            gameSettings ->
            {
                return Config.CLIENT.sounds.barrelLowVolume.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.sounds.barrelLowVolume.set(MathHelper.clamp(value, 0f, 1f));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.sounds.barrelLowVolume.get();
                return new TranslationTextComponent("tac.options.barrelLowVolume.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption Y_ReloadBar_POS = new GunSliderPercentageOption("tac.options.yReloadBarPos", -500, 500, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponReloadTimer.y.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponReloadTimer.y.set(MathHelper.clamp(value, -500, 500));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponReloadTimer.y.get();
                return new TranslationTextComponent("tac.options.yReloadBarPos.format", FORMAT.format(adsSensitivity));
            });
    public static final SliderPercentageOption SIZE_ReloadBar_POS = new GunSliderPercentageOption("tac.options.sizeReloadBarPos", 0.1, 4, 0.001F,
            gameSettings ->
            {
                return Config.CLIENT.weaponGUI.weaponReloadTimer.weaponReloadTimerSize.get();
                //return Config.CLIENT.controls.aimDownSightSensitivity.get();
            },
            (gameSettings, value) ->
            {
                Config.CLIENT.weaponGUI.weaponReloadTimer.weaponReloadTimerSize.set(MathHelper.clamp(value, 0.1, 4));
                Config.saveClientConfig();
            },
            (gameSettings, option) -> {
                double adsSensitivity = Config.CLIENT.weaponGUI.weaponReloadTimer.weaponReloadTimerSize.get();
                return new TranslationTextComponent("tac.options.sizeReloadBarPos.format", FORMAT.format(adsSensitivity));
            });
}