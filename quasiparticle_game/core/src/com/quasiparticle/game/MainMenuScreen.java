package com.quasiparticle.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by conrev on 7/12/16.
 */
public class MainMenuScreen implements Screen {
    OrthographicCamera camera;
    Texture tex;
    final quasiparticle game;

    public MainMenuScreen(final quasiparticle gam) {
        game = gam;
        tex= new Texture(Gdx.files.internal("quasi-particle3.jpg"));
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(tex,0,0);
        game.font.draw(game.batch, "Welcome to Quasi Particle",500, 360);
        game.font.draw(game.batch, "Tap anywhere to begin!", 530, 320);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
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


