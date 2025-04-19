package sh.lumin

import net.fabricmc.api.ModInitializer
import org.slf4j.LoggerFactory
object Dragfights : ModInitializer {
    private val logger = LoggerFactory.getLogger("dragfights")

	override fun onInitialize() {
		logger.info("Hello Fabric world!")

	}
}