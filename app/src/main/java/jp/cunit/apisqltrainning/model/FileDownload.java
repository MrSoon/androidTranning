package jp.cunit.apisqltrainning.model;

public class FileDownload {
    private String fname;
    private String fsize;

    public FileDownload(String fname, String fsize) {
        this.fname = fname;
        this.fsize = fsize;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFsize() {
        return fsize;
    }

    public void setFsize(String fsize) {
        this.fsize = fsize;
    }
}
