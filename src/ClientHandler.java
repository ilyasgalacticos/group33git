import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        try{

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            PackageData packageData = null;

            while((packageData=(PackageData)inputStream.readObject())!=null){

                if(packageData.getOperationType().equals("ADD_BOOK")){
                    Books book = packageData.getBook();
                    Main.addBook(book);
                    System.out.println(Main.getBooks());
                }else if(packageData.getOperationType().equals("LIST_BOOKS")){

                    ArrayList<Books> books = (ArrayList<Books>) Main.getBooks().clone();
                    System.out.println(books);
                    PackageData response = new PackageData();
                    response.setOperationType("LIST_BOOKS");
                    response.setBooks(books);

                    outputStream.writeObject(response);

                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
