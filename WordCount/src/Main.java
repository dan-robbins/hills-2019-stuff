import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

public class Main {

    private static ThreadWorker[] threads = new ThreadWorker[8];
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        System.out.println("Reading from url");
        long startTime = System.nanoTime();
        try {
            URL url = new URL("https://introcs.cs.princeton.edu/java/data/leipzig/leipzig1m.txt");

            // read text returned by server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;
            while ((line = in.readLine()) != null) {
                list.add(line);
            }
            in.close();

        }
        catch (MalformedURLException e) {
            System.out.println("Malformed URL: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        }
        long runTime   = System.nanoTime() - startTime;
        System.out.println("Read finished in " + runTime/1000000000.0 + " seconds");
        try {
            doWork(list, "the");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void doWork(LinkedList<String> list, String word) throws InterruptedException {
        int total = 0;
        long startTime = System.nanoTime();
        for(int i = 0; i < threads.length; i++){
            threads[i] = new ThreadWorker(list, word);
            threads[i].start();
        }

        for(ThreadWorker worker : threads){
            worker.join();
            total += worker.getCount();
        }
        long runTime   = System.nanoTime() - startTime;
        System.out.println("Work finished in " + runTime/1000000000.0 + " seconds");
        System.out.println(total + " occurrences of the word " + word);
    }
}