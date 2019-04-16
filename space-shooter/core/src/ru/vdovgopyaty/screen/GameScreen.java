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
    private final int SPEED = 500;
    private Vector2 position;
    private Vector2 newPosition;
    private Vector2 direction;
    private Vector2 velocity;

    @Override
    public void show() {
        super.show();
        position = new Vector2();
        setPosition(new Vector2(Gdx.graphics.getWidth() / 2, SPACESHIP_SIZE / 2));
        newPosition = getPosition().cpy();
        velocity = new Vector2(3, 2);
        direction = new Vector2();
        velocity = new Vector2(0, 0);
        background = new Texture("background.jpg");
        spaceship = new Texture("spaceship.png");
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(background, 0, 0, BACKGROUND_SIZE, BACKGROUND_SIZE);

        if (newPosition.cpy().sub(getPosition()).len() > velocity.len()) {
            position.add(velocity);
        }

        batch.draw(spaceship, position.x, position.y, SPACESHIP_SIZE, SPACESHIP_SIZE);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        background.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        newPosition.set(screenX, Gdx.graphics.getHeight() - screenY);
        Vector2 displacement = newPosition.cpy().sub(getPosition());
        direction = displacement.cpy().nor();
        velocity = direction.cpy().scl(SPEED * Gdx.graphics.getDeltaTime());
        return false;
    }

    private Vector2 getPosition() {
        return new Vector2(position.x + SPACESHIP_SIZE / 2, position.y + SPACESHIP_SIZE / 2);
    }

    private void setPosition(Vector2 newPosition) {
        position.set(newPosition.x - SPACESHIP_SIZE / 2, newPosition.y - SPACESHIP_SIZE / 2);
    }
}
