package com.quasiparticle.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Constants;
import com.quasiparticle.Math.Intersector;
import com.quasiparticle.Math.Rectangle;

/**
 * Created by conrev on 7/13/16.
 */
public class Tramp extends GameObject {

    Rectangle rect;

    public Tramp(Vector2 position,float height,float width)
    {
        super(position,false);
        rect = new Rectangle(getPosition().x-width/2,getPosition().y-height/2,width,height);
        setTex(new Texture(Gdx.files.internal("tramp.png")));
    }

    public Rectangle getRect()
    {
        return rect;
    }

    public void PlayerCollision(MainParticle mp)
    {
        if(Intersector.overlaps(mp.getBoundaries(),rect)) {
            if(Math.abs(mp.getVelocity().y)<300) {
                setVelocity(new Vector2(mp.getVelocity().x,0));
                accelerate(new Vector2(0, Constants.GRAVITY));

            }

            else
            mp.setVelocity(new Vector2(mp.getVelocity().x,-mp.getVelocity().y));

        }

    }




}
