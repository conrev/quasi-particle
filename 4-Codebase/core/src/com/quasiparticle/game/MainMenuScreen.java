package com.quasiparticle.game;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.quasiparticle.UIObject.LevelButton;
import com.quasiparticle.level.level1;
import com.quasiparticle.level.level2;
import com.quasiparticle.level.level3;
import com.quasiparticle.level.level4;

/**
 * Created by conrev on 7/12/16.
 */
public class MainMenuScreen implements Screen {
    OrthographicCamera camera;
    Texture tex;
    final quasiparticle game;
    private ArrayList<LevelButton> lbuttons;
    private int leveltoload;

    public MainMenuScreen(final quasiparticle gam) {
        game = gam;
        tex= new Texture(Gdx.files.internal("quasi-particle3.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        lbuttons = new ArrayList<LevelButton>();
        lbuttons.add(new LevelButton(new Vector2(450,500),"1"));
        lbuttons.add(new LevelButton(new Vector2(550,500),"2"));
        lbuttons.add(new LevelButton(new Vector2(650,500),"3"));
        lbuttons.add(new LevelButton(new Vector2(750,500),"4"));

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        if(leveltoload!=0)
        {
            if(leveltoload==1)game.setScreen(new level1(game));
            if(leveltoload==2)game.setScreen(new level2(game));
            if(leveltoload==3)game.setScreen(new level3(game));
            if(leveltoload==4)game.setScreen(new level4(game));




        }
        game.batch.begin();
        game.batch.draw(tex,0,0);
        game.font.draw(game.batch, "Welcome to Quasi Particle",500, 640);
        game.font.draw(game.batch, "Select A Level:", 530, 600);
        for(int i=0; i<lbuttons.size();i++)
        {
            System.out.println("tes");
            game.batch.draw(lbuttons.get(i).getTexture(),lbuttons.get(i).getPosition().x-32,lbuttons.get(i).getPosition().y-32);
            game.font.draw(game.batch, lbuttons.get(i).getLevelText(), lbuttons.get(i).getPosition().x-5,lbuttons.get(i).getPosition().y+10);
        }
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(touchPos);
            for(int i=0;i<lbuttons.size();i++)
            {
                if(lbuttons.get(i).getBounds().contains(touchPos.x,touchPos.y))
                {
                    leveltoload=i+1;
                    dispose();
                }
            }


        }

    }
    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
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
    }
}

