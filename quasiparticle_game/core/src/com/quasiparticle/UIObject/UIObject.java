package com.quasiparticle.UIObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Frederikus J on 7/19/2016.
 */
public class UIObject
{
   private Texture tx;
   private Vector2 position;


    public UIObject(Vector2 position, Texture tx)
    {
        this.position = position;
        this.tx = tx;
    }

    public UIObject(Vector2 position)
    {
        this.position = position;
    }

    public void setTexture(Texture tex)
    {
        this.tx = tex;
    }
    public Texture getTexture()
    {
        return this.tx;
    }
    public Vector2 getPosition()
    {
        return position;
    }

}
