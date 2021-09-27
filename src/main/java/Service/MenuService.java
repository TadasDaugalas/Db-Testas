package Service;

import Data.User;

import java.util.Scanner;

public class MenuService {
    UserService userService = new UserService();
    Scanner scanner= new Scanner(System.in);
    public void menu(){
//        userService.createIndex();
        while (true){
            System.out.println("1-Register user");
            System.out.println("2-Send money");
            System.out.println("0- Returt");
            switch (scanner.nextLine()){
                case "1":
                    System.out.println("Insert name");
                    String name= scanner.nextLine();
                    System.out.println("Insert Surname");
                    String surname = scanner.nextLine();
                    System.out.println("Insert balance ");
                    int balance = Integer.parseInt(scanner.nextLine());
                    userService.addUser(new User(null,name,surname,balance));
                    break;
                case "2":
                    System.out.println("Insert sender name");
                    String sender = scanner.nextLine();
                    System.out.println("Inser reciever name");
                    String reciever =scanner.nextLine();
                    System.out.println("Insert amount of sending");
                    int amount =Integer.parseInt(scanner.nextLine());
                    if(userService.checkUser(sender)&& userService.checkUser(reciever)){
                        userService.sendMoney(sender,reciever,amount);
                        userService.getInfo(reciever);
                    }else {
                        System.out.println("User not exist");
                    }
                    break;
                case "0": return;
            }

        }
    }
}
