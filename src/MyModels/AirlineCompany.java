package MyModels;

public class AirlineCompany {

    int id;
    String name;
    int countryId;
    int userId;

    public AirlineCompany() {}

    public AirlineCompany(int id, String name, int countryId, int userId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AirlineCompany{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryId=" + countryId +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
