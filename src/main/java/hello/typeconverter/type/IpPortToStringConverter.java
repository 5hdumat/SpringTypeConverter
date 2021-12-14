package hello.typeconverter.type;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort, String> {

    @Override
    public String convert(IpPort source) {
        log.info("converter source = {}", source);
        return source.getIp() + ":" + source.getPort();
    }
}
