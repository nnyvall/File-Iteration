import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterates through all files in a given folder
 */
public class DeepFileIterator implements Iterator {

  private File[] folderContents;
  private int nextIndex;
  private DeepFileIterator contentsIterator;

  /**
   * The constructor for an iterator that iterates through all files in the given folder
   * 
   * @param folder is the folder that the user wants to iterate through
   * @throws FileNotFoundException if the given folder does not exist
   */
  public DeepFileIterator(File folder) throws FileNotFoundException {
    if (!folder.exists()) {
      throw new FileNotFoundException("The given file does not exist");
    }
    contentsIterator = null;
    folderContents = folder.listFiles();
    if (folderContents != null) {
      Arrays.sort(folderContents);
    }
    nextIndex = 0;
  }

  /**
   * Checks to see if there are more elements in this directory
   * 
   * @return True if there are more elements, otherwise false.
   */
  @Override
  public boolean hasNext() {
    if (contentsIterator != null && contentsIterator.hasNext()) {
      return true;
    } else if (folderContents.length > this.nextIndex) {
      return true;
    }
    return false;
  }

  /**
   * Iterates through a directory to find the next file in the directory
   * 
   * @throws NoSuchElementException if there are no more files on the directory.
   * @return returns the next file in the directory
   */
  @Override
  public File next() throws NoSuchElementException {
    // Handles initial cases
    if (contentsIterator != null && contentsIterator.hasNext()) {
      File f = contentsIterator.next();
      if (!contentsIterator.hasNext()) {
        contentsIterator = null;
      }
      return f;
    }
    // Iterates through all files
    if (this.hasNext()) {
      contentsIterator = null;
      File f = folderContents[nextIndex];
      // Enters directories
      if (f.isDirectory()) {
        try {
          contentsIterator = new DeepFileIterator(f);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      nextIndex++;
      return f;
    }
    throw new NoSuchElementException("No more elements in folder");
  }
}

