package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import hello.typeconverter.type.IpPortToStringConverter;
import hello.typeconverter.type.StringToIpPortConverter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * 컨버터 객체를 등록만 해두면 사용하는 입장에서는 타입 컨버터를 전혀 몰라도 된다.
 * 또한 타입 컨버터는 모두 컨버전 서비스(ConversionService) 내부에 숨어서 제공된다. 따라서 타입 변환을 원하는 사용자는
 * 컨버전 서비스 인터페이스에만 의존하면 된다. (사용자 입장에서는 어떤 구체적인 컨버터가 있는지 몰라도 된다.)
 *
 * 물론 ConversionService를 등록/사용하는 부분을 분리하고 의존관계 주입을 사용해야 한다.
 *
 * 인터페이스 분리(ISP)의 원칙이 잘 적용되었다.
 * 인터페이스를 사용(ConversionService)과 등록(ConverterRegistry)과 같이 관심사에 따라 분리하게되면
 * 컨버터를 사용하는 클라이언트는 ConversionService에만 의존하면 되므로 컨버터가 어떻게 동작하고 관리하는지 몰라도 된다.
 *
 * 이렇게 인터페이스를 작은 덩어리로 분리하게되면 등록 인터페이스가 변해도 사용 인터페이스에서 전혀 영향을 주지 않으므로
 * 인터페이스가 명확해지고, 대체 가능성이 높아지게 된다.
 */
public class ConversionServiceTest {

    @Test
    void conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();

        // 컨버터 객체 등록
        conversionService.addConverter(new StringToIntergerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        // 사용
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        String result = conversionService.convert(new IpPort("127.0.0.1", 8080), String.class);
        assertThat(result).isEqualTo("127.0.0.1:8080");

        IpPort ipPortResult = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPortResult).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
