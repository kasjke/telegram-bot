package com.nerzon.notificationbot.service.contract;

import com.nerzon.notificationbot.bot.Bot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageListener {
    BotApiMethod<?> answerMessage(Message message, Bot bot);
}
