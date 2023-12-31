package org.render3d;

import org.render3d.matrix.Mat4x4;
import org.render3d.matrix.MatrixMath;
import org.render3d.vectors.vec4d.Vec4df;

public class Camera {

    /**
     * The vision matrix. It is the one that ends up being used for the transformation of the world
     */
    private Mat4x4 matView;

    /**
     * The camera matrix. It is needed to calculate the vision matrix
     */
    private Mat4x4 matCamera;

    /**
     * Where the camera is located
     */
    private Vec4df origin;

    /**
     * The direction the camera is pointing
     */
    private Vec4df lookDirection;

    /**
     * The height at which the camera is located
     */
    private Vec4df up = new Vec4df(0.0f, 1.0f, 0.0f);

    /**
     * The target at which the camera is looking at first. It is needed to calculate lookDirection
     */
    private Vec4df target = new Vec4df(0.0f, 0.0f, 1.0f);

    /**
     * The rotation of the camera in the 3 axes
     */
    private Vec4df cameraRot = new Vec4df(0.0f, 0.0f, 0.0f);

    /**
     * Constructor
     */
    public Camera() {
        origin = new Vec4df();
        lookDirection = new Vec4df();
        matCamera = calculateMatCamera(up, target, MatrixMath.matrixMakeIdentity());
        matView = MatrixMath.matrixQuickInverse(matCamera);
    }

    /**
     * Constructor with parameters
     * @param origin the origin of the camera
     */
    public Camera(Vec4df origin) {
        this.origin = origin;
        lookDirection = new Vec4df();
        matCamera = calculateMatCamera(up, target, MatrixMath.matrixMakeIdentity());
        matView = MatrixMath.matrixQuickInverse(matCamera);
    }

    /**
     * Constructor with parameters
     * @param x value x of the origin for the camera
     * @param y value y of the origin for the camera
     * @param z value z of the origin for the camera
     */
    public Camera(float x, float y, float z) {
        origin = new Vec4df(x, y, z);
        lookDirection = new Vec4df();
        matCamera = calculateMatCamera(up, target, MatrixMath.matrixMakeIdentity());
        matView = MatrixMath.matrixQuickInverse(matCamera);
    }

    /**
     * This method is used to calculate the matrix of the camera
     *
     * @param up how high the camera is at first
     * @param target the "target" point where the camera is pointing
     * @param transform the transformation that the camera has
     * @return the array representing the camera
     */
    private Mat4x4 calculateMatCamera(Vec4df up, Vec4df target, Mat4x4 transform) {
        lookDirection = MatrixMath.matrixMultiplyVector(transform, target);
        target = MatrixMath.vectorAdd(origin, lookDirection);
        return MatrixMath.matrixPointAt(origin, target, up);
    }

    /**
     * This method is used to calculate the matrix representing the camera and the vision matrix.
     * Perform the necessary transformations for camera rotation.
     * Updates the matrix of the camera and object vision.
     *
     * @return returns the matrix vision
     */
    public Mat4x4 getMatView() {
        Mat4x4 matCameraRotX = MatrixMath.matrixMakeRotationX(cameraRot.getX());
        Mat4x4 matCameraRotY = MatrixMath.matrixMakeRotationY(cameraRot.getY());
        Mat4x4 matCameraRotZ = MatrixMath.matrixMakeRotationZ(cameraRot.getZ());
        Mat4x4 matCameraRotXY = MatrixMath.matrixMultiplyMatrix(matCameraRotX, matCameraRotY);
        Mat4x4 matCameraRot = MatrixMath.matrixMultiplyMatrix(matCameraRotXY, matCameraRotZ);
        matCamera = calculateMatCamera(up, target, matCameraRot);
        matView = MatrixMath.matrixQuickInverse(matCamera);
        return matView;
    }

    /**
     * This method has nothing to do with the operations that are done for the camera.
     * It is only a method that is used to save code and to be able to print all the information of the camera.
     *
     * @param r the rendering class where the drawing functions are located.
     * @param name the name of the vector (for example: origin, target, lookDirection ...).
     * @param vec4Df the vector.
     * @param offSetX the offset X on the screen. That is, the X position where the text is drawn.
     * @param offSetY the offset Y on the screen. That is, the Y position where the text is drawn.
     * @param color the color of the text.
     */
    /*private void printVec3d(Renderer r, String name, Vec4df vec4Df, int offSetX, int offSetY, int color) {
        r.drawText(
                String.format("  %s X: %.3f Y: %.3f Z: %.3f",
                        name,
                        vec4Df.getX(),
                        vec4Df.getY(),
                        vec4Df.getZ()),
                offSetX, offSetY, color);
    }*/

    /**
     * This method is used to display all the information contained in the object on the screen
     * Camera per screen. It is really useful for debugging.
     *
     * @param r the rendering class where the drawing functions are located.
     * @param offSetX the offset X on the screen. That is, the X position where the text is drawn.
     * @param offSetY the offset Y on the screen. That is, the Y position where the text is drawn.
     * @param color the color of the text.
     */
    /*public void showInformation(Renderer r, int offSetX, int offSetY, int color) {
        int increment = 30;
        r.drawText("Camera", offSetX, offSetY, color);
        offSetY += increment;
        printVec3d(r, "Origin", origin, offSetX, offSetY, color);
        offSetY += increment;
        printVec3d(r, "Look direction", lookDirection, offSetX, offSetY, color);
        offSetY += increment;
        printVec3d(r, "Up", up, offSetX, offSetY, color);
        offSetY += increment;
        printVec3d(r, "Target", target, offSetX, offSetY, color);
        offSetY += increment;
        printVec3d(r, "Camera rotation", cameraRot, offSetX, offSetY, color);
    }*/

    /**
     * Rotate the camera on the X axis the radians passed by parameter
     *
     * @param angleRad the angle of rotation on the X axis.
     */
    public void rotX(float angleRad) {
        float rotX = cameraRot.getX();
        rotX += angleRad;
        cameraRot.setX(rotX);
    }

    /**
     * Rotate the camera on the Y axis the radians passed by parameter.
     *
     * @param angleRad the angle of rotation on the Y axis.
     */
    public void rotY(float angleRad) {
        float rotY = cameraRot.getY();
        rotY += angleRad;
        cameraRot.setY(rotY);
    }

    /**
     * Rotate the camera on the Z axis the radians passed by parameter.
     *
     * @param angleRad the angle of rotation in the Z axis.
     */
    public void rotZ(float angleRad) {
        float rotZ = cameraRot.getZ();
        rotZ += angleRad;
        cameraRot.setZ(rotZ);
    }

    /**
     * Modify the origin position of the camera.
     *
     * @param origin the new origin of the camera.
     */
    public void setOrigin(Vec4df origin) {
        this.origin = origin;
    }

    public void setOrigin(float x, float y, float z) {
        if (this.origin == null)
            this.origin = new Vec4df(x, y, z);
        else
            this.origin.set(x, y, z);
    }

    public Vec4df getOrigin() {
        return origin;
    }

    public Vec4df getCameraRot() {
        return cameraRot;
    }

    public Vec4df getUp() {
        return up;
    }

    public Vec4df getTarget() {
        return target;
    }

    public Vec4df getLookDirection() {
        return lookDirection;
    }

}
