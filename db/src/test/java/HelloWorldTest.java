import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

    @Test
    public void helloWorld() {

        String hello = "hello world";
        Assert.assertTrue("hello world".equals(hello));
    }

}
