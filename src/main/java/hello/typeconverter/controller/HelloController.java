package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        // 기본적으로 HTTP 요청 파라미터는 문자타입으로 조회된다.
        String data = request.getParameter("data");

        // 그래서 자바에서 다른 타입으로 변환해서 사용하고자 한다면 숫자 타입으로 변경해서 사용해야 한다.
        Integer intValue = Integer.valueOf(data);

        System.out.println("intValue = " + intValue);

        return "OK";
    }

    // 스프링은 아래와 같이 @RequestParam 애노테이션과 함께 파라미터 타입을 지정해서 받을 수 있다.
    // "이것은 스프링이 중간에서 타입을 변환해주었기 때문이다."
    // 참고로 이 기능은 @ModelAttribute, @PathVariable에서도 확인할 수 있다.
    /**
     * 컨버터를 등록한 후 아래 메서드를 재실행 해보면 정상동작한다.
     * 우리가 만든 컨버터도 잘 동작하는 것을 확인할 수 있다.
     * h.t.converter.StringToIntergerConverter  : converter source=121212
     *
     * 하지만 생각해보면 컨버터를 등록하기 전에도 아래 메서드는 잘 동작했었다.
     * 그것은 스프링이 기본적으로 수 많은 컨버터를 제공해주기 때문이다. (기본적인 숫자 <-> 문자 변환은 굳이 컨버터를 등록하지 않아도 된다.)
     * 우리가 추가한 컨버터가 우선 동작하는 것은 스프링은 항상 디테일한 것을 먼저 수행하기 하기 때문이다.
     */
    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        System.out.println(data);
        return "OK";
    }

    /**
     * 이번에는 우리가 직접 만든 IpPort 객체 컨버터를 사용해보자. IpPort 객체가 잘 들어오는 것을 확인할 수 있다.
     *
     * 원리는 다음과 같다.
     *
     * @RequestParam은 @RequestParam을 처리하는 ArgumentResolver인 RequestParamMethodArgumentResolver에서
     * ConversionService를 사용해서 타입을 변환해준다.
     * (부모 클래스와 다양한 외부 클래스를 호출하는 복잡한 내부 로직을 거치기 때문에 대략 이렇게 처리 되는구나 정도로만 이해해도 충분하다.)
     *
     * 참고로 @ModelAttribute, PathVariable 모두 동일하게 사용가능하다.
     *
     * 만약 더 깊이있게 확인하고 싶으면 IpPortConverter 에 디버그 브레이크 포인트를 걸어서 확인해보자.
     */
    @GetMapping("/ip-port")
    public String helloV3(@RequestParam IpPort ipPort) {
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());

        return "OK";
    }
}
