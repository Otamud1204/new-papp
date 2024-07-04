


import com.google.gson.Gson;
import com.sun.mail.imap.protocol.ID;
import uz.app.entity.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import static java.lang.ref.Cleaner.create;
import static jdk.internal.editor.external.ExternalEditor.edit;

public class Main {
    static String baseUrl = "http://localhost:8080/api/users";
    static ID id;
    static User user = new User();
    static Gson gson = new Gson();
    static Scanner scanner = new Scanner(System.in);
    static Scanner strScanner = new Scanner(System.in);
    static HttpClient client = HttpClient.newHttpClient();

    public static void main(String[] args) throws Exception {

        while (true) {
            System.out.println("""
                    0 exit
                    1 create user
                    2 show users
                    3 show user by id
                    4 edit user
                    5 delete user
                    """);
            switch (scanner.nextInt()) {
                case 0 -> {
                    System.out.println("see you soon");
                }
                case 1 -> {
                    createUser();
                }
                case 2 -> {
                    showUser();
                }
                case 3 -> {
                    showUserid();
                }
                case 4 -> {
                    edit();
                }
                case 5 -> {
                    System.out.println("enter user id");
                    String userId = strScanner.nextLine();
                    String path = baseUrl + "/" + userId;

                    HttpRequest req = HttpRequest
                            .newBuilder()
                            .uri(new URI(path))
                            .DELETE()
                            .header("Content-type", "application/json")
                            .build();

                    HttpResponse<String> send = client.send(req, HttpResponse.BodyHandlers.ofString());
                    System.out.println(send.body());

                }
            }
        }
    }

    private static void showUserid() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("enter user id");
        String userId = strScanner.nextLine();
        String path = baseUrl + "/" + userId;
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(new URI(path))
                .GET()
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> send = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());

    }

    private static void showUser() throws URISyntaxException, IOException, InterruptedException {
        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(new URI(baseUrl))
                .GET()
                .header("Content-type", "application/json")
                .build();
        HttpResponse<String> send = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());

    }

    private static void edit() throws URISyntaxException, IOException, InterruptedException {
        System.out.println("enter user id");
        String userId = strScanner.nextLine();
        String path = baseUrl + "/" + userId;

        User user = new User();
        System.out.println("enter name");
        user.setName(strScanner.nextLine());
        System.out.println("enter surname");
        user.setSurname(strScanner.nextLine());
        System.out.println("enter age");
        user.setAge(scanner.nextInt());

        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(new URI(path))
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> send = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
    }

    private static void createUser() throws URISyntaxException, IOException, InterruptedException {

        String path = baseUrl;

        User user = new User();
        System.out.println("enter name");
        user.setName(strScanner.nextLine());
        System.out.println("enter surname");
        user.setSurname(strScanner.nextLine());
        System.out.println("enter age");
        user.setAge(scanner.nextInt());

        HttpRequest req = HttpRequest
                .newBuilder()
                .uri(new URI(path))
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> send = client.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println(send.body());
    }
}

//http://10.10.1.187:8080/swagger-ui/index.html