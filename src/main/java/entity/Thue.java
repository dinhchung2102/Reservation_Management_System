package entity;

public class Thue {
    private int maThue;
    private String tenThue;
    private Double giaTriThue;
    public int getMaThue() {
        return maThue;
    }
    public void setMaThue(int maThue) {
        this.maThue = maThue;
    }
    public String getTenThue() {
        return tenThue;
    }
    public void setTenThue(String tenThue) {
        this.tenThue = tenThue;
    }
    public Double getGiaTriThue() {
        return giaTriThue;
    }
    public void setGiaTriThue(Double giaTriThue) {
        this.giaTriThue = giaTriThue;
    }
    public Thue(int maThue, String tenThue, Double giaTriThue) {
        super();
        this.maThue = maThue;
        this.tenThue = tenThue;
        this.giaTriThue = giaTriThue;
    }
    public Thue() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Thue(String tenThue, Double giaTriThue) {
        super();
        this.tenThue = tenThue;
        this.giaTriThue = giaTriThue;
    }


}
