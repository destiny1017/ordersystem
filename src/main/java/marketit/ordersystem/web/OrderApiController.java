package marketit.ordersystem.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}
