package com.quasiparticle.gameobject;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.quasiparticle.Math.Circle;
import com.quasiparticle.Math.Constants;

/**
 * Created by conrev on 7/19/16.
 */
public class Bubble extends GameObject {

    Circle boundaries;

    public Bubble(Vector2 position, World world)
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x/Constants.PPM,position.y/ Constants.PPM);
        bdef.fixedRotation=true;
        bdef.type= BodyDef.BodyType.StaticBody;

        Body body = world.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(30/Constants.PPM);
        FixtureDef fdef = new FixtureDef();
        fdef.shape=shape;
        fdef.density=1;
        fdef.friction=0;
        fdef.isSensor=true;
        fdef.filter.categoryBits= Constants.OBJECT;
        fdef.filter.maskBits=Constants.OBJECT;
        body.createFixture(fdef).setUserData("Bubble");

        shape.dispose();
        setBody(body);
        //setTexture(new Texture(Gdx.files.internal("")));
        boundaries=new Circle(position.x,position.y,32);
    }

    public Circle getBoundaries()
    {
        return boundaries;
    }

    public boolean checkPoint(Vector2 point)
    {
        if(boundaries.contains(point))
        {
            return true;
        }
        return false;
    }


    public void draw(ShapeRenderer shape)
    {
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.circle(boundaries.x,boundaries.y,boundaries.radius);
        shape.end();

    }






}
