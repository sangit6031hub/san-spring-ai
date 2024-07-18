package com.uberconf.san_spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopSongsController {

    private final ChatClient chatClient;

    private final String TOP_SONGS_PROMPTS = """
            What were the top songs for the year {year}
            """;

    public TopSongsController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping(path = "/topsongs", params = "year")
    public String getTopSongs(@RequestParam("year") String year) {
        return chatClient.prompt()
                .user(userSpec -> {
                    userSpec
                            .text(TOP_SONGS_PROMPTS)
                            .param("year", year);
                })
                .call()
                .content();
    }
}
