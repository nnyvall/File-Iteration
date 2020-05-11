import java.io.File;
import java.io.FileNotFoundException;

/**
 * Tests ShallowFileIterator, DeepFileIterator, and FilteredFileIterator
 */
public class IterationTester {

  /**
   * 
   * Tests the shallowFileIterator to ensure proper output and exceptions
   * 
   * @param folder the folder that the user wishes to iterate through
   * @return true if the test passes, otherwise false
   */
  public static boolean testShallowFileIterator(File folder) {
    try {
      ShallowFileIterator SFE = new ShallowFileIterator(folder);
      String expectedResults =
          "assignments, exam preparation, lecture notes, " + "reading notes, todo.txt, ";
      String returnedString = SFE.next().getName() + ", " + SFE.next().getName() + ", "
          + SFE.next().getName() + ", " + SFE.next().getName() + ", " + SFE.next().getName() + ", ";
      // System.out.println(returnedString);
      if (!(returnedString.equals(expectedResults))) {
        System.out.println(
            "Error in testShallowFileIterator: returned string did not match expected results.");
        return false;
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("Encountered fnfe in SFI");
      return false;
    } catch (NullPointerException npe) {
      System.out.println("Encountered npe in SFI");
      return false;
    } catch (Exception e) {
      System.out.println("Encountered unexpected exception in SFI");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * 
   * Tests the deepFileIterator to ensure proper output and exceptions
   * 
   * @param folder the folder that the user wishes to iterate through
   * @return true if the test passes, otherwise false
   */
  public static boolean testDeepFileIterator(File folder) {
    try {
      folder = new File(folder.getPath() + File.separator + "assignments");
      DeepFileIterator DFE = new DeepFileIterator(folder);
      String expectedResults = "P01, PiggyBank.java, P02, CalendarPrinter.java, P03, "
          + "ElasticBank.java, P04, ExceptionalPiggyBank.java, P05, ExtendedVersion, "
          + "WinterCarnival.java, WinterCarnival.java, P06, AlphabetTrain.java, ";
      String returnedString = DFE.next().getName() + ", " + DFE.next().getName() + ", "
          + DFE.next().getName() + ", " + DFE.next().getName() + ", " + DFE.next().getName() + ", "
          + DFE.next().getName() + ", " + DFE.next().getName() + ", " + DFE.next().getName() + ", "
          + DFE.next().getName() + ", " + DFE.next().getName() + ", " + DFE.next().getName() + ", "
          + DFE.next().getName() + ", " + DFE.next().getName() + ", " + DFE.next().getName() + ", ";
      // System.out.println(returnedString);
      if (!(returnedString.equals(expectedResults))) {
        System.out.println(
            "Error in testDeepFileIterator: returned string did not match expected results.");
        return false;
      }
    } catch (NullPointerException npe) {
      System.out.println("Encountered npe in DFI");
      return false;
    } catch (Exception e) {
      System.out.println("Encountered unexpected exception in DFI");
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * 
   * Tests the filteredFileIterator to ensure proper output and exceptions
   * 
   * @param folder the folder that the user wishes to iterate through
   * @return true if the test passes, otherwise false
   */
  public static boolean testFilteredFileIterator(File fileSystem) {
    FilteredFileIterator FFI = null;
    try {
      FFI = new FilteredFileIterator(fileSystem, ".java");
      String expectedResults = "PiggyBank.java, CalendarPrinter.java, ElasticBank.java, "
          + "ExceptionalPiggyBank.java, WinterCarnival.java, WinterCarnival.java, "
          + "AlphabetTrain.java, codeSamples.java, ";
      String returnedString = FFI.next().getName() + ", " + FFI.next().getName() + ", "
          + FFI.next().getName() + ", " + FFI.next().getName() + ", " + FFI.next().getName() + ", "
          + FFI.next().getName() + ", " + FFI.next().getName() + ", " + FFI.next().getName() + ", ";
      // System.out.println(returnedString);
      if (!(returnedString.equals(expectedResults))) {
        System.out.println(
            "Error in testFilteredFileIterator: returned string did not match expected results.");
        return false;
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("Encountered fnfe in FFI");
      return false;
    } catch (NullPointerException npe) {
      System.out.println("Encountered npe in FFI");
      return false;
    } catch (Exception e) {
      System.out.println("Encountered unexpected exception in FFI");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * 
   * Runs all the tests
   * 
   * @return true if all tests pass, otherwise false
   */
  public static boolean runTests() {
    File f = new File("filesystem");
    if (testShallowFileIterator(f) && testDeepFileIterator(f) && testFilteredFileIterator(f)) {
      return true;
    }
    return false;
  }

  /**
   * 
   * Main method to execute all tests
   * 
   * @param args string arguments
   */
  public static void main(String[] args) {
    System.out.println(runTests());
  }
}
