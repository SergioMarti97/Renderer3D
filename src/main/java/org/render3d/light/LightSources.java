package org.render3d.light;

import org.render3d.vectors.vec4d.Vec4df;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LightSources {

    /**
     * The sources of light
     */
    private List<LightSource> lights = new ArrayList<>();

    public LightSources() {

    }

    public LightSources(LightSource source) {
        lights.add(source);
    }

    public LightSources(LightSource... sources) {
        lights.addAll(Arrays.asList(sources));
    }

    // Methods

    public float calLight(Vec4df normal) {
        float lightValue = 0.0f;
        for (var lightSource : lights) {
            lightValue += lightSource.calLight(normal);
            if (lightValue >= 1.0f) {
                return 1.0f;
            }
        }
        return lightValue;
    }

    // Getters & Setters

    public List<LightSource> getLights() {
        return lights;
    }

}
