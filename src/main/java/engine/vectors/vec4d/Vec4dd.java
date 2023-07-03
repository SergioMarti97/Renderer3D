package engine.vectors.vec4d;

public class Vec4dd {

    private double x;

    private double y;

    private double z;

    private double w;

    public Vec4dd() {
        x = 0.0;
        y = 0.0;
        z = 0.0;
        w = 1.0;
    }

    public Vec4dd(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1.0;
    }

    public Vec4dd(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vec4dd(Vec4dd vec4dd) {
        this.x = vec4dd.x;
        this.y = vec4dd.y;
        this.z = vec4dd.z;
        this.w = vec4dd.w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setW(double w) {
        this.w = w;
    }

    public double mag() {
        return Math.sqrt(mag2());
    }

    public double mag2() {
        return (x * x) + (y * y) + (z * z) + (w * w);
    }

    public double dotProduct(Vec4dd v) {
        return x * v.getX() + y * v.getY() + z * v.getZ() + w * v.getW();
    }

    public void add(double amount) {
        x += amount;
        y += amount;
        z += amount;
        w += amount;
    }

    public void addToX(double amount) {
        x += amount;
    }

    public void addToY(double amount) {
        y += amount;
    }

    public void addToZ(double amount) {
        z += amount;
    }

    public void addToW(double amount) {
        w += amount;
    }

    public void sub(double amount) {
        x -= amount;
        y -= amount;
        z -= amount;
        w -= amount;
    }

    public void subToX(double amount) {
        x -= amount;
    }

    public void subToY(double amount) {
        y -= amount;
    }

    public void subToZ(double amount) {
        z -= amount;
    }

    public void subToW(double amount) {
        w -= amount;
    }

    public void mul(double amount) {
        x *= amount;
        y *= amount;
        z *= amount;
        w *= amount;
    }

    public void mulByX(double amount) {
        x *= amount;
    }

    public void mulByY(double amount) {
        y *= amount;
    }

    public void mulByZ(double amount) {
        z *= amount;
    }

    public void mulByW(double amount) {
        w *= amount;
    }

    public void div(double amount) {
        x /= amount;
        y /= amount;
        z /= amount;
        w /= amount;
    }

    public void divByX(double amount) {
        x /= amount;
    }

    public void divByY(double amount) {
        y /= amount;
    }

    public void divByZ(double amount) {
        z /= amount;
    }

    public void divByW(double amount) {
        w /= amount;
    }

    public void set(Vec4dd v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
        this.w = v.getW();
    }

    public void add(Vec4dd v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
        this.w += v.getW();
    }

    public void sub(Vec4dd v) {
        this.x -= v.getX();
        this.y -= v.getY();
        this.z -= v.getZ();
        this.w -= v.getW();
    }

    public void mul(Vec4dd v) {
        this.x *= v.getX();
        this.y *= v.getY();
        this.z *= v.getZ();
        this.w *= v.getW();
    }

    public void div(Vec4dd v) {
        this.x /= v.getX();
        this.y /= v.getY();
        this.z /= v.getZ();
        this.w /= v.getW();
    }

    public void normalize() {
        double l = mag();
        this.x /= l;
        this.y /= l;
        this.z /= l;
        this.w /= l;
    }

    public Vec4dd normal() {
        double r = mag();
        return new Vec4dd(x / r, y / r, z / r, w / r);
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " Z: " + z + " W: " + w;
    }

}
