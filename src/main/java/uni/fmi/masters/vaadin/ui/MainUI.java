package uni.fmi.masters.vaadin.ui;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.service.UserService;

@Route("vaadin/users")
public class MainUI extends Div {
	private static final long serialVersionUID = 1L;
	private Grid<UserBean> grid = new Grid<>(UserBean.class, false);
	private UserService userService;

	public MainUI(UserService userService) {
		this.userService = userService;
		setSizeFull();
		configureGrid();
		add(grid);
		grid.setItems(userService.findAll());
	}

	private void configureGrid() {
		grid.setSizeFull();
		grid.addColumn("username").setHeader("Потребителско име").setSortable(true);
		grid.addColumn("email").setHeader("Поща").setSortable(true);
		grid.addColumn("id").setHeader("ID");

	}
}
