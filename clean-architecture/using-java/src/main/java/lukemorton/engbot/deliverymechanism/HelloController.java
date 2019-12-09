package lukemorton.engbot.deliverymechanism;

import lukemorton.engbot.Channel;
import lukemorton.engbot.ListChannels;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    @RequestMapping("/")
    public ArrayList<Channel> index() {
        return new ListChannels().exec();
    }

}