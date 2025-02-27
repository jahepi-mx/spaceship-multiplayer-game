package com.jahepi.tank.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by javier.hernandez on 27/05/2016.
 */
public class Tile extends GameEntity {

    private TextureRegion background;
    private Vector2 origPosition;

    public Tile(float width, float height, int x, int y, TextureRegion background) {
        origPosition = new Vector2();
        this.background = background;
        size.set(width, height);
        position.set(x  * size.x, y * size.y);
        rectangle.setVertices(new float[]{0, 0, size.x, 0, size.x, size.y, 0, size.y});
        rectangle.setPosition(position.x, position.y);
        rectangle.setOrigin(size.x / 2, size.y / 2);
        this.rotationSpeed = MathUtils.random(10, 30);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(background, position.x, position.y, size.x / 2, size.y / 2, size.x, size.y, 1.0f, 1.0f, rotation, true);
    }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        renderer.setColor(Color.WHITE);
        renderer.polygon(rectangle.getTransformedVertices());
    }

    public Vector2 getOrigPosition() {
        return origPosition;
    }

    public void setOrigPosition(float x, float y) {
        this.origPosition.set(x, y);
    }

    @Override
    public void update(float deltatime) {
        this.rotation += rotationSpeed * deltatime;
    }
}
