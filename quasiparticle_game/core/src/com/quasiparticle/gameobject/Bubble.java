package com.quasiparticle.gameobject;



import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.Math.Constants;
import com.quasiparticle.Math.Circle;
/**
 * Created by conrev on 7/12/16.
 */
public class Bubble extends GameObject {
    MainParticle mp;
    Circle bounds;
    private float tta;

    public Bubble(Vector2 position)
    {
        super(position,true);
        bounds = new Circle(position,50);
        tta=22;
    }

    public void PlayerCollide(MainParticle mp)
    {
        if(bounds.overlaps(mp.getBoundaries()))
        {
            this.mp=mp;

        }

    }

    public Circle getBoundaries()
    {
        return bounds;
    }


    @Override
    public void step(float dt)
    {

        if(mp!=null)
        {
            bounds.setPosition(mp.getPosition());
            if(tta!=0) {
                mp.accelerate(new Vector2(0, 50f));
                tta-=1;
            }
            else
                mp.accelerate(new Vector2(0, Constants.GRAVITY));
        }
    }










}
