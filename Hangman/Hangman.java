import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;
public class Hangman
{
  private Map<String, List<String>> wordPartitions; // groups words according to positions of guessed letter
  private List<String> currentList; // remaining possible words that fit the information given so far
  Set<Character> wrongGuesses; // holds all the "wrong" guesses so far
  StringBuilder guessString; // current state of the word being guessed
  String justHyphens; // for checking whether a guess was "wrong"

  // initialize fields
  // currentList should contain all (and only) words of length wordLength
  // justHyphens and guessString should consist of wordLength hyphens
  /**
   * The constructor for Hangman initializes the fields and performs the first filter from the dictionary,
   * limiting the words in the list to those words with the length that is specified by the call.
   * @param wordLength
   * @throws FileNotFoundException
   */
  public Hangman(int wordLength) throws FileNotFoundException, IllegalArgumentException
  {
    wordPartitions = new TreeMap<String, List<String>>();
    currentList = new java.util.LinkedList<String>();
    wrongGuesses = new TreeSet<Character>();
    guessString = new StringBuilder();
    justHyphens = "";
    for(int i = 0; i < wordLength; i++)
    {
      justHyphens += "-";
      guessString.append("-");
    }
    File dictionary = new File("lexicon.txt");
    Scanner scanFile = new Scanner(dictionary);
    while(scanFile.hasNextLine())
    {
      String currentWord = scanFile.nextLine();
      if(currentWord.length() == wordLength)
      {
        currentList.add(currentWord);
      }
    }
    scanFile.close();
    if(currentList.size() < 1)
    {
      throw new IllegalArgumentException("No words of that length exist in the dictionary.");
    }
  }
  
  // main loop
  /**
   * The play method controls the flow of the application and determines when the game ends.
   */
  public void play()
  {
    char choice;
    do
    {
     do
     {
      choice = getUserChoice();
     } while( ! Character.isAlphabetic(choice));
      partition(choice);
      updateCurrentList(choice);
    } while (!gameOver());
    endMessage();
  }

  // display the guessString and the missed guesses
  // and get the next guess
  /*
   * the getUserChoice method gets input from the user and validates it before returning it to the play method.
   * Currently, the user can enter any number of characters in the input dialog box, but only the first one is
   * used. If I revisit this application, I would like to make it iterate through all characters entered, until
   * the game is over or it runs out of characters. A new field variable and a Scanner would probably be used.
   */
  private char getUserChoice()
  {
   String guesses = "";
   char c;
   Iterator<Character> iter = wrongGuesses.iterator();
   while(iter.hasNext())
   {
    guesses += iter.next();
   }
    String s;
    do
    {
     s = JOptionPane.showInputDialog(
       "What letter will you guess next, human?\nHere are your pathetic guesses so far:\n"
       + guesses + "\nThis is what your bumbling attempts have uncovered:\n" + guessString.toString());
    } while(s == null || s.length() < 1);
    s = s.toLowerCase();
    c = s.charAt(0);
    return c;
  }

  // use wordPartitions to partition currentList using
  // keys returned by getPartitionKey()
  /*
   * the partition method divides the words in currentList up by the letters in them,
   * specifically by the specific letter the user chose, and how that letter is
   * distributed in the word. each unique distribution pattern is assigned it's own
   * key and is added to the corresponding list.
   */
  private void partition(char choice)
  {
    for(int i = 0; i <currentList.size(); i++)
    {
      java.util.LinkedList<String> words = new java.util.LinkedList<String>();
      String newKey = getPartitionKey(currentList.get(i), choice);
      if(wordPartitions.containsKey(newKey))
      {
        wordPartitions.get(newKey).add(currentList.get(i));
      } else 
      {
        words.add(currentList.get(i));
        wordPartitions.put(newKey, words);
      }
    }
  }
  
  // update currentList to be a copy of the longest partition
  // if choice was "wrong", add choice to wrongGuesses
  // if choice was "right", update guessString
  /*
   * The updateCurrentList method finds the longest list (or one of the
   * largest if there is a tie) in wordPartitions map and replaces
   * currentList with it. If the longest list is one of the ones that 
   * includes a letter the user chose, the guessString is updated.
   * Otherwise, the WrongGuesses collection is updated. 
   */
  private void updateCurrentList(char choice)
  {
    Set<String> keys = wordPartitions.keySet();
    List<String> restrictedList = new java.util.LinkedList<String>();
    Iterator<String> iter = keys.iterator();
    String newKey = "";
    while(iter.hasNext())
    {
      String currentKey = iter.next();
//      test code
//      for(int i = 0; i < wordPartitions.get(currentKey).size(); i++)
//      {
//        System.out.println(wordPartitions.get(currentKey).get(i));
//      }
//      end test code
      if(wordPartitions.get(currentKey).size() >= restrictedList.size())
      {
        restrictedList = wordPartitions.get(currentKey);
        newKey = currentKey;
      }
    }
    if( ! newKey.equals(justHyphens))
    {
     addLetterToGuessString(guessString, choice, newKey);
    } else wrongGuesses.add(choice);
    currentList = new ArrayList<String>(restrictedList);
    wordPartitions.clear();
  }
  
  // checks for end of game
  /*
   * this method checks if the game has ended. if there is only 
   * one word in the currentList and that word is the same as
   * the guessString, then the game is over.
   */
  private boolean gameOver()
  {
    if(currentList.size() <= 1)
    {
      if(guessString.toString().equals(currentList.get(0)))
      {
        return true;
      }
    }
    return false;
  }

  // display the guessString and the missed guesses
  // and print "Congratulations!"
  /*
   * endMessage displays the final word once it has been guessed.
   * This method also has a bad attitude.
   */
  private void endMessage()
  {
    String message = "You beat me this time! You guessed the word.\nIt was \"" + guessString + ".\"";
    javax.swing.JOptionPane.showMessageDialog(null, message);
  }
 
  // returns string with '-' in place of each
  // letter that is NOT the guessed letter
  /*
   * the getPartitionKey method returns a new string where all the letters from the
   * original string that are not equal to the letter the user chose are replaced by
   * hyphens. this helps us to create keys for our map.
   */
  private String getPartitionKey(String s, char c)
  {
    String newKey = "";
    for(int i = 0; i < s.length(); i++)
    {
      if(s.charAt(i) == c)
      {
        newKey += s.substring(i, i+1);
      } else newKey += "-";
    }
    return newKey;
    
  }

  // update guessString with the guessed letter
  /*
   * The addLetterToGuessString method updates the guessString StringBuilder object.
   * it adds the letters to the guessString in the correct position as "correct" guesses 
   * are made. That is, as alternative choices are eliminated. I did not end up using the
   * "char" parameter of the method, since my implementation doesn't require it.
   * I left it in the application anyway though, since it was included in the skeleton and
   * I chose to leave everything that was in the original skeleton as is, and only fill in
   * the gaps.
   */
  private void addLetterToGuessString(StringBuilder guessString, char letter, String key)
  {
    for(int i = 0; i < key.length(); i++)
    {
     if(key.charAt(i) != '-')
     {
      guessString.replace(i, i+1, key.substring(i,i+1));
     }
    }
  }
}