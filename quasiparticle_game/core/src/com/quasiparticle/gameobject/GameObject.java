package com.quasiparticle.gameobject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Constants;
/**
 * Created by conrev on 7/12/16.
 */
public class GameObject {

    private Vector2 position;
    private Vector2 velocity;
    private boolean isStatic;
    private Texture tex;

    public GameObject(Vector2 pos, boolean st) {
        position = pos;
        isStatic = st;
        velocity = new Vector2();
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public void setPosition(Vector2 pos)
    {
        this.position=pos;
    }

    public void setTex(Texture t)
    {
        tex=t;
    }

    public Texture getTex()
    {
        return tex;
    }

    public Vector2 getVelocity()
    {
        return velocity;
    }

    public void setVelocity(Vector2 velo)
    {
        velocity=velo;
    }


    public void accelerate(Vector2 acceleration)
    {
        velocity.add(acceleration);
    }

    public void step(float dt)
    {
        if(!isStatic) {
            accelerate(new Vector2(0,-Constants.GRAVITY));
            setPosition(getPosition().add(velocity.x*dt,velocity.y*dt));
        }

    }




}
