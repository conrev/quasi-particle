package com.quasiparticle.game;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

/**
 * Created by conrev on 7/19/16.
 */
public class ContactListener implements com.badlogic.gdx.physics.box2d.ContactListener {

        private Array<Body> bodiesToRemove;
        private boolean playerDead;
        private boolean floating;
        private boolean winning;

        public ContactListener() {
            super();
            bodiesToRemove = new Array<Body>();
        }

        public void beginContact(Contact contact) {

            Fixture fa = contact.getFixtureA();
            Fixture fb = contact.getFixtureB();



                if (fa.getUserData() !=null && fa.getUserData().equals("Target")) {
                    bodiesToRemove.add(fa.getBody());
                    winning = true;
                }
                if (fb.getUserData() !=null && fb.getUserData().equals("Target")) {
                    bodiesToRemove.add(fb.getBody());
                    winning = true;
                }
                if (fa.getUserData() !=null && fa.getUserData().equals("Spikes")) {
                    playerDead = true;
                }
                if (fb.getUserData() !=null && fb.getUserData().equals("Spikes")) {
                    playerDead = true;
                }
                if (fa.getUserData() != null && fa.getUserData().equals("Bubble")) {
                    bodiesToRemove.add(fa.getBody());
                }
                if (fb.getUserData() != null && fb.getUserData().equals("Bubble")) {
                    bodiesToRemove.add(fb.getBody());
                }



        }

        public void endContact(Contact contact) {

            Fixture fa = contact.getFixtureA();
            Fixture fb = contact.getFixtureB();

            if(fa == null || fb == null) return;


        }

        public Array<Body> getBodies() { return bodiesToRemove; }
        public boolean isPlayerDead() { return playerDead; }
        public boolean isFloating() { return floating;}
        public void gofloat(){floating=true;}
        public void pop() {floating=false;}
        public boolean isWinning () {return winning;}
        public void preSolve(Contact c, Manifold m) {}
        public void postSolve(Contact c, ContactImpulse ci) {}

}


