package wearable.hotelbeds.shared.price;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zavierazo on 12/10/2015.
 */
public class BookingsBean implements Serializable {
    private static final String FILE_NAME = "bookings.obj";
    private List<ConfirmDataBean> confirmDataBeans;

    public BookingsBean() {
        confirmDataBeans = new ArrayList<>();
    }

    public List<ConfirmDataBean> getConfirmDataBeans() {
        return confirmDataBeans;
    }

    public void setConfirmDataBeans(List<ConfirmDataBean> confirmDataBeans) {
        this.confirmDataBeans = confirmDataBeans;
    }

    public static BookingsBean load(Context context) {
        FileInputStream fis = null;
        ObjectInputStream is = null;
        try {
            fis = context.openFileInput(FILE_NAME);
            is = new ObjectInputStream(fis);
            return (BookingsBean) is.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new BookingsBean();
    }

    public void save(Context context) {
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            os = new ObjectOutputStream(fos);
            os.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
