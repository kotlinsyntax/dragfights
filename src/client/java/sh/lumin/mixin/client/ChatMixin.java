package sh.lumin.mixin.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.network.chat.Style;
import net.minecraft.network.protocol.game.ClientboundSetTitleTextPacket;
import net.minecraft.server.commands.TitleCommand;
import net.minecraft.sounds.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sh.lumin.DragfightsClient;

import java.util.regex.Matcher;

@Mixin(ChatComponent.class)
public class ChatMixin {

	@Inject(at = @At("HEAD"), method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/GuiMessageTag;)V")
	private void init(Component message, MessageSignature signature, GuiMessageTag tag, CallbackInfo info) {
		String content = message.getString();
		if(content.startsWith("Lootnum:")) {
			String lootNum = content.substring(0, content.indexOf(" Magic")).substring(8);
			DragfightsClient.lastLootnum = Float.parseFloat(lootNum);
		}

		if(content.contains(Minecraft.getInstance().getUser().getName() + " Has obtained")) {
			Matcher matcher = DragfightsClient.obtained.matcher(content);
			if(matcher.find()) {
                DragfightsClient.lastLootname = matcher.group();
			}
		}

		if(content.startsWith("The ground shakes.. (1/4)")) {
			DragfightsClient.golemStage = 1;
		}

		if(content.startsWith("The ground shakes.. (2/4)")) {
			DragfightsClient.golemStage = 2;
		}

		if(content.startsWith("The ground shakes.. (3/4)")) {
			DragfightsClient.golemStage = 3;
		}

		if(content.startsWith("An Endstone Protector has spawned!")) {
			Minecraft.getInstance().gui.setTitle(
					Component.literal("GOLEM!").withStyle(ChatFormatting.RED)
			);

			DragfightsClient.golemStage = 0;
		}

	}
}