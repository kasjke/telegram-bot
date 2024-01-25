package com.nerzon.notificationbot.service;

import com.nerzon.notificationbot.bot.Bot;
import com.nerzon.notificationbot.entity.Action;
import com.nerzon.notificationbot.entity.User;
import com.nerzon.notificationbot.repository.UserRepo;
import com.nerzon.notificationbot.service.handler.CallbackQueryHandler;
import com.nerzon.notificationbot.service.handler.CommandHandler;
import com.nerzon.notificationbot.service.handler.MessageHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UpdateDispatcher {
    MessageHandler messageHandler;
    CommandHandler commandHandler;
    CallbackQueryHandler queryHandler;
    UserRepo userRepo;
    public BotApiMethod<?> distribute(Update update, Bot bot) {
        if (update.hasCallbackQuery()) {
            checkUser(update.getCallbackQuery().getMessage().getChatId());
            return queryHandler.answer(update.getCallbackQuery(), bot);
        }
        if (update.hasMessage()) {
            var message = update.getMessage();
            checkUser(message.getChatId());
            if (message.hasText()) {
                if (message.getText().charAt(0) == '/') {
                    return commandHandler.answer(message, bot);
                }
                return messageHandler.answer(message, bot);
            }
        }
        log.warn("Unsupported update type: " + update);
        return null;
    }

    private void checkUser(Long chatId) {
        if (userRepo.existsByChatId(chatId)) {
            return;
        }
        userRepo.save(
                User.builder()
                        .action(Action.FREE)
                        .registeredAt(LocalDateTime.now())
                        .chatId(chatId)
                        .firstName("Yo")
                        .build()
        );
    }

}
