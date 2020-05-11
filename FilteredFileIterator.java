import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterates through all files that contain a certain char pattern
 */
public class FilteredFileIterator implements Iterator {

  private DeepFileIterator fileIterator;
  private String searchPattern;
  private File nextMatchingFile;

  /**
   * Constructs a file iterator that finds files that contain a certain char sequence
   * 
   * @param folder        the supplied folder that will be iterated through
   * @param searchPattern the pattern that the user wants the files to contain
   * @throws FileNotFoundException if the folder that the user provides does not exist
   */
  public FilteredFileIterator(File folder, String searchPattern) throws FileNotFoundException {
    if (!folder.exists()) {
      throw new FileNotFoundException("The given file does not exist");
    }
    fileIterator = new DeepFileIterator(folder);
    this.searchPattern = searchPattern;
    nextMatchingFile = next();
  }

  /**
   * Determines whether the file iterator has more files to iterate through
   * 
   * @return true if the iterator if the iterator still has files to iterate through, otherwise
   *         returns false
   */
  @Override
  public boolean hasNext() {
    if (next() != null) {
      return true;
    }
    return false;
  }

  /**
   * Finds and returns the next file in the folder, iterating through all files
   * 
   * @return the next file in the folder
   */
  @Override
  public File next() {
    // If nextMatchingFile is null
    if (nextMatchingFile == null) {
      while (fileIterator.hasNext()) {
        File f = fileIterator.next();
        // Sets nextMatchingFile to first file
        this.nextMatchingFile = f;
        if (f.getName().contains(searchPattern)) {
          nextMatchingFile = null;
          return f;
        } else {
          continue;
        }
      }
    } else {
      // First nextMatchingFile has been found
      while (fileIterator.hasNext()) {
        File f = nextMatchingFile;
        if (f.getName().contains(searchPattern)) {
          nextMatchingFile = null;
          return f;
        } else {
          this.nextMatchingFile = fileIterator.next();
          continue;
        }
      }
    }
    // Handles cases when there are no more files
    throw new NoSuchElementException("There are no more files to iterate through");
  }
}
