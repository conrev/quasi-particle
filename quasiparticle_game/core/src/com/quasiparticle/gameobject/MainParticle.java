package com.quasiparticle.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Circle;
/**
 * Created by conrev on 7/12/16.
 */
public class MainParticle extends GameObject {

    private Circle bounds;

    public MainParticle(Vector2 position) {
        super(position,false);
        setTex(new Texture(Gdx.files.internal("Crystal_Ball.png")));
        bounds = new Circle(position,32);

    }


    public Circle getBoundaries()
    {
        return bounds;
    }



    @Override
    public void step(float dt)
    {
        super.step(dt);
        //setPosition(getPosition().add(getVelocity().x*dt,getVelocity().y*dt));
        bounds.setPosition(getPosition());
    }









}
