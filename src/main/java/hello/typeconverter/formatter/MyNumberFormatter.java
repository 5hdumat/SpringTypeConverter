package hello.typeconverter.formatter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Converter 는 입력과 출력 타입에 제한이 없는, 범용 타입 변환 기능을 제공한다.
 * 이번에는 일반적인 웹 애플리케이션 환경을 생각해보자.
 * 불린 타입을 숫자로 바꾸는 것 같은 범용 기능 보다는 개발자 입장에서는
 * 문자를 다른 타입으로 변환하거나, 다른 타입을 문자로 변환하는 상황이 대부분이다.
 * 앞서 살펴본 예제들을 떠올려 보면 문자를 다른 객체로 변환하거나 객체를 문자로 변환하는 일이 대부분이다.
 *
 * 포맷터(Formatter)는 객체를 문자로 변경하고, 문자를 객체로 변경하는 두 가지 기능을 모두 수행한다.
 *
 * 1,000을 입력받아 1000으로 만들어주는 기능을 수행해보자.
 *
 *
 */
@Slf4j
public class MyNumberFormatter implements Formatter<Number> {

    // T parse(String text, Locale locale) : 문자를 객체로 변경한다.
    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        log.info("text = {}, locale = {}", text, locale);

        // 1,000 -> 1000
        /**
         * ""1,000" 처럼 숫자 중간의 쉼표를 적용하려면 자바가 기본으로 제공하는 NumberFormat 객체를 사용하면 된다.
         * 이 객체는 Locale 정보를 활용해서 나라별로 다른 숫자 포맷을 만들어준다.
         */
        return NumberFormat.getInstance(locale).parse(text);
    }

    // String print(T object, Locale locale) : 객체를 문자로 변경한다.
    @Override
    public String print(Number object, Locale locale) {
        log.info("object = {}, locale = {}", object, locale);

        return NumberFormat.getInstance(locale).format(object);
    }
}
