package com.quasiparticle.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Circle;
/**
 * Created by conrev on 7/12/16.
 */
public class TargetParticle extends GameObject
{
    private Circle bounds;


    public TargetParticle(Vector2 position)
    {
        super(position,true);
        bounds=new Circle(position,32);
        setTex(new Texture(Gdx.files.internal("dog.png")));
    }

    public boolean playerCollide(MainParticle mp)
    {
        if(bounds.overlaps(mp.getBoundaries())) {
            return true;
        }
        else return false;

    }





}
