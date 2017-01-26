package com.nodomain.savewords;


import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class YandexService {

    private static final String TRANSLATOR_API_KEY =
            "trnsl.1.1.20161207T122822Z.79bcd6930b08828a.cdd362a7d638a1647677e91a421574cb8e716a98";
    private static final String SPEECH_API_KEY = "f9135a1d-c8d3-4152-a14b-7a202173d637";


    interface Languages {
        String ENGLISH = "en";
        String RUSSIAN = "ru";
    }

    interface SoundFormats {
        String MP3 = "mp3";
        String WAV = "wav";
        String OPUS = "opus";
    }

    interface Speakers {
        String JANE = "jane";
        String OKSANA = "oksana";
        String ALYSS = "alyss";
        String OMAZH = "omazh";
        String ZAHAR = "zahar";
        String ERMIL = "ermil";
    }


    public String defineLanguage(String word) throws IOException {
        String requestUrl = createLanguageDefineRequestUrl(word);
        String responseJson = readJsonFromUrl(requestUrl);
        String language = getLanguageFromJson(responseJson);

        return language;
    }

    public String translateWordFromEnglishToRussian(String englishWord) throws IOException {
        String requestUrl = createTranslateRequestUrl(englishWord, Languages.ENGLISH, Languages.RUSSIAN);
        String responseJson = readJsonFromUrl(requestUrl);
        String translatedWord = getTranslatedWordFromJson(responseJson);

        return translatedWord;
    }

    public String translateWordFromRussianToEnglish(String russianWord) throws IOException {
        String requestUrl = createTranslateRequestUrl(russianWord, Languages.RUSSIAN, Languages.ENGLISH);
        String responseJson = readJsonFromUrl(requestUrl);
        String translatedWord = getTranslatedWordFromJson(responseJson);

        return translatedWord;
    }

    public byte[] englishWordToSpeech(String word) throws IOException {
        String requestUrl = createSpeechRequestUrl(word);
        return readBytesFromUrl(requestUrl);
    }


    private String createTranslateRequestUrl(String word, String fromLanguage, String toLanguage)
            throws UnsupportedEncodingException {
        String requestUrl = "https://translate.yandex.net/api/v1.5/tr.json/translate"
                + "?key=" + TRANSLATOR_API_KEY
                + "&text=" + URLEncoder.encode(word, "UTF-8")
                + "&lang=" + fromLanguage + "-" + toLanguage;

        return requestUrl;
    }

    private String createLanguageDefineRequestUrl(String word) throws UnsupportedEncodingException {
        String requestUrl = "https://translate.yandex.net/api/v1.5/tr.json/detect?"
                + "key=" + TRANSLATOR_API_KEY
                + "&text=" + URLEncoder.encode(word, "UTF-8");

        return requestUrl;
    }

    private String createSpeechRequestUrl(String word) {
        String requestUrl = "https://tts.voicetech.yandex.net/generate"
                + "?text=" + word
                + "&format=" + SoundFormats.MP3
                + "&lang=" + Languages.ENGLISH
                + "&speaker=" + Speakers.ZAHAR
                + "&speed=0.1"
                + "&emotion=good"
                + "&key=" + SPEECH_API_KEY;

        return requestUrl;
    }


    private String readJsonFromUrl(String requestUrl) throws IOException {
        URL url = new URL(requestUrl);
        URLConnection urlConnection = url.openConnection();
        InputStreamReader inputStreamReader = new InputStreamReader(urlConnection.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        StringBuilder stringBuilder = new StringBuilder();
        String nextLine;
        do {
            nextLine = bufferedReader.readLine();
            stringBuilder.append(nextLine);
        } while (nextLine != null);

        return stringBuilder.toString();
    }

    private byte[] readBytesFromUrl(String requestUrl) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream in = null;
        URL url = new URL(requestUrl);

        try {
            in = url.openStream ();
            byte[] buffer = new byte[4096];
            int n;

            while ((n = in.read(buffer)) > 0) {
                baos.write(buffer, 0, n);
            }
        } catch (IOException e) {
            throw new IOException();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return baos.toByteArray();
    }


    private String getTranslatedWordFromJson(String json) {
        String[] splitedJson = json.split("\"text\":");

        String a = splitedJson[splitedJson.length-1];
        a = a.substring(2, a.length()-7);

        String[] b = a.split(" ");
        String word = b[b.length - 1];

        return word;
    }

    private String getLanguageFromJson(String json) {
        String[] splitedJson = json.split("\"lang\":");

        String a = splitedJson[splitedJson.length-1];
        a = a.substring(1, a.length()-6);

        return a;
    }
}
