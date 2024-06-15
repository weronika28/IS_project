package pl.pollub.ISbackend.model;

public class VehicleStatistics {

    private String voivodeship;
    private long totalVehicles;
    private String mostPopularBrand;
    private String mostPopularFuelType;
    private double averageEngineCapacity;

    public VehicleStatistics(String voivodeship, long totalVehicles, String mostPopularBrand, String mostPopularFuelType, double averageEngineCapacity) {
        this.voivodeship = voivodeship;
        this.totalVehicles = totalVehicles;
        this.mostPopularBrand = mostPopularBrand;
        this.mostPopularFuelType = mostPopularFuelType;
        this.averageEngineCapacity = averageEngineCapacity;
    }

    // Gettery i settery

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public long getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(long totalVehicles) {
        this.totalVehicles = totalVehicles;
    }

    public String getMostPopularBrand() {
        return mostPopularBrand;
    }

    public void setMostPopularBrand(String mostPopularBrand) {
        this.mostPopularBrand = mostPopularBrand;
    }

    public String getMostPopularFuelType() {
        return mostPopularFuelType;
    }

    public void setMostPopularFuelType(String mostPopularFuelType) {
        this.mostPopularFuelType = mostPopularFuelType;
    }

    public double getAverageEngineCapacity() {
        return averageEngineCapacity;
    }

    public void setAverageEngineCapacity(double averageEngineCapacity) {
        this.averageEngineCapacity = averageEngineCapacity;
    }
}
