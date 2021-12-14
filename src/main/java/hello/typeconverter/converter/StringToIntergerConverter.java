package hello.typeconverter.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntergerConverter implements Converter<String, Integer> {

    // Converter<S, T>
    // S: 파라미터 T: 반환값
    @Override
    public Integer convert(String source) {
        log.info("converter source={}", source);
        return Integer.valueOf(source);
    }
}
