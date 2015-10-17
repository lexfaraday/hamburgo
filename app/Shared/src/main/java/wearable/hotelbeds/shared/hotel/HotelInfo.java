package wearable.hotelbeds.shared.hotel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lexfaraday on 14/10/15.
 */
public class HotelInfo implements Serializable {
    private String name;
    private BigDecimal price;
    private int stars;
    private String reg;
    private String codHab;
    private List<String> images;

    public HotelInfo(String name, BigDecimal price, List<String> images, int stars, String reg, String codHab) {
        this.name = name;
        this.price = price;
        this.images = images;
        this.stars = stars;
        this.reg = reg;
        this.codHab = codHab;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getCodHab() {
        return codHab;
    }

    public void setCodHab(String codHab) {
        this.codHab = codHab;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
