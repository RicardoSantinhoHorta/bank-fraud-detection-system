package fraudetection.exceptions;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("Sender Account with insufficient Balance to complete the transaction");
    }
}
