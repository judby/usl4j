usl4j
=====

# Ultra Simple Logging for JAVA (usl4j)

The Ultra Simple Logging for JAVA (usl4j) is a KISS wrapper around standard JDK logging that gives your application a much simpler API.
No magic applied, just simplicity.

## Building usl4j
    git clone git@github.com:judby/usl4j.git
    mvn install -DskipTests

## Write Application Code
The following sample illustrates how to use the Logger and LoggerFactory.

```java
    import java.util.Date;
    import net.udby.usl4j.Logger;
    import net.udby.usl4j.LoggerFactory;

    public class SampleCode {
        private static final Logger logger = LoggerFactory.getLogger();

        /**
         * @param args
         */
        public static void main(String[] args) {
            logger.config("user={0}", System.getProperty("user.name"));
            logger.info("started={0}", new Date());
            logger.error("error", new Exception());
        }
    }
