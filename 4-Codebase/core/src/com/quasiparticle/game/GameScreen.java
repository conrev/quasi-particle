package com.quasiparticle.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.quasiparticle.Math.Arc;
import com.quasiparticle.Math.Circle;
import com.quasiparticle.Math.Constants;
import com.quasiparticle.gameobject.*;

public class GameScreen implements Screen {
    final quasiparticle game;


    private MainParticle mp;
    private ArrayList<Spikes> sp;
    private ArrayList<Bubble> bub;
    private ArrayList<Ropes> rp;
    private ArrayList<RopeStand> rs;
    private ArrayList<Tramp> trmp;
    private Texture background;
    private ContactListener cl;
    private int GAME_STATE;
    private Box2DDebugRenderer b2dbg;
    private Target tg;
    private Arc a;
    /*
    private MainParticle mp;
    private TargetParticle tp;
    private int GAME_STATE;
    private ArrayList<Bubble> bub;
    private ArrayList<Spike> spike;
    private Tramp trmp;
    private RopeAnchor ra;
    */
    private World world;

    private OrthographicCamera camera;

    public GameScreen(final quasiparticle gm) {

        world=new World(new Vector2(0,-9.81f),true);
        cl=new ContactListener();
        world.setContactListener(cl);
        sp=new ArrayList<Spikes>();
        bub=new ArrayList<Bubble>();
        rp=new ArrayList<Ropes>();
        rs=new ArrayList<RopeStand>();
        trmp=new ArrayList<Tramp>();
        this.game = gm;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        background = new Texture(Gdx.files.internal("quasi-particle.jpg"));
        b2dbg=new Box2DDebugRenderer();
        GAME_STATE=1;
        CreateWorld();
        a=new Arc();

    }

    public void CreateWorld()
    {
        CreatePlayer(new Vector2(640,520));
        CreateSpikes(new Vector2(460,100));
        CreateTarget(new Vector2(640,100));
        CreateBubble(new Vector2(640,300));
        CreateRopeStand(new Vector2(440,420),0);
        CreateRopeStand(new Vector2(840,620),0);
        CreateRopeStand(new Vector2(440,620),0);
        CreateRopeStand(new Vector2(840,420),0);
      //  CreateTramp(new Vector2(640,200));

    }


    public void CreatePlayer(Vector2 position)
    {

        mp=new MainParticle(position,world);

    }
    public void CreateTarget(Vector2 position)
    {

        tg=new Target(position,world);
    }
    public void CreateSpikes(Vector2 position)
    {

        Spikes s=new Spikes(position,world);
        sp.add(s);
    }

    public void CreateBubble(Vector2 position)
    {

        Bubble s=new Bubble(position,world);
        bub.add(s);
    }

    public void CreateRopeStand(Vector2 position,float radius) {
        if (radius == 0)
        {
        rs.add(new RopeStand(position,world));
            rp.add(new Ropes(rs.get(rs.size() - 1).getBody(), mp.getBody(), world, 1f));
            rs.get(rs.size() - 1).addRopes();
        }
        else
        {
            rs.add(new RopeStand(position,world,radius));
        }
    }

    public void CreateTramp(Vector2 position)
    {
        trmp.add(new Tramp(position,world));
    }



    public void draw()
    {

            game.batch.begin();
            game.batch.draw(background,0,0);

            if(GAME_STATE==0)
            {
                game.batch.draw(new Texture(Gdx.files.internal("game-over.png")),0,0);
                game.font.draw(game.batch,"YOU LOSE! ", 570, 560);
                game.font.draw(game.batch, "Tap anywhere to return to title screen.",430,520);


                if(Gdx.input.isTouched())
                {
                    game.setScreen(new MainMenuScreen(game));
                }
            }
             game.batch.end();
              if(GAME_STATE==1) {

                  mp.draw(game.batch);
                  for (Spikes spike : sp) {
                      spike.draw(game.batch);
                  }

                  for (Bubble b : bub) {
                      game.shape.begin(ShapeRenderer.ShapeType.Line);
                      game.shape.circle(b.getBoundaries().x, b.getBoundaries().y, b.getBoundaries().radius);
                      game.shape.end();
                  }

                  for (RopeStand r : rs) {
                      game.shape.begin(ShapeRenderer.ShapeType.Line);
                      game.shape.circle(r.getBody().getPosition().x * Constants.PPM, r.getBody().getPosition().y * Constants.PPM, 32);
                      //  game.shape.line(r.getBody().getPosition().scl(Constants.PPM),mp.getBody().getPosition().scl(Constants.PPM));
                      game.shape.end();
                      r.draw(game.shape);
                  }

                  for (Ropes r : rp) {

                  }

              }
            if(GAME_STATE==2)
            {   game.batch.begin();
                game.batch.draw(new Texture(Gdx.files.internal("winscreen.jpg")),0,0);
                game.font.draw(game.batch, "Tap anywhere to return to title screen.",430,520);


                if(Gdx.input.isTouched())
                {
                    game.setScreen(new MainMenuScreen(game));
                }
                game.batch.end();


            }
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

        Circle cr = new Circle(mp.getBody().getPosition().x*Constants.PPM,mp.getBody().getPosition().y*Constants.PPM,32);
        for(int i=0;i<bub.size();i++) {
            if (bub.get(i).getBoundaries().contains(mp.getBody().getPosition().scl(Constants.PPM)))
                {bub.remove(i);
                cl.gofloat();}
        }
        draw();
        world.step(Gdx.graphics.getDeltaTime(),1,1);

        if(cl.isWinning())
            GAME_STATE=2;
        if(cl.isPlayerDead()&&GAME_STATE!=2)
            GAME_STATE=0;
        if((mp.getBody().getPosition().x*Constants.PPM<0||mp.getBody().getPosition().y*Constants.PPM<0||mp.getBody().getPosition().x*Constants.PPM>1280||mp.getBody().getPosition().y*Constants.PPM>720)&&!cl.isWinning())
            GAME_STATE=0;

       // game.font.draw(game.batch,"Main Particle Position: ")

            if(cl.isFloating()){

            MassData md = mp.getBody().getMassData();
            md.mass=0;
            game.shape.begin(ShapeRenderer.ShapeType.Line);
            game.shape.circle(cr.x,cr.y,cr.radius);
            game.shape.end();
            if(mp.getBody().getLinearVelocity().y<10)mp.getBody().applyLinearImpulse((new Vector2(0,50*delta)),mp.getBody().getWorldCenter(),false);
            if(mp.getBody().getLinearVelocity().y>5)mp.getBody().setLinearVelocity(new Vector2(0,5));
            if (Gdx.input.isTouched()) {
                Vector3 touchPos = new Vector3();
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(touchPos);
                if(cr.contains(new Vector2(touchPos.x,touchPos.y)))
                    cl.pop();
            }
        }
        else {
            MassData md = mp.getBody().getMassData();
            md.mass=1;

        }

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            for(Ropes r : rp)
            {
                if(r.checkPoint(new Vector2(touchPos.x/Constants.PPM,touchPos.y/Constants.PPM))) {
                    r.dispose();
                }
            }
        }

        for(RopeStand r: rs) {
            if(r.shouldAttach(mp.getBody().getPosition().scl(Constants.PPM)))
            {
                rp.add(new Ropes(r.getBody(),mp.getBody(),world,1f));
                r.addRopes();
            }
        }

        Array<Body> bodies = cl.getBodies();
        for(int i = 0; i < bodies.size; i++) {
            Body b = bodies.get(i);
            world.destroyBody(bodies.get(i));
        }
        bodies.clear();

        if(GAME_STATE==1)b2dbg.render(world,camera.combined.scale(Constants.PPM,Constants.PPM,0));
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
        /*
        mp.getTex().dispose();
        tp.getTex().dispose();
        trmp.getTex().dispose();
        for(int i=0;i<bub.size();i++)
        {
            bub.get(i).getTex().dispose();
        }
        */
    }

}