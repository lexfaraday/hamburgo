package wearable.hotelbeds.shared.price;

import java.io.Serializable;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class ConfirmDataBean implements Serializable {
    private String token;
    private boolean success;
    private PriceInfoBean price;

    public ConfirmDataBean(boolean success, String token, PriceInfoBean price) {
        this.success = success;
        this.token = token;
        this.price = price;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public PriceInfoBean getPrice() {
        return price;
    }

    public void setPrice(PriceInfoBean price) {
        this.price = price;
    }
}
