package fil.iagl.iir.outils;

/**
 * @author RMS
 *
 * Une exception specifique à l'application
 * Etend @see java.lang.RuntimeException
 */
public class FeedMeException extends RuntimeException {

  private static final long serialVersionUID = -1374905127904717607L;

  public FeedMeException() {
    super();
  }

  public FeedMeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public FeedMeException(String message, Throwable cause) {
    super(message, cause);
  }

  public FeedMeException(String message) {
    super(message);
  }

  public FeedMeException(Throwable cause) {
    super(cause);
  }

}
