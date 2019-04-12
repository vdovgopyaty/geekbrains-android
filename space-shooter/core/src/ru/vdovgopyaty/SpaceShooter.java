package ru.vdovgopyaty;

import com.badlogic.gdx.Game;

import ru.vdovgopyaty.screen.GameScreen;

public class SpaceShooter extends Game {
	
	@Override
	public void create () {
	    setScreen(new GameScreen());
	}
}
