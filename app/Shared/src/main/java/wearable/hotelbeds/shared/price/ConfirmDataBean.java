package wearable.hotelbeds.shared.price;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class ConfirmDataBean {
    private String token;

    public ConfirmDataBean(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
