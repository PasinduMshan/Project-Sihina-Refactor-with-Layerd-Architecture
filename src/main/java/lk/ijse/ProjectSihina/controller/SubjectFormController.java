package lk.ijse.ProjectSihina.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.ProjectSihina.Other.ArrowKeyPress;
import lk.ijse.ProjectSihina.db.DbConnection;
import lk.ijse.ProjectSihina.dto.ClassDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.dto.SubjectDto;
import lk.ijse.ProjectSihina.dto.TeacherDto;
import lk.ijse.ProjectSihina.dto.Tm.SubjectTm;
import lk.ijse.ProjectSihina.model.ClassModel;
import lk.ijse.ProjectSihina.model.StudentModel;
import lk.ijse.ProjectSihina.model.SubjectModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SubjectFormController implements Initializable {

    @FXML
    private JFXComboBox<String> cmbTeacherName;

    @FXML
    private TableColumn<?, ?> colClass;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableColumn<?, ?> colTeacherName;

    @FXML
    private TableColumn<?, ?> colMonthlyAmount;

    @FXML
    private AnchorPane moveNode;

    @FXML
    private TableView<SubjectTm> tblSubject;

    @FXML
    private JFXTextField txtAvailableClass;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtSubject;

    @FXML
    private JFXTextField txtMonthlyAmount;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllSubject();
        loadAllTeacher();
        generateSubjectId();
        tableListener();
        ArrowKeyPress.switchTextFieldOnArrowPressDown(txtID,txtAvailableClass);
        ArrowKeyPress.switchTextFieldOnArrowPressDown(txtSubject,txtMonthlyAmount);
        ArrowKeyPress.switchTextFieldOnArrowPressUP(txtAvailableClass,txtID);
        ArrowKeyPress.switchTextFieldOnArrowPressUP(txtMonthlyAmount,txtSubject);
        ArrowKeyPress.switchTextFieldOnArrowPressRight(txtID,txtSubject);
        ArrowKeyPress.switchTextFieldOnArrowPressLeft(txtSubject,txtID);
        ArrowKeyPress.switchTextFieldOnArrowPressRight(txtAvailableClass,txtMonthlyAmount);
        ArrowKeyPress.switchTextFieldOnArrowPressLeft(txtMonthlyAmount,txtAvailableClass);
    }

    private void loadAllTeacher() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<TeacherDto> nameList = SubjectModel.getAllTeacher();

            for (TeacherDto dto : nameList) {
                obList.add(dto.getName());
            }
            cmbTeacherName.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void generateSubjectId() {
        try {
            String id = SubjectModel.generateSubId();
            txtID.setText(id);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void loadAllSubject() {
        ObservableList<SubjectTm> obList = FXCollections.observableArrayList();

        try {
            List<SubjectDto> dtoList = SubjectModel.getAllDetails();

            for (SubjectDto dto : dtoList) {
                obList.add(new SubjectTm(
                        dto.getId(),
                        dto.getSubject(),
                        dto.getTeacherName(),
                        dto.getAvailableClass(),
                        dto.getMonthlyAmount()
                ));
            }
            tblSubject.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Sub_Id"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("Subject"));
        colTeacherName.setCellValueFactory(new PropertyValueFactory<>("TeacherName"));
        colClass.setCellValueFactory(new PropertyValueFactory<>("AvailableClass"));
        colMonthlyAmount.setCellValueFactory(new PropertyValueFactory<>("MonthlyAmount"));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String id = txtID.getText();

        String subject = txtSubject.getText();
        boolean matches = Pattern.matches("[A-Za-z.\\s&]+", subject);
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name!!").show();
            return;
        }

        String AvailableClass = txtAvailableClass.getText();
        boolean matches2 = Pattern.matches("[A-Za-z0-9/,\\s]+", AvailableClass);
        if (!matches2) {
            new Alert(Alert.AlertType.ERROR, "Invalid AvailableClass!!").show();
            return;
        }
        String monthlyAmount = txtMonthlyAmount.getText();
        boolean matches3 = Pattern.matches("[0-9.]+", monthlyAmount);
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR,"Invalid Amount!!").show();
            return;
        }

        String teacherName = cmbTeacherName.getValue();

        if (id.isEmpty() || subject.isEmpty() || AvailableClass.isEmpty() || teacherName.isEmpty() || monthlyAmount.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Empty Fields Found!!!").show();
            return;
        }

        double amount = Double.parseDouble(monthlyAmount);

        SubjectDto dto = new SubjectDto(id, subject, AvailableClass, teacherName,amount);

        try {
            boolean isSaved = SubjectModel.saveSubject(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION,"Subject Save Success!!!").showAndWait();
                loadAllSubject();
                clearField();
                generateSubjectId();
            } else {
                new Alert(Alert.AlertType.ERROR,"Subject Save Failed!!!").showAndWait();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtID.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Field Not Found!!!").show();
            return;
        }

        try {
            boolean isDeleted = SubjectModel.deleteSubject(id);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION,"Subject Delete Success!!!").showAndWait();
                loadAllSubject();
                clearField();
                generateSubjectId();
            } else {
                new Alert(Alert.AlertType.ERROR,"Delete Failed!!!").showAndWait();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnPrintOnAction(ActionEvent event) {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/Subject.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport compileReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    compileReport,
                    null,
                    DbConnection.getInstance().getConnection()
            );
            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtID.getText();

        String subject = txtSubject.getText();
        boolean matches = Pattern.matches("[A-Za-z.\\s]+", subject);
        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Name!!").show();
            return;
        }

        String AvailableClass = txtAvailableClass.getText();
        boolean matches2 = Pattern.matches("[A-Za-z0-9/,\\s]+", AvailableClass);
        if (!matches2) {
            new Alert(Alert.AlertType.ERROR, "Invalid Available Class!!").show();
            return;
        }

        String monthlyAmount = txtMonthlyAmount.getText();
        boolean matches3 = Pattern.matches("[0-9.]+", monthlyAmount);
        if (!matches3) {
            new Alert(Alert.AlertType.ERROR,"Invalid Amount!!").show();
            return;
        }

        String teacherName = cmbTeacherName.getValue();

        if (id.isEmpty() || subject.isEmpty() || AvailableClass.isEmpty() || teacherName.isEmpty() || monthlyAmount.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Empty Fields Found!!!").show();
            return;
        }

        double amount = Double.parseDouble(monthlyAmount);

        SubjectDto dto = new SubjectDto(id, subject, AvailableClass, teacherName,amount);

        try {
            boolean isUpdated = SubjectModel.updateSubject(dto);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION,"Update Subject Success!!!").showAndWait();
                loadAllSubject();
                clearField();
                generateSubjectId();
            } else {
                new Alert(Alert.AlertType.ERROR,"Update Failed!!!").showAndWait();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void SearchIdOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();

        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Id Field Not Found!!!").show();
            return;
        }

        try {
            SubjectDto dto = SubjectModel.searchSubject(id);

            if (dto != null) {
                txtID.setText(dto.getId());
                txtSubject.setText(dto.getSubject());
                txtAvailableClass.setText(dto.getAvailableClass());
                cmbTeacherName.setValue(dto.getTeacherName());
                txtMonthlyAmount.setText(String.valueOf(dto.getMonthlyAmount()));
            } else {
                new Alert(Alert.AlertType.ERROR,"Can't Find Subject!!!").showAndWait();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void clearField() {
        txtID.setText("");
        txtSubject.setText("");
        txtAvailableClass.setText("");
        cmbTeacherName.setValue("");
        txtMonthlyAmount.setText("");
    }

    public void btnRefreshOnAction(ActionEvent actionEvent) {
        clearField();
        generateSubjectId();
    }
    private void tableListener(){
        tblSubject.getSelectionModel()
                .selectedItemProperty()
                .addListener((observableValue, SubjectTm, t1) -> {
                    try {
                        SubjectDto dto = SubjectModel.searchSubject(t1.getSub_Id());

                        if (dto != null) {
                            txtID.setText(dto.getId());
                            txtSubject.setText(dto.getSubject());
                            txtAvailableClass.setText(dto.getAvailableClass());
                            cmbTeacherName.setValue(dto.getTeacherName());
                            txtMonthlyAmount.setText(String.valueOf(dto.getMonthlyAmount()));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

                });
    }
}