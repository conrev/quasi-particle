package com.quasiparticle.UIObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Frederikus J on 7/19/2016.
 */
public class LevelButton extends UIObject
{
    private Circle bounds;
    private String levelText;
    public LevelButton(Vector2 position,String lv)
    {
        super(position);
        setTexture(new Texture(Gdx.files.internal("LevelButton.png")));
        this.levelText = lv;
        this.bounds = new Circle(position,32);
    }
     public Circle getBounds()
    {
        return bounds;
    }
    public String getLevelText()
    {
        return this.levelText;
    }
}
