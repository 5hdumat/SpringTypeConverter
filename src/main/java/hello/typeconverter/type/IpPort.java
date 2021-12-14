package hello.typeconverter.type;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

/**
 * "127.0.0.1:8080" 를 IpPort 객체로 바꾸거나
 * IpPort 객체를 "127.0.0.1:8080" 으로 바꿔보자
 */
@Getter
@EqualsAndHashCode
public class IpPort {
    private String ip;
    private int port;

    public IpPort(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * @EqualsAndHashCode 는 아래와 같이 필드에 대한 equals와 hashcode를 재정의해주는 롬복의 기능이다.
     * 따라서 모든 필드의 값이 같다면 a.equals(b) 의 결과가 참이 된다.
     * 테스트 코드를 작성할 때 유용하다.
     */
    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof IpPort)) return false;
//        IpPort ipPort = (IpPort) o;
//        return port == ipPort.port && Objects.equals(ip, ipPort.ip);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(ip, port);
//    }
}
