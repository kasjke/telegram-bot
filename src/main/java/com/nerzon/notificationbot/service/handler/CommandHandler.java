package com.nerzon.notificationbot.service.handler;

import com.nerzon.notificationbot.bot.Bot;
import com.nerzon.notificationbot.service.contract.AbstractHandler;
import com.nerzon.notificationbot.service.manager.MainManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommandHandler extends AbstractHandler {
    MainManager mainManager;

    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        var message = (Message) object;
        if ("/start".equals(message.getText())) {
            return mainManager.answerCommand(message, bot);
        }
        throw new UnsupportedOperationException();
    }
}