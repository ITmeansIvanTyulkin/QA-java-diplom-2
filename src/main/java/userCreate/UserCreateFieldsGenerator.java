package userCreate;

import org.apache.commons.lang3.RandomStringUtils;
import java.security.SecureRandom;
import java.util.Random;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.toLowerCase;

public class UserCreateFieldsGenerator {

    // Инициализирую массив данных emails с 10 различными вариантами существующих электронных почт.
    static final String[] emails = {
            "@yandex.ru",       // 1.
            "@gmail.com",       // 2.
            "@yahoo.com",       // 3.
            "@mail.ru",         // 4.
            "@ya.ru",           // 5.
            "@hotbox.com",      // 6.
            "@rambler.ru",      // 7.
            "@list.ru",         // 8.
            "@bk.ru",           // 9.
            "@postbox.com"      // 10.
    };

    // Создаю генератор имени для email. К имени будут подставляться рандомно варианты email из массива String[] emails.
    public static String email() {
        Random randomLength = new Random();
        int length = randomLength.nextInt(10) + 1;

        Random randomEmails = new Random();
        int index = randomEmails.nextInt(emails.length);

        boolean useLetters = true;
        boolean useNumbers = false;

        String generatedEmail = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedEmail.toLowerCase() + emails[index];
    }

    // Создаю генератор пароля и привожу его к строковому типу в соответствии с полем JSON из документации.
    public static String password() {
        int max = 12;
        int min = 6;

        int randomInt = new SecureRandom().nextInt(max - min) + min;

        boolean useLetters = false;
        boolean useNumbers = true;

        String generatedPassword = RandomStringUtils.random(randomInt, useLetters, useNumbers);

        return generatedPassword.toString();
    }

    // Создаю генератор имён пользователей.
    public static String name() {
        Random name = new Random();
        int length = name.nextInt(10) + 1;

        boolean useLetters = true;
        boolean useNumbers = false;

        String generatedName = RandomStringUtils.random(length, useLetters, useNumbers);

        return generatedName;
    }

    // Создаю пользователя по умолчанию для негативного теста попытки регистрации идентичных пользователей.
    public static UserCreate userDefault() {
        return new UserCreate("basilio_cat@yandex.ru", "12344321", "Basilio");
    }

    // Создаю уникальных пользователей из конструкторов рандомных значений с помощью методов email(), password(), name(), описанных выше.
    public static UserCreate passingGeneratorData() {
        return new UserCreate().setEmail(email()).setPassword(password()).setName(name());
    }

    // Создаю уникального пользователя из конструктора рандомных значений без одного из обязательных полей.
    public static UserCreate passingGeneratorDataWithoutOneRequiredField() {
        return new UserCreate().setEmail(email()).setPassword(password());
    }
}