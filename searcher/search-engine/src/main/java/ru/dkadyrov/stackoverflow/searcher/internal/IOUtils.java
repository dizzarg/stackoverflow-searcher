package ru.dkadyrov.stackoverflow.searcher.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class IOUtils {

    public static String unzipStream(InputStream in) {
        try (GZIPInputStream gzipInputStream = new GZIPInputStream(in);
             InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream, StandardCharsets.UTF_8);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            return bufferedReader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            throw new RuntimeException("Fail to read response", e);
        }
    }


}
