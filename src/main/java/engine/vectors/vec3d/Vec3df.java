package engine.vectors.vec3d;

public class Vec3df {

    private float x;

    private float y;

    private float z;

    public Vec3df() {
        x = 0.0f;
        y = 0.0f;
        z = 1.0f;
    }

    public Vec3df(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 1.0f;
    }

    public Vec3df(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3df(Vec3df vec3dd) {
        this.x = vec3dd.x;
        this.y = vec3dd.y;
        this.z = vec3dd.z;
    }

    public void add(float amount) {
        x += amount;
        y += amount;
        z += amount;
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

    public void sub(float amount) {
        x -= amount;
        y -= amount;
        z -= amount;
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

    public void mul(float amount) {
        x *= amount;
        y *= amount;
        z *= amount;
    }

    public void mulByX(float amount) {
        x *= amount;
    }

    public void mulByY(float amount) {
        y *= amount;
    }

    public void mulByZ(float amount) {
        z *= amount;
    }

    public void div(float amount) {
        x /= amount;
        y /= amount;
        z /= amount;
    }

    public void divByX(float amount) {
        x /= amount;
    }

    public void divByY(float amount) {
        y /= amount;
    }

    public void divByZ(float amount) {
        z /= amount;
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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float mag() {
        return (float)Math.sqrt(mag2());
    }

    public float mag2() {
        return (x * x) + (y * y) + (z * z);
    }

    public float dotProduct(Vec3df v) {
        return x * v.getX() + y * v.getY() + z * v.getZ();
    }

    public void set(Vec3df v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
    }

    public void add(Vec3df v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
    }

    public void sub(Vec3df v) {
        this.x -= v.getX();
        this.y -= v.getY();
        this.z -= v.getZ();
    }

    public void mul(Vec3df v) {
        this.x *= v.getX();
        this.y *= v.getY();
        this.z *= v.getZ();
    }

    public void div(Vec3df v) {
        this.x /= v.getX();
        this.y /= v.getY();
        this.z /= v.getZ();
    }

    public void normalize() {
        float l = mag();
        this.x /= l;
        this.y /= l;
        this.z /= l;
    }

    public Vec3df normal() {
        float r = mag();
        return new Vec3df(x / r, y / r, z / r);
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " Z: " + z;
    }
    
}
