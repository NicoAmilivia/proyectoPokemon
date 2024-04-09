package es.cesur.progprojectpok;

import javafx.beans.property.SimpleStringProperty;

public class ImageData {

    private final SimpleStringProperty imagePath;
    private final SimpleStringProperty name; // Otros datos como ejemplo

    public ImageData(String imagePath, String name) {
        this.imagePath = new SimpleStringProperty(imagePath);
        this.name = new SimpleStringProperty(name);
    }

    public String getImagePath() {
        return imagePath.get();
    }

    public void setImagePath(String imagePath) {
        this.imagePath.set(imagePath);
    }

    public String getName() {
        return name.get();
    }

}
