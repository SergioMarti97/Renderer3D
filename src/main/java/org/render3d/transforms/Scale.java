package org.render3d.transforms;

import org.render3d.matrix.Mat4x4;
import org.render3d.matrix.MatrixMath;
import org.render3d.vectors.vec4d.Vec4df;

public class Scale extends Transform {

    public Scale() {
    }

    public Scale(float x, float y, float z) {
        super(x, y, z);
    }

    public Scale(Vec4df delta) {
        super(delta);
    }

    public Scale(Mat4x4 mat, Vec4df d) {
        super(mat, d);
    }

    public Scale(Transform t) {
        super(t);
    }

    @Override
    public Transform update() {
        mat = MatrixMath.matrixMakeScale(d.getX(), d.getY(), d.getZ());
        return this;
    }

}
