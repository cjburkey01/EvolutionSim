package com.cjburkey.evosim;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;
import org.joml.Vector2i;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;

public class Window {
	
	private long window;
	
	public void init() {
		GLFWErrorCallback.createPrint(System.out).set();
		if (!glfwInit()) {
			throw new RuntimeException("GLFW could not be initialized");
		}
		
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
		glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
		glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
		glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
		
		window = glfwCreateWindow(300, 300, "EvolutionSim", NULL, NULL);
		if (window == NULL) {
			throw new RuntimeException("GLFW window could not be created");
		}
		
		glfwMakeContextCurrent(window);
		center();
	}
	
	public void show(boolean vsync) {
		glfwSwapInterval((vsync) ? 1 : 0);
		glfwShowWindow(window);
	}
	
	public void hide() {
		glfwHideWindow(window);
	}
	
	public void preRender() {
		glfwPollEvents();
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public void postRender() {
		glfwSwapBuffers(window);
	}
	
	public Vector2i getWindowSize() {
		int[] w = new int[1];
		int[] h = new int[1];
		glfwGetWindowSize(window, w, h);
		return new Vector2i(w[0], h[0]);
	}
	
	public Vector2i getMonitorSize(long monitor) {
		GLFWVidMode vid = glfwGetVideoMode(monitor);
		return new Vector2i(vid.width(), vid.height());
	}
	
	public Vector2i getMonitorSize() {
		return getMonitorSize(glfwGetPrimaryMonitor());
	}
	
	public void center() {
		Vector2i mon = getMonitorSize();
		Vector2i win = getWindowSize();
		glfwSetWindowPos(window, (mon.x - win.x) / 2, (mon.y - win.y) / 2);
	}
	
	public long getId() {
		return window;
	}
	
	public boolean getShouldClose() {
		return glfwWindowShouldClose(window);
	}
	
}