import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main{

    private static ArrayList<Books> books = new ArrayList<>();
    private static int id = 1;

    public static void main(String[] args) {

        try{

            ServerSocket serverSocket = new ServerSocket(1989);
            while(true){

                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void addBook(Books book){
        book.setId(id);
        books.add(book);
        id++;
    }

    public static ArrayList<Books> getBooks(){
        return books;
    }
}