package softwareengineering.scarlet.coursework2.models;

/**
 * An enum of different types of things that can appear in a level.
 *
 * Used to perform location logic within the level (i.e. what happens if you move on to a cell
 * containing Foo?)
 */
public enum CellType {
  VOID, ROOM, CORRIDOR, HEALTH, GOLD, STRENGTH1, STRENGTH2, STRENGTH3, STAIRSUP, STAIRSDOWN, EXIT, MONSTER, MONSTERSPAWNER
}
