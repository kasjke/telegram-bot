package com.nerzon.notificationbot.service.contract;

import com.nerzon.notificationbot.bot.Bot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;

public abstract class AbstractManager {

    public abstract BotApiMethod<?> mainMenu(Message message, Bot bot);
    public abstract BotApiMethod<?> mainMenu(CallbackQuery query, Bot bot);

}
