package com.quasiparticle.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class quasiparticle extends Game {


		public SpriteBatch batch;
		public BitmapFont font;
		public ShapeRenderer shape;

		public void create() {
			batch = new SpriteBatch();
			//Use LibGDX's default Arial font.
			font = new BitmapFont(Gdx.files.internal("gdf.fnt"),false);
			shape = new ShapeRenderer();
			this.setScreen(new MainMenuScreen(this));
		}

		public void render() {
			super.render(); //important!
		}

		public void dispose() {
			batch.dispose();
			font.dispose();
			shape.dispose();
		}

	}

