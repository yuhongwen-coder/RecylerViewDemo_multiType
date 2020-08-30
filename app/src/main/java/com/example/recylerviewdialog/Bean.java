package com.example.recylerviewdialog;

public class Bean {
    private String contentItem;

    public String getContentItem() {
        return contentItem;
    }

    public void setContentItem(String contentItem) {
        this.contentItem = contentItem;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "contentItem='" + contentItem + '\'' +
                '}';
    }
}
