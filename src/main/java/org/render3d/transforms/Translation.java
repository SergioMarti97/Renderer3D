package org.render3d.transforms;

import org.render3d.matrix.Mat4x4;
import org.render3d.matrix.MatrixMath;
import org.render3d.vectors.vec4d.Vec4df;

public class Translation extends Transform {

    public Translation() {
    }

    public Translation(float v) {
        super(v, v, v);
    }

    public Translation(float x, float y, float z) {
        super(x, y, z);
    }

    public Translation(Vec4df delta) {
        super(delta);
    }

    public Translation(Mat4x4 mat, Vec4df d) {
        super(mat, d);
    }

    public Translation(Transform t) {
        super(t);
    }

    @Override
    public Transform update() {
        mat = MatrixMath.matrixMakeTranslation(d.getX(), d.getY(), d.getZ());
        return this;
    }

}
