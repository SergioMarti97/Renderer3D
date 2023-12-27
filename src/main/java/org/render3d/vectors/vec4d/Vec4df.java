package org.render3d.vectors.vec4d;

public class Vec4df {

    private float x;

    private float y;

    private float z;

    private float w;

    public Vec4df() {
        x = 0.0f;
        y = 0.0f;
        z = 0.0f;
        w = 1.0f;
    }

    public Vec4df(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1.0f;
    }

    public Vec4df(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4df(Vec4df vec4df) {
        this.x = vec4df.x;
        this.y = vec4df.y;
        this.z = vec4df.z;
        this.w = vec4df.w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float mag() {
        return (float)Math.sqrt(mag2());
    }

    public float mag2() {
        return (x * x) + (y * y) + (z * z) + (w * w);
    }

    public float dotProduct(Vec4df v) {
        return x * v.getX() + y * v.getY() + z * v.getZ() + w * v.getW();
    }

    public void add(float amount) {
        x += amount;
        y += amount;
        z += amount;
        w += amount;
    }

    public void addToX(float amount) {
        x += amount;
    }

    public void addToY(float amount) {
        y += amount;
    }

    public void addToZ(float amount) {
        z += amount;
    }

    public void addToW(float amount) {
        w += amount;
    }

    public void sub(float amount) {
        x -= amount;
        y -= amount;
        z -= amount;
        w -= amount;
    }

    public void subToX(float amount) {
        x -= amount;
    }

    public void subToY(float amount) {
        y -= amount;
    }

    public void subToZ(float amount) {
        z -= amount;
    }

    public void subToW(float amount) {
        w -= amount;
    }

    public void mul(float amount) {
        x *= amount;
        y *= amount;
        z *= amount;
        w *= amount;
    }

    public void mulXBy(float amount) {
        x *= amount;
    }

    public void mulYBy(float amount) {
        y *= amount;
    }

    public void mulZBy(float amount) {
        z *= amount;
    }

    public void mulWBy(float amount) {
        w *= amount;
    }

    public void div(float amount) {
        x /= amount;
        y /= amount;
        z /= amount;
        w /= amount;
    }

    public void divXBy(float amount) {
        x /= amount;
    }

    public void divYBy(float amount) {
        y /= amount;
    }

    public void divZBy(float amount) {
        z /= amount;
    }

    public void divWBy(float amount) {
        w /= amount;
    }

    public void set(Vec4df v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
        this.w = v.getW();
    }

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void add(Vec4df v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
        this.w += v.getW();
    }

    public void sub(Vec4df v) {
        this.x -= v.getX();
        this.y -= v.getY();
        this.z -= v.getZ();
        this.w -= v.getW();
    }

    public void mul(Vec4df v) {
        this.x *= v.getX();
        this.y *= v.getY();
        this.z *= v.getZ();
        this.w *= v.getW();
    }

    public void div(Vec4df v) {
        this.x /= v.getX();
        this.y /= v.getY();
        this.z /= v.getZ();
        this.w /= v.getW();
    }

    public void normalize() {
        float l = mag();
        this.x /= l;
        this.y /= l;
        this.z /= l;
        this.w /= l;
    }

    public Vec4df normal() {
        float r = mag();
        return new Vec4df(x / r, y / r, z / r, w / r);
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " Z: " + z + " W: " + w;
    }

}
