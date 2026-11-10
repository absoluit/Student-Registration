package collegestudentregistration;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Implementing BorderPane layout as root
        BorderPane root = new BorderPane();


        // HBox for application title on top
        HBox appTitle = new HBox(15);
        appTitle.setPadding(new Insets(10));
        appTitle.setStyle("-fx-background-color: #c0c0c0;");
        appTitle.setAlignment(Pos.BASELINE_LEFT);

        Label applicationTitle = new Label("Teagan's College JavaFX Layouts Lab");

        appTitle.getChildren().addAll(applicationTitle);
        root.setTop(appTitle);


        // VBox for Dashboard, Registration, Courses, and Help btns on left

        VBox menu = new VBox(15);
        menu.setPadding(new Insets(10));
        menu.setStyle("-fx-background-color: #d8d8d8;");

        Button btnDash = new Button("Dashboard");

        Button btnRegistration = new Button("Registration");

        Button btnCourses = new Button("Courses");

        Button btnHelp = new Button("Help");

        menu.getChildren().addAll(
                btnDash,
                btnRegistration,
                btnCourses,
                btnHelp
        );

        root.setLeft(menu);


        // VBox for registration form & buttons in centre

        VBox form = new VBox(15);
        form.setPadding(new Insets(10, 10, 10, 10));
        form.setStyle("-fx-background-color: #f0f0f0;");


        // Registration form title

        Label formTitle = new Label("Student Registration");
        formTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        // Implementing GridPane layout, gaps, and alignment

        GridPane formQuestions = new GridPane();
        formQuestions.setAlignment(Pos.BASELINE_LEFT);
        formQuestions.setHgap(10);
        formQuestions.setVgap(10);


        // Initialize dropdown menu/options

        ComboBox<String> courseDropdown = new ComboBox<> (
                FXCollections.observableArrayList(
                        "Software Engineering Technician",
                        "Software Engineering Technology",
                        "Healthcare Administration",
                        "Biomedical Engineering",
                        "Forensic Science"
                ));

        // UI component, columnNo., columnRow

        TextField idField = new TextField();
        TextField nameField = new TextField();
        TextField emailField = new TextField();

        formQuestions.add(new Label("Student ID:"), 0, 1);
        formQuestions.add(idField, 1, 1);

        formQuestions.add(new Label("Full Name:"), 0, 2);
        formQuestions.add(nameField, 1, 2);

        formQuestions.add(new Label("Email:"), 0, 3);
        formQuestions.add(emailField, 1, 3);

        formQuestions.add(new Label("Program:"), 0, 4);
        formQuestions.add(courseDropdown, 1, 4);

        Label statusLabel = new Label();

        form.getChildren().addAll(
                formTitle,
                formQuestions,
                statusLabel
        );

        root.setCenter(form);


        // Action buttons for form

        HBox actionButtons = new HBox(15);
        actionButtons.setPadding(new Insets(10));
        actionButtons.setStyle("-fx-background-color: #f0f0f0;");

        Button btnSubmit = new Button("Submit");
        Button btnReset = new Button("Reset");

        actionButtons.getChildren().addAll(btnSubmit, btnReset);

        root.setBottom(actionButtons);


        // Submit & Reset button functionality

        btnSubmit.setOnAction(e -> {
            String studentId = idField.getText().trim();
            String fullName = nameField.getText().trim();
            String email = emailField.getText().trim();
            String program = courseDropdown.getValue();

            // Validation
            if(studentId.isEmpty()) {
                statusLabel.setStyle("-fx-text-fill: red;");
                statusLabel.setText("Error: Student ID is required.");
                return;
            }

            if(fullName.isEmpty()) {
                statusLabel.setStyle("-fx-text-fill: red;");
                statusLabel.setText("Error: Fullname is required.");
                return;
            }

            if(email.isEmpty()) {
                statusLabel.setStyle("-fx-text-fill: red;");
                statusLabel.setText("Error: email is required");
                return;
            }

            if(!email.contains("@") || !email.contains(".")) {
                statusLabel.setStyle("-fx-text-fill: red;");
                statusLabel.setText("Error: Invalid email format.");
                return;
            }

            if(program == null || program.isEmpty()) {
                statusLabel.setStyle("-fx-text-fill: red;");
                statusLabel.setText("Error: Please select a program.");
                return;
            }

            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Successfully Submitted.");

            idField.clear();
            nameField.clear();
            emailField.clear();
            courseDropdown.getSelectionModel().clearSelection();
        });

        btnReset.setOnAction(e -> {
            idField.clear();
            nameField.clear();
            emailField.clear();
            courseDropdown.getSelectionModel().clearSelection();

            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setText("Form Reset.");
        });



        // Initializing scene and insert it into stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Student Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
