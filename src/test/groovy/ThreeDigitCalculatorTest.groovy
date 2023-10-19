import org.junit.jupiter.api.Test

class ThreeDigitCalculatorTest {
    ThreeDigitCalculator sut = new ThreeDigitCalculator()

    @Test
    void "Calculator only sums natural numbers"() {
        def error = assertThrows(RuntimeException, { ->
            sut.suma(-1,2)
        })
        assert error.message == "Numbers must be non-negative"
    }

    @Test
    void "Calculator refuses numbers bigger than 3-digits"() {
        def error = assertThrows(RuntimeException, { ->
            sut.suma(2,2000)
        })
        assert error.message == "Numbers must not exceed 999"
    }

    @Test
    void "Calculator correctly sums numbers up to 1000"() {
        assert sut.suma(2, 997) == 999
        def error = assertThrows(RuntimeException, { ->
            sut.suma(3,997)
        })
        assert error.message == "Result is too big, cannot display more than 3 digits on-screen"
    }

    @Test
    void "Calculator correctly substract numbers up to 1000"() {
        assert sut.resta(20, 19) == 1
    }

    @Test
    void "Calculator correctly multiplies numbers up to 1000"() {
        assert sut.multiplica(50, 2) == 100
        def error = assertThrows(RuntimeException, { ->
            sut.multiplica(50, 100)
        })
        assert error.message == "Result is too big, cannot display more than 3 digits on-screen"
    }

    @Test
    void "Calculator correctly divides numbers up to 1000"() {
        assert sut.divide(15, 3) == 5
        assert sut.divide(0, 3) == 0
        def error = assertThrows(RuntimeException, { ->
            sut.divide(3, 0)
        })
        assert error.message == "Cannot divide by zero"
    }
}
