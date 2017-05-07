package sample;

/**
 *
 */
public class Platform {

    private String abbreviation = null;
    private String fullName = null;
    private String maker = null;

    public Platform(String abbrivation, String fullName, String maker) {
        setAbbreviation(abbrivation);
        setFullName(fullName);
        setMaker(maker);
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMaker() {
        return maker;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}