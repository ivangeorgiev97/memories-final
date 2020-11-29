package uni.fmi.masters.vaadin.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("testview")
public class TestView extends Div {
	public TestView() {
		add(new UserForm(null));
	}
}
