package com.quasiparticle.gameobject;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.quasiparticle.Math.Constants;

public class Ropes {

    public Body attachBodyA;
    public Body attachBodyB;
    public ArrayList<Body> bodies = new ArrayList<Body>();
    public Vector2[] bodiesvec = new Vector2[3];
    public World world;
    public boolean isTramp;

    public Ropes(Body attachBodyA, Body attachBodyB, World world,float multiplier) {
        this(attachBodyA, attachBodyB, world, 0, false, false,multiplier);
    }

    public Ropes(Body attachBodyA, Body attachBodyB, World world, boolean elastic) {
        this(attachBodyA, attachBodyB, world, 0, elastic, false,1f);
    }

    public Ropes(Body attachBodyA, Body attachBodyB, World world, int extraLength, boolean elastic) {
        this(attachBodyA, attachBodyB, world, extraLength, elastic, false,1f);
    }

    public Ropes(Body attachBodyA, Body attachBodyB, World world, int extraLength, boolean elastic, boolean enableCollision,float multiplier) {
        RopeJointDef rj = new RopeJointDef();

        float distance=attachBodyA.getPosition().dst(attachBodyB.getPosition());
        rj.localAnchorA.set(0,-10f/Constants.PPM/2);
        rj.localAnchorB.set(0,10f/Constants.PPM/2);
        rj.maxLength=distance/2f;

        this.attachBodyA=attachBodyA;
        this.attachBodyB=attachBodyB;
        this.world = world;

        attachBodyA.setAngularDamping(1f);
        attachBodyB.setAngularDamping(1f);

        BodyDef bdef=new BodyDef();
        bdef.type=BodyType.DynamicBody;
        float width=10f,height=10f;

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(height/Constants.PPM/2,width/Constants.PPM/2);
        FixtureDef fd = new FixtureDef();
        fd.isSensor=true;
        fd.shape=shape;
        fd.filter.categoryBits= Constants.NON_OBJECT;
        fd.filter.maskBits=Constants.NON_OBJECT;

        bdef.angularDamping=10f;
        bdef.linearDamping=2f;
        fd.friction=10f;

        Vector2 dv;
        dv = attachBodyA.getPosition().sub(attachBodyB.getPosition());
        Vector2 nb=attachBodyA.getPosition();
        float incx=dv.x;
        float incy=dv.y;
        Body tempBody=attachBodyA;
        Body tempBody2=attachBodyA;

        bodies.add(attachBodyA);
        bodiesvec[0]=attachBodyA.getPosition();
        RevoluteJointDef rvj = new RevoluteJointDef();
        for(float i=0;i<3;i++);
        {
            nb.add(incx/Constants.PPM*2,incy/Constants.PPM*2);
         //   System.out.println(nb.x+" "+nb.y);
            bdef.position.set(nb);

            //bdef.angle=dv.angleRad();
            tempBody2=world.createBody(bdef);
            tempBody.createFixture(fd);
            rj.bodyA=tempBody;
            rj.bodyB=tempBody2;
             world.createJoint(rj);

            rvj.bodyA=tempBody;
            rvj.bodyB=tempBody2;
            rvj.localAnchorA.set(0,-10f/Constants.PPM/2);
            rvj.localAnchorB.set(0,10f/Constants.PPM/2);
        //     world.createJoint(rvj);

            bodies.add(tempBody2);
            bodiesvec[1]=tempBody2.getPosition();
            tempBody=tempBody2;

        }
            rj.bodyA=tempBody2;
            rj.bodyB=attachBodyB;
            world.createJoint(rj);
            rvj.bodyA=tempBody2;
            rvj.bodyB=attachBodyB;

            bodiesvec[2]=attachBodyB.getPosition();
         //  world.createJoint(rvj);



        shape.dispose();
    }

    public boolean checkPoint(Vector2 point) {
        for (Body b : bodies) {
            if (b.getPosition().dst2(point) < 1 )
                return true;
        }
        return false;
    }

    public void dispose() {
        for (Body b : bodies) {
            while(b.getJointList().size!=0) {
                world.destroyJoint(b.getJointList().get(0).joint);
            }
            b.setGravityScale(3);
        }
    }


}
