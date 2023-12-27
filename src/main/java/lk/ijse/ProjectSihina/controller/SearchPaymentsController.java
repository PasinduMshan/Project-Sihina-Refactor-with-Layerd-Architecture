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
import lk.ijse.ProjectSihina.Other.ArrowKeyPress;
import lk.ijse.ProjectSihina.Other.Months;
import lk.ijse.ProjectSihina.dto.PaymentDto;
import lk.ijse.ProjectSihina.dto.StudentDto;
import lk.ijse.ProjectSihina.dto.Tm.PaymentTm;
import lk.ijse.ProjectSihina.model.PaymentModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SearchPaymentsController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colPayId;

    @FXML
    private TableColumn<?, ?> colSubject;

    @FXML
    private TableView<PaymentTm> tblPayDetail;

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXComboBox<String> cmbMonth;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtSubject;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        loadAllMonth();
        ArrowKeyPress.switchTextFieldOnArrowPressDown(txtID,txtName);
        ArrowKeyPress.switchTextFieldOnArrowPressDown(txtName,txtSubject);
        ArrowKeyPress.switchTextFieldOnArrowPressUP(txtName,txtID);
        ArrowKeyPress.switchTextFieldOnArrowPressUP(txtSubject,txtName);
    }
    private void loadAllMonth() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        Months[] months = Months.values();
        for (Months month : months){
            obList.add(String.valueOf(month));
        }
        cmbMonth.setItems(obList);
    }

    private void setCellValueFactory() {
        colPayId.setCellValueFactory(new PropertyValueFactory<>("Pay_id"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("Stu_Subject"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    }

    @FXML
    void RefreshOnAction(ActionEvent event) {
        tblPayDetail.setItems(FXCollections.observableArrayList());
        txtID.setText("");
        txtName.setText("");
        txtSubject.setText("");
        cmbMonth.setValue("");
    }

    @FXML
    void SearchMonthDeatilOnAction(ActionEvent event) {
        String id = txtID.getText();
        String month =cmbMonth.getValue();
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();

        if (id.isEmpty() || month.isEmpty()) {
            new Alert(Alert.AlertType.ERROR,"Some Fields are Empty!!!").show();
            return;
        }

        try {
            List<PaymentDto> dtoList = PaymentModel.searchStuPays(id,month);

            for (PaymentDto PayDto : dtoList) {
                System.out.println(PayDto.getSubject());
                obList.add(new PaymentTm(
                        PayDto.getPayID(),
                        PayDto.getSubject(),
                        PayDto.getAmount()
                ));
            }
            tblPayDetail.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void SearchOnAction(ActionEvent event) {
        SearchMonthDeatilOnAction(event);
    }

    public void StudentIdSearchOnAction(ActionEvent actionEvent) {
        String id = txtID.getText();
        if (id.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Id Field is empty!!!").show();
            return;
        }

        try {
            StudentDto dto = PaymentModel.searchStu(id);

            if (dto != null) {
                txtName.setText(dto.getName());
                txtSubject.setText(dto.getSubject());
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Student Not Found!!!").showAndWait();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}