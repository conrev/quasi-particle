package com.quasiparticle.gameobject;

/**
 * Created by Frederikus J on 7/18/2016.
 */
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Circle;

public class Spike extends GameObject
{
    private Circle bounds;
    public Spike (Vector2 position)
    {
        super(position,true);
        setTex(new Texture(Gdx.files.internal("Spike.png")));
        bounds = new Circle(position,32);
    }

    public Circle getBoundaries()
    {
        return bounds;
    }

    public boolean isCollide(MainParticle mp)
    {
        if(bounds.overlaps(mp.getBoundaries()))
        {
            return true;
        }
        return false;
    }

}
