package gov.uk.digital.dvla.vechicleenquiry.servicelayer;

public class FileInformation {
    private String filename;
    private String mimeType;
    private long fileSize;
    private String fileExt;

    public String getFileName() {
        return this.filename;
    }

    public long getSize() {
        return fileSize;
    }

    public String getExt() {
        return fileExt;
    }

    public void setFileName(String fileName) {
        this.filename = fileName;
    }

    public void setExt(String ext) {
        this.fileExt = ext;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMimeType(String type) {
        this.mimeType = type;
    }

    public String getMimeType() {
        return mimeType;
    }
}

