package softwareengineering.scarlet.coursework2.models;

/**
 * Represents a name/score pairing for the leaderboard
 */
public class GameScore {
  private String playerName;
  private int score;

  /**
   * Create a new score
   *
   * @param playerName The player's name
   * @param score The player's score
   */
  public GameScore(String playerName, int score) {
    super();
    this.playerName = playerName;
    this.score = score;
  }

  /**
   * Get the name associated with the score
   *
   * @return The player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Get the score associated with the name
   *
   * @return The player's score
   */
  public int getScore() {
    return score;
  }
}
