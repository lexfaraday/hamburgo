package wearable.hotelbeds.shared.visa;

import java.io.Serializable;

/**
 * Created by lexfaraday on 18/10/15.
 */
public class VisaBean implements Serializable {
    boolean required;
    String typeDocument;
    String description;

    public VisaBean(boolean required, String typeDocument, String description) {
        this.required = required;
        this.typeDocument = typeDocument;
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
