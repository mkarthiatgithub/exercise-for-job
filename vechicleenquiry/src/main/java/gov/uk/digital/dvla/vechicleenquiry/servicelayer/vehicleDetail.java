package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

public class vehicleDetail {
    private String regNumber;
    private String color;
    private String make;

    public vehicleDetail(String regNumber, String color, String make) {
        this.regNumber = regNumber;
        this.color = color;
        this.make = make;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }


}
