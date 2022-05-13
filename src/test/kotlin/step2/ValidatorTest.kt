package step2

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = [""])
    fun `입력값이 null이거나 빈 공백 문자일 경우 IllegalArgumentException 발생`(input: String) {
        val validator = Validator()
        assertThatThrownBy { validator.validate(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .message().isEqualTo("입력 값이 null(널)이거나 빈 공백 문자입니다.")
    }

    @ParameterizedTest
    @ValueSource(strings = ["1 + 2 * 4 _ 6"])
    fun `허용하지 않는 operator 혹은 문자일 경우 IllegalArgumentException 발생`(input: String) {
        val validator = Validator()
        assertThatThrownBy { validator.validate(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .message().isEqualTo("허용하지 않는 operator 혹은 문자입니다. '_'")
    }
}