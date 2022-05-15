import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    @Test
    void testLocalizationRu() {
        LocalizationServiceImpl localization = new LocalizationServiceImpl();
        String result = localization.locale(Country.RUSSIA);
        String expected = "Добро пожаловать";

        Assertions.assertEquals(expected, result);
    }

    @Test
    void testLocalizationEv() {
        LocalizationServiceImpl localization = new LocalizationServiceImpl();
        String result = localization.locale(Country.GERMANY);
        String expected = "Welcome";

        Assertions.assertEquals(expected, result);
    }
}
