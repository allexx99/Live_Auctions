package com.example.Live.Auctions.exporter;

public class TXTFileExporter implements FileExporter {

    @Override
    public String exportData(Object object) {
        return object.toString();
    }
}
