package xyz.achu.vandalize.mixin;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen extends Screen  {
    @Shadow
    private int copyrightTextWidth;
    @Shadow
    private int copyrightTextX;

    private String copyrightMessage = "OwO What's this?";

    protected MixinTitleScreen(Text title) {
        super(title);
    }

    /* Check out: net/minecraft/client/gui/screen/TitleScreen.java
     * We have the line: this.splashText = this.minecraft.getSplashTextLoader().get();
     * I have edited this callback directly so that get() gives us a custom string instead!
     */

    @Redirect(method="init", at = @At(value="INVOKE", target="Lnet/minecraft/client/resource/SplashTextResourceSupplier;get()Ljava/lang/String;"))
    public String mixinInitGet(SplashTextResourceSupplier splashTextResourceSupplier) {
        return "Go fuck yourself :D";
    }

    @Inject(method="init", at = @At("RETURN"))
    public void init(CallbackInfo ci) {
        this.copyrightTextWidth = this.font.getStringWidth(this.copyrightMessage);
        this.copyrightTextX = this.width - this.copyrightTextWidth - 2;

    }

    /* I did it this way because I had trouble using @Redirect for the specific call I wanted to override. */
    @Override
    public void drawString(TextRenderer textRenderer, String str, int x, int y, int color)
    {
        if (str.contains("Do not distribute")) {
            str = this.copyrightMessage;
        }
        super.drawString(textRenderer, str, x, y, color);
    }



}
