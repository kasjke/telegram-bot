package com.nerzon.notificationbot.service.contract;

import com.nerzon.notificationbot.bot.Bot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface CommandListener {
    BotApiMethod<?> answerCommand(Message message, Bot bot);
}
