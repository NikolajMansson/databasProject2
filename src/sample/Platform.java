package sample;

/**
 *
 */
public class Platform {

    private String abbreviation;

    private String fullName;

    private String maker;

    public Platform(String abbrivation, String fullName, String maker) {
        this.abbreviation = abbrivation;
        this.fullName = fullName;
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