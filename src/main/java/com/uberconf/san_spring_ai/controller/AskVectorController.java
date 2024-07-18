package com.uberconf.san_spring_ai.controller;

import com.uberconf.san_spring_ai.Answer;
import com.uberconf.san_spring_ai.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AskVectorController {

    private final ChatClient chatClient;

    public AskVectorController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }


    @PostMapping("/askVector")
    public Answer ask(@RequestBody Question question) {
        return new Answer(chatClient.prompt().user(question.question()).call().content());
    }


}