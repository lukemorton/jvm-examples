package lukemorton.engbot;

import java.util.ArrayList;

public class ListChannels {
    public ArrayList<Channel> exec() {
        ArrayList<Channel> channels = new ArrayList<Channel>();
        channels.add(new Channel("eng-general"));
        return channels;
    }
}
