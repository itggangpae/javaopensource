package opensource;

import org.junit.Assert;
import junit.framework.TestCase;

public class Test3 extends TestCase {
	public void test(){
		Integer result = new Source().add(100, 200);
		Integer i = 400;
		Assert.assertEquals(result, i);
	}
}

