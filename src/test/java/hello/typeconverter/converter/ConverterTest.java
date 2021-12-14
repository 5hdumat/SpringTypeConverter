package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import hello.typeconverter.type.IpPortToStringConverter;
import hello.typeconverter.type.StringToIpPortConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ConverterTest {
    @Test
    void stringToInteger() {
        StringToIntergerConverter converter = new StringToIntergerConverter();
        Integer result = converter.convert("10");

        assertThat(result).isEqualTo(10);
    }

    @Test
    void integerToString() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String result = converter.convert(10);

        assertThat(result).isEqualTo("10");
    }

    /**
     * 컨버팅 객체를 만들어서 하나하나 직접 사용하니 이게 뭔가 싶다.
     * 이러한 컨버팅 객체를 묶어서 사용할 수 있도록 스프링이 제공하는
     * 컨버전 서비스(ConversionService)를 이용할 때 그 진가가 발휘된다.
     * 일단 당장은 컨버팅 객체를 만들어서 이렇게 사용하는구나 정도만 알아두고 넘어가자.
     */
    @Test
    void ipPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(ipPort);

        assertThat(result).isEqualTo("127.0.0.1:8080");
    }

    @Test
    void StringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        String source = "127.0.0.1:8080";
        IpPort result = converter.convert(source);

        /**
         * lombok @EqualsAndHashCode가 선언되어있어 equals와 hashcode가 재정의되어있다.
         * 재정의 된 equals로 인해 result와 new IsPort의 참조값이 달라도 필드값만 같으면 true가 반환된다.
         */
        assertThat(result).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
