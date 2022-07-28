package ru.madeira.onlinelibrarymanagementsystem.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.madeira.onlinelibrarymanagementsystem.entity.User;
import ru.madeira.onlinelibrarymanagementsystem.service.MailSenderService;
import ru.madeira.onlinelibrarymanagementsystem.service.UserHistoryService;
import ru.madeira.onlinelibrarymanagementsystem.service.UserService;

import java.util.List;

@Component
public class DebtRemindScheduler {

    private final MailSenderService mailSenderService;
    private final UserHistoryService userHistoryService;
    private final UserService userService;
    private static final String CRON_TIME_FOR_REMIND = "0 0 12 1 * ?";

    public DebtRemindScheduler(MailSenderService mailSenderService, UserHistoryService userHistoryService, UserService userService) {
        this.mailSenderService = mailSenderService;
        this.userHistoryService = userHistoryService;
        this.userService = userService;
    }

    @Scheduled(cron = CRON_TIME_FOR_REMIND)
    public void remindUsersAboutDebts() {
        List<Long> listUsersIdsWhoHaveDebts = userHistoryService.findAllUsersIdWhoHaveDebts();
        for (User user : userService.getAllUsersWhichIdInList(listUsersIdsWhoHaveDebts)) {
            mailSenderService.sendReminderAboutDebt(user.getName(), user.getEmail());
        }
    }


}
