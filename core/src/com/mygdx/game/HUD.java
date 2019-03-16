package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class HUD implements Disposable {
    public Stage stage;

    private Integer timeCount;

    private Viewport port;

    private int happy = 100;
    private int hunger;
    private int age;

    Label huLabel;
    Label haLabel;
    Label ageLabel;

    public HUD(SpriteBatch sb){

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Pokemon Solid.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();

        timeCount = 0;

        port = new FitViewport(800, 480, new OrthographicCamera());
        stage = new Stage(port, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        huLabel = new Label(String.format("Hunger: %d", hunger), new Label.LabelStyle(font, Color.WHITE));
        haLabel = new Label(String.format("Happy: %d", happy), new Label.LabelStyle(font, Color.WHITE));
        ageLabel = new Label(String.format("Age: %d", age), new Label.LabelStyle(font, Color.WHITE));

        table.add(huLabel).expandX().top().left();
        table.row();
        table.add(haLabel).expandX().top().left();
        table.row();
        table.add(ageLabel).expandX().top().left();

        stage.addActor(table);
    }

    public void incHappy() {
        this.happy++;
        haLabel.setText(String.format("Happy: %d", happy));
    }

    public void incHunger() {
        this.hunger++;
        huLabel.setText(String.format("Hunger: %d", hunger));
    }

    public void incAge() {
        age++;
        ageLabel.setText(String.format("Age: %d", age));
    }

    public void setAge(int age) {
        this.age = age;
        ageLabel.setText(String.format("Age: %d", age));
    }

    public int getAge() {
        return age;
    }

    public void decrementHunger(){
        hunger++;
        huLabel.setText(String.format("Hunger: %d", hunger));
    }

    public void decrementHappy(){
        happy--;
        haLabel.setText(String.format("Happy: %d", happy));
    }

    public void incrementAge(){
        age++;
       ageLabel.setText(String.format("Age: %d", age));
    }

    public int getHappy() {
        return happy;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
