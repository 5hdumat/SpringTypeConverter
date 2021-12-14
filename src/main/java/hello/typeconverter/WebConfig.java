package hello.typeconverter;

import hello.typeconverter.converter.IntegerToStringConverter;
import hello.typeconverter.converter.StringToIntergerConverter;
import hello.typeconverter.formatter.MyNumberFormatter;
import hello.typeconverter.type.IpPortToStringConverter;
import hello.typeconverter.type.StringToIpPortConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 스프링에 무언가를 등록하려면 우리는 항상 WebMvcConfigurer를 구현해야함을 잊지말자.
 *
 *
 * !!! 메시지 컨버터에는 컨버전 서비스 적용 안된다. (@ResponeBody, @RresponseEntity 등) !!!
 * !!!!!! 컨버전 서비스는 @RequestParam @ModelAttribute @PathVariable, 뷰 템플릿 등에서만 사용 할 수 있다. !!!!!!
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * WebMvcConfigurer가 제공하는 addFormatters()를 사용해서
     * 추가하고 싶은 컨버터를 등록하면 된다.
     * 이렇게 하면 스프링은 내부에서 사용하는 ConversionService에 컨버터를 추가해준다.
     *
     * 스프링이 일관성있게 다 적용해준다.
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 우리가 만든 포맷터도 문자를 숫자로 바꾸고 숫자를 문자로 바꾸기 때문에 포맷터를 테스트 하려면 주석처리
        //        registry.addConverter(new StringToIntergerConverter());
        //        registry.addConverter(new IntegerToStringConverter());
        registry.addConverter(new StringToIpPortConverter());
        registry.addConverter(new IpPortToStringConverter());

        // 포맷터 추가
        registry.addFormatter(new MyNumberFormatter());
    }
}
