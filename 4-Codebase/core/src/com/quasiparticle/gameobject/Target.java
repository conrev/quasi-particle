package com.quasiparticle.gameobject;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.quasiparticle.Math.Constants;

/**
 * Created by conrev on 7/20/16.
 */
public class Target extends GameObject {

    public Target(Vector2 position, World world)
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x/ Constants.PPM,position.y/ Constants.PPM);
        bdef.fixedRotation=true;
        bdef.type= BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50/Constants.PPM,25/ Constants.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape=shape;
        fdef.density=1;
        fdef.friction=0;
        fdef.filter.categoryBits= Constants.OBJECT;
        fdef.filter.maskBits=Constants.OBJECT;
        body.createFixture(fdef).setUserData("Target");

        shape.dispose();

    }

}
