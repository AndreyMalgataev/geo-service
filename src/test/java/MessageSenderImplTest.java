import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MessageSenderImplTest {

    @ParameterizedTest
    @ValueSource(strings = {"172.156", "96.645.", "172.158"})
    void testSender(String ip) {
        LocalizationService localization = Mockito.spy(LocalizationServiceImpl.class);

        GeoService geoService = Mockito.spy(GeoServiceImpl.class);

        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        MessageSenderImpl sender = new MessageSenderImpl(geoService, localization);
        String result = sender.send(headers);
        String expected = ip.startsWith("172.") ? "Добро пожаловать" : "Welcome";
        Assertions.assertEquals(expected, result);
    }
}
