import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: Sui, ChengBin
 * @Date: 2020/5/29
 **/
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) {
        Test test = new Test();
        new Thread(()->test.method()).start();
        Test test1 = new Test();
        new Thread(()->test1.method()).start();

    }

    public static synchronized void staticMethod()  {
        logger.info("1");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("2");
    }

    public synchronized void method() {
        logger.info("1");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("2");
    }

}
