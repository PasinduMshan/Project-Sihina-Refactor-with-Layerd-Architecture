package lk.ijse.ProjectSihina.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import lk.ijse.ProjectSihina.Other.ArrowKeyPress;
import lk.ijse.ProjectSihina.Other.Months;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.GuardianDto;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.dto.Tm.RegisterTm;
import lk.ijse.ProjectSihina.model.ClassModel;
import lk.ijse.ProjectSihina.model.PaymentModel;
import lk.ijse.ProjectSihina.model.RegisterStudentModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegistrationPayForm implements Initializable {

    @FXML
    private JFXComboBox<String> cmbClass;

    @FXML
    private JFXComboBox<String> cmbMonth;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colClass;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStu_Id;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private Label lblType;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<RegisterTm> tblPayment;

    @FXML
    private JFXTextField txtAmount;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPayId;

    private StudentDto studentDto;

    private GuardianDto guardianDto;

    private Object year;
    private Object month;
    private Object DATE;

    private String Subject;
    private String Type = "RegistrationFee";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        generatePayId();
        setDateAndTime();
        loadAllMonth();
        loadAllClass();
        loadAllRegistrations();
        lblType.setText(Type);
        tableListener();
        ArrowKeyPress.switchTextFieldOnArrowPressRight(txtPayId,txtID);
        ArrowKeyPress.switchTextFieldOnArrowPressRight(txtID,txtName);
        ArrowKeyPress.switchTextFieldOnArrowPressLeft(txtName,txtID);
        ArrowKeyPress.switchTextFieldOnArrowPressLeft(txtID,txtPayId);
        ArrowKeyPress.switchTextFieldOnArrowPressDown(txtID,txtAmount);
        ArrowKeyPress.switchTextFieldOnArrowPressDown(txtPayId,txtAmount);
        ArrowKeyPress.switchTextFieldOnArrowPressDown(txtName,txtAmount);
        ArrowKeyPress.switchTextFieldOnArrowPressUP(txtAmount,txtID);
    }

    public void initialData(StudentDto dto, GuardianDto guardianDto) {
        this.studentDto = dto;
        this.guardianDto = guardianDto;
        txtID.setText(dto.getID());
        txtName.setText(dto.getName());
        cmbClass.setValue(dto.getStu_Class());
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("PayId"));
        colStu_Id.setCellValueFactory(new PropertyValueFactory<>("StuId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("StuName"));
        colClass.setCellValueFactory(new PropertyValueFactory<>("StuClass"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    }

    private void loadAllRegistrations() {
        ObservableList<RegisterTm> obList = FXCollections.observableArrayList();

        try {
            List<PaymentDto> dtoList = RegisterStudentModel.getAllRegisterPayment(Type);

            for (PaymentDto dto : dtoList) {
                obList.add(new RegisterTm(
                        dto.getPayID(),
                        dto.getStuID(),
                        dto.getStuName(),
                        dto.getStuClass(),
                        dto.getDate(),
                        dto.getAmount()
                ));
            }
            tblPayment.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadAllClass() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<ClassDto> nameList = ClassModel.getAllClass();

            for (ClassDto dto : nameList) {
                obList.add(dto.getClassName());
            }
            cmbClass.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllMonth() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        Months[] months = Months.values();
        for (Months month : months){
            obList.add(String.valueOf(month));
        }
        cmbMonth.setItems(obList);
    }

    private void setDateAndTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void updateTime() {
        LocalTime now = LocalTime.now();
        String formatted = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        lblTime.setText(formatted);
    }

    private void generateFieldInRegisterForm() {
        if (studentDto != null) {
            txtID.setText(studentDto.getID());
            txtName.setText(studentDto.getName());
            cmbClass.setValue(studentDto.getStu_Class());
            Subject = studentDto.getSubject();
        } else {
            new Alert(Alert.AlertType.ERROR,"Fill out the Student Information Form before registering").show();
        }
    }

    private void generatePayId() {
        try {
            String PayId = PaymentModel.generateNextPayId();
            txtPayId.setText(PayId);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String PayId = txtPayId.getText();

        if (PayId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Id Field is Empty!!").show();
            return;
        }

        try {
            boolean isDeleted = PaymentModel.DeletePayment(PayId);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Delete Success!!!").showAndWait();
                clearField();
                loadAllRegistrations();
            } else {
                new Alert(Alert.AlertType.ERROR,"Delete Failed!!!").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clearField() throws SQLException {
        txtID.setText("");
        txtName.setText("");
        cmbClass.setValue("");
        txtAmount.setText("");
        cmbMonth.setValue("");
        lblType.setText(Type);
        generatePayId();
        setDateAndTime();
    }

    @FXML
    void btnPayOnAction(ActionEvent event) {
        generateFieldInRegisterForm();
        String PayId = txtPayId.getText();
        String StuId = txtID.getText();
        String StuName = txtName.getText();
        String StuClass = cmbClass.getValue();

        double PayAmount = Double.parseDouble(txtAmount.getText());
        String isAmount = String.valueOf(PayAmount);
        boolean matches = Pattern.matches("[0-9.]+", isAmount);
        if (!matches) {
            new Alert(Alert.AlertType.ERROR,"Invalid Amount!!").show();
            return;
        }

        String month = cmbMonth.getValue();
        LocalDate date = LocalDate.parse(lblDate.getText());
        LocalTime time = LocalTime.parse(lblTime.getText());
        String type = lblType.getText();

        if (PayId.isEmpty() || StuId.isEmpty()|| type.isEmpty() || StuClass.isEmpty() || month.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Some Fields Are Empty!!!").showAndWait();
            return;
        }

        PaymentDto PayDto = new PaymentDto(PayId,StuId, StuName,type,StuClass,month,Subject,PayAmount,date,time);

        try {
            boolean isRegisterStudent = RegisterStudentModel.SaveStudentRegisterAndPayment(studentDto, PayDto, guardianDto);
            if (isRegisterStudent) {
                new Alert(Alert.AlertType.INFORMATION, "Student Register Success!!").showAndWait();
                clearField();
                loadAllRegistrations();
            } else {
                new Alert(Alert.AlertType.ERROR,"Student Register Failed!!!").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnPrintOnAction(ActionEvent event) {
        try {
            String payId = txtPayId.getText();
            String StuId = txtID.getText();
            String type = lblType.getText();
            String StuClass = cmbClass.getValue();
            String month = cmbMonth.getValue();
            String Amount = txtAmount.getText();

            HashMap hashMap = new HashMap();
            hashMap.put("Pay_id", payId);
            hashMap.put("Stu_id" , StuId);
            hashMap.put("Type", type);
            hashMap.put("Stu_Class", StuClass);
            hashMap.put("Pay_Month", month);
            hashMap.put("Amount", Amount);

            InputStream resourceAsStream = getClass().getResourceAsStream("/report/Payment.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    compileReport,
                    hashMap,
                    new JREmptyDataSource()
            );
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String PayId = txtPayId.getText();
        try {
            PaymentDto dto = PaymentModel.SearchPaymontId(PayId);
            if (dto != null) {
                txtPayId.setText(dto.getPayID());
                txtID.setText(dto.getStuID());
                txtName.setText(dto.getStuName());
                cmbClass.setValue(dto.getStuClass());
                txtAmount.setText(String.valueOf(dto.getAmount()));
                cmbMonth.setValue(dto.getPayMonth());
                lblDate.setText(String.valueOf(dto.getDate()));
                lblTime.setText(String.valueOf(dto.getTime()));
                lblType.setText(Type);
            } else {
                new Alert(Alert.AlertType.ERROR,"Payment Not Found!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) throws SQLException {
        clearField();
    }

    private void tableListener(){
        tblPayment.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, PaymentTm, t1) -> {
                    try {
                        PaymentDto dto = PaymentModel.SearchPaymontId(t1.getPayId());
                        if (dto != null) {
                            txtPayId.setText(dto.getPayID());
                            txtID.setText(dto.getStuID());
                            txtName.setText(dto.getStuName());
                            cmbClass.setValue(dto.getStuClass());
                            txtAmount.setText(String.valueOf(dto.getAmount()));
                            cmbMonth.setValue(dto.getPayMonth());
                            lblDate.setText(String.valueOf(dto.getDate()));
                            lblTime.setText(String.valueOf(dto.getTime()));
                            lblType.setText(Type);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}