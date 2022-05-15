import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;


import java.util.HashMap;
import java.util.Map;

import static ru.netology.geo.GeoServiceImpl.LOCALHOST;

public class GeoServiceImplTest {
    @ParameterizedTest(name = "#{index} - Test with Argument={arguments}")
    @CsvSource(value = {
            "127.0.0.1,,,,0"
            , "172.0.32.11,Moscow,RUSSIA,Lenina,15"
            , "96.44.183.149,New York,USA, 10th Avenue,32"
            , "172.4564,Moscow,RUSSIA,,0"
            , "96.213,New York,USA,,0"
            , "94.24,,,,"
    }, ignoreLeadingAndTrailingWhitespace = false)
    void testGeoLocationByIp(String ip, String city, Country country, String street, Integer building) {
        GeoServiceImpl geo = new GeoServiceImpl();
        Location result = geo.byIp(ip);
        Location expected = building == null ? null : new Location(city, country, street, building);

        if (expected == null) {
            Assertions.assertNull(result);
        } else {
            Assertions.assertEquals(country, result.getCountry());
            Assertions.assertEquals(building, result.getBuiling());
            Assertions.assertEquals(city, result.getCity());
            Assertions.assertEquals(street, result.getStreet());
        }
    }

    @Test
    void testGeoLocationByCoordinates() {
        GeoServiceImpl geo = new GeoServiceImpl();
        double a = 12.1231, b = 12.13123;

        RuntimeException result = Assertions.assertThrows(RuntimeException.class, () -> geo.byCoordinates(a, b));

        Assertions.assertEquals("Not implemented", result.getMessage());
    }
}
