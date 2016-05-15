package com.jahepi.tank;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Disposable;

public class Assets implements Disposable {

	private static Assets self;
	
	private AssetManager manager;
	private TextureAtlas atlas;
	private ParticleEffect effect1;
	private ParticleEffect effect2;
	private Sound audio1;
	private Sound audio2;
	private Sound audioItem;
	private Sound audioSpeedUp;
	private Sound destroySound;
	private Music music, action;
	private FreeTypeFontGenerator fontGenerator;
	private BitmapFont UIFontMain, UIFont, UIFontSmall, UIFontTitle, UIFontExtraSmall;
	private ShaderProgram monochromeShader;
	private Skin skin;
	private Preferences preferences;
	
	private Assets() {
		manager = new AssetManager();
		manager.load("images/multiplayer.pack", TextureAtlas.class);
		manager.finishLoading();
		atlas = manager.get("images/multiplayer.pack");
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		preferences = Gdx.app.getPreferences("game_preferences");
		
		preferences.putFloat("music", 0.3f);
		preferences.putFloat("effects", 1.0f);
		preferences.putInteger("port", 38000);
		preferences.putString("name", "xxxx");
		preferences.putInteger("ms", 200);
		preferences.putString("language", Language.SPANISH);
		
		effect1 = new ParticleEffect();
		effect1.load(Gdx.files.internal("particles/effect1.pfx"), Gdx.files.internal("images"));
		effect1.scaleEffect(0.05f);
		effect2 = new ParticleEffect();
		effect2.load(Gdx.files.internal("particles/effect2.pfx"), Gdx.files.internal("images"));
		effect2.scaleEffect(0.05f);
		audio1 = Gdx.audio.newSound(Gdx.files.internal("audio/laser1.mp3"));
		audio2 = Gdx.audio.newSound(Gdx.files.internal("audio/laser2.mp3"));
		audioItem = Gdx.audio.newSound(Gdx.files.internal("audio/powerup.mp3"));
		destroySound = Gdx.audio.newSound(Gdx.files.internal("audio/explosion.wav"));
		audioSpeedUp = Gdx.audio.newSound(Gdx.files.internal("audio/speedup.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		music.setVolume(getMusicVolume());
		music.setLooping(true);
		action = Gdx.audio.newMusic(Gdx.files.internal("audio/action.ogg"));
		action.setVolume(getMusicVolume());
		action.setLooping(true);
		
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font/game.ttf"));
		
		FreeTypeFontParameter parameters1 = new FreeTypeFontParameter();
	    parameters1.size = 60;
	    parameters1.shadowOffsetX = 1;
	    parameters1.shadowOffsetY = 1;
	    parameters1.color = Color.WHITE;
	    UIFont = fontGenerator.generateFont(parameters1);
	    
	    FreeTypeFontParameter parameters2 = new FreeTypeFontParameter();
	    parameters2.size = 45;
	    parameters2.shadowOffsetX = 1;
	    parameters2.shadowOffsetY = 1;
	    parameters2.color = Color.WHITE;
	    UIFontSmall = fontGenerator.generateFont(parameters2);
	    
	    FreeTypeFontParameter parameters3 = new FreeTypeFontParameter();
	    parameters3.size = 90;
	    parameters3.shadowOffsetX = 1;
	    parameters3.shadowOffsetY = 1;
	    parameters3.color = Color.GREEN;
	    parameters3.borderWidth = 2;
	    parameters3.borderColor = Color.GRAY;
	    UIFontTitle = fontGenerator.generateFont(parameters3);
	    
	    FreeTypeFontParameter parameters4 = new FreeTypeFontParameter();
	    parameters4.size = 40;
	    parameters4.shadowOffsetX = 1;
	    parameters4.shadowOffsetY = 1;
	    parameters4.color = Color.WHITE;
	    UIFontExtraSmall = fontGenerator.generateFont(parameters4);
	    
	    FreeTypeFontParameter parameters5 = new FreeTypeFontParameter();
	    parameters5.size = 80;
	    parameters5.shadowOffsetX = 1;
	    parameters5.shadowOffsetY = 1;
	    parameters5.color = Color.ORANGE;
	    parameters5.borderWidth = 2;
	    parameters5.borderColor = Color.WHITE;
	    UIFontMain = fontGenerator.generateFont(parameters5);
	    
	    monochromeShader = new ShaderProgram(Gdx.files.internal("shader/monochrome.vs"), Gdx.files.internal("shader/monochrome.fs"));
	    monochromeShader.setUniformf("u_amount", 1.0f);
	}
	
	public static Assets getInstance() {
		if (self == null) {
			self = new Assets();
		}
		return self;
	}

	public TextureRegion getBackground() {
		return atlas.findRegion("background_game");
	}

	public TextureRegion getMainBackground() {
		return atlas.findRegion("background_main");
	}

	public TextureRegion getShip1() {
		return atlas.findRegion("ship1");
	}

	public TextureRegion getShip2() {
		return atlas.findRegion("ship2");
	}
	
	public TextureRegion getShip3() {
		return atlas.findRegion("ship3");
	}
	
	public TextureRegion getShip4() {
		return atlas.findRegion("ship4");
	}
	
	public TextureRegion getShip5() {
		return atlas.findRegion("ship5");
	}
	
	public TextureRegion getSKull() {
		return atlas.findRegion("skull");
	}

	public TextureRegion getRocket1() {
		return atlas.findRegion("rocket1");
	}

	public TextureRegion getRocket2() {
		return atlas.findRegion("rocket2");
	}
	
	public TextureRegion getRocket3() {
		return atlas.findRegion("rocket3");
	}
	
	public TextureRegion getRocket4() {
		return atlas.findRegion("rocket4");
	}
	
	public TextureRegion getRocket5() {
		return atlas.findRegion("rocket5");
	}
	
	public TextureRegion getRocket6() {
		return atlas.findRegion("rocket6");
	}

	public ParticleEffect getEffect1() {
		return effect1;
	}
	
	public ParticleEffect getEffect2() {
		return effect2;
	}

	public Sound getAudio1() {
		return audio1;
	}

	public Sound getAudio2() {
		return audio2;
	}
	
	public Sound getAudioItem() {
		return audioItem;
	}

	public Sound getDestroySound() {
		return destroySound;
	}

	public Sound getAudioSpeedUp() {
		return audioSpeedUp;
	}

	public Music getMusic() {
		return music;
	}
	
	public Music getActionMusic() {
		return action;
	}

	public BitmapFont getUIFont() {
		return UIFont;
	}

	public BitmapFont getUIFontSmall() {
		return UIFontSmall;
	}
	
	public BitmapFont getUIFontTitle() {
		return UIFontTitle;
	}
	
	public BitmapFont getUIFontExtraSmall() {
		return UIFontExtraSmall;
	}
	
	public BitmapFont getUIFontMain() {
		return UIFontMain;
	}

	public TextureRegion getNukeItem() {
		return atlas.findRegion("nuke");
	}
	
	public TextureRegion getEneryItem() {
		return atlas.findRegion("energy");
	}
	
	public TextureRegion getShieldItem() {
		return atlas.findRegion("shield");
	}
	
	public TextureRegion getHealthItem() {
		return atlas.findRegion("health");
	}
	
	public TextureRegion getFreezeItem() {
		return atlas.findRegion("freeze");
	}

	public TextureRegion getLeftArrow() {
		return atlas.findRegion("button_arrow_left");
	}
	
	public TextureRegion getLeftArrowOn() {
		return atlas.findRegion("button_arrow_left_on");
	}

	public TextureRegion getRightArrow() {
		return atlas.findRegion("button_arrow_right");
	}
	
	public TextureRegion getRightArrowOn() {
		return atlas.findRegion("button_arrow_right_on");
	}

	public TextureRegion getTopArrow() {
		return atlas.findRegion("button_arrow_top");
	}
	
	public TextureRegion getTopArrowOn() {
		return atlas.findRegion("button_arrow_top_on");
	}

	public TextureRegion getBottomArrow() {
		return atlas.findRegion("button_arrow_bottom");
	}
	
	public TextureRegion getBottomArrowOn() {
		return atlas.findRegion("button_arrow_bottom_on");
	}

	public TextureRegion getShootButton() {
		return atlas.findRegion("button_shoot");
	}
	
	public TextureRegion getShootButtonOn() {
		return atlas.findRegion("button_shoot_on");
	}
	
	public TextureRegion getLaser() {
		return atlas.findRegion("laser");
	}

	public ShaderProgram getMonochromeShader() {
		return monochromeShader;
	}
	
	public Animation getSpeedUpAnimation() {
		return new Animation(1.0f/12.0f, atlas.findRegions("OrangeBulletExplo"), Animation.PlayMode.LOOP);
	}

	public Skin getSkin() {
		return skin;
	}
	
	public String getNickname() {
		return preferences.getString("name");
	}
	
	public void setNickname(String name) {
		preferences.putString("name", name);
	}
	
	public int getPort() {
		return preferences.getInteger("port");
	}
	
	public void setPort(int port) {
		preferences.putInteger("port", port);
	}
	
	public int getMs() {
		return preferences.getInteger("ms");
	}
	
	public void setMs(int ms) {
		preferences.putInteger("ms", ms);
	}
	
	public float getMusicVolume() {
		return preferences.getFloat("music");
	}
	
	public void setMusicVolume(float volume) {
		preferences.putFloat("music", volume);
	}
	
	public float getEffectsVolume() {
		return preferences.getFloat("effects");
	}
	
	public void setEffectsVolume(float volume) {
		preferences.putFloat("effects", volume);
	}
	
	public void setLanguage(String language) {
		preferences.putString("language", language);
	}
	
	public String getLanguage() {
		return preferences.getString("language");
	}

	@Override
	public void dispose() {
		monochromeShader.dispose();
		atlas.dispose();
		music.dispose();
		action.dispose();
		audio1.dispose();
		audio2.dispose();
		audioItem.dispose();
		audioSpeedUp.dispose();
		destroySound.dispose();
		fontGenerator.dispose();
		effect1.dispose();
		effect2.dispose();
		UIFont.dispose();
		UIFontSmall.dispose();
		UIFontTitle.dispose();
		UIFontMain.dispose();
		UIFontExtraSmall.dispose();
		skin.dispose();
	}
}
