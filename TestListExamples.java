import static org.junit.Assert.*;
import org.junit.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class IsMoon implements StringChecker {
  public boolean checkString(String s) {
    return s.equalsIgnoreCase("moon");
  }
}
class TrueChecker implements StringChecker{
    public boolean checkString(String s){
        return true;
    }
}

public class TestListExamples {
  @Test(timeout = 500)
  public void testMergeRightEnd() {
    List<String> left = Arrays.asList("a", "b", "c");
    List<String> right = Arrays.asList("a", "d");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }

  @Test(timeout = 500)
  public void testMergeMiddle() {
    List<String> left = Arrays.asList("a", "b", "d");
    List<String> right = Arrays.asList("a", "c");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("a", "a", "b", "c", "d");
    assertEquals(expected, merged);
  }
  @Test(timeout = 500)
  public void testMergeLeft() {
    List<String> left = Arrays.asList("a", "b", "d");
    List<String> right = Arrays.asList("A", "A");
    List<String> merged = ListExamples.merge(left, right);
    List<String> expected = Arrays.asList("A", "A", "a", "b", "d");
    assertEquals(expected, merged);
  }
  @Test
  public void testFilter(){
      String[] arr = {"hi", "hello"};
      List<String> test = new ArrayList(Arrays.asList(arr));
      assertArrayEquals(arr, ListExamples.filter(test, new TrueChecker()).toArray());
  }
  @Test
  public void testFilter2(){
      String[] arr = {"hi", "hello"};
      String[] actualarr = new String[0];
      List<String> test = new ArrayList(Arrays.asList(arr));
      assertArrayEquals(actualarr, ListExamples.filter(test, new IsMoon()).toArray());
  }
}
