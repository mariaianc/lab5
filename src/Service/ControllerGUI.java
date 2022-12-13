package Service;

import DOMAIN.BirthdayCake;
import DOMAIN.Order;
import Repo.CakeShop;
import Repo.OrderRepo;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ControllerGUI {

    CakeShop cs = new CakeShop();
    OrderRepo or = new OrderRepo();

    @FXML
    TextField orderID;

    @FXML
    TextField personName;

    @FXML
    TextField cake_id;

    @FXML
    TextField finish;

    @FXML
    TextField begin;

    @FXML
    TextField cakeID;
    @FXML
    TextField cakeName;
    @FXML
    TextField cakePrice;
    @FXML
    TextField cakeExpiration;
    @FXML
    ListView<String> mainList;

    public void refreshListCake(){
        mainList.getItems().clear();
        for(BirthdayCake c : cs.getAll()){
            mainList.getItems().add(c.toString());
        }
    }

    public void refreshListOrder(){
        mainList.getItems().clear();
        for(Order o : or.getAll()){
            mainList.getItems().add(o.toString());
        }
    }

    @FXML
    public void addCake(){
        BirthdayCake c = new BirthdayCake(cakeName.getText(),Double.parseDouble(cakePrice.getText()),
                cakeExpiration.getText(), Integer.parseInt(cakeID.getText()));
        cs.add(c);
        refreshListCake();
    }

    @FXML
    public void addOrder(){
        Order o = new Order(personName.getText(),cs.getCake(Integer.parseInt(cake_id.getText())),
                begin.getText(), finish.getText(), Integer.parseInt(orderID.getText()));
        or.add(o);
        refreshListOrder();
    }

    @FXML
    public void printAllOrders(){
        refreshListOrder();
    }

    @FXML
    public void printAllCakes(){
        refreshListCake();
    }

}
