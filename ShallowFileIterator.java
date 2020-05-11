import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterates through and finds the most shallow files in a folder
 */
public class ShallowFileIterator implements Iterator {

  private File[] folderContents;
  private int nextIndex;

  /**
   * Constructs an iterator that finds the most shallow files in a folder
   * 
   * @param folder the folder that you want to iterate through
   * @throws FileNotFoundException if the supplied folder does not exist
   */
  public ShallowFileIterator(File folder) throws FileNotFoundException {
    if (!folder.exists()) {
      throw new FileNotFoundException("The given file does not exist");
    }
    folderContents = folder.listFiles();
    Arrays.sort(folderContents);
    nextIndex = 0;
  }

  /**
   * Returns a reference to a different file that is contained within the provided dictionary. Once
   * all files have been iterated through, throws No Such Element Exception.
   */
  @Override
  public File next() {
    // If there are no more files, throws exception
    if (!hasNext()) {
      throw new NoSuchElementException("There are no more files in this folder");
    } else if (hasNext()) {
      // Iterates through files
      File f = folderContents[nextIndex];
      nextIndex++;
      return f;
    }
    System.out.println("Error: default case reached in next method");
    return null;
  }


  /**
   * Checks to see if there are un-iterated files in the file system.
   * 
   * @return Returns true while there are more files that have not been iterated through in the file
   *         system, otherwise returns false
   */
  @Override
  public boolean hasNext() {
    if (nextIndex >= folderContents.length) {
      return false;
    }
    return true;
  }
}
