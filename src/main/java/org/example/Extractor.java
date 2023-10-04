package org.example;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Extractor {
    Map<String, List<String>> allCities = new HashMap<>();
    final URL generalURL= new URL ("https://kovalut.ru");

    public Extractor() throws MalformedURLException {
    }

    public void getCitiesFromXML() throws IOException {
        Map<String, String> allCities = new HashMap<>();
        Document document = Jsoup.connect("https://kovalut.ru/bank-vtb/").get();
        Element table = document.select("table").first();
        Elements rows;
        if (table != null) {
            rows = table.select("tr");
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");
                Element link = cols.select("td > a").first();
                if (link != null) {
                    String url = link.attr("href");
                    allCities.put(cols.get(0).text(), url);
                }
            }
        }
        for (String city : allCities.keySet()) {
            takeCityInformation(city, allCities.get(city));
        }
    }

    public void takeCityInformation(String city, String url) throws IOException {

        Document document = Jsoup.connect(generalURL + url).get();
        Elements tables = document.select("table");
        List<String> adress = new ArrayList<>();
        for (Element table : tables) {
            Elements rows;
            if (table != null) {
                Elements subTable = table.getElementsMatchingOwnText("Адрес");
                if (!subTable.isEmpty()) {
                    for (Element subTableNode : subTable) {
                        if (subTableNode.parent() != null) {
                            rows = subTableNode.parent().select("tr").select("td");
                            for (Element row : rows) {
                                adress.add(row.text());
                            }
                            allCities.put(city, adress);
                        }
                    }
                }

            }

        }
    }

}
