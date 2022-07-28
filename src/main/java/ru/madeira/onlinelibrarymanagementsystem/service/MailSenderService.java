package ru.madeira.onlinelibrarymanagementsystem.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private final JavaMailSender mailSender;

    private static final String EMAIL_FROM = "library_system@example.com";
    private static final String REGISTRATION_SUBJECT_SENTENCE = "Registration in Library";
    private static final String REGISTRATION_MESSAGE_SENTENCE_FIRST = "Hello! Your password from your account in the electronic library system: ";
    private static final String REGISTRATION_MESSAGE_SENTENCE_SECOND = "\nYou can change your password in your personal account at any time!";
    private static final String REMIND_SUBJECT = "Reminder about your debts in Library!";
    private static final String REMIND_ABOUT_DEBTS_MESSAGE = ", hello! Please, give us our books!";

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    @Async
    public void sendRegistrationMail(String emailTo, String password) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EMAIL_FROM);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(REGISTRATION_SUBJECT_SENTENCE);
        mailMessage.setText(REGISTRATION_MESSAGE_SENTENCE_FIRST + password + REGISTRATION_MESSAGE_SENTENCE_SECOND);

        mailSender.send(mailMessage);
    }

    @Async
    public void sendReminderAboutDebt(String userName, String emailTo) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(EMAIL_FROM);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(REMIND_SUBJECT);
        mailMessage.setText(userName + REMIND_ABOUT_DEBTS_MESSAGE);

        mailSender.send(mailMessage);
    }


}
