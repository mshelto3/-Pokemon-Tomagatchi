package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainScreen implements Screen {

    private RaiseEmUp game;
    OrthographicCamera camera;
    FitViewport viewport;
    private Stage stage;
    Texture backGround2;
    TextureRegion backGround;
    Animation<TextureRegion> animation;
    private Texture pokeImage;
    Rectangle pokeball;
    private Music pokeMusic;
    private HUD hud;
    float elapsed;
    float helper;
    int poke;

    public MainScreen(RaiseEmUp game, int poke) {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new FitViewport(800, 480, camera);
        stage = new Stage(viewport, game.batch);
        this.poke = poke;
        backGround2 = new Texture(Gdx.files.internal("bg.png"));
        backGround = new TextureRegion(backGround2, 0, 0, 1920, 1080);
        pokeMusic = Gdx.audio.newMusic(Gdx.files.internal("Canalave City.mp3"));
        pokeMusic.setLooping(true);
        pokeMusic.play();
        hud = new HUD(game.batch);
        pokeball = new Rectangle();
        pokeball.x = 750;
        pokeball.y = 450;
        pokeball.width =  200;
        pokeball.height = 200;
        pokeImage = new Texture(Gdx.files.internal("pokeball.png"));
        elapsed = 0;
        helper = 0;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3();
            tmp.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(tmp);
            if(pokeball.contains(tmp.x, tmp.y)){
                game.setScreen(new Minigame(game, hud, poke));
                pokeMusic.stop();
                dispose();
            }
        }
        updatePoke();
        helper+= Gdx.graphics.getDeltaTime();
        System.out.println(helper);
        if(helper > 0) {
            hud.incAge();
            hud.incHunger();
            hud.decrementHappy();
            helper = 0;
        }
        if(hud.getAge() == 100){
            evolvePokemon();
            hud.setAge(0);
        }
        if(hud.getHappy() < 0) game.setScreen(new GameOver(game));
        elapsed += Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(backGround, 0, 0, 800, 480);
        game.batch.draw(animation.getKeyFrame(elapsed), 400.0f, 50.0f);
        game.batch.draw(pokeImage, pokeball.x, pokeball.y);
        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    }

    public void updatePoke(){
        if(poke == 1) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("squirtle.gif").read());
        if(poke == 2) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("charmander.gif").read());
        if(poke == 3) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("bulbasaur.gif").read());
        if(poke == 4) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("wartortle.gif").read());
        if(poke == 5) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("charmeleon.gif").read());
        if(poke == 6) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("ivysaur.gif").read());
        if(poke == 7) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("blastoise.gif").read());
        if(poke == 8) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("charizard.gif").read());
        if(poke == 9) animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("venusaur.gif").read());
    }
    public void evolvePokemon(){
        if(poke == 1) poke = 4;
        else if(poke == 2) poke = 5;
        else if(poke == 3) poke = 6;
        else if(poke == 4) poke = 7;
        else if(poke == 5) poke = 8;
        else if(poke == 6) poke = 9;
    }
}
