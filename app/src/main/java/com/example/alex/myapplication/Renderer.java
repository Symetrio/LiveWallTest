package com.example.alex.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.methods.DiffuseMethod;
import org.rajawali3d.materials.textures.ATexture;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.math.vector.Vector3;
import org.rajawali3d.primitives.Sphere;

/**
 * Created by Alex on 8/3/2017.
 */

public class Renderer extends org.rajawali3d.renderer.Renderer {

public Context context;

    private DirectionalLight directionalLight;
    private Sphere earthSphere;

    public Renderer(Context context) {
        super(context);
        this.context = context;
        setFrameRate(60);

    }

    @Override
    protected void initScene() {

        directionalLight = new DirectionalLight(1f, .2f, -1.0f); // pravljenje novog svetla (
        directionalLight.setColor(1.0f, 1.0f, 1.0f); // boja svetla
        directionalLight.setPower(2); // Intenzitet svetla
        getCurrentScene().addLight(directionalLight);

        Material material = new Material(); // pravljenje materijala
        material.enableLighting(true); // ukljucivanje osvetljenja
        material.setDiffuseMethod(new DiffuseMethod.Lambert()); // dodeljivanje materijala (vrsta)

        material.setColor(0); // mislim da je ovo default-na boja materijala ?!

        // pravljenje texture
        Texture earthTexture = new Texture("Earth", R.drawable.earthtruecolor_nasa_big);
        try{
            material.addTexture(earthTexture); // dodeljivanje texture napravljenom materijalu

        } catch (ATexture.TextureException error){
            Log.d("DEBUG", "TEXTURE ERROR"); // ako ne uspe, napisi log error
        }

        earthSphere = new Sphere(1, 24, 24);
        earthSphere.setMaterial(material);
        getCurrentScene().addChild(earthSphere);
        getCurrentCamera().setZ(4.2f);

    }

    @Override
    public void onOffsetsChanged(float xOffset, float yOffset, float xOffsetStep,
                                 float yOffsetStep, int xPixelOffset, int yPixelOffset) {

    }


    @Override
    public void onTouchEvent(MotionEvent event) {



    }
    @Override
    public void onRender(final long elapsedTime, final double deltaTime){
        super.onRender(elapsedTime, deltaTime);
        earthSphere.rotate(Vector3.Axis.Y, 1.0);

    }



}
