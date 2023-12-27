package org.render3d.light;

import org.render3d.matrix.MatrixMath;
import org.render3d.vectors.vec4d.Vec4df;

/**
 * This class represents a light source
 */
public class LightSource {

    /**
     * The id for the light source
     */
    private int id = -1;

    /**
     * The origin of the light source
     */
    private Vec4df origin;

    /**
     * The direction of the light source
     */
    private Vec4df direction;

    /**
     * The vector of the light
     * Both vectors, origin and direction, are used to calcule the vector of the light
     */
    private Vec4df vector;

    /**
     * Constructor
     * @param origin the origin of the light source
     * @param direction the direction of the light source
     */
    public LightSource(Vec4df origin, Vec4df direction) {
        this.origin = origin;
        this.direction = direction;
        update();
    }

    /**
     * Constructor
     * Direction by default is pointing one positive unit in z axis
     * @param origin the origin of the light source
     */
    public LightSource(Vec4df origin) {
        this.origin = origin;
        this.direction = new Vec4df(0.0f, 0.0f, 1.0f);
        update();
    }

    /**
     * Constructor
     * @param sourceX source x axis value
     * @param sourceY source y axis value
     * @param sourceZ source z axis value
     * @param directionX direction x value
     * @param directionY direction y value
     * @param directionZ direction z value
     */
    public LightSource(
            float sourceX,
            float sourceY,
            float sourceZ,
            float directionX,
            float directionY,
            float directionZ) {
        this(new Vec4df(sourceX, sourceY, sourceZ), new Vec4df(directionX, directionY, directionZ));
    }

    /**
     *  Default constructor
     *  Light source = 0.0f x 0.0f y 0.0f z
     *  Light direction = 0.0f x 0.0f y 1.0f z
     *
     *  Direction by default is pointing one positive unit in z axis
     */
    public LightSource() {
        this(new Vec4df());
    }

    /**
     * Copy constructor
     * @param other other instance of LightSource
     */
    public LightSource(LightSource other) {
        assert other != null;
        setOrigin(other.origin);
        setDirection(other.direction);
        setVector(other.vector);
    }

    /**
     * Constructor
     * Direction by default is pointing one positive unit in z axis
     * @param sourceX source x axis value
     * @param sourceY source y axis value
     * @param sourceZ source z axis value
     */
    public LightSource(
            float sourceX,
            float sourceY,
            float sourceZ) {
        this(new Vec4df(sourceX, sourceY, sourceZ));
    }

    // Calculate the light vector

    private Vec4df calLightVector(Vec4df origin, Vec4df direction) {
        Vec4df vector = new Vec4df(origin);
        vector.add(direction);
        vector.normalize();
        return vector;
    }

    public void update() {
        this.vector = calLightVector(this.origin, this.direction);
    }

    public Vec4df calLightVector() {
        update();
        return vector;
    }

    // Calculate the brightness

    public float calLight(Vec4df normal) {
        return MatrixMath.vectorDotProduct(normal, this.vector);
    }

    // Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vec4df getOrigin() {
        return origin;
    }

    public void setOrigin(Vec4df origin) {
        this.origin.set(origin);
    }

    public void setOrigin(float x, float y, float z) {
        if (this.origin == null) {
            this.origin = new Vec4df(x, y, z);
        } else {
            this.origin.set(x, y, z);
        }
    }

    public Vec4df getDirection() {
        return direction;
    }

    public void setDirection(Vec4df direction) {
        this.direction.set(direction);
    }

    public void setDirection(float x, float y, float z) {
        if (this.direction == null) {
            this.direction = new Vec4df(x, y, z);
        } else {
            this.direction.set(x, y, z);
        }
    }

    public Vec4df getVector() {
        return vector;
    }

    public void setVector(Vec4df vector) {
        this.vector.set(vector);
    }

    public void setVector(float x, float y, float z) {
        if (this.vector == null) {
            this.vector = new Vec4df(x, y, z);
        } else {
            this.vector.set(x, y, z);
        }
    }

}
