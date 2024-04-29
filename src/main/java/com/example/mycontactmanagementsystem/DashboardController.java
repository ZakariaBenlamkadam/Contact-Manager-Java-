package com.example.mycontactmanagementsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import com.example.mycontactmanagementsystem.Group;

public class DashboardController implements Initializable {

    public TextField addGroup_IDGroup;
    public TextField addGroup_nom;
    public Button addGroup_deleteBtn;
    public Button addGroup_updateBtn;
    public Button addGroup_addBtn;
    public TextField addGroup_search;
    public TableView<Group> addGroup_tableView;
    public TableColumn<Group, String> addGroup_col_IDgroup;
    public TableColumn<Group, String> addGroup_col_nom;
    @FXML
    private AnchorPane addGroup_form;

    @FXML
    private Label Genre_nom;

    @FXML
    private Label Genre_prenom;

    @FXML
    private TextField addContact_IDcontact;

    @FXML
    private TextField addContact_idGroup;

    @FXML
    private TextField addContact_Telephone1;

    @FXML
    private TextField addContact_Telephone2;

    @FXML
    private Button addContact_addBtn;

    @FXML
    private TextField addContact_adresse;

    @FXML
    private Button addContact_btn;

    @FXML
    private Button addContact_clearBtn;

    @FXML
    private TableColumn<Contact, String> addContact_col_Adresse;

    @FXML
    private TableColumn<Contact, String> addContact_col_EmailProfessionel;

    @FXML
    private TableColumn<Contact, String> addContact_col_Emailpersonnel;

    @FXML
    private TableColumn<Contact, String> addContact_col_Genre;

    @FXML
    private TableColumn<Contact, String> addContact_col_IDcontact;

    @FXML
    private TableColumn<Contact, String> addContact_col_IDgroup;

    @FXML
    private TableColumn<Contact, String> addContact_col_Telephone1;

    @FXML
    private TableColumn<Contact, String> addContact_col_Telephone2;

    @FXML
    private TableColumn<Contact, String> addContact_col_nom;

    @FXML
    private TableColumn<Contact, String> addContact_col_prenom;

    @FXML
    private Button addContact_deleteBtn;

    @FXML
    private TextField addContact_emailpersonnel;

    @FXML
    private TextField addContact_emailprofessioneel;

    @FXML
    private AnchorPane addContact_form;

    @FXML
    private ComboBox<?> addContact_genre;

    @FXML
    private TextField addContact_nom;

    @FXML
    private TextField addContact_prenom;

    @FXML
    private Button addContact_search;

    @FXML
    private TableView<Contact> addContact_tableView;

    @FXML
    private Button addContact_updateBtn;

    @FXML
    private Button addGroup_btn;

    @FXML
    private Button close;

    @FXML
    private Button home_btn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_totalContacts;

    @FXML
    private Label home_totalInactiveEm;

    @FXML
    private Label home_totalPresents;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    private Connection connect;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;

    private ObservableList<Contact> addContactList;
    private ObservableList<Group> addGroupList;
    private Connection connection;

    public DashboardController() {
        connection = DatabaseConnection.connectDb();
    }


    public void displayGroups() {
        Group.displayAllGroups();
    }

    public void addGroupShowListData(){
        List<Group> groupList = addGroupListData();

        addGroup_col_IDgroup.setCellValueFactory(new PropertyValueFactory<>("IDgroup"));
        addGroup_col_nom.setCellValueFactory(new PropertyValueFactory<>("nomGroup"));

        addGroup_tableView.setItems(FXCollections.observableArrayList(groupList));
    }


    public void addContactShowListData(){
        addContactList =addContactListData();
        addContact_col_IDcontact.setCellValueFactory(new PropertyValueFactory<>("IDcontact"));
        addContact_col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        addContact_col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        addContact_col_Telephone1.setCellValueFactory(new PropertyValueFactory<>("Telephone1"));
        addContact_col_Telephone2.setCellValueFactory(new PropertyValueFactory<>("Telephone2"));
        addContact_col_Adresse.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
        addContact_col_Emailpersonnel.setCellValueFactory(new PropertyValueFactory<>("EmailPersonnel"));
        addContact_col_EmailProfessionel.setCellValueFactory(new PropertyValueFactory<>("EmailProfessionel"));
        addContact_col_Genre.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        addContact_col_IDgroup.setCellValueFactory(new PropertyValueFactory<>("IdGroup"));

        addContact_tableView.setItems(addContactList);
    }

    @FXML
    void addContactAdd(ActionEvent event) {
        String sql = "INSERT INTO contact " +
                "(IDcontact, nom, prenom, Telephone1, Telephone2, Adresse, EmailPersonnel, EmailProfessionel, Genre, idGroup) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if (addContact_IDcontact.getText().isEmpty()
                    || addContact_nom.getText().isEmpty()
                    || addContact_prenom.getText().isEmpty()
                    || addContact_Telephone1.getText().isEmpty()
                    || addContact_Telephone2.getText().isEmpty()
                    || addContact_adresse.getText().isEmpty()
                    || addContact_emailpersonnel.getText().isEmpty()
                    || addContact_genre.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("MESSAGE D'ERREUR");
                alert.setHeaderText(null);
                alert.setContentText("Merci de remplir ces champs vides");
                alert.showAndWait();
            } else {
                String check = "SELECT IDcontact FROM contact WHERE IDcontact='" + addContact_IDcontact.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("MESSAGE D'ERREUR");
                    alert.setHeaderText(null);
                    alert.setContentText("L'ID de contact " + addContact_IDcontact.getText() + " existe déjà !");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addContact_IDcontact.getText());
                    prepare.setString(2, addContact_nom.getText());
                    prepare.setString(3, addContact_prenom.getText());
                    prepare.setString(4, addContact_Telephone1.getText());
                    prepare.setString(5, addContact_Telephone2.getText());
                    prepare.setString(6, addContact_adresse.getText());
                    prepare.setString(7, addContact_emailpersonnel.getText());
                    prepare.setString(8, addContact_emailprofessioneel.getText());
                    prepare.setString(9, (String) addContact_genre.getSelectionModel().getSelectedItem());
                    prepare.setString(10, addContact_idGroup.getText().isEmpty() ? null : addContact_idGroup.getText());
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("MESSAGE D'INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Ajouté avec succès");
                    alert.showAndWait();

                    addContactShowListData();
                    addContactReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void addContactDelete(ActionEvent event) {

        String sql = "DELETE FROM contact WHERE IDcontact = '" + this.addContact_IDcontact.getText() + "'";
        this.connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if (!this.addContact_IDcontact.getText().isEmpty() && !this.addContact_nom.getText().isEmpty() && !this.addContact_prenom.getText().isEmpty() && this.addContact_genre.getSelectionModel().getSelectedItem() != null && !this.addContact_Telephone1.getText().isEmpty() && !this.addContact_Telephone2.getText().isEmpty() && !this.addContact_emailpersonnel.getText().isEmpty() && !this.addContact_emailprofessioneel.getText().isEmpty() && !this.addContact_idGroup.getText().isEmpty() && !this.addContact_adresse.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cofirmation Message");
                alert.setHeaderText((String)null);
                alert.setContentText("Are you sure you want to DELETE Employee ID: " + this.addContact_IDcontact.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (((ButtonType)option.get()).equals(ButtonType.OK)) {
                    this.statement = this.connect.createStatement();
                    this.statement.executeUpdate(sql);
                    String deleteInfo = "DELETE FROM contact WHERE IDcontact = '" + this.addContact_IDcontact.getText() + "'";
                    this.prepare = this.connect.prepareStatement(deleteInfo);
                    this.prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText((String)null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    this.addContactShowListData();
                    this.addContactReset();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText((String)null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private String[] genderList ={"Homme","Femme"};
    @FXML
    void addContactGenreList() {
        List<String> list= new ArrayList<>();
        for(String data:genderList){
            list.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(list);
        addContact_genre.setItems(listData);

    }

    @FXML
    void addContactReset() {
        addContact_IDcontact.setText("");
        addContact_nom.setText("");
        addContact_prenom.setText("");
        addContact_Telephone1.setText("");
        addContact_Telephone2.setText("");
        addContact_adresse.setText("");
        addContact_emailpersonnel.setText("");
        addContact_emailprofessioneel.setText("");
        addContact_genre.getSelectionModel().clearSelection();
        addContact_idGroup.setText("");

    }


    @FXML
    public void addContactSearch() {
        String id = addContact_IDcontact.getText();
        String nom = addContact_nom.getText();
        String prenom = addContact_prenom.getText();
        String Telephone1 = addContact_Telephone1.getText();
        String Telephone2 = addContact_Telephone2.getText();
        String EmailPersonnel = addContact_emailpersonnel.getText();
        String EmailProfessionel = addContact_emailprofessioneel.getText();
        String Adresse = addContact_adresse.getText();
        String Idgroup = addContact_idGroup.getText();


        String query = "SELECT * FROM contact WHERE 1=1";
        if (!id.isEmpty()) {
            query += " AND IDcontact = '" + id + "'";
        }
        if (!nom.isEmpty()) {
            query += " AND nom = '" + nom + "'";
        }
        if (!prenom.isEmpty()) {
            query += " AND prenom = '" + prenom + "'";
        }
        if (!Telephone1.isEmpty()) {
            query += " AND Telephone1 = '" + Telephone1 + "'";
        }
        if (!Telephone2.isEmpty()) {
            query += " AND Telephone2 = '" + Telephone2 + "'";
        }
        if (!EmailPersonnel.isEmpty()) {
            query += " AND EmailPersonnel = '" + EmailPersonnel + "'";
        }
        if (!EmailProfessionel.isEmpty()) {
            query += " AND EmailProfessionel = '" + EmailProfessionel + "'";
        }
        if (!Adresse.isEmpty()) {
            query += " AND Adresse = '" + Adresse + "'";
        }
        if (!Idgroup.isEmpty()) {
            query += " AND Idgroup = '" + Idgroup + "'";
        }

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int contactID = rs.getInt("IDcontact");
                String contactName = rs.getString("nom");
                String contactPrenom = rs.getString("prenom");
                String contactTelephone1 = rs.getString("Telephone1");
                String contactTelephone2 = rs.getString("Telephone2");
                String contactEmailpersonel = rs.getString("EmailPersonnel");
                String contactEmailprofessionnel = rs.getString("EmailProfessionel");
                String contactAdresse = rs.getString("Adresse");
                int groupID = rs.getInt("idGroup");





                System.out.println("Contact ID: " + contactID);
                System.out.println("Name: " + contactName);
                System.out.println("Prenom: " + contactPrenom);
                System.out.println("Telephone 1 : " + contactTelephone1);
                System.out.println("Telephone 2: " + contactTelephone2);
                System.out.println("Email personnel: " + contactEmailpersonel);
                System.out.println("Email professionel: " + contactEmailprofessionnel);
                System.out.println("Adresse: " + contactAdresse);
                System.out.println("Group ID: " + groupID);
                System.out.println("_____________________________________________________");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void addContactUpdate() {
        String nom = this.addContact_nom.getText();
        String prenom = this.addContact_prenom.getText();
        String telephone1 = this.addContact_Telephone1.getText();
        String telephone2 = this.addContact_Telephone2.getText();
        String adresse = this.addContact_adresse.getText();
        String emailPersonnel = this.addContact_emailpersonnel.getText();
        String emailProfessionnel = this.addContact_emailprofessioneel.getText();
        String genre = (String) this.addContact_genre.getSelectionModel().getSelectedItem();
        String idGroup = this.addContact_idGroup.getText();
        String IDcontact = this.addContact_IDcontact.getText();

        String sql = "UPDATE contact SET nom = ?, prenom = ?, Telephone1 = ?, Telephone2 = ?, Adresse = ?, " +
                "EmailPersonnel = ?, EmailProfessionel = ?, genre = ?, idGroup = ? WHERE IDcontact = ?";

        try {
            Alert alert;
            if (!IDcontact.isEmpty() && !nom.isEmpty() && !prenom.isEmpty() && genre != null &&
                    !telephone1.isEmpty() && !telephone2.isEmpty() && !emailPersonnel.isEmpty() &&
                    !emailProfessionnel.isEmpty() && !adresse.isEmpty()) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Employee ID: " + IDcontact + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    this.connect = DatabaseConnection.connectDb();
                    this.prepare = this.connect.prepareStatement(sql);
                    this.prepare.setString(1, nom);
                    this.prepare.setString(2, prenom);
                    this.prepare.setString(3, telephone1);
                    this.prepare.setString(4, telephone2);
                    this.prepare.setString(5, adresse);
                    this.prepare.setString(6, emailPersonnel);
                    this.prepare.setString(7, emailProfessionnel);
                    this.prepare.setString(8, genre);
                    this.prepare.setString(9, idGroup.isEmpty() ? null : idGroup);
                    this.prepare.setString(10, IDcontact);
                    this.prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    this.addContactShowListData();
                    this.addContactReset();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @FXML
    void close(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void displayUsername(){
        username.setText(getData.username);
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Message de Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de vouloir vous déconnecter?");
        Optional<ButtonType> option = alert.showAndWait();
        try {

            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();

            }
        }catch(Exception e){e.printStackTrace();}

    }

    @FXML
    void minimize(ActionEvent event) {
        Stage stage = (Stage)main_form.getScene().getWindow();
        stage.setIconified(true);

    }

    @FXML
    void switchForm(ActionEvent event) {
        if (event.getSource() == addContact_btn){
            addContact_form.setVisible(true);
            addGroup_form.setVisible(false);


            this.addContact_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2c0767, #571d96);");
            this.addGroup_btn.setStyle("-fx-background-color:transparent");


            this.addContactGenreList();
            this.addContactSearch();

        }else if (event.getSource() == addGroup_btn){
            addContact_form.setVisible(false);
            addGroup_form.setVisible(true);

            this.addGroup_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #2c0767, #571d96);");
            this.addContact_btn.setStyle("-fx-background-color:transparent");
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.displayUsername();
        this.addContactShowListData();
        this.addContactGenreList();
        this.addGroupShowListData();

    }

    public void addGroupAdd(ActionEvent actionEvent) {
        String sql = "INSERT INTO groupe (idGroup, Nom) VALUES (?, ?)";

        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if (addGroup_IDGroup.getText().isEmpty() || addGroup_nom.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("MESSAGE D'ERREUR");
                alert.setHeaderText(null);
                alert.setContentText("Merci de remplir ces champs vides");
                alert.showAndWait();
            } else {
                String check = "SELECT IDgroup FROM groupe WHERE idGroup='" + addGroup_IDGroup.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(check);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("MESSAGE D'ERREUR");
                    alert.setHeaderText(null);
                    alert.setContentText("L'ID de groupe " + addGroup_IDGroup.getText() + " existe déjà !");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addGroup_IDGroup.getText());
                    prepare.setString(2, addGroup_nom.getText());
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("MESSAGE D'INFORMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Groupe ajouté avec succès");
                    alert.showAndWait();

                    Group group = new Group();
                    group.displayAllGroups();

                    addGroupShowListData();
                    //addGroupReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGroupUpdate(ActionEvent actionEvent) {
        String nomGroup = addGroup_nom.getText();
        Integer IDgroup = Integer.valueOf(addGroup_IDGroup.getText());

        String sql = "UPDATE groupe SET nom = ? WHERE IDgroup = ?";

        try {
            Alert alert;
            if (!nomGroup.isEmpty() && IDgroup != null) {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to update Group ID: " + IDgroup + "?");
                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    connect = DatabaseConnection.connectDb();
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, nomGroup);
                    prepare.setInt(2, IDgroup);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();


                    Group group = new Group();
                    group.displayAllGroups();

                    addGroupShowListData();
                    //addGroupReset();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGroupDelete(ActionEvent actionEvent) {

        String sql = "DELETE FROM groupe WHERE IDgroup = ?";
        connect = DatabaseConnection.connectDb();

        try {
            Alert alert;
            if (addGroup_IDGroup.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please provide the Group ID");
                alert.showAndWait();
            } else {
                String check = "SELECT idGroup FROM groupe WHERE idGroup='" + addGroup_IDGroup.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);
                if (result.next()) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure you want to delete Group ID: " + addGroup_IDGroup.getText() + "?");
                    Optional<ButtonType> option = alert.showAndWait();
                    if (option.isPresent() && option.get() == ButtonType.OK) {
                        prepare = connect.prepareStatement(sql);
                        prepare.setString(1, addGroup_IDGroup.getText());
                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Group deleted successfully");
                        alert.showAndWait();


                        Group group = new Group();
                        group.displayAllGroups();

                        addGroupShowListData();
                        //addGroupReset();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Group ID " + addGroup_IDGroup.getText() + " does not exist!");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addGroupSearch(KeyEvent keyEvent) {
    }

    @FXML
    public void addContactSelect(){
        Contact contact = addContact_tableView.getSelectionModel().getSelectedItem();
        int num = addContact_tableView.getSelectionModel().getSelectedIndex();

        if((num -1)< -1){return;}

        addContact_IDcontact.setText(String.valueOf(contact.getIDcontact()));
        addContact_nom.setText(String.valueOf(contact.getNom()));
        addContact_prenom.setText(String.valueOf(contact.getPrenom()));
        addContact_Telephone1.setText(String.valueOf(contact.getTelephone1()));
        addContact_Telephone2.setText(String.valueOf(contact.getTelephone2()));
        addContact_adresse.setText(String.valueOf(contact.getAdresse()));
        addContact_emailpersonnel.setText(String.valueOf(contact.getEmailPersonnel()));
        addContact_emailprofessioneel.setText(String.valueOf(contact.getEmailProfessionel()));
        addContact_idGroup.setText(String.valueOf(contact.getIdGroup()));


    }
    public void addGroupSelect() {
        Group group = addGroup_tableView.getSelectionModel().getSelectedItem();
        int index = addGroup_tableView.getSelectionModel().getSelectedIndex();

        if (index < 0) {
            return;
        }

        addGroup_IDGroup.setText(String.valueOf(group.getIDgroup()));
        addGroup_nom.setText(String.valueOf(group.getNomGroup()));

    }

    @FXML
    public ObservableList<Contact> addContactListData(){
        ObservableList<Contact> ListData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contact ";

        connect = DatabaseConnection.connectDb();


        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Contact contact;

            while (result.next()){
                contact = new Contact(result.getInt("IDcontact"),
                        result.getString("nom"),
                        result.getString("prenom"),
                        result.getString("Telephone1"),
                        result.getString("Telephone2"),
                        result.getString("Adresse"),
                        result.getString("EmailPersonnel"),
                        result.getString("EmailProfessionel"),
                        result.getString("Genre"),
                        result.getInt("idGroup"));
                ListData.add(contact);

            }

        }catch(Exception e){ e.printStackTrace();}
        return ListData;
    }

    @FXML
    public ObservableList<Group> addGroupListData() {

        ObservableList<Group> groupList = FXCollections.observableArrayList();
        connect = DatabaseConnection.connectDb();

        try {
            statement = connect.createStatement();
            result = statement.executeQuery("SELECT * FROM groupe");

            while (result.next()) {
                Integer groupId = result.getInt("idGroup");
                String groupName = result.getString("Nom");

                Group group = new Group(groupId, groupName);
                groupList.add(group);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groupList;
    }


}
