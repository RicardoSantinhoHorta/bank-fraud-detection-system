package fraudetection.fraud.rule;

import java.util.Set;

public final class taxHavenCountries {

    private static final Set<String> TAX_HAVENS = Set.of(
            "Monaco",
            "Panama"
    );

    public static boolean isTaxHavenCountry(String country) {
        return TAX_HAVENS.contains(country);
    }
}
