package business;

//import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

public class Logger {
//    private static Logger instance;
java.util.logging.Logger logger = java.util.logging.Logger.getLogger("chess");

//    private Logger(){}

//    public static Logger getLogger(){
//        if (instance == null){
//            instance = new Logger();
//        }
//
//        return instance;
//    }

    public void info(String message, Object... objects){
//        String message1 = MessageFormatter.arrayFormat(message, objects).getMessage();
//        logger.info(message1);
    }
}
