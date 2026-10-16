class CalculatorConundrum {
    public String calculate(int operand1, int operand2, String operation) throws IllegalOperationException {

        if (operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }
        if (operation.isBlank()) {
            throw new IllegalArgumentException("Operation cannot be empty");
        }

        int result =
                switch (operation) {
                    case "+" -> operand1 + operand2;
                    case "*" -> operand1 * operand2;
                    case "/" -> {
                        try {
                            yield operand1 / operand2;
                        } catch (ArithmeticException e) {
                            throw new IllegalOperationException("Division by zero is not allowed", e);
                        }
                    }
                    default  -> throw new IllegalOperationException ("Operation '%s' does not exist".formatted(operation));
                };
        
        return "%d %s %d = %d".formatted(operand1, operation, operand2, result);
    }
}
