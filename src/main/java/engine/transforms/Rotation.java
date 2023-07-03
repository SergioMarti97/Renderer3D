package engine.transforms;

import engine.matrix.Mat4x4;
import engine.matrix.MatrixMath;
import engine.vectors.vec4d.Vec4df;

public class Rotation extends Transform {

    public Rotation() {
    }

    public Rotation(float x, float y, float z) {
        super(x, y, z);
    }

    public Rotation(Vec4df delta) {
        super(delta);
    }

    public Rotation(Mat4x4 mat, Vec4df d) {
        super(mat, d);
    }

    public Rotation(Transform t) {
        super(t);
    }

    public Rotation rotX(float x) {
        d.setX(x);
        return this;
    }

    public Rotation rotY(float y) {
        d.setY(y);
        return this;
    }

    public Rotation rotZ(float z) {
        d.setZ(z);
        return this;
    }

    public Rotation rot(float x, float y, float z) {
        d.set(x, y, z);
        return this;
    }

    @Override
    public Transform update() {
        Mat4x4 matRotX = MatrixMath.matrixMakeRotationX(d.getX());
        matRotX = MatrixMath.matrixMultiplyMatrix(MatrixMath.matrixMakeIdentity(), matRotX);
        Mat4x4 matRotY = MatrixMath.matrixMakeRotationY(d.getY());
        Mat4x4 matRotXY = MatrixMath.matrixMultiplyMatrix(matRotY, matRotX);
        Mat4x4 matRotZ = MatrixMath.matrixMakeRotationZ(d.getZ());
        mat = MatrixMath.matrixMultiplyMatrix(matRotXY, matRotZ);
        return this;
    }

}
