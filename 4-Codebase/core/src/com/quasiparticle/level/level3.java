package com.quasiparticle.level;

/**
 * Created by conrev on 7/20/16.
 */

import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.game.GameScreen;
import com.quasiparticle.game.quasiparticle;

public class level3 extends GameScreen {

    public level3(final quasiparticle gam)
    {
        super(gam);
    }
    public void CreateWorld()
    {
        CreatePlayer(new Vector2(640,620));

        CreateTarget(new Vector2(640,100));

        CreateRopeStand(new Vector2(640,300),100);

    }

}