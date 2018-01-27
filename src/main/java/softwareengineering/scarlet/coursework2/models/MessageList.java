package softwareengineering.scarlet.coursework2.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A utility class for storing "messages" - bursts of text to be displayed on the screen for a short
 * amount of time.
 *
 * A singleton so it can be used widely throughout controllers etc without tight binding.
 */
public class MessageList {
  private static List<String> messages;

  /**
   * Add a message to the shared message list
   *
   * @param message The message to add
   */
  public static void addMessage(String message) {
    getMessages().add(message);
  }

  /**
   * Does the message list contain messages?
   *
   * @return True if the message list contains messages, false otherwise
   */
  public static boolean hasMessages() {
    return getMessages().size() > 0;
  }

  /**
   * Clear the message list of messages
   */
  public static void clear() {
    messages = null;
  }

  /**
   * Returns the shared message list
   *
   * If it doesn't exist, it is created.
   *
   * @return The full message list
   */
  public static List<String> getMessages() {
    if (messages == null) {
      messages = new ArrayList<String>();
    }
    return messages;
  }

  /**
   * Checks if a message exists already in the message list and remove it
   */
  public static void removeDuplicateMessages() {
    for (Iterator<String> it = MessageList.getMessages().iterator(); it.hasNext();) {
      String msg = it.next();
      if (msg == "A monster is hunting you!")
        it.remove();
    }


  }
}
