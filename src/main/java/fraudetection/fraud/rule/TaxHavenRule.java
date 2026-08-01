package fraudetection.fraud.rule;

public class TaxHavenRule{

    public boolean isTaxHaven(String senderCountry) {
        return taxHavenCountries.isTaxHavenCountry(senderCountry);
    }
}