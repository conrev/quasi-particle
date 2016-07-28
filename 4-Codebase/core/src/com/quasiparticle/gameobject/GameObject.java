package com.quasiparticle.gameobject;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.quasiparticle.Math.Constants;
/**
 * Created by conrev on 7/12/16.
 */
public class GameObject {

    private Body body;
    private Texture tex;

    public GameObject() {
    }

    public Body getBody() {
        return body;
    }
    public void setBody(Body b) {
        if(b!=null)
        this.body=b;
    }


    public void setTexture(Texture t) {
        if(t!=null)
        tex = t;
    }

    public void draw(SpriteBatch spr) {
        spr.begin();
        spr.draw(tex, body.getPosition().x * Constants.PPM - 32, body.getPosition().y * Constants.PPM - 32);
        spr.end();
    }

}