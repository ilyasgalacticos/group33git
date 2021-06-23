import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        try{

            Socket socket = new Socket("127.0.0.1", 1989);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

            while(true){

                System.out.println("PRESS 1 TO ADD BOOK");
                System.out.println("PRESS 2 TO LIST BOOKS");
                System.out.println("PRESS 0 TO EXIT");

                String choice = in.next();

                if(choice.equals("1")){

                    System.out.println("INSERT NAME:");
                    String name = in.next();

                    System.out.println("INSERT AUTHOR:");
                    String author = in.next();

                    Books book = new Books();
                    book.setName(name);
                    book.setAuthor(author);

                    PackageData packageData = new PackageData();
                    packageData.setOperationType("ADD_BOOK");
                    packageData.setBook(book);

                    outputStream.writeObject(packageData);

                }else if(choice.equals("2")){

                    PackageData packageData = new PackageData();
                    packageData.setOperationType("LIST_BOOKS");

                    outputStream.writeObject(packageData);

                    PackageData response = (PackageData)inputStream.readObject();

                    if(response.getOperationType().equals("LIST_BOOKS")){

                        ArrayList<Books> books = response.getBooks();

                        if(books!=null) {
                            for (Books b : books) {
                                System.out.println(b);
                            }
                        }

                    }

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
