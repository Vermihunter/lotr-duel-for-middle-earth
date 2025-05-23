package vermesa.lotr.view.console.commands.exceptions;

public class BadCommandArgumentsException extends RuntimeException {
    public BadCommandArgumentsException(String message) {
        super(message);
    }
}
