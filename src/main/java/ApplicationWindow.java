import org.lwjgl.glfw.*;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.MemoryStack.*;

public class ApplicationWindow{
    private final long window;

    public ApplicationWindow(final int width, final int height, final String title){
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GFLW");
        }

        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        window = glfwCreateWindow(width, height, title, NULL, NULL);

        if(window == NULL){
            throw new RuntimeException("Window or OpenGL context creation failed");
        }

        this.setWindowKeyCallbacks();

        try(MemoryStack stack = stackPush()){
            IntBuffer contentAreaWidth = stack.mallocInt(1);
            IntBuffer contentAreaHeight = stack.mallocInt(1);

            glfwGetWindowSize(window, contentAreaWidth, contentAreaHeight);
            GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(window, vidMode.width() / 2, vidMode.height() / 2);
        }
        //Make the created OpenGL context current
        glfwMakeContextCurrent(window);

        glfwSwapInterval(1);
        glfwShowWindow(window);
    }

    /**
     * Destroys any remaining windows and releases any other resources allocated by GLFW.
     * GLFW must be initialized again after this method is called
     */
    public void close(){
        glfwDestroyWindow(window);
        glfwTerminate();
    }

    private void setWindowKeyCallbacks(){
        glfwSetKeyCallback(window, (window, key, scancode, action, modes) -> {
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true);
        });
    }

    public long getWindow(){
        return this.window;
    }
}