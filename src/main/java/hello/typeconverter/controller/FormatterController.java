package hello.typeconverter.controller;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

/**
 * IDE에서 Formatter 인터페이스의 구현 클래스를 찾아보면 수 많은 날짜나 시간 관련 포맷터가 제공되는 것을 확인할 수 있다.
 * 그런데 포맷터는 자바에서 제공하는 NumberFormat과 같은 기본 형식이 지정되어 있기 때문에, 객체의 각 필드마다 다른 형식으로 포맷을 지정하기는 어렵다.
 */
@Controller
public class FormatterController {

    @GetMapping("/formatter/edit")
    public String formatterForm(Model model) {
        Form form = new Form();
        form.setNumber(10000);
        form.setLocalDateTime(LocalDateTime.now());

        model.addAttribute("form", form);
        return "formatter-form";
    }

    /**
     * @ModelAttribute 사용하면 Model 생략 가능
     * @param form
     * @return
     */
    @PostMapping("/formatter/edit")
    public String formatterForm(@ModelAttribute Form form) {
        return "formatter-view";
    }

    /**
     * 스프링은 @NumberFormat, @DateTimeFormat 두 가지 기본 포맷터를 제공해준다.
     * (문자 <-> 객체, 양방향으로 모두 포맷팅 해준다. 똑똑하다.)
     * "10,000" -> 10000
     * "2021-12-15 02:09:00 -> localDateTime
     */
    @Data
    static class Form {
        @NumberFormat(pattern = "###,###")
        private Integer number;

        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;
    }
}
