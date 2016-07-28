package com.quasiparticle.gameobject;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.quasiparticle.Math.Constants;

/**
 * Created by conrev on 7/19/16.
 */
public class MainParticle extends GameObject {

    public MainParticle(Vector2 position,World world)
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x/ Constants.PPM,position.y/ Constants.PPM);
        bdef.type= BodyDef.BodyType.DynamicBody;

        Body body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(32/Constants.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape=shape;
        fdef.density=1;
        fdef.friction=0;
        fdef.filter.categoryBits= Constants.OBJECT;
        fdef.filter.maskBits=Constants.OBJECT;
        body.createFixture(fdef).setUserData("Player");
        setBody(body);
        shape.dispose();
        setTexture(new Texture(Gdx.files.internal("Crystal_Ball.png")));
    }




}
