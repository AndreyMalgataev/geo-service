package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    @Test
    void testLocalization() {
        LocalizationServiceImpl localization = new LocalizationServiceImpl();
        String resultRu = localization.locale(Country.RUSSIA);
        String resultEn = localization.locale(Country.GERMANY);
        String expectedRu = "Добро пожаловать";
        String expectedEn = "Welcome";

        Assertions.assertEquals(expectedRu, resultRu);
        Assertions.assertEquals(expectedEn, resultEn);

    }
}
