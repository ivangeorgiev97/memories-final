package uni.fmi.masters.vaadin.ui;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.StringLengthValidator;

import uni.fmi.masters.bean.UserBean;
import uni.fmi.masters.service.UserService;

public class UserForm extends FormLayout {
	private static final long serialVersionUID = 1L;

	private TextField useranemeFiled = new TextField("Потребителско име");
	private PasswordField passwordFiled = new PasswordField("Парола");
	private PasswordField secondPasswordFiled = new PasswordField("Повтори парола");
	private EmailField emailFiled = new EmailField("Електронна поща");
	private Button okButton = new Button("OK");
	private Button closeButton = new Button("Отказ");
	private UserBean selectedUser;
	private Binder<UserBean> binder = new Binder<>();
	private UserService userService;

	public UserForm(final UserService userService) {
		this(userService, new UserBean());
	}

	public UserForm(final UserService userService, final UserBean selectedUser) {
		this.selectedUser = selectedUser;
		this.userService = userService;
		initContent();
	}

	private void initContent() {
		add(useranemeFiled, passwordFiled, secondPasswordFiled, emailFiled, configureActions());
		binder.forField(useranemeFiled).asRequired()
				.withValidator(new StringLengthValidator("Въведете име с дължина между 3 и 20 синвола", 3, 20))
				.bind(UserBean::getUsername, UserBean::setUsername);
		binder.forField(passwordFiled).asRequired()
				.withValidator(new StringLengthValidator("Въведете парола с дължина между 3 и 20 синвола", 3, 20))
				.bind(UserBean::getPassword, UserBean::setPassword);
		binder.forField(emailFiled).asRequired().withValidator(new EmailValidator("Въведете валидна поща!"))
				.bind(UserBean::getEmail, UserBean::setEmail);
		binder.readBean(selectedUser);
	}

	public boolean isValid() {
		return binder.isValid();
	}

	private HorizontalLayout configureActions() {
		okButton.addClickListener(listener -> {
			if (binder.writeBeanIfValid(selectedUser)) {
				Notification info = new Notification();
				info.setPosition(Position.MIDDLE);
				info.setDuration(3000);
				try {
					userService.saveUser(selectedUser);
					info.add("Потребителя беше добавен!");
				} catch (Exception e) {
					info.add("Потребителя не беше добавен!");
				}
				info.open();
			} else {
				// show error message
			}
		});

		HorizontalLayout actions = new HorizontalLayout(okButton, closeButton);
		actions.setDefaultVerticalComponentAlignment(Alignment.CENTER);
		return actions;
	}

	public void addOKClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
		okButton.addClickListener(listener);
	}

	public void addCloseClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
		closeButton.addClickListener(listener);
	}

}
