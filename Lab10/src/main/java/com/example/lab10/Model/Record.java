package com.example.lab10.Model;

public class Record {


    int id;
    String makerName;
    String dateRelease;
    String recordName;
    String genreName;


    public Record (  int id, String makerName, String dateRelease, String recordName, String genreName) {
        this.id = id;
        this.makerName = makerName;
        this.dateRelease = dateRelease;
        this.recordName = recordName;
        this.genreName = genreName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakerName(){
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(String dateRelease) {
        this.dateRelease = dateRelease;
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
