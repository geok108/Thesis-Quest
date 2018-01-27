package softwareengineering.scarlet.coursework2.views.game;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.util.Arrays;
import java.util.List;
import softwareengineering.scarlet.coursework2.models.CellType;
import softwareengineering.scarlet.coursework2.models.Dungeon;
import softwareengineering.scarlet.coursework2.models.Level;

/**
 * Render a level and the static entities contained within to the screen
 */
public class LevelRenderer {
  private static List<CellType> needsFloor = Arrays.asList(CellType.EXIT, CellType.GOLD,
      CellType.HEALTH, CellType.STAIRSDOWN, CellType.STAIRSUP, CellType.STRENGTH1,
      CellType.STRENGTH2, CellType.STRENGTH3, CellType.MONSTER);

  private Dungeon dungeon;

  public LevelRenderer(Dungeon dungeon) {
    init(dungeon);
  }

  /**
   * Prepare the renderer with a link to the current dungeon that needs rendering
   */
  public void init(Dungeon dungeon) {
    this.dungeon = dungeon;
  }

  /**
   * Render a level and entities within to the screen in a grid
   *
   * @param level The level to render
   * @param g2d The screen to render to
   * @param observer Link to the panel's observer
   */
  private void renderGrid(Level level, Graphics2D g2d, ImageObserver observer) {
    CellType[][] grid = level.getGrid();

    for (int x = 0; x < level.getWidth(); x++) {
      for (int y = 0; y < level.getHeight(); y++) {
        // If void, don't draw anything
        if (grid[x][y] == CellType.VOID) {
          continue;
        }

        int screenX = GameView.getXForGridX(x);
        int screenY = GameView.getYForGridY(y);

        // Some entities require floor to be drawn beneath them, so draw that first
        if (needsFloor.contains(grid[x][y])) {
          LevelItemsFactory.init(CellType.ROOM).draw(screenX, screenY, g2d, observer);
        }

        // Finally load the entity image from the cache and draw it
        LevelItemsFactory.init(grid[x][y]).draw(screenX, screenY, g2d, observer);
      }
    }
  }

  /**
   * Render the dungeon to the screen
   */
  public void render(Graphics2D g2d, ImageObserver observer) {
    Level level = dungeon.getCurrentLevel();

    renderGrid(level, g2d, observer);
  }
}
