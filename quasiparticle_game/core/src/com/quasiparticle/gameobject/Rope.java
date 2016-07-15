package com.quasiparticle.gameobject;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Constants;
import sun.applet.Main;

/**
 * Created by conrev on 7/13/16.
 */
public class Rope {

    public Vector2 startpos;
    public Vector2 endpos;
    private float length;
    private float angle;
    private float angularvelocity;
    private float angularaccel;
    private boolean isAttached;
    private MainParticle mp;
    public float tempvelocity;

    public Rope(RopeAnchor ra)
    {
        this.startpos=ra.getPosition();
    }

    public float getLength()
    {
        return length;
    }

    public void attach(MainParticle mp)
    {
        if(this.mp==null)this.mp=mp;
        endpos=mp.getPosition();
        length=Math.abs(startpos.dst(endpos));
        isAttached=true;
        Vector2 temp=new Vector2(startpos);
        angle= temp.sub(endpos).angleRad();
    }

    public void step(float dt)
    {
            tempvelocity= (float)Math.sqrt(2*Constants.GRAVITY*length*(1-Math.cos(angle)));

            endpos=mp.getPosition();



            angularaccel=Constants.GRAVITY/length * (float) Math.sin(angle);
            angularvelocity+=angularaccel;
            angle+=angularvelocity*dt;


            mp.setVelocity(new Vector2(tempvelocity*(float)Math.cos(angle),tempvelocity*(float)Math.sin(angle)));


    }





}
