package sample;

import java.util.Random;

public class World {

    public static Tile[][] generateWorld(int worldWidth, int worldHeight) {
        Tile[][] world = new Tile[worldWidth][worldHeight];

        // vsetko vynuluj
        for (int x = 0; x < worldWidth; x++) {
            for (int y = 0; y < worldHeight; y++) {
                world[x][y] = Tile.EMPTY;
            }
        }

        // nastav okraje na kamenne policka

        // lavy a pravy okraj
        for (int y = 0; y < worldHeight; y++) {
            world[0][y] = Tile.ROCK;
            world[worldWidth - 1][y] = Tile.ROCK;
        }
        // horna a dolna strana
        for (int x = 0; x < worldWidth; x++) {
            world[x][0] = Tile.ROCK;
            world[x][worldHeight - 1] = Tile.ROCK;
        }

        Random rnd = new Random();
        // nahodne vygeneruj <= 10 kamenov
        for (int i = 0; i < 10; i++) {
            int x = rnd.nextInt(worldWidth - 2) + 1;
            int y = rnd.nextInt(worldHeight - 2) + 1;
            world[x][y] = Tile.ROCK;
        }

        // nahodne vygeneruj <= 50 tehlovych policok
        for (int i = 0; i < 50; i++) {
            int x = rnd.nextInt(worldWidth - 2) + 1;
            int y = rnd.nextInt(worldHeight - 2) + 1;
            world[x][y] = Tile.BRICK;
        }

        // stredne musi byt prazdne
        world[worldWidth / 2][worldHeight / 2] = Tile.EMPTY;

        return world;
    }

}