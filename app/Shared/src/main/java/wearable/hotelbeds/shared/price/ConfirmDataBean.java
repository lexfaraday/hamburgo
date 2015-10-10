package wearable.hotelbeds.shared.price;

import java.io.Serializable;

/**
 * Created by Zavierazo on 08/10/2015.
 */
public class ConfirmDataBean implements Serializable {
    private String token;
    private boolean success;

    public ConfirmDataBean(boolean success, String token) {
        this.success = success;
        this.token = token;
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
}
