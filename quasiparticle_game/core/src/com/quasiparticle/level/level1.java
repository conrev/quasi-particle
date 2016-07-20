package com.quasiparticle.level;

import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.game.GameScreen;
import com.quasiparticle.game.quasiparticle;

/**
 * Created by conrev on 7/20/16.
 */
public class level1 extends GameScreen {

    public level1(final quasiparticle gam)
    {
       super(gam);
    }
    public void CreateWorld()
    {
        CreatePlayer(new Vector2(640,420));

        CreateTarget(new Vector2(640,100));

        CreateRopeStand(new Vector2(640,600),0);

    }


}
