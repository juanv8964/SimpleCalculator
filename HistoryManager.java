import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HistoryManager {
    private final File historyFile = new File("history.txt");
    private final ArrayList<String> history = new ArrayList<>();
    public int getHistorySize()
    {
        return history.size();
    }
    public void addEntry(String entry)
    {
    
        history.add(entry);
    }
    public ArrayList<String> getHistory()
    {
        return history;
    }
    public boolean isEmpty()
    {
        return history.isEmpty();
    }
    public void clearHistory(){
        if (history.isEmpty()) {
            System.out.println("History is already empty.");
            return;

        }
        history.clear();
        System.out.println("History has been cleared from memory.");
    }
    public void clearFile(){
        try{
            FileWriter writer = new FileWriter(historyFile, false);
            writer.close();
            System.out.println("File cleared successfully.");
        } catch(IOException e){
            System.out.println("Error clearing file: " + e.getMessage());
        }
    }
    public void loadHistory()
    {
        if(!historyFile.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(historyFile))){
            String line;
            while ((line = reader.readLine()) != null){
                history.add(line);
            }
            System.out.println("You have loaded in " + history.size() + " history entries");
        }
            catch(IOException e){
                System.out.println("Error loading history: " + e.getMessage());
            }
    }
    public void saveHistoryFile(){
        if(history.isEmpty()) {
            System.out.println("Error: No history to save.");
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(historyFile)))
        {
            for (String entry : history)
            {
                writer.write(entry + "\n");
            }
            System.out.println("History saved to file successfully");
        }
        catch(IOException e){
            System.out.println("Error: cannot save history: " + e.getMessage());
        }
    }
}
