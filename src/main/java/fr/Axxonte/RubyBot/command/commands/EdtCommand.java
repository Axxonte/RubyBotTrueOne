package fr.Axxonte.RubyBot.command.commands;

import fr.Axxonte.RubyBot.command.CommandContext;
import fr.Axxonte.RubyBot.command.ICommand;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class EdtCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) throws Exception {

        /*String url = "https://www.gpu-lr.fr/sat/index.php?page_param=accueilsatellys.php&cat=0&numpage=1&niv=0&clef=/";
        String pdf = "https://www.gpu-lr.fr/gpu/imp_edt_pdf.php?type=etudiant&codef=INFO2_S3&coder=203775&semaine=42&ansemaine=2020&ispdf=1";

        HttpUrlConnection http = new HttpUrlConnection();

        //make sure cookies is turn on
        CookieHandler.setDefault(new CookieManager());

        //1. Send a "GET" request, so that you can extract the form's data.
        String page = http.GetPageContent(url);
        String postParams = http.getFormParams(page, "203775", "123");

        //2. Construct above post's content and then send a POST request for
        //authentication
        http.sendPost(url, postParams);

        //3. success then go to gmail.
        String result = http.GetPageContent(pdf);
        System.out.println(result);*/

    }

    @Override
    public String getName() {
        return "edt";
    }

    @Override
    public String getHelp() {
        return null;
    }

    public static String get(String url) throws IOException{

        String source ="";
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            source +=inputLine;
        in.close();
        return source;
    }
}
