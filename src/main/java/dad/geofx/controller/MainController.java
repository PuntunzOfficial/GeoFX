package dad.geofx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import dad.geofx.model.Example;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainController implements Initializable {

	private LocationController locationController = new LocationController();
	private ConnectionController connectionController = new ConnectionController();
	private SecurityController securityController = new SecurityController();

	private StringProperty IP = new SimpleStringProperty();

	
	private ObjectProperty<Example> example = new SimpleObjectProperty<Example>();

	private static final String BASE_URL = "https://ipapi.com/ip_api.php?ip=";



	@FXML
	private TextField IPTextfield;

	@FXML
	private Button checkIPButton;

	@FXML
	private Tab connectionTab;

	@FXML
	private Tab locationTab;

	@FXML
	private Tab securityTab;

	@FXML
	private BorderPane view;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		locationTab.setContent(locationController.getView());
		connectionTab.setContent(connectionController.getView());
		securityTab.setContent(securityController.getView());

		IPTextfield.textProperty().bindBidirectional(IP);
		IPTextfield.setText("8.8.8.8");
		
		example.addListener((o, ov, nv) -> onExampleChanged(o, ov, nv));
	}

	private void onExampleChanged(ObservableValue<? extends Example> o, Example ov, Example nv) {
		if(nv != null)
		{
			locationController.exampleProperty().bind(example);
			connectionController.exampleProperty().bind(example);
			securityController.exampleProperty().bind(example);
		}
		if(ov != null)
		{
			locationController.exampleProperty().unbind();
			connectionController.exampleProperty().unbind();
			securityController.exampleProperty().unbind();
		}
	}

	public BorderPane getView() {
		return view;
	}

	@FXML
	void onCheckButton(ActionEvent event) throws IOException {

		try {
			OkHttpClient client = new OkHttpClient();

	        Request request = new Request.Builder()
	            .url("https://ipapi.com/ip_api.php?ip="+IP.get())
	            .get()
	            .build();

	        Response response = client.newCall(request).execute();

	        String json = response.body().string();
	        
	        System.out.println(json);
	        
	        Gson gson = new Gson();
	        
	        example.set(gson.fromJson(json, Example.class));
		}catch(Exception e) {
			System.err.println("Ocurrio el siguiente error: "+e);
		}
		
	
	}

	public final ObjectProperty<Example> exampleProperty() {
		return this.example;
	}
	

	public final Example getExample() {
		return this.exampleProperty().get();
	}
	

	public final void setExample(final Example example) {
		this.exampleProperty().set(example);
	}
	

}
