package com.nerzon.notificationbot.service.handler;

import com.nerzon.notificationbot.bot.Bot;
import com.nerzon.notificationbot.repository.UserRepo;
import com.nerzon.notificationbot.service.contract.AbstractHandler;
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
public class MessageHandler extends AbstractHandler {
    UserRepo userRepo;
    @Override
    public BotApiMethod<?> answer(BotApiObject object, Bot bot) {
        var message = (Message) object;
        var user = userRepo.findByChatId(message.getChatId());
        switch (user.getAction()) {
            case FREE -> {
                return null;
            }
        }
        throw new UnsupportedOperationException();
    }
}
