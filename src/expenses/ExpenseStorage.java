package expenses;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExpenseStorage {

    public static final ArrayList<ExpenseData> expenses = new ArrayList<>();

    

    public static void writer() throws IOException {
        JSONArray jsonArray = new JSONArray();
        for (ExpenseData data : expenses) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id", data.getId());
            jsonObject.put("Description", data.getDescription());
            jsonObject.put("Amount", data.getAmount());
            jsonObject.put("Date", data.getDate());
            jsonArray.put(jsonObject);
        }
        FileWriter fileWriter = new FileWriter("expenses.json");
        fileWriter.write(jsonArray.toString());
        fileWriter.close();

    }



    public static void reader() throws IOException {
        if (!expenses.isEmpty()) {
            return;
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader("expenses.json"));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
        }

        bufferedReader.close();

        JSONArray jsonArray = new JSONArray(builder.toString());
        for (int i = 0; i < jsonArray.length(); i++) {
            ExpenseData data = new ExpenseData();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Id", data.getId());
            jsonObject.put("Description", data.getDescription());
            jsonObject.put("Amount", data.getAmount());
            jsonObject.put("Date", data.getDate());
            expenses.add(data);
        }

    }


}
