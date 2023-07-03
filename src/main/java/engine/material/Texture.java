package engine.material;

import javafx.scene.image.*;

import java.nio.IntBuffer;

public class Texture {

    /**
     * The image width
     */
    protected int w;

    /**
     * The image height
     */
    protected int h;

    /**
     * The image pixel array
     */
    protected int[] p;

    /**
     * If the image has alpha
     */
    protected boolean alpha = false;

    public Texture() {
        w = 0;
        h = 0;
        p = new int[w * h];
    }

    /**
     * The constructor
     *
     * @param p the pixel array what conforms the image
     * @param w width
     * @param h height
     */
    public Texture(int[] p, int w, int h) {
        this.w = w;
        this.h = h;
        this.p = p;
    }

    /**
     * The constructor
     *
     * @param w width
     * @param h height
     */
    public Texture(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public Texture(PixelReader pr, double width, double height) {
        this.w = (int)width;
        this.h = (int)height;
        p = readPixels(pr, 0, 0, w, h);
    }

    public Texture(Image img) {
        this(img.getPixelReader(), img.getWidth(), img.getHeight());
    }

    public Texture(String path) {
        this(new Image(path));
    }

    // Read pixels

    private int[] readPixels(PixelReader reader, int x, int y, int w, int h) {
        int[] pixels = new int[w * h];
        PixelFormat.Type type = reader.getPixelFormat().getType();
        WritablePixelFormat<IntBuffer> format;

        if (type == PixelFormat.Type.INT_ARGB_PRE) {
            format = PixelFormat.getIntArgbPreInstance();
        } else {
            format = PixelFormat.getIntArgbInstance();
        }

        try {
            reader.getPixels(x, y, w, h, format, pixels, 0, w);
        } catch ( ArrayIndexOutOfBoundsException e ) {
            System.out.println(e.getMessage());
        }
        return pixels;
    }

    private int[] readPixels(Image img, int x, int y, int w, int h) {
        return readPixels(img.getPixelReader(), x, y, w, h);
    }

    // Change the image for the texture

    public void set(PixelReader pr, double width, double height) {
        this.w = (int)width;
        this.h = (int)height;
        p = readPixels(pr, 0, 0, w, h);
    }

    public void set(Image img) {
        set(img.getPixelReader(), img.getWidth(), img.getHeight());
    }

    public void set(String path) {
        set(new Image(path));
    }

    // Get Pixel & Get Sample

    /**
     * This method returns an pixel for the image
     *
     * @param x the X position of the pixel inside the image
     * @param y the Y position of the pixel inside the image
     * @return a pixel for the image
     * @throws ArrayIndexOutOfBoundsException if the x and/or y positions are outside of the image
     */
    public int getPixel(int x, int y) throws ArrayIndexOutOfBoundsException {
        int index = x + w * y;
        assert p != null;
        if ( index < p.length ) {
            return p[x + w * y];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * This method returns a sample (pixel) of the image
     * @param x the X position of the pixel inside the image
     * @param y the Y position of the pixel inside the image
     * @return a pixel of the image
     * @throws ArrayIndexOutOfBoundsException if the x and/or y positions are outside of the image
     */
    public int getSample(float x, float y) {
        int color;
        int sampleX = Math.min((int)(x * (float)w), w > 0 ? w - 1 : w);
        int sampleY = Math.min((int)(y * (float)h), h > 0 ? h - 1 : h);
        try {
            color = getPixel(sampleX, sampleY);
        } catch ( ArrayIndexOutOfBoundsException e ) {
            color = 0x00000000;
            String errorMessage = "X: " + x + " Y: " + y + " outside of " + getW() + "x" + getH();
            System.out.println("Get sample Error: " + errorMessage + e.getMessage());
        }
        return color;
    }

    // Getters & Setters

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public int[] getP() {
        return p;
    }

    public boolean isAlpha() {
        return alpha;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setP(int[] p) {
        this.p = p;
    }

    public void set(int x, int y, int pixel) {
        p[x + w * y] = pixel;
    }

    public int get(int x, int y) {
        return p[x + w * y];
    }

    public void setAlpha(boolean alpha) {
        this.alpha = alpha;
    }

}
