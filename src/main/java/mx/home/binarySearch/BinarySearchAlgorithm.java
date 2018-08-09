package mx.home.binarySearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Class to practice the binary search algorithm. */
public final class BinarySearchAlgorithm {
  /** Reference to the log of the application. */
  private final static Logger LOG = LogManager.getLogger(BinarySearchAlgorithm.class);
  
  /**
   * Binary search only works in ordered arrays.
   * @param array array of integers
   * @param value the value that we are looking for
   * @return index of the value in the array or -1 if it doesn't exists
   */
  public static int binarySearch(Integer[] array, int value) {
    if(array == null || array.length == 0) {
      throw new IllegalArgumentException();
    }
    
    int start = 0;
    int end = array.length - 1;
    int result = -1;
    
    while(start <= end) {
      int middle = (start + end) / 2;
      if(array[middle].equals(value)) {
        result = middle;
        break;
      } else if(value < array[middle]) {
        end = middle - 1;
      } else if(value > array[middle]) {
        start = middle + 1;
      }
    }
    return result;
  }
  
  /** Función para probar constantemente las funciones agregadas. */
  public static void main(String[] args) {
    Integer[] integerArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    LOG.info(binarySearch(integerArray, 11));
  }
  
}
