package com.quasiparticle.level;

import com.badlogic.gdx.math.Vector2;
import com.quasiparticle.game.GameScreen;
import com.quasiparticle.game.quasiparticle;

/**
 * Created by conrev on 7/20/16.
 */
public class level4 extends GameScreen {
    public level4(final quasiparticle gam)
    {
        super(gam);
    }
    public void CreateWorld()
    {
        CreatePlayer(new Vector2(640,420));

        CreateTarget(new Vector2(940,100));

        //CreateRopeStand(new Vector2(640,300),100);

        CreateRopeStand(new Vector2(640,570),0);
        CreateRopeStand(new Vector2(640,270),0);
        CreateRopeStand(new Vector2(340,520),0);
        CreateRopeStand(new Vector2(940,520),0);
        CreateRopeStand(new Vector2(340,320),0);
        CreateRopeStand(new Vector2(940,320),0);

        //CreateRopeStand(new Vector2())

    }
}
