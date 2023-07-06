# Rendereizador de gráficos en 3D para JavaFX

Este repositorio contiene el proyecto de un renderizador de gráficos en 3D.

**version**: 1.0

**fecha**: 2023/07/01

**autor**: Sergio Martí Torregrosa

## Features:

Estas son las características de este renderizador de gráficos en 3D:

- Fácil implementación y no hay dependencias de otras librerías.
- Permite renderizar gráficos en 3D con una buena calidad.
- Permite leer modelos con extensión ".obj" texturizados.
- Disponible 7 modos de renderización:
  * wire: renderiza solamente los bordes de los triángulos que forman la maya.
  * flat: renderiza la maya un color plano y muestra los bordes.
  * smooth_flat: renderiza la maya con un color plano, pero no muestra los bordes.
  * textured: renderiza la maya con una textura. Muestra los bordes y no tiene en cuenta la fuente de luz.
  * full_textured: renderiza la maya con una textura. No muestra los bordes y no tiene en cuenta la fuente de luz.
  * textured_shadow: renderiza la maya con una textura. Muestra los bordes y tiene en cuenta la fuente de luz.
  * full_textured_shadow: renderiza la maya con una textura. No muestra los bordes y tiene en cuenta la fuente de luz.
- Camara libre: el desarrollador cuenta con los métodos necesarios para mover la cámara libremente por el entorno 3D.
- Fuente única de luz: el entorno 3D dispone de una única fuente de luz. El desarrollador dispone de los métodos necesarios para mover la fuente de luz por el entorno 3D.
- Transformaciones: la librería dispone de las clases necesarias para realizar translaciones, rotaciones y escalado.

## Características que se implementarán en un futuro

Se tiene pensado implementar las siguientes características:

- Lectura de modelos con otras extensiones.
- Simplificar el uso de las clases.
- Añadir múltiples fuentes de luz.
- Mejorar el movimiento de la cámara.
- Mejorar la cámara ortogonal: actualmente no es una visión ortogonal completa.

## Renderizador: la clase *PipeLine*

La clase *PipeLine* contiene todos los métodos necesarios para renderizar un objeto en 3D.

El proyecto trabaja utiliza el tipo numérico de coma flotante "*float*" para realizar todos los cálculos matemáticos. No utiliza números con coma flotante de doble precisión "*double*".

## Clases

- paquete *material*: este paquete contiene las clases necesarias para trabajar con modelos texturizados.
  * Material: clase que almacena todos los datos sobre el material del objeto a renderizar.
  * Texture: clase que representa una textura. Se instancia a partir de una imagen de JavaFx y dispone de los métodos adecuados para leer los pixeles que forman la textura
- paquete *matrix*: las matrices y los vectores son la base de un motor de gráficos en 3D. Este proyecto utiliza una propia implementación de matrices para realizar todos los cálculos. 
  * Mat4x4: representa una matriz de 4 filas por 4 columnas.
  * MatrixMath: clase estática que contiene todos los métodos para realizar los cálculos necesarios para renderizar los gráficos.
- paquete *vectors*: contiene clases que representan vectores matemáticos. Estos vectores contienen los métodos necesarios para realizar operaciones matemáticas: sumar, restar, multiplicar, dividir. Además de operaciones de vectores como: calcular el módulo, producto escalar, producto vectorial (*dot product*) o calcular el vector perpendicular a otro.  
  * Vec3df: representa un vector de 3 coordenadas: x, y, z, de números con coma flotante de precisión simple tipo "*float*". Se utiliza para almacenar la información de las texturas en dos dimensiones.
  * Vec4df: representa un vector de 4 coordenadas: x, y, z, w de números con coma flotante de precisión simple tipo "*float*". Se utiliza para almacenar la información de la posición de los vertices en un espacio tridimensional. También se conoce este tupo
- paquete *mesh*:
  * Triangle: representa un triángulo en un espacio tridimensional. Almacena la información de la posición de los vertices y la textura.
  * Mesh: representa una lista de triángulos que forman una malla.
  * MeshObject: una composición con la maya y el material de un objeto.
  * Model: los modelos 3D pueden estar formados por varias mayas con distintos materiales. Este objeto permite trabajar con un mismo modelo sin necesidad de separar individualmente cada maya.
  * MeshFactory: clase estática que contiene métodos para generar varias formas básicas tridimensionales: cubo unitario, plano.
  * ObjReader: esta clase permite leer un modelo 3D con la extensión ".obj".
- paquete *transforms*: contiene las clases necesarias para realizar transformaciones sobre una maya. Permiten combinar transformaciones.
  * Transform: clase genérica para realizar cualquier tipo de transformación. Contiene una matriz y un vector ("delta").
  * Translation: esta clase sirve para realizar una translación en uno de los 3 ejes: x, y, z.
  * Rotation: esta clase sirve para realizar una rotación en uno de los 3 ejes: x, y, z.
  * Scale: esta clase sirve para realizar un escalado en uno de los 3 ejes: x, y, z.
- paquete *render*: contiene las clases que permiten dibujar formas sobre un array de bytes.
  * PixelRenderer: contiene los métodos básicos para dibujar formas sencillas: líneas, rectángulos, círculos y triángulos.
  * PixelRenderer3D: clase hija de PixelRenderer. Contiene los métodos necesarios para dibujar triángulos texturizados.

Resto de clases:

- Camera: representa la cámara que renderiza el entorno tridimensional. El desarrollador dispone de los métodos necesarios para moverla por el entorno libremente.
- Perspective: enumeración que contiene los dos tipos de camara disponibles: normal y ortogonal.
- RenderFlags: enumeración que contiene los modos de renderización disponibles: 
  * RENDER_WIRE
  * RENDER_FLAT
  * RENDER_SMOOTH_FLAT
  * RENDER_TEXTURED
  * RENDER_FULL_TEXTURED
  * RENDER_TEXTURED_SHADOW
  * RENDER_FULL_TEXTURED_SHADOW
- PipeLine: esta clase contiene los métodos necesarios para renderizar gráficos en 3D. Resulta de la composición de las clases anteriores. Además, implementa el algoritmo, o la secuencia de pasos necesarios, para transformar los triángulos de la maya de un modelo 3D a los triángulos 2D para pintar en la pantalla. 

## Ejemplo

Aquí se muestra una implementación en una aplicación JavaFX de este renderizador.

```java
import engine.PipeLine;
import engine.RenderFlags;
import engine.material.Texture;
import engine.mesh.Mesh;
import engine.mesh.MeshFactory;
import engine.mesh.Model;
import engine.transforms.Rotation;
import engine.transforms.Transform;
import engine.transforms.Translation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Visualizer extends Application {

    // Settings of the application

    private String appName = "Visualizer";

    private int width = 800;

    private int height = 600;

    // Scene layout

    private WritableImage img;

    // 3D Render class

    private PipeLine pipeLine;

    private Mesh mesh;
    
    @Override
    public void start(Stage stage) throws Exception {
        // Setting the 3D render
        pipeLine = new PipeLine(width, height);
        mesh = MeshFactory.getUnitCube();
        
        // Setting the image view
        img = new WritableImage(width, height);

        ImageView imageView = new ImageView(img);
        imageView.setFocusTraversable(true);

        StackPane pane = new StackPane(imageView);

        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.fitHeightProperty().bind(pane.heightProperty());

        // Rendering the 3D mesh
        pipeLine.getRenderer3D().clear(0xff000000);
        pipeLine.renderMesh(mesh, 0xff00aaff);
        pipeLine.clearDepthBuffer();
        pipeLine.getRenderer3D().writeImage(img);

        // Set the scene
        Scene scene = new Scene(pane, width, height);
        stage.setScene(scene);
        stage.setTitle(appName);

        // Show the scene
        stage.show();
    }

}
```

## Como utilizarlo

Si quieres usar el código de este repositorio, puedes descargar el repositorio y utilizar directamente las clases, o bien puedes añadir como librería externa el artifact del proyecto.

## Capturas:

Aquí se muestran algunas capturas del funcionamiento del renderizador.

![Bloque completo](/images/full_block.PNG)

![Bloque texturizado](/images/coin_block.PNG)

![Bloque texturizado y sombreado](/images/coin_block_shadow.PNG)

![Gorra de mario bros en blender](/images/blender_gorra_mario.PNG)

![Gorra de mario bros con el renderizador](/images/gorra_mario.PNG)

Aunque de una versión anterior, [aquí](https://www.youtube.com/watch?v=a1zTm6arvv0) hay un vídeo del funcionamiento del renderizador.



