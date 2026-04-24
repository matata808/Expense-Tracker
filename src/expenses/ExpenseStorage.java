package expenses;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExpenseStorage {

    public static final ArrayList<ExpenseData> expenses = new ArrayList<>();
    //ids always start at 1 and not 0.
    private int nextId = 1;

    public int getNextId() {
        //increases ID once called
        return nextId++;
    }

    public void add(ExpenseData e){
        expenses.add(e);
    }

    public static List<ExpenseData> findAll(){
        return new ArrayList<>(expenses);
    }

    /**
     * creates and writes JSON objects inside a JSON array
     * @throws IOException warns that file writing can fail (e.g. no disk space)
     */
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
        try (FileWriter fileWriter = new FileWriter("expenses.json")) {
            fileWriter.write(jsonArray.toString());
        }

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
