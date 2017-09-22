import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static class Review {
        int rating;
        String text;
        public Review (int rating, String text) {
            this.rating = rating;
            this.text = text;
        }
    }

    private static void parseCategory(String url) throws IOException {
//      Try to create "data" directory:
        File file = new File("data");
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
            String link = tile.select("a").first().attr("href") + "comments/";
            parseReviews(link);
        }

    }

    private static void parseReviews(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements nums = doc.select("a.paginator-catalog-l-link");
        int size = nums.size();
        if (size != 0) {
            size = Integer.parseInt(nums.last().html());
        }
        List<Review> reviewsArr = new ArrayList<Review>();
        for (int i=0; i<size; i++) {
            String pg = url + String.format("page=%d/", i);
            reviewsArr.addAll(parseReviewsPage(pg));
        }

        for (Review review : reviewsArr) {
            System.out.println(String.format("%d %s", review.rating, review.text));
        }

        String filename = "data/" + url.split("/")[4] + ".csv";
        PrintWriter writer = new PrintWriter(filename, "UTF-8");
        for (Review review : reviewsArr) {
            writer.println(String.format("%d;%s", review.rating, review.text));
        }
        writer.close();
    }

    private static List<Review> parseReviewsPage(String url) throws IOException {
        Document html_doc = Jsoup.connect(url).get();
        Elements reviews = html_doc.select("article.pp-review-i");

        List<Review> reviewsArr = new ArrayList<Review>();

        for (Element review: reviews) {
            Elements star = review.select("span.g-rating-stars-i");
            Elements text = review.select("div.pp-review-text");
            if (star.size() > 0) {
                Elements texts = text.select("div.pp-review-text-i");
                int star_num = Integer.parseInt(star.first().attr("content"));
                reviewsArr.add(new Review(star_num, texts.first().html().replaceAll("<br>", " ")));
            }
        }

        return reviewsArr;


    }

    public static void main(String[] args) throws IOException {
        parseCategory("https://rozetka.com.ua/ua/tablets/c130309/filter/");
    }
}
