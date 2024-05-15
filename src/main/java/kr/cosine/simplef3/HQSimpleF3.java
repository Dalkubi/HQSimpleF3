package kr.cosine.simplef3;

import kr.cosine.simplef3.config.SimpleF3ClientConfig;
import net.fabricmc.api.ClientModInitializer;

public class HQSimpleF3 implements ClientModInitializer {

	public static final String MOD_ID = "hqsimplef3";

	@Override
	public void onInitializeClient() {
		SimpleF3ClientConfig.load();
	}
}