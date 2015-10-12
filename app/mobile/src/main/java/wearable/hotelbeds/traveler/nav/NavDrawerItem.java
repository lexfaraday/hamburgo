package wearable.hotelbeds.traveler.nav;

/**
 * Created by Zavierazo on 12/10/2015.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private int icon;
    private boolean checked;


    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, int icon, boolean checked) {
        this.showNotify = showNotify;
        this.title = title;
        this.icon = icon;
        this.checked = checked;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}