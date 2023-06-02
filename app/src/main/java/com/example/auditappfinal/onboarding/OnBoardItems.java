package com.example.auditappfinal.onboarding;

public class OnBoardItems {
    String header,description;
    int image;


    public OnBoardItems(String header, String description, int image) {
        this.header = header;
        this.description = description;
        this.image = image;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
