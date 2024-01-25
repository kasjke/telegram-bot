package com.nerzon.notificationbot.service.handler;

import com.nerzon.notificationbot.bot.Bot;
import com.nerzon.notificationbot.service.contract.AbstractHandler;
import com.nerzon.notificationbot.service.manager.MainManager;
import com.nerzon.notificationbot.service.manager.NotificationManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler extends AbstractHandler {
    NotificationManager notificationManager;
    MainManager mainManager;
    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        var query = (CallbackQuery) object;
        String[] words = query.getData().split("_");
        switch (words[0]) {
            case "notification" -> {
                return notificationManager.answerQuery(query, words, bot);
            }
            case "main" -> {
                return mainManager.answerQuery(query, words, bot);
            }
        }
        throw new UnsupportedOperationException();
    }
}
