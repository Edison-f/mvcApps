package mvc;

public abstract class Model extends Bean {
    boolean unsavedChanges;
    String fileName;

    public Model () {
        unsavedChanges = false;
        fileName = null;
    }

    public void changed() {

    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fName) {
        fileName = fName;
    }

    public void setUnsavedChanges(boolean b) {
        unsavedChanges = b;
    }
}
