package com.psu.scrumboard.views.dialogs;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import java.util.function.Consumer;

//confirm dialog for creating new column
public class CreateColumn extends Dialog {

  private static final long serialVersionUID = 3959841920378174696L;

  public CreateColumn(String name, String description, Consumer<String[]> saveListener) {
    setWidth("400px");

    TextField txtName = new TextField("Name");
    txtName.setWidthFull();
    txtName.focus();
    txtName.setValue(name);

    TextField txtDescription = new TextField("Description");
    txtDescription.setWidthFull();
    txtDescription.setValue(description);

    VerticalLayout fieldsLayout = new VerticalLayout(txtName, txtDescription);
    fieldsLayout.setWidthFull();
    fieldsLayout.setPadding(false);
    fieldsLayout.setMargin(false);
    fieldsLayout.getStyle()
                .set("margin-top", "1em");

    String labelCreate = "Create";
    if (!txtName.isEmpty()) {
      labelCreate = "Update";
    }
    Icon check=VaadinIcon.CHECK.create();
    check.getStyle().set("color", "green");
    Button create = new Button(labelCreate,check );
    create.getStyle().set("color", "green");
    create.addClickShortcut(Key.ENTER);
    create.addClickListener(e -> {
      saveListener.accept(new String[]{txtName.getValue(), txtDescription.getValue()});
      close();
    });
    Icon closee=VaadinIcon.CLOSE.create();
    closee.getStyle().set("color", "red");
    Button close = new Button(closee);
    close.addClickShortcut(Key.ESCAPE);
    close.addClickListener(e -> close());

    HorizontalLayout buttonsLayout = new HorizontalLayout(close, create);
    buttonsLayout.setWidthFull();
    buttonsLayout.setJustifyContentMode(JustifyContentMode.END);
    buttonsLayout.setPadding(false);
    buttonsLayout.setMargin(false);
    buttonsLayout.getStyle()
                 .set("padding-top", "1em");

    VerticalLayout layout = new VerticalLayout(fieldsLayout, buttonsLayout);
    layout.setFlexGrow(1);
    layout.getStyle()
          .set("overflow", "hidden")
          .set("display", "flex")
          .remove("width");
    layout.setMargin(true);
    layout.setPadding(false);
    add(layout);
  }

}
