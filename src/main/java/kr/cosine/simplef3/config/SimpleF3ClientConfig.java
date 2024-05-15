package kr.cosine.simplef3.config;

import com.google.gson.Gson;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class SimpleF3ClientConfig {

    private static final String folderPath = FabricLoader.getInstance().getConfigDir().toString();
    private static final String fileName = "hqsimplef3-client.json";

    private static final Gson gson = new Gson();

    private static SimpleF3ClientSetting simpleF3ClientSetting = new SimpleF3ClientSetting();

    public static SimpleF3ClientSetting getSimpleF3ClientSetting() {
        return simpleF3ClientSetting;
    }

    public static void load() {
        File file = new File(folderPath, fileName);
        if (file.exists()) {
            try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
            ) {
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                String json = stringBuilder.toString();
                simpleF3ClientSetting = gson.fromJson(json, SimpleF3ClientSetting.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            save();
        }
    }

    public static void save() {
        String json = gson.toJson(simpleF3ClientSetting);
        File file = new File(folderPath, fileName);
        try (
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            bufferedWriter.append(json);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
