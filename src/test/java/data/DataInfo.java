package data;

import org.testng.annotations.DataProvider;

/**
 * Created by hp on 30.07.2017.
 */
public class DataInfo {

    @DataProvider
    public static Object[][] questionsNegative() {
        return new Object[][]{
                {"","glaza-kot-usy-koska-klyki-usi-azyk.jpg"},
                {"#$#$%#^@$","glaza-kot-usy-koska-klyki-usi-azyk.jpg"},
                {"/*SELECT * FROM a*/","glaza-kot-usy-koska-klyki-usi-azyk.jpg"},
                {"TEST","Xvid-1.3.5-20171208.exe"},
                {"TEST",""},
        };
    }
    @DataProvider
    public static Object[][] emailNegative() {
        return new Object[][]{
                {""},
                {"mari12345678@yandex.ru"},
                {"%$$@.test"}
        };
    }
    @DataProvider
    public static Object[][] fileNegative() {
        return new Object[][]{
                {"Sky.jpg"},
                {"archive.7z"},
                {"Xvid-1.3.5-20171208.exe"}
        };
    }
    @DataProvider
    public static Object[][] textNegative() {
        return new Object[][]{
                {""},
                {"%#$%@%@#@#^^$"}
        };
    }
    @DataProvider
    public static Object[][] city() {
        return new Object[][]{
                {"Саратов","6"},
                {"Балаково","2"},
                {"Вольск","4"}
        };
    }
    @DataProvider
    public static Object[][] emailNegativeAndCurrentPassword() {
        return new Object[][]{
                {"","password"},
                {"lukashovama@sgu.ru","password"},
                {"mari-lukashowa@yandex.ru","password1"},
                {"mari-lukashowa@yandex.ru","password"}
        };
    }
    @DataProvider
    public static Object[][] birthdays() {
        return new Object[][]{
                {"02.12.98"},
                {"05-10-1990"},
                {"12/03/1999"}
        };
    }
    @DataProvider
    public static Object[][] birthdaysNegative() {
        return new Object[][]{
                {"test"},
                {"05/45/1987"},
                {"09.12.2030"},
                {"^$%#$@"}
        };
    }
    @DataProvider
    public static Object[][] phonesNegative() {
        return new Object[][]{
                {"test"},
                {"234"},
                {"##$#%#"}
        };
    }
    @DataProvider
    public static Object[][] phones() {
        return new Object[][]{
                {"+79031453765"},
                {"+7(903)145-37-65"},
                {"8 903 145 37 65"}
        };
    }
}
