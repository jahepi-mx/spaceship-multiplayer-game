package com.jahepi.tank.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.jahepi.tank.Assets;
import com.jahepi.tank.Config;
import com.jahepi.tank.Language;
import com.jahepi.tank.TankField;
import com.jahepi.tank.TankField.SCREEN_TYPE;

public class Main implements Screen {
	
	public static final String TAG = "Main";

	private Stage stage;
	private TankField tankField;
	private SpriteBatch batch;
	
	public Main(TankField tankField) {
		this.tankField = tankField;
		batch = tankField.getBatch();
		StretchViewport viewport = new StretchViewport(Config.UI_WIDTH, Config.UI_HEIGHT);
		stage = new Stage(viewport, batch);
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		stage.clear();
		if (!Assets.getInstance().getMusic().isPlaying()) {
			Assets.getInstance().getMusic().play();
		}
		
		LabelStyle titleStyle = new LabelStyle();
		titleStyle.font = Assets.getInstance().getUIFontTitle();
		Label titleLabel = new Label(String.format(Language.getInstance().get("game_title"), "\n", Config.VERSION), titleStyle);
		titleLabel.setAlignment(Align.center);
		
		LabelStyle style = new LabelStyle();
		BitmapFont uiFont = Assets.getInstance().getUIFontMain();
		style.font = uiFont;
		
		Label playLabel = new Label(Language.getInstance().get("play_btn"), style);
		Button playBtn = new Button(new ButtonStyle());
		playBtn.setWidth(playLabel.getWidth());
		playBtn.add(playLabel);
		
		Label configLabel = new Label(Language.getInstance().get("config_btn"), style);
		Button configBtn = new Button(new ButtonStyle());
		configBtn.setWidth(configLabel.getWidth());
		configBtn.add(configLabel);
		
		Label creditsLabel = new Label(Language.getInstance().get("credits_btn"), style);
		Button creditsBtn = new Button(new ButtonStyle());
		creditsBtn.setWidth(creditsLabel.getWidth());
		creditsBtn.add(creditsLabel);
		
		playBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				tankField.changeScreen(SCREEN_TYPE.GAMEOPTIONS);
			}		
		});
		
		configBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				tankField.changeScreen(SCREEN_TYPE.CONFIG);
			}		
		});
		
		Table table = new Table();
		table.add(titleLabel).pad(40.0f);
		table.row();
		table.add(playBtn).pad(10.0f).uniform();
		table.row();
		table.add(configBtn).pad(10.0f).uniform();
		table.row();
		table.add(creditsBtn).pad(10.0f).uniform();
		table.setFillParent(true);
		table.getColor().a = 0;
		table.addAction(Actions.fadeIn(0.5f));
		table.pack();
		
		stage.addActor(table);
	}

	@Override
	public void render(float delta) {
		batch.begin();
		batch.setShader(null);
		batch.draw(Assets.getInstance().getMainBackground(), 0, 0, Config.UI_WIDTH, Config.UI_HEIGHT);
		batch.end();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, false);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		stage.clear();
		stage = null;
	}
}
