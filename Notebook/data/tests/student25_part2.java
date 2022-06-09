package be.unamur.game2048;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import be.unamur.game2048.controllers.*;
import be.unamur.game2048.helpers.*;
import be.unamur.game2048.models.*;

public class Test2048 {

    @Test
    public void testTileEqualsSameValue() {
        // Arrange
        Tile expected = new Tile(2);
        Tile current = new Tile(2);

        // Assert
        assertTrue(expected.equals(current));
        assertTrue(expected.getValue() == 2);
        assertTrue(current.getValue() == 2);
    }

    @Test
    public void testTileEqualsDifferentValue() {
        // Arrange
        Tile expected = new Tile(2);
        Tile current = new Tile(4);

        // Assert
        assertTrue(!expected.equals(current));
        assertTrue(expected.getValue() == 2);
        assertTrue(current.getValue() == 4);
    }

    @Test
    public void testTileEqualsNumber() {
        // Arrange
        Tile tile = new Tile(2);

        // Assert
        assertTrue(!tile.equals(2));
        assertTrue(tile.getValue() == 2);
    }

    @Test
    public void testTileEqualsSame() {
        // Arrange
        Tile tile = new Tile(2);

        // Assert
        assertTrue(tile.equals(tile));
        assertTrue(tile.getValue() == 2);
    }

    @Test
    public void testTileEqualsNull() {
        // Arrange
        Tile tile = new Tile(2);

        // Assert
        assertTrue(!tile.equals(null));
        assertTrue(tile.getValue() == 2);
    }

    @Test
    public void testTileEqualsValidMaxValue() {
        // Arrange
        Tile expected = new Tile(Integer.MAX_VALUE);
        Tile current = new Tile(Integer.MAX_VALUE);

        // Assert
        assertTrue(expected.equals(current));
        assertTrue(expected.getValue() == Integer.MAX_VALUE);
        assertTrue(current.getValue() == Integer.MAX_VALUE);
    }

    @Test
    public void testTileSetMergedTrue() {
        Tile tile = new Tile(2);

        assertTrue(!tile.isMerged());
        assertTrue(tile.getValue() == 2);
        
        tile.setMerged(true);

        assertTrue(tile.getValue() == 2);
        assertTrue(tile.isMerged());
    }

    @Test
    public void testTileSetMergedFalse() {
        Tile tile = new Tile(2);

        assertTrue(!tile.isMerged());
        assertTrue(tile.getValue() == 2);

        tile.setMerged(true);

        assertTrue(tile.isMerged());
        assertTrue(tile.getValue() == 2);
        
        tile.setMerged(false);

        assertTrue(tile.getValue() == 2);
        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTileCanMergeWithValid() {
        Tile tile = new Tile(2);
        Tile other = new Tile(2);

        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());

        assertTrue(tile.canMergeWith(other));
        assertTrue(other.canMergeWith(tile));
        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 2);
        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithOneMergedSameValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(2);

        tile.setMerged(true);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 2);

        assertTrue(tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithOneMergedDifferentValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        tile.setMerged(true);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);

        assertTrue(tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithNotMergedDifferentValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);

        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileCanMergeWithTwoMergedDifferentValue() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        tile.setMerged(true);
        other.setMerged(true);

        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));

        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);

        assertTrue(tile.isMerged());
        assertTrue(other.isMerged());
    }

    @Test
    public void testTileMergeWithSameNumber() {
        Tile tile = new Tile(2);
        Tile other = new Tile(2);

        assertTrue(tile.mergeWith(other) == 4);
        assertTrue(tile.getValue() == 4);
        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));
        assertTrue(tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileMergeWithDifferentNumber() {
        Tile tile = new Tile(2);
        Tile other = new Tile(4);

        assertTrue(tile.mergeWith(other) == -1);
        assertTrue(tile.getValue() == 2);
        assertTrue(other.getValue() == 4);
        assertTrue(!tile.canMergeWith(other));
        assertTrue(!other.canMergeWith(tile));
        assertTrue(!tile.isMerged());
        assertTrue(!other.isMerged());
    }

    @Test
    public void testTileMergeWithNull() {
        Tile tile = new Tile(2);

        assertTrue(tile.mergeWith(null) == -1);

        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTileNotPower2() {
        Tile tile = new Tile(7);

        assertTrue(tile.getValue() == 8);
        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTilePower2() {
        Tile tile = new Tile(2);

        assertTrue(tile.getValue() == 2);
        assertTrue(!tile.isMerged());
    }

    @Test
    public void testTileToString() {
        Tile tile = new Tile(2);

        assertTrue("2".equals(tile.toString()));
        
        assertTrue(tile.getValue() == 2);
        assertTrue(!tile.isMerged());
    }

    @Test
    public void testGridGetTileCoord() {
        Grid grid = new Grid();

        assertTrue(grid.getLength() == GameParams.sideLength);

        for (int i = 0; i < GameParams.sideLength; i++) {
            for (int j = 0; j < GameParams.sideLength; j++) {
                assertTrue(grid.getTile(j, i) == null);
            }
        }

        assertTrue(grid.getLength() == GameParams.sideLength);
    }

    @Test
    public void testGridGetTilePos() {
        Grid grid = new Grid();

        assertTrue(grid.getLength() == GameParams.sideLength);

        for (int i = 0; i < GameParams.sideLength; i++) {
            for (int j = 0; j < GameParams.sideLength; j++) {
                assertTrue(grid.getTile(j + i * GameParams.sideLength) == null);
            }
        }

        assertTrue(grid.getLength() == GameParams.sideLength);
    }

    @Test
    public void testGridSetTilePos() {
        Grid grid = new Grid();
        int pos = GameParams.sideLength + 1;
        Tile tile = new Tile(2);

        assertTrue(grid.getLength() == GameParams.sideLength);
        assertTrue(grid.getTile(pos) == null);

        grid.setTile(pos, tile);

        assertTrue(grid.getTile(pos).equals(tile));

        assertTrue(grid.getLength() == GameParams.sideLength);
    }

    @Test
    public void testGridGetColNull() {
        Grid grid = new Grid();
        
        assertTrue(grid.getLength() == GameParams.sideLength);

        for (int j = 0; j < GameParams.sideLength; j++) {
            Tile[] col = grid.getCol(j);
            for (int i = 0; i < GameParams.sideLength; i++) {
                assertTrue(col[i] == null);
            }
        }

        assertTrue(grid.getLength() == GameParams.sideLength);
    }

    @Test
    public void testGridGetRowNull() {
        Grid grid = new Grid();

        assertTrue(grid.getLength() == GameParams.sideLength);

        for (int i = 0; i < GameParams.sideLength; i++) {
            Tile[] row = grid.getRow(i);
            for (int j = 0; j < GameParams.sideLength; j++) {
                assertTrue(row[j] == null);
            }
        }

        assertTrue(grid.getLength() == GameParams.sideLength);
    }

    @Test
    public void testGridClearMerged() {
        Grid grid = new Grid();
        for (int i = 0; i < GameParams.sideLength; i++) {
            for (int j = 0; j < GameParams.sideLength; j++) {
                Tile tile = new Tile(i + j);
                tile.setMerged(true);
                grid.setTile(i * GameParams.sideLength + j, tile);
            }
        }

        grid.clearMerged();

        for (int i = 0; i < GameParams.sideLength; i++) {
            for (int j = 0; j < GameParams.sideLength; j++) {
                assertTrue(!grid.getTile(i * GameParams.sideLength + j).isMerged());
            }
        }

        assertTrue(grid.getLength() == GameParams.sideLength);
    }

    @Test
    public void testGameController() {
        GameController controller = new GameController();

        assertTrue(controller.getGamestate() == GameState.start);
        assertTrue(controller.getGrid() == null);
        assertTrue(controller.getHighestScore() == 0);
        assertTrue(controller.getScore() == 0);
    }

    @Test
    public void testGameControllerStartGameNotRunning() {
        GameController controller = new GameController();

        controller.startGame();

        assertTrue(controller.getGamestate() == GameState.running);
        assertTrue(controller.getGrid() != null);
        assertTrue(controller.getHighestScore() == 0);
        assertTrue(controller.getScore() == 0);
        for (int pos = 0; pos < GameParams.sideLength * GameParams.sideLength; pos++) {
            if (pos < GameParams.nbStartTileFilled) {
                assertTrue(controller.getGrid().getTile(pos) != null);
            } else {
                assertTrue(controller.getGrid().getTile(pos) == null);
            }
        }
    }
}
