package xyz.achu.vandalize.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    private static final Logger logger = LogManager.getLogger();

    /* Check out: net/minecraft/client/gui/screen/TitleScreen.java
     * We have the line: this.splashText = this.minecraft.getSplashTextLoader().get();
     * I have edited this callback directly so that get() gives us a custom string instead!
     */
    @Redirect(method="init", at = @At(value="INVOKE", target="Lnet/minecraft/client/resource/SplashTextResourceSupplier;get()Ljava/lang/String;"))
    public String mixinInitGet(SplashTextResourceSupplier splashTextResourceSupplier) {
        logger.info("The UwU injection has begun!");
        return "Hewwo UwU";
    }

    @Redirect(method="render", at = @At(value="INVOKE", target="Lnet/minecraft/client/gui/DrawableHelper;drawString(Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V"))
    public void mixinRenderTextRenderer(DrawableHelper drawableHelper, TextRenderer textRenderer, String str, int x, int y, int color) {
        logger.info("this is a test");
    }
}
