import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;


public class Main {

    private static void parseCategory(String url) throws IOException {
//      Try to create "data" directory:
        File file = new File("..\\data");
        if (!file.exists()) {
            if (!file.mkdir()) {
                System.out.println("Failed to create directory!");
            }
        }
        Document html_doc = Jsoup.connect(url).get();
        Elements nums = html_doc.select("a.paginator-catalog-l-link");
        int num = Integer.parseInt(nums.last().html());
        for (int i=0; i<num; i++) {
            String pg = url + String.format("page=%d/", i+1);
            parseCategoryPage(pg);
        }
    }

    private static void parseCategoryPage(String url) throws IOException {
        Document html_doc = Jsoup.connect(url).get();
        Elements tiles = html_doc.select("div.g-i-tile-i-title");
        for (Element tile: tiles) {
            String link = tile.html();
            System.out.println(link);
            link = tile.attr("abs:href");
            System.out.println(link);
        }

    }

    private static void parseReviews(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements nums = doc.select("a.paginator-catalog-l-link");
        int size = nums.size();
        System.out.println(size);
    }

    private void parseReviewsPage(String url) {

    }

    public static void main(String[] args) throws IOException {
        parseCategory("https://rozetka.com.ua/ua/tablets/c130309/filter/");
    }
}
