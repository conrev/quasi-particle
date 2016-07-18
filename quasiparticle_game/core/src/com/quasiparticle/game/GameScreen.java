package com.quasiparticle.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.quasiparticle.gameobject.*;

public class GameScreen implements Screen {
    final quasiparticle game;

    private MainParticle mp;
    private TargetParticle tp;
    private int GAME_STATE;
    private ArrayList<Bubble> bub;
    private ArrayList<Spike> spike;
    private Tramp trmp;
    private Texture background;
    private RopeAnchor ra;

    private OrthographicCamera camera;
    public GameScreen(final quasiparticle gm) {
        this.game = gm;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        background=new Texture(Gdx.files.internal("quasi-particle.jpg"));
        bub=new ArrayList<Bubble>();
        spike = new ArrayList<Spike>();
        createWorld();

    }

    public void createWorld()
    {
        mp=new MainParticle(new Vector2(600,600));
        tp=new TargetParticle(new Vector2(600,32));
        trmp=new Tramp(new Vector2(300,100),32,80);
        //bub.add(new Bubble(new Vector2(600,200)));
        bub.add(new Bubble(new Vector2(300,500)));
        spike.add(new Spike(new Vector2(600,200)));
      //  ra=new RopeAnchor(new Vector2(500,600),false);
      //  ra.createConnection(mp);

        GAME_STATE=1;
    }

    public void performGameLogic() {
        if (tp.playerCollide(mp)) {
            GAME_STATE = 2;
        }


        for(int i=0;i<bub.size();i++) {
            bub.get(i).PlayerCollide(mp);
            bub.get(i).step(Gdx.graphics.getDeltaTime());
        }
        for(int i=0;i<spike.size();i++)
        {
            if(spike.get(i).isCollide((mp)))
            {
                GAME_STATE = 0;
            }
        }
        trmp.PlayerCollision(mp);
        mp.step(Gdx.graphics.getDeltaTime());
//        ra.step(Gdx.graphics.getDeltaTime());

    }

    public void draw()
    {

            game.batch.begin();
            game.shape.begin(ShapeRenderer.ShapeType.Line);
            game.batch.draw(background,0,0);
            if(GAME_STATE==0)
            {
                game.font.draw(game.batch,"YOU LOSE! ", 0, 720);

            }
            else if(GAME_STATE==1) {
                //game.font.draw(game.batch, "Current Ball Position x= " + mp.getBoundaries().x+" y= "+ mp.getBoundaries().y, 0, 720);
                game.font.draw(game.batch, "Current Ball Velocity x= " + mp.getVelocity().x+" y= "+ mp.getVelocity().y, 0, 720);
//                game.font.draw(game.batch, "Temporary Velocity" +ra.rp.tempvelocity+"Length: "+ra.rp.getLength(),0,700);
                game.batch.draw(mp.getTex(), mp.getPosition().x-32, mp.getPosition().y-32);
                game.batch.draw(tp.getTex(), tp.getPosition().x-32, tp.getPosition().y-32);
                game.shape.rect(trmp.getRect().getX(),trmp.getRect().getY(),80,32);
//                game.shape.line(ra.rp.startpos,ra.rp.endpos);
                for(int i=0;i<bub.size();i++)
                {
                    game.shape.circle(bub.get(i).getBoundaries().x,bub.get(i).getBoundaries().y,bub.get(i).getBoundaries().radius);
                 //   game.font.draw(game.batch,"Current Bubble Position: x= "+bub.get(i).getBoundaries().x+"y= "+bub.get(i).getBoundaries().y,0,690);
                }
                for(int i=0;i<spike.size();i++)
                {
                    game.batch.draw(spike.get(i).getTex(), spike.get(i).getPosition().x-32, spike.get(i).getPosition().y-32);
                }
            }
            else if(GAME_STATE==2)
            {
                game.font.draw(game.batch, "YOU WIN! ", 0, 720);
                game.batch.draw(tp.getTex(), tp.getPosition().x-32, tp.getPosition().y-32);
            }

            game.batch.end();
            game.shape.end();


    }




    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell the camera to update its matrices.
        camera.update();

        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);
        game.shape.setProjectionMatrix(camera.combined);
        // begin a new batch and draw the bucket and
        // all drops
        draw();
        if(GAME_STATE==1)performGameLogic();


        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(),0);

            camera.unproject(touchPos);
           for(int i=0;i<bub.size();i++) {
               if(bub.get(i).getBoundaries().contains(touchPos.x, touchPos.y)) bub.remove(i);

           }
            game.batch.begin();
            game.font.draw(game.batch,"Touch Position x="+ touchPos.x+"y = "+touchPos.y,0,660);
            game.batch.end();


        }
        /*
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            bucket.x += 200 * Gdx.graphics.getDeltaTime();

        // make sure the bucket stays within the screen bounds
        if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > 800 - 64)
            bucket.x = 800 - 64;

        // check if we need to create a new raindrop
        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we play back
        // a sound effect as well.
        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 9.8 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(bucket)) {
                dropsGathered++;
               // dropSound.play();
                iter.remove();

            }

        }
        */
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        //rainMusic.play();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        mp.getTex().dispose();
        tp.getTex().dispose();
        trmp.getTex().dispose();
        for(int i=0;i<bub.size();i++)
        {
            bub.get(i).getTex().dispose();
        }
    }

}