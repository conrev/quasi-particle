package com.quasiparticle.gameobject;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.quasiparticle.Math.Circle;
import com.quasiparticle.Math.Constants;

/**
 * Created by conrev on 7/19/16.
 */
public class RopeStand extends GameObject {

    private boolean isStatic;
    private Circle boundaries;
    private float radius;
    private boolean haveRopes;

    public RopeStand(Vector2 position, World w)
    {
        BodyDef bdef = new BodyDef();
        bdef.position.set(position.x/Constants.PPM,position.y/ Constants.PPM);
        bdef.fixedRotation=true;
        bdef.type= BodyDef.BodyType.StaticBody;

        Body body = w.createBody(bdef);

        CircleShape shape = new CircleShape();
        shape.setRadius(32/Constants.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape=shape;
        fdef.density=1;
        fdef.friction=0;
        fdef.isSensor=true;
        body.createFixture(fdef);

        shape.dispose();
        this.isStatic=true;
        setBody(body);

    }

    public RopeStand(Vector2 position,World world,float radius)
    {
        this(position,world);
        this.isStatic=false;
        boundaries=new Circle(getBody().getPosition().x* Constants.PPM,getBody().getPosition().y* Constants.PPM,radius);
    }

    public boolean shouldAttach(Vector2 mpos)
    {
        if(!isStatic && !haveRopes)
        {
            if(boundaries.contains(mpos))return true;

        }
        return false;
    }

    public void draw(ShapeRenderer shape)
    {
        if(!isStatic) {
            shape.begin(ShapeRenderer.ShapeType.Line);
            shape.circle(boundaries.x, boundaries.y, boundaries.radius);
            shape.end();
        }
    }

    public void addRopes()
    {
        haveRopes=true;
    }

    public boolean haveRopes()
    {
        return haveRopes;
    }







}
