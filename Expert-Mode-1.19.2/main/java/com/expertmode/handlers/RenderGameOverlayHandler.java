package com.expertmode.handlers;

import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderGameOverlayHandler {
	
	@SubscribeEvent
	public static void renderGameOverlayHandler(RenderGuiOverlayEvent.Post event) {
/*
		//if(event.getOverlay() == GuiOverlayManager.getOverlays().get(1)) {
		//if(event.getWindow()) {
		
			Minecraft minecraft = Minecraft.getInstance();
			
			int level = EnchantmentHelper.getEnchantmentLevel(EnchantmentLoader.CURSE_OF_DARKNESS.get(), minecraft.player);

			if(level > 0) {
			
				renderVignette(event.getWindow().getGuiScaledWidth(), event.getWindow().getGuiScaledHeight());
			
			}
		
		//}
*/	
	}
	
	/*private static void renderVignette(int screenWidth, int screenHeight) {
	
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F); 
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, new ResourceLocation("textures/misc/vignette.png")); 
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		bufferbuilder.vertex(0.0D, (double)screenHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
		bufferbuilder.vertex((double)screenWidth, (double)screenHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
		bufferbuilder.vertex((double)screenWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
		bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
		tesselator.end();
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.defaultBlendFunc();
		
	}*/


}
