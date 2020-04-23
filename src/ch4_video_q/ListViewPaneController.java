/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch4_video_q;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author AboOod_shbika99
 */
public class ListViewPaneController implements Initializable {

    @FXML
    private ListView<?> listView;
    @FXML
    private Button buttonShow;
    @FXML
    private Button buttonReset;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void buttonShowHandle(ActionEvent event) {
    }

    @FXML
    private void buttonResetHandle(ActionEvent event) {
    }
    
}
