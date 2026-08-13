package fraudetection.exception;

public class AiServiceUnavailableException extends RuntimeException {
    public AiServiceUnavailableException() {
        super("AI service is not available");
    }
}
