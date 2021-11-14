package opensource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class LogbackSample {
 	private static final Logger logger = LoggerFactory.getLogger(LogbackSample.class);
	 public static void main(String[] args) {
		 logger.info("애플리케이션을 실행하였다");
 	}
}