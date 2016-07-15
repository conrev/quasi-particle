package com.quasiparticle.gameobject;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Circle;


/**
 * Created by conrev on 7/13/16.
 */
public class RopeAnchor extends GameObject {

    Circle cir;
    boolean dynamic;
    public Rope rp;
    public RopeAnchor(Vector2 position,boolean isDynamic)
    {
        super(position,false);
        dynamic=isDynamic;
    }

    public void createConnection(MainParticle mp)
    {
        rp=new Rope(this);
        rp.attach(mp);

    }
    public void step(float dt)
    {
        if(rp!=null)
        {
            rp.step(dt);
        }
    }




}
