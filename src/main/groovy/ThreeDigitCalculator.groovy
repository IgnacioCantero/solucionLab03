class ThreeDigitCalculator {
    void validateInputs(int a, int b) {
        if ([a, b].any { it < 0 }) {
            throw new RuntimeException("Numbers must be non-negative")
        }
        if ([a, b].any { it > 999 }) {
            throw new RuntimeException("Numbers must not exceed 999")
        }
    }

    void validateOutput(int result) {
        if (result > 999) {
            throw new RuntimeException("Result is too big, cannot display more than 3 digits on-screen")
        }
        if (result < 0) {
            throw new RuntimeException("Result would be negative")
        }

    }

    ThreeDigitCalculator() {
        [
                "suma"      : { int a, b -> a + b },
                "resta"     : { int a, b -> a - b },
                "multiplica": { int a, b -> a * b },
                "divide"    : { int a, b ->
                    if (b==0) {
                        throw new RuntimeException("Cannot divide by zero")
                    }
                    a / b
                }
        ].each { String name, Closure<Integer> operation ->
            this.metaClass[name] = { int a, b ->
                validateInputs(a, b)
                int result = operation(a, b)
                validateOutput(result)
                return result
            }
        }
    }
}
