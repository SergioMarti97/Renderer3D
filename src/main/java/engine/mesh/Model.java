package engine.mesh;

import java.util.ArrayList;

public class Model {

    private String name;

    private ArrayList<MeshObject> o;

    public Model(ArrayList<MeshObject> o) {
        this.o = o;
    }

    public Model(String name, ArrayList<MeshObject> o) {
        this.name = name;
        this.o = o;
    }

    public Model(String path) {
        ObjReader reader = new ObjReader();
        reader.load(path);
        setObjects(reader.getObjects());
    }

    public Model(String name, String path) {
        this.name = name;
        ObjReader reader = new ObjReader();
        reader.load(path);
        setObjects(reader.getObjects());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<MeshObject> getObjects() {
        return o;
    }

    public void setObjects(ArrayList<MeshObject> o) {
        this.o = o;
    }

}
