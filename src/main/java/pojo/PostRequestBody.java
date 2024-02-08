package pojo;

import java.util.List;

public class PostRequestBody {
    private String name, job;
    private List<String> languages;
    private List<CityBody> cityPayload;

    public List<CityBody> getCityPayload() {
        return cityPayload;
    }

    public void setCityPayload(List<CityBody> cityPayload) {
        this.cityPayload = cityPayload;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


}
