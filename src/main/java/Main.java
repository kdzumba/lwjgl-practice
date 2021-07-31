import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    public static void main(String[] args){
        ApplicationWindow app = new ApplicationWindow(640, 480, "Test Window");

        long window = app.getWindow();

        while(!glfwWindowShouldClose(window)){
            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        app.close();
    }
}
