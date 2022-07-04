package by.it.academy.controllrs.shopping_cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/getSession")
@RequiredArgsConstructor
public class ShoppingCartController {


    /**
     * displays purchased items
     * @param session get value from attribute "productRead"
     * @return set value
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String setSession (HttpSession session) {
        String value = (String) session.getAttribute("productRead");
        if (StringUtils.isEmpty(value)) {
            log.info("no session");
        }
        return value;
    }
}

