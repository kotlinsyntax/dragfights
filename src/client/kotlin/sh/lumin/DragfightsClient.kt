package sh.lumin

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback
import net.minecraft.client.Minecraft
import java.util.regex.Pattern

object DragfightsClient : ClientModInitializer {
	@JvmField
	var lastLootnum: Float = 0f
	@JvmField
	var lastLootname: String = "None"
	@JvmField
	val obtained = Pattern.compile("(?<=obtained\\s).*")
	@JvmField
	var golemStage = 0
	override fun onInitializeClient() {
		HudRenderCallback.EVENT.register { ctx, tickDelta ->
			ctx.drawString(Minecraft.getInstance().font, "Last Loot Number: $lastLootnum", 10, 10, 0xFFFFFF, false)
			ctx.drawString(Minecraft.getInstance().font, "Last Loot Drop: ${lastLootname.replace("!", "")}", 10, 20, 0xFFFFFF, false)
			ctx.drawString(Minecraft.getInstance().font, "Golem Stage: ($golemStage / 3)", 10, 30, 0xFFFFFF, false)
		}
	}
}