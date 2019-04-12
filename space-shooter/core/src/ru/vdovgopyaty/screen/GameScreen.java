package ru.vdovgopyaty.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.vdovgopyaty.base.BaseScreen;

public class GameScreen extends BaseScreen {
    private Texture background;
    private final int BACKGROUND_SIZE = 2048;
    private Texture spaceship;
    private final int SPACESHIP_SIZE = 192;
    private Vector2 touch;
    private Vector2 spaceshipPosition;
    private Vector2 spaceshipVelocity;

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        spaceshipPosition = new Vector2((Gdx.graphics.getWidth() - SPACESHIP_SIZE) / 2,
                SPACESHIP_SIZE / 2);
        spaceshipVelocity = new Vector2(3, 2);
        background = new Texture("background.jpg");
        spaceship = new Texture("spaceship.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(background, 0, 0, BACKGROUND_SIZE, BACKGROUND_SIZE);
        batch.draw(spaceship, spaceshipPosition.x, spaceshipPosition.y,
                SPACESHIP_SIZE, SPACESHIP_SIZE);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("Нажатие " + touch.x + " " + touch.y);

        Vector2 movement = spaceshipPosition.sub(touch);
        System.out.println("Длина " + movement.len());

        Vector2 direction = new Vector2(movement).nor();
        System.out.println("Направление " + direction.x + " " + direction.y);

        setSpaceshipPosition(touch);

        return false;
    }

    private void setSpaceshipPosition(Vector2 in) {
        spaceshipPosition.set(new Vector2(in.x - SPACESHIP_SIZE / 2,in.y - SPACESHIP_SIZE / 2));
    }
}
