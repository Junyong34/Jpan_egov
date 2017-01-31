package egov.wizware.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class DLogFactory implements LoggerFactory {
  public  DLogFactory() {
  }

  public  Logger makeNewLoggerInstance(String name) {
    return new DLog(name);
  }
}
