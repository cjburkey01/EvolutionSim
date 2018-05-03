package com.cjburkey.evosim;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;

public final class EvolutionSim {
	
	public static final EvolutionSim evosim = new EvolutionSim();
	public static final EvoSimHandler handler = new EvoSimHandler();
	public static final Window window = new Window();
	
	private static boolean firstLoop = true;
	private static boolean running = false;
	private static double deltaTime = 0.0f;
	private static GLCapabilities glcaps;
	
	public static void main(String[] args) {
		Debug.setDefaultErrorHandler();
		Debug.log("Starting EvolutionSim by CJ Burkey");
		evosim.begin();
		evosim.exit();
	}
	
	private void begin() {
		handler.launch();
		startLoop();
	}
	
	private void startLoop() {
		running = true;
		long last = System.nanoTime();
		long current;
		while (true) {
			current = System.nanoTime();
			deltaTime = (current - last) / 1000000000.0d;
			last = current;
			Debug.log("Delta time: {} | FPS Estimate: {}", deltaTime, Util.format0Decimal(1.0d / deltaTime));
			if (firstLoop) {
				window.init();
				glcaps = GL.createCapabilities();
				GL11.glClearColor(0.0f, 0.5f, 1.0f, 1.0f);
				window.show(true);
				handler.init();
				firstLoop = false;
			}
			window.preRender();
			if (window.getShouldClose()) {
				stop();
			}
			handler.update();
			handler.render();
			window.postRender();
			if (deltaTime < 0.01f) {
				try {
					Thread.sleep(1L);
				} catch (Exception e) {
					Debug.exception(e);
				}
			}
			if (!running) {
				handler.exit();
				break;
			}
		}
	}
	
	private void exit() {
		Debug.log("Stopping EvolutionSim");
		handler.close();
	}
	
	public static void stop() {
		running = false;
	}
	
	public static boolean getIsRunning() {
		return running;
	}
	
	public static double getDeltaTime() {
		return deltaTime;
	}
	
	public static float getDeltaTimeF() {
		return (float) deltaTime;
	}
	
	public static GLCapabilities getGlCaps() {
		return glcaps;
	}
	
}