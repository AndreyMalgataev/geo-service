package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    @ParameterizedTest
    @ValueSource(strings = {GeoServiceImpl.LOCALHOST,
            GeoServiceImpl.MOSCOW_IP,
            GeoServiceImpl.NEW_YORK_IP,
            "172.4564",
            "96.213",
            "94.24"
    })
    void testGeoLocationByIp(String ip) {
        GeoServiceImpl geo = new GeoServiceImpl();
        Location result = geo.byIp(ip);
        Location expected = null;
        if (ip.equals(GeoServiceImpl.LOCALHOST)) {
            expected = new Location(null, null, null, 0);
        } else if (ip.equals(GeoServiceImpl.MOSCOW_IP)) {
            expected = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        } else if (ip.equals(GeoServiceImpl.NEW_YORK_IP)) {
            expected = new Location("New York", Country.USA, " 10th Avenue", 32);
        } else if (ip.startsWith("172.")) {
            expected = new Location("Moscow", Country.RUSSIA, null, 0);
        } else if (ip.startsWith("96.")) {
            expected = new Location("New York", Country.USA, null, 0);
        }

        if (expected == null) {
            Assertions.assertNull(result);
        } else {
            Assertions.assertEquals(expected.getCountry(), result.getCountry());
            Assertions.assertEquals(expected.getBuiling(), result.getBuiling());
            Assertions.assertEquals(expected.getCity(), result.getCity());
            Assertions.assertEquals(expected.getStreet(), result.getStreet());
        }
    }

    @Test
    void testGeoLocationByCoordinates () {
        GeoServiceImpl geo = new GeoServiceImpl();
        double a = 12.1231, b = 12.13123;

        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () -> geo.byCoordinates(a,b));

        Assertions.assertEquals("Not implemented", result.getMessage());
    }
}
