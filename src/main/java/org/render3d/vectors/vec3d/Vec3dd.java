package org.render3d.vectors.vec3d;

public class Vec3dd {

    private double x;

    private double y;

    private double z;

    public Vec3dd() {
        x = 0.0;
        y = 0.0;
        z = 1.0;
    }

    public Vec3dd(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 1.0;
    }

    public Vec3dd(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vec3dd(Vec3dd vec3dd) {
        this.x = vec3dd.x;
        this.y = vec3dd.y;
        this.z = vec3dd.z;
    }

    public void add(double amount) {
        x += amount;
        y += amount;
        z += amount;
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

    public void sub(double amount) {
        x -= amount;
        y -= amount;
        z -= amount;
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

    public void mul(double amount) {
        x *= amount;
        y *= amount;
        z *= amount;
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

    public void div(double amount) {
        x /= amount;
        y /= amount;
        z /= amount;
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
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

    public double mag() {
        return Math.sqrt(mag2());
    }

    public double mag2() {
        return (x * x) + (y * y) + (z * z);
    }

    public double dotProduct(Vec3dd v) {
        return x * v.getX() + y * v.getY() + z * v.getZ();
    }

    public void set(Vec3dd v) {
        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
    }

    public void add(Vec3dd v) {
        this.x += v.getX();
        this.y += v.getY();
        this.z += v.getZ();
    }

    public void sub(Vec3dd v) {
        this.x -= v.getX();
        this.y -= v.getY();
        this.z -= v.getZ();
    }

    public void mul(Vec3dd v) {
        this.x *= v.getX();
        this.y *= v.getY();
        this.z *= v.getZ();
    }

    public void div(Vec3dd v) {
        this.x /= v.getX();
        this.y /= v.getY();
        this.z /= v.getZ();
    }

    public void normalize() {
        double l = mag();
        this.x /= l;
        this.y /= l;
        this.z /= l;
    }

    public Vec3dd normal() {
        double r = mag();
        return new Vec3dd(x / r, y / r, z / r);
    }

    public Vec3dd perpendicular() {
        return new Vec3dd(y, -x, z);
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " Z: " + z;
    }

}
