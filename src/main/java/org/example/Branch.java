package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {

    @JsonProperty("salePointName")
    String salePointName;
    @JsonProperty("address")
    String adress;
    @JsonProperty("openHours")
    private List<OpenHours> openHours;
    @JsonProperty("rko")
    String rko;
    @JsonProperty("openHoursIndividual")
    private List<OpenHours> openHoursIndividual;
    @JsonProperty("officeType")
    String officeType;
    @JsonProperty("salePointFormat")
    String salePointFormat;
    @JsonProperty("suoAvailability")
    String suoAvailabilty;
    @JsonProperty("hasRamp")
    String hasRamp;
    @JsonProperty("latitude")
    Double latitude;
    @JsonProperty("longitude")
    Double longitude;
    @JsonProperty("metroStation")
    String metroStation;
    @JsonProperty("distance")
    int distance;
    @JsonProperty("kep")
    boolean kep;

    public List<OpenHours> getOpenHours() {
        return openHours;
    }

    public void setOpenHours(List<OpenHours> openHours) {
        this.openHours = openHours;
    }

    static class OpenHours {
        String days;
        String hours;

        public String getDays() {
            return days;
        }

        public void setDays(String days) {
            this.days = days;
        }

        public String getHours() {
            return hours;
        }

        public void setHours(String hours) {
            this.hours = hours;
        }
    }

    private static final int AVERAGE_RECEPTION_TIME = 20;
    int numberOfEmployees;
    String workloadBranch;

    public String getAdress() {
        return adress;
    }

    ElectronicQueue electronicQueue;

    public int timeInQueue() {
        int speedService = (electronicQueue.getPersonInQueue() / numberOfEmployees);
        return speedService * AVERAGE_RECEPTION_TIME;
    }

    public void setWorkloadBranch() {
        int timeInQueue = timeInQueue();
        if (timeInQueue > 60) {
            workloadBranch = "Very busy";
        } else if (timeInQueue < 60 && timeInQueue > 30) {
            workloadBranch = "Busy";
        } else workloadBranch = "Low workload";
    }

    public String toString() {
        return "Branch{" +
                "salePointName='" + salePointName + '\'' +
                ", adress='" + adress + '\'' +
                ", openHours=" + openHours +
                ", rko='" + rko + '\'' +
                ", openHoursIndividual=" + openHoursIndividual +
                ", officeType='" + officeType + '\'' +
                ", salePointFormat='" + salePointFormat + '\'' +
                ", suoAvailabilty='" + suoAvailabilty + '\'' +
                ", hasRamp='" + hasRamp + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", metroStation='" + metroStation + '\'' +
                ", distance=" + distance +
                ", kep=" + kep +
                ", numberOfEmployees=" + numberOfEmployees +
                ", workloadBranch='" + workloadBranch + '\'' +
                ", electronicQueue=" + electronicQueue +
                '}';
    }
}
