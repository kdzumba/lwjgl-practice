import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import render.Mesh;
import render.MeshLoader;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Main {
    public static void main(String[] args){
        ApplicationWindow app = new ApplicationWindow(640, 480, "Test Window");

        long window = app.getWindow();
        GL.createCapabilities();
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        float[] vertices = {-0.5f, -0.5f, 0f, 0.5f, -0.5f, 0f, 0f, 0.5f, 0f};
        int[] indices = {0, 1, 2};

        Mesh triangleMesh = MeshLoader.createMesh(vertices, indices);

        while(!glfwWindowShouldClose(window)){
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            GL30.glBindVertexArray(triangleMesh.getVaoID());
            GL20.glEnableVertexAttribArray(0);
            GL11.glDrawElements(GL11.GL_TRIANGLES, triangleMesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        app.close();
    }
}
